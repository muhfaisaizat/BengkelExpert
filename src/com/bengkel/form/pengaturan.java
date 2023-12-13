
package com.bengkel.form;

//import com.raven.component.Header;
import com.bengkel.component.Header;
import com.bengkel.main.tess;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import koneksi.koneksii;




public class pengaturan extends javax.swing.JPanel {
 private koneksii koneksi;
private FileDialog fileDialog;

    public pengaturan() {
        initComponents();
//        this.h=h;
        setOpaque(false);
        na.setHintText("nama admin");
        userm.setHintText("username");
        pw.setHintText("password");

    }
    
//    public void foto1(){
//        if (Desktop.isDesktopSupported()) {
//    try {
//         fileDialog = new FileDialog(new Frame(), "Pilih File Gambar");
//        // Membuka jendela dialog file explorer
//        fileDialog.setVisible(true);
//
//        String directory = fileDialog.getDirectory();
//        String filename = fileDialog.getFile();
//
//        if (directory != null && filename != null) {
//            File selectedFile = new File(directory, filename);
//
//            // Mengubah file gambar menjadi objek Icon
//            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
//
//            // Mengatur objek Icon ke ImageAvatar
//            foto.setIcon(icon);
////            h.pic.setIcon(icon);
//
//            // Dapatkan path absolut dari file gambar yang dipilih
//            String selectedFilePath = selectedFile.getAbsolutePath();
//
//            // Baca file gambar sebagai byte array
//            Path path = Paths.get(selectedFilePath);
//            byte[] imageBytes = Files.readAllBytes(path);
//
//            // Gunakan PreparedStatement untuk mengupdate data di database
//            String updateQuery = "UPDATE admin SET foto = ? WHERE Username = ?";
//            java.sql.PreparedStatement preparedStatement = null;
//            try {
//                preparedStatement = koneksi.con.prepareStatement(updateQuery);
//
//                // Bind parameter foto (byte array) ke statement
//                preparedStatement.setBytes(1, imageBytes);
//
//                // Bind parameter Username ke statement
//                preparedStatement.setString(2, userm.getText()); // Ganti userm.getText() dengan nilai Username yang sesuai
//
//                // Eksekusi pernyataan update
//                preparedStatement.executeUpdate();
//
//                System.out.println("Foto berhasil diupdate di database");
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                // Tutup statement
//                if (preparedStatement != null) {
//                    try {
//                        preparedStatement.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        // Tutup jendela file explorer
////        this.h.revalidate();
////        this.h.repaint();
//        this.revalidate();
//        this.repaint();
//        fileDialog.dispose();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//
//    }
    
