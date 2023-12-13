
package com.bengkel.form;




import java.sql.Blob;
import com.bengkel.component.Header;
import com.bengkel.dialog.lupaPW1;
import com.bengkel.main.tess;
import com.bengkel.swing.noticeboard.ModelNoticeBoard;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import koneksi.koneksii;
import com.bengkel.form.pengaturan;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;




public class ts1 extends javax.swing.JFrame {
private koneksii koneksi;
private boolean tessShown = false;

//    private awal myDialog;
    public ts1() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/iconlogo.png"));
        setIconImage(icon.getImage());
        closeminim1.init(this);
        jLabel9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        noticeBoard1.setOpaque(false);
        noticeBoard1.addNoticeBoard(new ModelNoticeBoard(new Color(0,0,0), "", "", "Aplikasi Bengkel Reastu adalah program komputer yang dirancang khusus untuk memudahkan pengelolaan Bengkel Restu. Aplikasi ini memberikan informasi yang berguna seperti status pemesanan, inventaris bahan baku, jadwal produksi, dan faktur pelanggan. Dengan menggunakan aplikasi ini, pemilik bisnis dapat mengoptimalkan operasional bengkel Reastu dan memaksimalkan keuntungan. Selain itu, aplikasi ini juga dapat membantu pemilik bisnis dalam mengidentifikasi area yang membutuhkan perbaikan dan mengambil tindakan yang tepat. Sebagai alat manajemen bisnis yang penting, aplikasi bengkel Restu dapat membantu meningkatkan efisiensi dan produktivitas bengkel las."));
        init();
        init1();
        eye();
        eye1();
//        reset();
       
        
        use.setHintText("masukan username anda");
        pw1.setHintText("**********");
        mu.setVisible(false);
        mp.setVisible(false);

    }


    
    public void init(){
         Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/Wellcome to.png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    bg.setIcon(icon);
    }
    public void init1(){
        Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-welder-40.png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    jLabel3.setIcon(icon);
    }
    
    public void eye(){
          Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/close-eyes (1).png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(eye1.getWidth(), eye1.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    eye1.setIcon(icon);
//    close-eyes (1)
    }
    
    public void eye1(){
         Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-eye-100.png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(eye2.getWidth(), eye2.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    eye2.setIcon(icon);
    eye2.setVisible(false);
    }
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        closeminim1 = new javaswingdev.closeminim();
        jLabel2 = new javax.swing.JLabel();
        noticeBoard1 = new com.bengkel.swing.noticeboard.NoticeBoard();
        button1 = new com.bengkel.swing.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        eye1 = new javax.swing.JLabel();
        eye2 = new javax.swing.JLabel();
        pw1 = new com.bengkel.swing.PasswordField();
        mu = new javax.swing.JLabel();
        use = new com.bengkel.swing.textfield();
        mp = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setTitle("Bengkel Restu - Login");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("Bengkel Restu");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));
        getContentPane().add(closeminim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Tentang Bengkal Restu");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        noticeBoard1.setBackground(new java.awt.Color(0, 153, 255));
        noticeBoard1.setOpaque(false);
        getContentPane().add(noticeBoard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 410, 250));

        button1.setBackground(new java.awt.Color(0, 51, 102));
        button1.setText("Login");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 160, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("wellcome to");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("BENGKEL RESTU");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Username");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Password");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Lupa Password ?");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        eye1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eye1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eye1MousePressed(evt);
            }
        });
        getContentPane().add(eye1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 20, 20));

        eye2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eye2MousePressed(evt);
            }
        });
        getContentPane().add(eye2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 20, 20));

        pw1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pw1FocusLost(evt);
            }
        });
        pw1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pw1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pw1MouseExited(evt);
            }
        });
        pw1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pw1ActionPerformed(evt);
            }
        });
        getContentPane().add(pw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 160, -1));

        mu.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        mu.setForeground(new java.awt.Color(204, 0, 0));
        mu.setText("masukan username anda!");
        getContentPane().add(mu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        use.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                useFocusLost(evt);
            }
        });
        use.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                useMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                useMouseExited(evt);
            }
        });
        getContentPane().add(use, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 160, -1));

        mp.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        mp.setForeground(new java.awt.Color(204, 0, 0));
        mp.setText("masukan password anda!");
        getContentPane().add(mp, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 439));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked

