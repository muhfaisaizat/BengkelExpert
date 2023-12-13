
package com.bengkel.component;

import com.bengkel.model.ModelTambah;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;




public class tambah extends javax.swing.JPanel {

    

public Color getColorGradient() {
        return colorGradient;
    }

 public JButton getButton() {
    JButton button = null;
        return button;
    }
 
 

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }

    
    private Color colorGradient;
    public tambah() {
        initComponents();
        setOpaque(false);
           setBackground(new Color(51,153,0));
        colorGradient = new Color(51,255,51);
      setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(50, 50, 50));
        setBorder(new EmptyBorder(0, 0, 0, 0));
//        setContentAreaFilled(false);
        setFocusable(false);
        
        JLabel label= new JLabel();
        addMouseListener(new MouseAdapter() {
    public void mouseEntered(MouseEvent e) {
        label.setBackground(Color.LIGHT_GRAY); // Ubah warna latar belakang saat mouse berada di atas label
    }

    public void mouseExited(MouseEvent e) {
        label.setBackground(null); // Kembalikan warna latar belakang ke default saat mouse meninggalkan label
    }

    public void mouseClicked(MouseEvent e) {
        // Aksi yang dilakukan saat label diklik
    }
});
    }


  
    
    
    
    public void setData(ModelTambah data){
      
        lbTambah.setText(data.getTitle());
        Icon.setIcon(data.getIcon());
    }
    
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTambah = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        lbTambah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbTambah.setForeground(new java.awt.Color(51, 51, 255));
        lbTambah.setText("Tambah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
       
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
       
    }//GEN-LAST:event_formMouseExited

     @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel lbTambah;
    // End of variables declaration//GEN-END:variables

 
}
