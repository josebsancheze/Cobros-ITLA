/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jsanchez
 */
public class CargarProperties {
    
    
    static List cuenta;
    private List database;
    public static List getCaso(String path, String name) {
        
        try {
            String file = new File(path+"//"+name).getAbsolutePath();
            //System.out.println("El archivo empresa.properties debe estar en: " + file);
            FileInputStream f = new FileInputStream(file);
            Properties p = new Properties();
            p.load(f);
           
            cuenta = new ArrayList();
            
            cuenta.add(p.getProperty("cuenta"));//0
            cuenta.add(p.getProperty("cedula"));//1
            cuenta.add(p.getProperty("nombres"));//2
            cuenta.add(p.getProperty("apellidos"));//3
            cuenta.add(p.getProperty("contrato"));//4
            cuenta.add(p.getProperty("tel1"));//5
            cuenta.add(p.getProperty("tel2"));//6
            cuenta.add(p.getProperty("zona"));//7
            cuenta.add(p.getProperty("dir1"));//8
            cuenta.add(p.getProperty("dir2"));//9
            cuenta.add(p.getProperty("monto"));//10
            cuenta.add(p.getProperty("empresa"));//11
            cuenta.add(p.getProperty("fechaContrato"));//12
            cuenta.add(p.getProperty("comentario"));//13
            cuenta.add(p.getProperty("disposicion"));//14
            cuenta.add(p.getProperty("recordatorio"));//15
            cuenta.add(p.getProperty("descripcionRecordatorio"));//16

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cuenta;

    }
    
    public List getDatabase() {

        try {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("database.properties"));

            database = new ArrayList();

            database.add(p.getProperty("port"));
            database.add(p.getProperty("db"));
            database.add(p.getProperty("user"));
            database.add(p.getProperty("pass"));
            database.add(p.getProperty("server"));
            database.add(p.getProperty("driver"));
            
        } catch (IOException e) {
            System.out.println("Hubo un problema al cargar los datos del properties: " + e.getMessage());
        }

        return database;

    }
}