Header h= new Header();
pengaturan p= new pengaturan();

      try {
    koneksi = new koneksii();
    koneksi.koneksimysql();
    java.sql.Connection conn = koneksi.con;

    // Mengeksekusi query untuk memeriksa keberadaan pengguna
    String query = "SELECT * FROM admin WHERE Username = ? AND Password = ?";
    java.sql.PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, use.getText());
    statement.setString(2, pw1.getText());
    java.sql.ResultSet resultSet = statement.executeQuery();


    if (resultSet.next()) {
        // Login berhasil
p.userm.setText(resultSet.getString(1));
p.pw.setText(resultSet.getString(2));
p.na.setText(resultSet.getString(3));
h.lbUser.setText(resultSet.getString(3));
//   Mengambil data foto sebagai string
String foto = resultSet.getString(4);
Blob photoBlob = resultSet.getBlob("foto");
InputStream is = photoBlob.getBinaryStream();
ByteArrayOutputStream buffer = new ByteArrayOutputStream();

int nRead;
byte[] data = new byte[1024];

while ((nRead = is.read(data, 0, data.length)) != -1) {
    buffer.write(data, 0, nRead);
}

buffer.flush();
byte[] photoData = buffer.toByteArray();

ImageIcon photoIcon = new ImageIcon(photoData);
h.pic.setIcon(photoIcon);
p.foto.setIcon(photoIcon);
        new tess().setVisible(true);
        this.setVisible(false);
        System.out.println("Login berhasil!");
        // Di sini, Anda dapat melakukan navigasi ke jpanel lain atau mengaktifkan fungsionalitas tertentu
    } else {
        // Login gagal
        
        // Periksa apakah login gagal karena kesalahan username atau password
        String username = use.getText();
        String password = pw1.getText();
        query = "SELECT * FROM admin WHERE Username = ?";
        statement = conn.prepareStatement(query);
        statement.setString(1, username);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
        mp.setVisible(true);
            // Username ditemukan, jadi kesalahan ada pada password
            System.out.println("Login gagal! Kesalahan pada password.");
        } else {
        mu.setVisible(true);
            // Username tidak ditemukan, jadi kesalahan ada pada username
            System.out.println("Login gagal! Kesalahan pada username.");
        }
    }

    // Menutup koneksi ke database (jika diperlukan)
    // conn.close();
} catch (Exception e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_button1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
//        try {
//             // Mengambil koneksi dari class koneksi
//            java.sql.Connection conn = koneksi.con;
//
//            // Mengeksekusi query untuk memeriksa keberadaan pengguna
//            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
//            java.sql.PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, use.getText());
//            statement.setString(2, pw1.getText());
//            java.sql.ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                // Login berhasil
//                 new tess().setVisible(true);
//        this.dispose();
//                System.out.println("Login berhasil!");
//                // Di sini, Anda dapat melakukan navigasi ke jpanel lain atau mengaktifkan fungsionalitas tertentu
//            } else {
//                // Login gagal
//                System.out.println("Login gagal!");
//            }
//
//            // Menutup koneksi ke database (jika diperlukan)
//            // conn.close();
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_button1ActionPerformed

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        jLabel9.setForeground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
         jLabel9.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

          lupaPW1 dialog = new lupaPW1(this, true);
           dialog.setParentFrame(this);
           dialog.setVisible(true);
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void eye1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eye1MouseClicked
        if (eye1.isShowing()) {
            pw1.setEchoChar((char)0);
        } else if (rootPaneCheckingEnabled) {
            
        }
    }//GEN-LAST:event_eye1MouseClicked

    private void eye1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eye1MousePressed
       eye1.setVisible(false);
       eye2.setVisible(true);
       pw1.setEchoChar((char)0);
    }//GEN-LAST:event_eye1MousePressed

    private void eye2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eye2MousePressed
        eye2.setVisible(false);
        eye1.setVisible(true);
        pw1.setEchoChar('*');
    }//GEN-LAST:event_eye2MousePressed

    private void useMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_useMouseClicked
        use.setHintText("");
//        use.setForeground(Color.BLACK);
    }//GEN-LAST:event_useMouseClicked

    private void useMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_useMouseExited
        use.setHintText("masukan username anda");
//        use.setForeground(Color.RED); 
    }//GEN-LAST:event_useMouseExited

    private void pw1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw1MouseClicked
        pw1.setHintText("");
