
package com.bengkel.component;

import com.bengkel.model.ModelCek;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class cek extends javax.swing.JPanel {

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }

    private Color colorGradient;
    public cek() {
        initComponents();
        setOpaque(false);
           setBackground(new Color(112, 69, 246));
        colorGradient = new Color(255, 255, 255);
        
    }
    
    public void setData(ModelCek data){
      
        lbtitle.setText(data.getTitle());
        lbtitle1.setText(data.getTitle1());
        lbtitle2.setText(data.getTitle2());
        lbvalue1.setText(Integer.toString(data.getValue1()));
        lbvalue2.setText(Integer.toString(data.getValue2()));
        lbicon.setIcon(data.getIcon());
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbicon = new javax.swing.JLabel();
        lbtitle = new javax.swing.JLabel();
        lbtitle1 = new javax.swing.JLabel();
        lbtitle2 = new javax.swing.JLabel();
        lbvalue1 = new javax.swing.JLabel();
        lbvalue2 = new javax.swing.JLabel();

        lbicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbtitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbtitle.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle.setText("Title");

        lbtitle1.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle1.setText("title1");

        lbtitle2.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle2.setText("title2");

        lbvalue1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbvalue1.setForeground(new java.awt.Color(255, 255, 255));
        lbvalue1.setText("value1");

        lbvalue2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbvalue2.setForeground(new java.awt.Color(255, 255, 255));
        lbvalue2.setText("value2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbtitle1)
                                    .addComponent(lbtitle2))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbvalue1)
                                    .addComponent(lbvalue2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(lbtitle)))
                        .addGap(0, 84, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbicon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtitle1)
                    .addComponent(lbvalue1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtitle2)
                    .addComponent(lbvalue2))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
   @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(grphcs);
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbicon;
    private javax.swing.JLabel lbtitle;
    private javax.swing.JLabel lbtitle1;
    private javax.swing.JLabel lbtitle2;
    private javax.swing.JLabel lbvalue1;
    private javax.swing.JLabel lbvalue2;
    // End of variables declaration//GEN-END:variables
}
