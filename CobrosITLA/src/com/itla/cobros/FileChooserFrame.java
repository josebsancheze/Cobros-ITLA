/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jose
 */
public class FileChooserFrame  extends JFrame{
    static final JFrame parent = new JFrame();
    
//    public static void main(String[] args){
//        initFileChooser();
//    }
    
    public static JFileChooser initFileChooser(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV Files", "csv");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Eligió abrir este archivo: "+ chooser.getSelectedFile().getName());
            System.out.println(chooser.getCurrentDirectory());
            
            //ABRIR ARCHIVO...
            try{
                Runtime runtime = Runtime.getRuntime();
               
              //  Process process = runtime.exec("C:\\Windows\\notepad.exe C:\\Users\\jsanchez\\Desktop\\COBROS ITLA\\cobros.properties");
            }catch(Exception e){
                System.out.println("ERROR: NO SE PUDO ABRIR EL CSV");
            }
            return chooser;
            
            //CERRAR ARCHIVO...
        }else if (returnVal == JFileChooser.CANCEL_OPTION) {
            System.out.println("Decidio cancelar");
            return null;
        }
        return null;
        
    }
}
