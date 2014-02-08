/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsanchez
 */
public class ConectarPostgre {
    
    Connection connection = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void conectar(String rol, String contrasena){
        String server = "127.0.0.1";
        String port = "5433";
        String database = "postgres";
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://" + server + ":" + port + "/" + database;
        
        System.out.println("-------- PostgreSQL----------");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Debe incluir el driver de Postgre en el path de la libreria ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver OK!");
        
        //Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, rol, contrasena);
        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexión! Chequee el log por la consola");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("Conexión establecida exitosamente!");
        } else {
            System.out.println("No se pudo establecer la conexión!");
        }
    }
    
    public void consulta(){
        if (connection != null) {
            try{
                pst = connection.prepareStatement("SELECT * FROM prueba.disposicion");
                rs = pst.executeQuery();
                if (rs.next()) {
                    //System.out.println(rs.getString(1));
                    System.out.print(rs.getInt(1));
                    System.out.print(": ");
                    System.out.println(rs.getString(2));
                }
                System.out.println("BOOM!");
            }catch(Exception ex){
                Logger lgr = Logger.getLogger(ConectarPostgre.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            } finally {

                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }

                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(ConectarPostgre.class.getName());
                    lgr.log(Level.WARNING, ex.getMessage(), ex);
                }
            }
        } else {
            System.out.println("No se pudo establecer la conexión! BOOM!!!");
        }
    }
    
}
