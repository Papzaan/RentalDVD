/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaldvd;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Asus
 */
public class RentalDVD {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            SwingUtilities.updateComponentTreeUI(new Pelanggan());
        } catch (Exception e) {
        }
        new Pelanggan().setVisible(true);
    }
}