//         pw1.setForeground(Color.BLACK);
    }//GEN-LAST:event_pw1MouseClicked

    private void pw1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw1MouseExited
        pw1.setHintText("********");
//pw1.setForeground(Color.BLACK);
    }//GEN-LAST:event_pw1MouseExited

    private void pw1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pw1FocusLost
        if (pw1.getPassword().length == 0) {
             mp.setVisible(true);
        } else {
            mp.setVisible(false);// kembalikan warna teks ke warna hitam saat teks tidak kosong
        }
    }//GEN-LAST:event_pw1FocusLost

    private void useFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_useFocusLost
        if (use.getText().isEmpty()) {
             mu.setVisible(true);
        } else {
            mu.setVisible(false);// kembalikan warna teks ke warna hitam saat teks tidak kosong
        }
    }//GEN-LAST:event_useFocusLost

    private void pw1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pw1ActionPerformed
      Header h= new Header();
pengaturan p= new pengaturan();

      try {
    koneksi = new koneksii();
    koneksi.koneksimysql();
    java.sql.Connection conn = koneksi.con;

    // Mengeksekusi query untuk memeriksa keberadaan pengguna
    String query = "SELECT * FROM admin WHERE Username = ? AND Password = ?";
    java.sql.PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, use.getText());
    statement.setString(2, pw1.getText());
    java.sql.ResultSet resultSet = statement.executeQuery();


    if (resultSet.next()) {
        // Login berhasil
p.userm.setText(resultSet.getString(1));
p.pw.setText(resultSet.getString(2));
p.na.setText(resultSet.getString(3));
h.lbUser.setText(resultSet.getString(3));
//   Mengambil data foto sebagai string
String foto = resultSet.getString(4);
Blob photoBlob = resultSet.getBlob("foto");
InputStream is = photoBlob.getBinaryStream();
ByteArrayOutputStream buffer = new ByteArrayOutputStream();

int nRead;
byte[] data = new byte[1024];

while ((nRead = is.read(data, 0, data.length)) != -1) {
    buffer.write(data, 0, nRead);
}

buffer.flush();
byte[] photoData = buffer.toByteArray();

ImageIcon photoIcon = new ImageIcon(photoData);
h.pic.setIcon(photoIcon);
p.foto.setIcon(photoIcon);
        new tess().setVisible(true);
        this.setVisible(false);
        System.out.println("Login berhasil!");
        // Di sini, Anda dapat melakukan navigasi ke jpanel lain atau mengaktifkan fungsionalitas tertentu
    } else {
        // Login gagal
        
        // Periksa apakah login gagal karena kesalahan username atau password
        String username = use.getText();
        String password = pw1.getText();
        query = "SELECT * FROM admin WHERE Username = ?";
        statement = conn.prepareStatement(query);
        statement.setString(1, username);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
        mp.setVisible(true);
            // Username ditemukan, jadi kesalahan ada pada password
            System.out.println("Login gagal! Kesalahan pada password.");
        } else {
        mu.setVisible(true);
            // Username tidak ditemukan, jadi kesalahan ada pada username
            System.out.println("Login gagal! Kesalahan pada username.");
        }
    }

    // Menutup koneksi ke database (jika diperlukan)
    // conn.close();
} catch (Exception e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_pw1ActionPerformed

 
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ts1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ts1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ts1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ts1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            
//            public void run() {
//        
//                
////                new ts1().setVisible(true);
////           open1 frame1 = new open1(); // Create an instance of JFrame1
////            frame1.setVisible(true);// Display JFrame1
////            
////            
////            
////            javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent e) {
////                    frame1.dispose(); // Dispose JFrame1 after 1 seconds
////                    new ts1().setVisible(true); // Display JFrame2
////                }
////                
////            });
////            timer.setRepeats(false); // Set the timer to only run once
////            timer.start(); // Start the timer
////
////            }
////        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private com.bengkel.swing.Button button1;
    private javaswingdev.closeminim closeminim1;
    private javax.swing.JLabel eye1;
    private javax.swing.JLabel eye2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel mp;
    private javax.swing.JLabel mu;
    private com.bengkel.swing.noticeboard.NoticeBoard noticeBoard1;
    private com.bengkel.swing.PasswordField pw1;
    private com.bengkel.swing.textfield use;
    // End of variables declaration//GEN-END:variables
}
