/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.Soundbank;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jsanchez
 */
public class Reporte {
    
    DataBase dataBase = new DataBase();

    public Reporte() {
    }

    public void runReporte(String nombre) {
        //this.id_contact="";
        //this.id_contact = id;

        try {
            dataBase.conectar();

            //       this.getClass().getResource( "/Reportes/"+nombre"+.jasper" );
            // String master = System.getProperty("user.dir") +
            //                     "/src/Reportes/"+nombre+".jasper";
            URL master = this.getClass().getResource("/Reportes/" + nombre + ".jasper");

            System.out.println("master" + master);
            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }

            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro = new HashMap();
            parametro.put("usuario", "itla");
            System.out.println(parametro.get("usuario"));
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, dataBase.getConexion());

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("REPORTE BOOM!");
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }

    }

    public void runReporte(String nombre, ResultSet dataset) {
        //this.id_contact="";
        //this.id_contact = id;

        try {
            dataBase.conectar();
            URL master = this.getClass().getResource("/Reportes/" + nombre + ".jasper");

            System.out.println("master" + master);
            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }

            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro = new HashMap();



            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, new JRResultSetDataSource(dataset));

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("REPORTE BOOM2!");
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }

    }

    public void runReporte(String nombre, String Parametro) {
        //this.id_contact="";
        //this.id_contact = id;

        try {
            dataBase.conectar();
            URL master = this.getClass().getResource("/Reportes/" + nombre + ".jasper");

            System.out.println("master" + master);
            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }

            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro = new HashMap();
            parametro.put("id", Parametro);

            ResultSet codigo = dataBase.getResultSet("select *,Codaut AS autores_Codaut,Nomaut AS autores_Nomaut from autores order by codaut desc ");


            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, new JRResultSetDataSource(codigo));

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("ALOO2");
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }

    }

    public void runReporte(String nombre, Map parametros) {
        //this.id_contact="";
        //this.id_contact = id;

        try {
            dataBase.conectar();
            URL master = this.getClass().getResource("/Reportes/" + nombre + ".jasper");

            System.out.println("master" + master);
            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }

            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            //    Map parametro = new HashMap();

            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametros, dataBase.getConexion());

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("ALOO");
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }

    }

    public void cerrar() {
        dataBase.cerrarConexion();

    }
}
