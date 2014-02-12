/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jose
 */
public class CargarCSV {
    public static List getCaso(String ruta, String archivo) {
        //String csvFile =  "C:\\Users\\Jose\\Documents\\NetBeansProjects\\CobrosITLA\\CobrosITLA\\src\\Reportes\\GeoIPCountryWhois.csv";
        String csvFile = (ruta + "\\" + archivo);
        BufferedReader br = null;
        String linea = "";
        String separarPor = ",";
        //List<String> cuenta = null ;
        List cuenta = null;
        try {
            Map<String, String> maps = new HashMap<String, String>();
            //cuenta = new ArrayList<String>(maps.values());
            cuenta = new ArrayList<>();
            br = new BufferedReader(new FileReader(csvFile));
            while ((linea = br.readLine()) != null) {
                // use comma as separator
                String[] caso = linea.split(separarPor);
                maps.put(caso[0], caso[1]);
                cuenta.add(caso[0]);//cuenta
                cuenta.add(caso[1]);//cedula
                cuenta.add(caso[2]);//nombres
                cuenta.add(caso[3]);//apellidos
                cuenta.add(caso[4]);//contrato
                cuenta.add(caso[5]);//tel1
                cuenta.add(caso[6]);//tel2
                cuenta.add(caso[7]);//zona
                cuenta.add(caso[8]);//dir1
                cuenta.add(caso[9]);//dir2
                cuenta.add(caso[10]);//monto
                cuenta.add(caso[11]);//empresa
                cuenta.add(caso[12]);//fechaContrato
                cuenta.add(caso[13]);//comentario
                cuenta.add(caso[14]);//disposicion
                cuenta.add(caso[15]);//recordatorio
                cuenta.add(caso[16]);//descripcionRecordatorio
                
            }
            //loop map
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                System.out.println("Cuenta= " 
                        + entry.getKey() + " , Cedula="
                        + entry.getValue() + "]");
            }
            return cuenta;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return cuenta;
        } catch (IOException e) {
            e.printStackTrace();
            return cuenta;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cuenta;
        }
        //System.out.println("Done");
        //return cuenta;
    }
}
