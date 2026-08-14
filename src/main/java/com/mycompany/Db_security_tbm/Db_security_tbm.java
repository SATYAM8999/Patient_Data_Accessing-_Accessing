

package com.mycompany.Db_security_tbm;

import client_operation.ClientLoginFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.UIManager;


public class Db_security_tbm {

    public static void main(String[] args) 
    {
      try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    ClientLoginFrame alf = new ClientLoginFrame();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    alf.setSize(d);
    alf.setVisible(true);
    }
}
