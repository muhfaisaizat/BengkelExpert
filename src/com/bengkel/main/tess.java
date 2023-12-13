/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bengkel.main;

import com.bengkel.component.Header;
import com.bengkel.component.Menu;
import com.bengkel.event.EventMenuSelected;
import com.bengkel.event.EventShowPopupMenu;
import com.bengkel.form.Form1;
import com.bengkel.form.Form_Home;
import com.bengkel.form.MainForm;
import com.bengkel.form.bahan;
import com.bengkel.form.barangjadi;
import com.bengkel.form.dashboard;
import com.bengkel.form.jasa;
import com.bengkel.form.laporanKeuangan;
import com.bengkel.form.open1;
import com.bengkel.form.pelanggan;
import com.bengkel.form.pengaturan;
import com.bengkel.form.pesanBarang;
import com.bengkel.form.transaksi;
import com.bengkel.form.ts1;
import com.bengkel.swing.MenuItem;
import com.bengkel.swing.PopupMenu;
import com.bengkel.swing.icon.GoogleMaterialDesignIcons;
import com.bengkel.swing.icon.IconFontSwing;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaswingdev.SimpleTitleBar;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author user
 */
public class tess extends javax.swing.JFrame {

  private MigLayout layout;
    private Menu menu;
    private SimpleTitleBar simpletitlebar;
    private Header header;
    private MainForm main;
    private Animator animator;
   
   

    

    public tess() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/bengkel/icon/iconlogo.png"));
        setIconImage(icon.getImage());
        init();
        simpletitlebar.init(this);
        setBackground(new Color(244,244,244));
//        GlassPanePopup.install(this);

       
    }


    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        
        menu = new Menu();
        simpletitlebar= new SimpleTitleBar();
        header = new Header();
        main = new MainForm();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
//                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
//                 if (menuIndex == 0) {
//                    if (subMenuIndex == 0) {
//                        main.showForm(new Form_Home());
//                    } else if (subMenuIndex == 1) {
//                        main.showForm(new Form1());
//                    }
//                }
switch (menuIndex) {
                case 0:
                main.showForm(new dashboard());
                break;
                case 1:
                main.showForm(new transaksi());
                break;
                case 2:
                switch (subMenuIndex) {
                case 0:
                main.showForm(new pelanggan());
                break;
                case 1:
                main.showForm(new pesanBarang());
                break;
                case 2:
                main.showForm(new barangjadi());
                break;
                case 3:
                main.showForm(new bahan());
                break;
                case 4:
                main.showForm(new jasa());
                break;
                default:
                break;
                }
                break;
                case 3:
                if (subMenuIndex == 0) {
                main.showForm(new laporanKeuangan());
                }
                break;
                case 4:
                main.showForm(new pengaturan());
                break;
                case 5:
                ts1 loginForm = new ts1(); // Ganti dengan nama kelas JFrame login Anda
                loginForm.setVisible(true);
                tess.this.dispose();
                break;
                default:
                break;
                }
            }

           
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(tess.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = tess.this.getX() + 52;
                int y = tess.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 3");    // Span Y 2cell
        bg.add(simpletitlebar,"h 27!,wrap");
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany3");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new dashboard());
        
    
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bengkel Restu");
        setBackground(new java.awt.Color(244, 244, 244));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(244, 244, 244));
        bg.setForeground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(tess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //                new Main().setVisible(true);
                open1 frame1 = new open1(); // Create an instance of JFrame1
            frame1.setVisible(true);// Display JFrame1
            
            
            
            javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame1.dispose(); // Dispose JFrame1 after 1 seconds
                    new ts1().setVisible(true); // Display JFrame2
                }
                
            });
            timer.setRepeats(false); // Set the timer to only run once
            timer.start(); // Start the timer

            }
        });
    }
    
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
