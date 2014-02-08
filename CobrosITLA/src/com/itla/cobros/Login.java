/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

/**
 *
 * @author jsanchez
 */
public class Login {
    public static boolean autenticar(String usuario, String contrasena){
        //arreglar login para que sea con base de datos
        if(usuario.equals("jose") && contrasena.equals("sanchez")){
            return true;
        }
        return false;
    }
}
