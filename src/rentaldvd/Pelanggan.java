/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaldvd;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.MathContext;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Pelanggan extends javax.swing.JFrame {

    /**
     * Creates new form Pelanggan
     */
    private static Statement stm;
    private static Connection con;
    String kolom[] = new String[]{"Id_Pelanggan", "Nama", "No_Telp", "Alamat", "No_Antre"};
    private DefaultTableModel pelanggan;

    public Pelanggan() {
        initComponents();
        pelanggan = new DefaultTableModel();

        jpel.setModel(pelanggan);
        pelanggan.addColumn("Id_Pelanggan");
        pelanggan.addColumn("Nama");
        pelanggan.addColumn("No_Telp");
        pelanggan.addColumn("Alamat");
        pelanggan.addColumn("No_Antre");
        ambilpel();
        setTitle("Pelanggan Rental Kuy");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }
//Insert Ke Tabel

    public void InsertPelanggan() {
        try {
            String sql = "INSERT INTO pelanggan VALUES ('" + idpel.getText() + "','" + namapel.getText() + "','" + nohp.getText() + "','" + alamat.getText() + "','" + antre.getText() + "')";
            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = (java.sql.Statement) koneksi.getKoneksi().createStatement();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void hapus_pelanggan() {
        // fungsi hapus data
        try {
            String sql = "delete from pelanggan where id_pelanggan='" + idpel.getText() + "'";
            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        ambilpel();
        kosong();
    }

    private void ambilpel() {
        // TODO add your handling code here:
        pelanggan.getDataVector().removeAllElements();
        pelanggan.fireTableDataChanged();
        try {

            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = (java.sql.Statement) koneksi.getKoneksi().createStatement();
            String sql = "Select * from pelanggan";
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pelanggan");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("no_telp");
                obj[3] = res.getString("alamat");
                obj[4] = res.getString("no_antre");

                pelanggan.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    public void updatepel() {
        try {
            String sql = "UPDATE pelanggan SET id_pelanggan = '" + idpel.getText() + "', nama = '" + namapel.getText() + "', no_telp = '" + nohp.getText()
                    + "',alamat= '" + alamat.getText() + "',no_antre= '" + antre.getText() + "' WHERE id_pelanggan = '" + idpel.getText() + "'";
            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Perbarui");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        ambilpel();
        kosong();
    }

    public void cari() {
        try {
            pelanggan.getDataVector().removeAllElements();
            pelanggan.fireTableDataChanged();
            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = (java.sql.Statement) koneksi.getKoneksi().createStatement();
            String sql = "Select * from pelanggan where id_pelanggan like '%"
                    + cari.getText() + "%'"
                    + "or nama like '%" + cari.getText() + "%'" + "or no_antre like '%" + cari.getText() + "%'";
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pelanggan");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("no_telp");
                obj[3] = res.getString("alamat");
                obj[4] = res.getString("no_antre");

                pelanggan.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        //prosedur cari data
        /*  try {

            Connection con = koneksi.getKoneksi();

            String sql = "Select * from pelanggan where id_pelanggan like '%"
                    + cari.getText() + "%'"
                    + "or nama like '%" + cari.getText() + "%'";

            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {

                pelanggan.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)

                });

            }

            jpel.setModel(pelanggan);
            JOptionPane.showMessageDialog(cari, "Ada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(cari, e.getMessage() + "Salah");

        }*/
    }

    public void kosong() {
        idpel.setText("");
        namapel.setText("");
        nohp.setText("");
        alamat.setText("");
        antre.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        idpel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        namapel = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jpel = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        cari = new javax.swing.JTextField();
        antre = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));
        setResizable(false);
        setSize(new java.awt.Dimension(1080, 720));

        jLabel1.setText("ID Pelanggan");

        idpel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idpelActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Pelanggan");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("Nomor Handphone");

        jLabel5.setText("Alamat");

        jLabel6.setText("No. Antre");

        namapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namapelActionPerformed(evt);
            }
        });

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Perbarui");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jpel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jpel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jpel);

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jButton6.setText("Hapus");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("Menu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("Transaksi");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 143, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(idpel)
                                                .addComponent(namapel)
                                                .addComponent(nohp)
                                                .addComponent(jScrollPane1))
                                            .addComponent(antre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(79, 79, 79))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6)
                                        .addGap(29, 29, 29)
                                        .addComponent(jButton7))))
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(antre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idpel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idpelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idpelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idpelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void namapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namapelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        InsertPelanggan();
        idpel.setText("");
        nohp.setText("");
        namapel.setText("");
        alamat.setText("");
        antre.setText("");
        ambilpel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updatepel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jpelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpelMouseClicked
        // TODO add your handling code here:
        // menampilkan data ke textboxt
        try {
            int row = jpel.getSelectedRow();
            String tabel_klik = (jpel.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn = (java.sql.Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from pelanggan where id_pelanggan='" + tabel_klik + "'");
            if (sql.next()) {
                String antri = sql.getString("no_antre");
                antre.setText(antri);
                String id = sql.getString("id_pelanggan");
                idpel.setText(id);
                String nama = sql.getString("nama");
                namapel.setText(nama);
                String no = sql.getString("no_telp");
                nohp.setText(no);
                String alam = sql.getString("alamat");
                alamat.setText(alam);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jpelMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        hapus_pelanggan();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new Pinjam().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField antre;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField idpel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jpel;
    private javax.swing.JTextField namapel;
    private javax.swing.JTextField nohp;
    // End of variables declaration//GEN-END:variables
}