    private void foto2(){
        if (Desktop.isDesktopSupported()) {
    try {
         fileDialog = new FileDialog(new Frame(), "Pilih File Gambar");
        // Membuka jendela dialog file explorer
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();

        if (directory != null && filename != null) {
            File selectedFile = new File(directory,filename);

            // Mengubah file gambar menjadi objek Icon
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

            // Mengatur objek Icon ke ImageAvatar
            foto.setIcon(icon);
          

            // Dapatkan path absolut dari file gambar yang dipilih
            String selectedFilePath = selectedFile.getAbsolutePath();

            // Baca file gambar sebagai byte array
            Path path = Paths.get(selectedFilePath);
            byte[] imageBytes = Files.readAllBytes(path);

            // Gunakan PreparedStatement untuk mengupdate data di database
            String updateQuery = "UPDATE admin SET foto = ? WHERE Username = ?";
            java.sql.PreparedStatement preparedStatement = null;
            try {
                preparedStatement = koneksi.con.prepareStatement(updateQuery);

                // Bind parameter foto (byte array) ke statement
                preparedStatement.setBytes(1, imageBytes);

                // Bind parameter Username ke statement
                preparedStatement.setString(2, userm.getText()); // Ganti userm.getText() dengan nilai Username yang sesuai

                // Eksekusi pernyataan update
                preparedStatement.executeUpdate();

                System.out.println("Foto berhasil diupdate di database");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Tutup statement
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Tutup jendela file explorer

        fileDialog.dispose();
        pengaturan.this.revalidate();
        pengaturan.this.repaint();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    }
    


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelRound2 = new com.bengkel.swing.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelRound3 = new com.bengkel.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelRound4 = new com.bengkel.swing.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelRound5 = new com.bengkel.swing.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jCheckBoxCustom1 = new com.bengkel.swing.JCheckBoxCustom();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        simpanlogin = new com.bengkel.swing.Button();
        tes = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Pengaturan");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nama Admin");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Anda bisa merubah nama admin disini dan akan tampil di kanan atas");

        na.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                naMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                naMouseExited(evt);
            }
        });
        na.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 587, Short.MAX_VALUE)
                .addComponent(na, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(na, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Foto Profil");

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Tambahkan foto anda dan akan tampil di kanan atas");

        button1.setBackground(new java.awt.Color(242, 242, 242));
        button1.setForeground(new java.awt.Color(51, 51, 51));
        button1.setText("Pilih Foto");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Atur username admin login anda");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Username");

        userm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usermMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usermMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userm, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(userm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setRoundBottomLeft(20);
        panelRound5.setRoundBottomRight(20);
        panelRound5.setRoundTopLeft(20);
        panelRound5.setRoundTopRight(20);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Password");

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Anda bisa merubah password login disini, maksimal password 8 digit");

        pw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pwMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pwMouseExited(evt);
            }
        });
        pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwActionPerformed(evt);
            }
        });

        jCheckBoxCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCustom1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Admin");

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Login");

        simpanlogin.setBackground(new java.awt.Color(0, 102, 204));
        simpanlogin.setText("Simpan Login");
        simpanlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanloginActionPerformed(evt);
            }
        });

        tes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tes, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(simpanlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(simpanlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(tes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
       foto2();
    }//GEN-LAST:event_button1ActionPerformed

    private void naMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naMouseClicked
        na.setHintText("");
    }//GEN-LAST:event_naMouseClicked

    private void naMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naMouseExited
        na.setHintText("nama admin");
    }//GEN-LAST:event_naMouseExited

    private void usermMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usermMouseClicked
        userm.setHintText("");
    }//GEN-LAST:event_usermMouseClicked

    private void usermMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usermMouseExited
        userm.setHintText("username");
    }//GEN-LAST:event_usermMouseExited

    private void pwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pwMouseClicked
       pw.setHintText("");
    }//GEN-LAST:event_pwMouseClicked

    private void pwMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pwMouseExited
       pw.setHintText("password"); 
    }//GEN-LAST:event_pwMouseExited

    private void naActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naActionPerformed
//Header h= new Header();
try {
    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    java.sql.Connection conn = koneksi.con;

    String updateSql = "UPDATE admin SET nama = ? WHERE Username = ?";
    java.sql.PreparedStatement updateStatement = conn.prepareStatement(updateSql);
    updateStatement.setString(1, na.getText());
    updateStatement.setString(2, userm.getText());
    updateStatement.executeUpdate();

    // Mengeksekusi query untuk memeriksa keberadaan pengguna
    String query = "SELECT * FROM admin WHERE Username = ? AND Password = ?";
    java.sql.PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, userm.getText());
    statement.setString(2, pw.getText());
    java.sql.ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
        // Login berhasil
        userm.setText(resultSet.getString(1));
        pw.setText(resultSet.getString(2));
        na.setText(resultSet.getString(3));
