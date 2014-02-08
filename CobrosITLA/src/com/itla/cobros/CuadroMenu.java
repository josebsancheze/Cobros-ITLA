/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author jsanchez
 */
public class CuadroMenu  extends JMenuBar{
    
    public CuadroMenu(){
        initMenu();
    }
    public static final JMenuBar initMenu(){
        JMenuBar menubar = new JMenuBar();
        ImageIcon iconNew = new ImageIcon("new.png");
        ImageIcon iconOpen = new ImageIcon("open.png");
        ImageIcon iconSave = new ImageIcon("save.png");
        ImageIcon iconExit = new ImageIcon("exit.png");

        JMenu archivo = new JMenu("Archivo");
        archivo.setMnemonic(KeyEvent.VK_A);
        JMenu ver = new JMenu("Ver");
        ver.setMnemonic(KeyEvent.VK_V);

        JMenuItem archivoImportar = new JMenuItem("Importar Cartera De Cobros", iconNew);
        archivoImportar.setMnemonic(KeyEvent.VK_I);
        
        JMenuItem archivoExit = new JMenuItem("Cerrar Aplicación", iconExit);
        archivoExit.setMnemonic(KeyEvent.VK_C);
        archivoExit.setToolTipText("Exit application");
        archivoExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));

        archivoImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                CuadroSelectorArchivos selector = new CuadroSelectorArchivos();
                selector.initFileChooser();
            }
        });
        archivoExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        
        
        
        JMenuItem verAsignacionesPendientes = new JMenuItem("Mis Asignaciones Pendientes", iconNew);
        verAsignacionesPendientes.setMnemonic(KeyEvent.VK_P);
        JMenuItem verAsignacionesYaRealizadas = new JMenuItem("Mis Asignaciones Ya Realizadas", iconNew);
        verAsignacionesYaRealizadas.setMnemonic(KeyEvent.VK_Y);
        JMenuItem verEmpresas = new JMenuItem("Mis Empresas", iconNew);
        verEmpresas.setMnemonic(KeyEvent.VK_E);
        JMenuItem verRecordatorios = new JMenuItem("Mis Recordatorios", iconNew);
        verRecordatorios.setMnemonic(KeyEvent.VK_R);
        JMenuItem verPerfil = new JMenuItem("Mi Perfil De Usuario", iconNew);
        verPerfil.setMnemonic(KeyEvent.VK_U);
        JMenuItem verTrackCuenta = new JMenuItem("Track Cuenta Actual", iconNew);
        verTrackCuenta.setMnemonic(KeyEvent.VK_T);
        
        archivo.add(archivoImportar);
        archivo.addSeparator();
        archivo.add(archivoExit);
        
        ver.add(verAsignacionesPendientes);
        ver.add(verAsignacionesYaRealizadas);
        ver.add(verEmpresas);
        ver.add(verRecordatorios);
        ver.add(verPerfil);
        ver.add(verTrackCuenta);

        menubar.add(archivo);
        menubar.add(ver);
   
        return menubar;
    }
   
}
