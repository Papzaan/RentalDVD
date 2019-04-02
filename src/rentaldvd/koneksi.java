/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaldvd;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class koneksi {

    private static Connection con;
    private static Statement stm;

    public static Connection getKoneksi() {
        String host = "jdbc:mysql://localhost/rentaldvd",
                user = "root",
                pass = "";
        try {
            con = (Connection) DriverManager.getConnection(host, user, pass);
            stm = (Statement) con.createStatement();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
    }
}
