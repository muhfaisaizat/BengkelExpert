
package cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelAction extends javax.swing.JPanel {

    public PanelAction() {
        initComponents();
    }
    
     public void initEvent(TableActionEvent event, int row) {
        detail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onDetail(row);
            }
        });
        edit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onEdit(row);
            }
        });
        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onHapus(row);
            }
        });
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detail = new cell.ActionButton();
        edit1 = new cell.ActionButton();
        hapus = new cell.ActionButton();

        detail.setBackground(new java.awt.Color(255, 255, 255));
        detail.setForeground(new java.awt.Color(255, 255, 255));
        detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-more-details-18.png"))); // NOI18N

        edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-edit-18.png"))); // NOI18N

        hapus.setBackground(new java.awt.Color(255, 255, 255));
        hapus.setForeground(new java.awt.Color(255, 255, 255));
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-remove-18.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cell.ActionButton detail;
    private cell.ActionButton edit1;
    private cell.ActionButton hapus;
    // End of variables declaration//GEN-END:variables
}
