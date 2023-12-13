
package com.bengkel.dialog;


import com.bengkel.form.ts1;
import com.bengkel.swing.noticeboard.ModelNoticeBoard;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.net.URI;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import koneksi.koneksii;


public class lupaPW1 extends javax.swing.JDialog {
private koneksii koneksi;
   private ts1 parentFrame; 
    public lupaPW1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
         noticeBoard1.addNoticeBoard(new ModelNoticeBoard(new Color(0,0,0), "", "", "kata sandi baru Anda harus berbeda dari kata sandi yang digunakan sebelumnya"));
        pw1.setHintText("**************");
        pw2.setHintText("**************");
        use.setHintText("masukan username anda");
        mu.setVisible(false);
        mu1.setVisible(false);
        eye();
        eye1();
        setLocationRelativeTo(null);
        
 
    }
    
    public void setParentFrame(ts1 parentFrame) {
        this.parentFrame = parentFrame;
        setLocationRelativeTo(parentFrame);
    }

    
    
    public void eye(){
          Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/close-eyes (1).png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(c.getWidth(), c.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    c.setIcon(icon);
    c1.setIcon(icon);
    }
    public void eye1(){
         Icon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-eye-100.png"));
    Image img = ((ImageIcon) icon).getImage();
    Image newImg = img.getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    p.setIcon(icon);
    p1.setIcon(icon);
    p.setVisible(false);
    p1.setVisible(false);
    }

    private boolean isUsernameExists(String username) {
    try {
        koneksi = new koneksii();
        koneksi.koneksimysql();
        java.sql.Connection conn = koneksi.con;

        String query = "SELECT * FROM admin WHERE Username = ?";
        java.sql.PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        java.sql.ResultSet resultSet = statement.executeQuery();

        return resultSet.next(); // Mengembalikan nilai true jika username sudah ada, false jika tidak ditemukan
    } catch (Exception e) {
        e.printStackTrace();
        return false; // Jika terjadi kesalahan, mengembalikan false
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.bengkel.swing.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        noticeBoard1 = new com.bengkel.swing.noticeboard.NoticeBoard();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        button1 = new com.bengkel.swing.Button();
        jLabel5 = new javax.swing.JLabel();
        gm = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        use = new com.bengkel.swing.textfield();
        mu = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        p = new javax.swing.JLabel();
        pw1 = new com.bengkel.swing.PasswordField();
        pw2 = new com.bengkel.swing.PasswordField();
        mu1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(242, 242, 242));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Kembali");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Buat Password Baru");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        noticeBoard1.setOpaque(false);
        panelRound1.add(noticeBoard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 270, 46));

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Password");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Konfirmasi password");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        button1.setBackground(new java.awt.Color(0, 51, 102));
        button1.setText("Reset Password");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelRound1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 238, -1));

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Username ");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        gm.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        gm.setForeground(new java.awt.Color(0, 51, 204));
        gm.setText(" Developer");
        gm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gmMouseExited(evt);
            }
        });
        panelRound1.add(gm, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 50, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Jika lupa username? silakan hubungi");
        panelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

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
        use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useActionPerformed(evt);
            }
        });
        panelRound1.add(use, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 243, -1));

        mu.setBackground(new java.awt.Color(204, 0, 0));
        mu.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        mu.setForeground(new java.awt.Color(204, 0, 0));
        mu.setText("masukan username anda!!");
        panelRound1.add(mu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 140, -1));

        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });
        panelRound1.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 20, 20));

        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c1MouseClicked(evt);
            }
        });
        panelRound1.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 20, 20));

        c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cMouseClicked(evt);
            }
        });
        panelRound1.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 20, 20));

        p.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMouseClicked(evt);
            }
        });
        panelRound1.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 20, 20));

        pw1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pw1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pw1MouseExited(evt);
            }
        });
        panelRound1.add(pw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 240, -1));

        pw2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pw2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pw2MouseExited(evt);
            }
        });
        panelRound1.add(pw2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 240, -1));

        mu1.setBackground(new java.awt.Color(204, 0, 0));
        mu1.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        mu1.setForeground(new java.awt.Color(204, 0, 0));
        mu1.setText("password anda tidak sama , masukan password yang sama !!");
        panelRound1.add(mu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setForeground(new Color(0,51,102));
    }//GEN-LAST:event_jLabel1MouseExited

    private void gmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gmMouseEntered
        gm.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_gmMouseEntered

    private void gmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gmMouseExited
        gm.setForeground(new Color(0,51,204));
    }//GEN-LAST:event_gmMouseExited

    private void gmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gmMouseClicked
         try {
               // membuka link website pada browser default
               Desktop.getDesktop().browse(new URI("https://api.whatsapp.com/send?phone=6282245886429"));
            } catch (Exception ex) {
               ex.printStackTrace();
            }
    }//GEN-LAST:event_gmMouseClicked

    private void useFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_useFocusLost
       if (use.getText().isEmpty()) {
             mu.setVisible(true);
        } else {
            mu.setVisible(false);// kembalikan warna teks ke warna hitam saat teks tidak kosong
        }
    }//GEN-LAST:event_useFocusLost

    private void useMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_useMouseClicked
        use.setHintText("");
    }//GEN-LAST:event_useMouseClicked

    private void useMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_useMouseExited
       use.setHintText("masukan username anda");
    }//GEN-LAST:event_useMouseExited

    private void pw1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw1MouseClicked
        pw1.setHintText("");
    }//GEN-LAST:event_pw1MouseClicked

    private void pw1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw1MouseExited
        pw1.setHintText("**************");
    }//GEN-LAST:event_pw1MouseExited

    private void pw2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw2MouseClicked
        pw2.setHintText("");
    }//GEN-LAST:event_pw2MouseClicked

    private void pw2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pw2MouseExited
        pw2.setHintText("**************");
    }//GEN-LAST:event_pw2MouseExited

    private void cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cMouseClicked
         c.setVisible(false);
       p.setVisible(true);
       pw1.setEchoChar((char)0);
    }//GEN-LAST:event_cMouseClicked

    private void pMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMouseClicked
        p.setVisible(false);
        c.setVisible(true);
        pw1.setEchoChar('*');
    }//GEN-LAST:event_pMouseClicked

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        p1.setVisible(false);
        c1.setVisible(true);
        pw2.setEchoChar('*');
    }//GEN-LAST:event_p1MouseClicked

    private void c1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c1MouseClicked
          c1.setVisible(false);
       p1.setVisible(true);
       pw2.setEchoChar((char)0);
    }//GEN-LAST:event_c1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

           if (!isUsernameExists(use.getText())) {
                    mu.setVisible(true);
                    return;
                }
                if (Arrays.equals(pw1.getPassword(), pw2.getPassword())) {
                    try {
                        String sql = "UPDATE admin SET Password=? WHERE Username=?";
                        koneksi = new koneksii();
                        koneksi.koneksimysql();
                        java.sql.Connection con = koneksi.con;
                        java.sql.PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, new String(pw2.getPassword()));
                        pst.setString(2, use.getText());

                        pst.executeUpdate();
                        System.out.println("Data berhasil diperbarui");
//                        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Password tidak cocok. Silakan coba lagi.");
                    mu1.setVisible(true);
                }
            
    }//GEN-LAST:event_button1ActionPerformed

    private void useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button button1;
    private javax.swing.JLabel c;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel gm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel mu;
    private javax.swing.JLabel mu1;
    private com.bengkel.swing.noticeboard.NoticeBoard noticeBoard1;
    private javax.swing.JLabel p;
    private javax.swing.JLabel p1;
    private com.bengkel.swing.PanelRound panelRound1;
    private com.bengkel.swing.PasswordField pw1;
    private com.bengkel.swing.PasswordField pw2;
    private com.bengkel.swing.textfield use;
    // End of variables declaration//GEN-END:variables
}