//      h.lbUser.setText(resultSet.getString(3));
      


        System.out.println("masuk");
        // Di sini, Anda dapat melakukan navigasi ke jpanel lain atau mengaktifkan fungsionalitas tertentu
    } else {
        // Username tidak ditemukan, jadi kesalahan ada pada username
        System.out.println("Login gagal! Kesalahan pada username.");
    }

    // Menutup koneksi ke database (jika diperlukan)
    // conn.close();
} catch (Exception e) {
    e.printStackTrace();
}


    }//GEN-LAST:event_naActionPerformed

    private void jCheckBoxCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCustom1ActionPerformed
if (jCheckBoxCustom1.isSelected()) {
            pw.setEchoChar((char)0);
        } else {
            pw.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBoxCustom1ActionPerformed

    private void simpanloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanloginActionPerformed
          try {
        String username = userm.getText();
        String password = new String(pw.getPassword());
        String nama = na.getText();
        Icon icn = foto.getIcon();
        // Mengekstrak gambar dari objek Icon menjadi objek BufferedImage
BufferedImage bufferedImage = new BufferedImage(icn.getIconWidth(), icn.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
Graphics2D g2d = bufferedImage.createGraphics();
icn.paintIcon(null, g2d, 0, 0);
g2d.dispose();
        // Membuat ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
 ImageIO.write(bufferedImage, "png", baos);
           
             
            byte[] foto1 = baos.toByteArray();

        if (password.length() > 8) {
            System.out.println("Password melebihi batas maksimal. Silakan coba lagi.");
            return;
        }

        // Cek apakah data dengan username tertentu sudah ada di database
        String query = "SELECT * FROM admin WHERE Username = ?";
        koneksi = new koneksii();
        koneksi.koneksimysql();
        java.sql.Connection con = koneksi.con;
        java.sql.PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, username);
        java.sql.ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Jika data dengan username tersebut sudah ada, jalankan proses update
              String updateSql = "UPDATE admin SET Password = ? WHERE Username = ?";
    java.sql.PreparedStatement updateStatement = con.prepareStatement(updateSql);
    updateStatement.setString(1, password);
    updateStatement.setString(2, username);
    int rowsUpdated = updateStatement.executeUpdate();
    if (rowsUpdated > 0) {
        System.out.println("Data berhasil diperbarui");
    }
        } else {
            // Jika data dengan username tersebut belum ada, jalankan proses insert
             // Jika data dengan username tersebut belum ada, jalankan proses insert
    String insertSql = "INSERT INTO `admin` (`Username`, `Password`, `nama`, `foto`) VALUES (?, ?, ?, ?)";
    java.sql.PreparedStatement insertStatement = con.prepareStatement(insertSql);
    insertStatement.setString(1, username);
    insertStatement.setString(2, password);
    insertStatement.setString(3, nama);
    insertStatement.setBytes(4, foto1);
    int rowsInserted = insertStatement.executeUpdate();
    if (rowsInserted > 0) {
        System.out.println("Data berhasil ditambahkan");
    }
        }

        // Set nilai username dan password yang ditampilkan di GUI
        userm.setText(username);
        pw.setText(password);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_simpanloginActionPerformed

    private void pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final com.bengkel.swing.Button button1 = new com.bengkel.swing.Button();
    public static final com.bengkel.swing.ImageAvatar foto = new com.bengkel.swing.ImageAvatar();
    private com.bengkel.swing.JCheckBoxCustom jCheckBoxCustom1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static final com.bengkel.swing.textfield na = new com.bengkel.swing.textfield();
    private com.bengkel.swing.PanelRound panelRound2;
    private com.bengkel.swing.PanelRound panelRound3;
    private com.bengkel.swing.PanelRound panelRound4;
    private com.bengkel.swing.PanelRound panelRound5;
    public static final com.bengkel.swing.PasswordField pw = new com.bengkel.swing.PasswordField();
    private com.bengkel.swing.Button simpanlogin;
    private javax.swing.JLabel tes;
    public static final com.bengkel.swing.textfield userm = new com.bengkel.swing.textfield();
    // End of variables declaration//GEN-END:variables
}
