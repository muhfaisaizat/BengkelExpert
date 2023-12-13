
package com.bengkel.dialog;

import com.bengkel.form.pelanggan;
import com.bengkel.form.tambahtransaksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import koneksi.koneksii;






public class tambahPelanggann extends javax.swing.JDialog {


    private koneksii koneksi;
    private pelanggan pelanggan;

    public tambahPelanggann(java.awt.Frame parent, boolean modal,pelanggan pelanggan) {
        super(parent, modal);
        initComponents();
        this.pelanggan=pelanggan;
        setBackground(new Color(0, 0, 0, 0));
        kembali.setCursor(new Cursor(Cursor.HAND_CURSOR));
        alamat.setLineWrap(true);
        alamat.setWrapStyleWord(true);
        setLocationRelativeTo(null);
        id_auto();
        
    }
    
    public void id_auto() {
        try {
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
            String sql = "SELECT MAX(ID_Pelanggan) AS lastID FROM pelanggan";
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String lastID = rs.getString("lastID");
                if (lastID == null) {
                    lastID = "P00000";
                }

                int incrementValue = Integer.parseInt(lastID.substring(1)) + 1;
                String newID = "P" + String.format("%05d", incrementValue);

                id.setText(newID);
            }

            rs.close();
            stmt.close();
            koneksi.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void simpan1(){
     try {
        koneksii koneksi = new koneksii();
        koneksi.koneksimysql();

        String sql = "INSERT INTO pelanggan VALUES ('"+id.getText()+"', '"+nama.getText()+"', '"+nohp.getText()+"', '"+alamat.getText()+"')";
        java.sql.Connection con = koneksi.con;
        java.sql.PreparedStatement pst = con.prepareStatement(sql);
        pst.executeUpdate();

        System.out.println("terkirim");
        pelanggan.tampiltabel();
        dispose();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void simpan2(){
     try {
        koneksii koneksi = new koneksii();
        koneksi.koneksimysql();

        String sql = "INSERT INTO pelanggan VALUES ('"+id.getText()+"', '"+nama.getText()+"', '"+nohp.getText()+"', '"+alamat.getText()+"')";
        java.sql.Connection con = koneksi.con;
        java.sql.PreparedStatement pst = con.prepareStatement(sql);
        pst.executeUpdate();

        System.out.println("terkirim");
        
        dispose();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new com.bengkel.swing.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound1 = new com.bengkel.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        Simpan = new com.bengkel.swing.Button();
        kembali = new javax.swing.JLabel();
        alamat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(0, 51, 204));
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah Palanggan");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 170, 30));

        panelRound1.setBackground(new java.awt.Color(239, 239, 239));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID_Pelanggan");

        jLabel3.setText("Nama ");

        jLabel4.setText("NoHp");

        jLabel5.setText("Alamat");

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        Simpan.setBackground(new java.awt.Color(0, 102, 255));
        Simpan.setText("Simpan");
        Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanActionPerformed(evt);
            }
        });

        kembali.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        kembali.setForeground(new java.awt.Color(0, 51, 255));
        kembali.setText("Kembali");
        kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kembaliMouseClicked(evt);
            }
        });

        alamat.setColumns(20);
        alamat.setRows(5);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(kembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nohp)
                            .addComponent(nama)
                            .addComponent(id)
                            .addComponent(alamat, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kembali))
                .addGap(25, 25, 25))
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 69, 380, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanActionPerformed
        if (pelanggan!=null) {
            simpan1();
        }else{
            simpan2();
        }
    }//GEN-LAST:event_SimpanActionPerformed

    private void kembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliMouseClicked
        setVisible(false);
    }//GEN-LAST:event_kembaliMouseClicked

      
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button Simpan;
    private javax.swing.JTextArea alamat;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel kembali;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nohp;
    private com.bengkel.swing.PanelRound panelRound1;
    private com.bengkel.swing.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
