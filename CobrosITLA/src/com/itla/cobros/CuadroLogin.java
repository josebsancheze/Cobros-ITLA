/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.cobros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author jsanchez
 */
public class CuadroLogin extends JDialog{
   
    private JTextField tfUsuario;
    private JPasswordField pfContrasena;
    private JLabel lbUsuario;
    private JLabel lbContrasena;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
    public CuadroLogin(Frame parent) {
        super(parent, "Cobros ITLA", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsuario = new JLabel("Usuario: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsuario, cs);
 
        tfUsuario = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsuario, cs);
 
        lbContrasena = new JLabel("Contraseña: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbContrasena, cs);
 
        pfContrasena = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfContrasena, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Iniciar Sesión");
 
        btnLogin.addActionListener(new ActionListener() {
 
        public void actionPerformed(ActionEvent e) {
                if (Login.autenticar(getUsuario(), getContrasena())) {
                    JOptionPane.showMessageDialog(CuadroLogin.this,
                            "Hola " + getUsuario() + "!",
                            "Cobros ITLA",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CuadroLogin.this,
                            "Usuario y/o contraseña incorrecto",
                            "Cobros ITLA",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsuario.setText("");
                    pfContrasena.setText("");
                    succeeded = false;
 
                }
            }
        });
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsuario() {
        return tfUsuario.getText().trim();
    }
 
    public String getContrasena() {
        return new String(pfContrasena.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }

}
