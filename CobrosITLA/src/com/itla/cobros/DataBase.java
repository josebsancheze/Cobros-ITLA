/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jsanchez
 */
public class DataBase {
   
    public static String error_db="";
    public static Connection conexion;
    
    public void conectar(){
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
            Class.forName(driver).newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
        } catch (Exception exc){
             System.err.println("** Error de Bases de datos **\n"+exc.getMessage());
        }
    }    
    
    //metodo que retorna un resultset pasandole el query
    public static ResultSet getResultSet(String query){
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = comando.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("** Error de Base de datos 1**\n"+ex.getMessage());
            return null;
        }
    }
    
    // metodo para ejecutar update, insert o delete
    public void executeUpdate(String query){
        Component JButton = null;
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("** Error de Base de datos 2**\n"+ex.getMessage());
            JOptionPane.showMessageDialog(JButton, "La Base De Datos No Esta En Servicio","Aviso", JOptionPane.INFORMATION_MESSAGE);
      
        }
    }
    
    //metodo para obtener la cantidad de registros
    public int cantRegistros(ResultSet ResultSet){
        try {
             int int_cantreg=0;
             while(ResultSet.next()){
                 int_cantreg++;
             }
             return int_cantreg;
         } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }

    public void cerrarConexion() {
        try {

            conexion.close();
            conexion = null;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
