/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jsanchez
 */
public class IniciarReporte {
    Connection conn=null;
    DataBase dataBase;

    public IniciarReporte(DataBase database) {
        
        this.dataBase = database;
        CargarProperties cp = new CargarProperties();
        List dblista = new ArrayList();
        dblista = cp.getDatabase();
        
        String port = dblista.get(0).toString();
        String db = dblista.get(1).toString();
        String user = dblista.get(2).toString();
        String pass = dblista.get(3).toString();
        String server = dblista.get(4).toString();
        String driver = dblista.get(5).toString();
        
        String url = "jdbc:postgresql://" + server + ":" + port + "/" + db;
        
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Conexión establecida");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ejecutarReporte(String nombreReporte, String userInsertado) throws JRException {
        try {
            dataBase.conectar();
            URL archivo2 = this.getClass().getResource("/Reportes/" + nombreReporte + ".jasper");
            String archivo = "/Reportes/"+nombreReporte+".jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                System.out.println("No se encuentra el archivo.");
                System.exit(2);
            }
            
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo2);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            
            Map parametro = new HashMap();
            parametro.put("userInsertado", userInsertado);

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("ITLA - Reporte");
            jviewer.setVisible(true);
        } catch (Throwable j) {
            System.out.println("Mensaje de error: " + j.getMessage());
        }
    }

    public void Cerrar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
