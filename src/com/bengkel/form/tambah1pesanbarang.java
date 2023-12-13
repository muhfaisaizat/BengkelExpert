
package com.bengkel.form;

import com.bengkel.model.kirimdata;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class tambah1pesanbarang extends javax.swing.JPanel {
//private kirimdata data;

    public tambah1pesanbarang() {
        initComponents();
//        this.data = data;
        setOpaque(false);
//        init2();
        ipb.setHintText(" ");
        nbr.setHintText(" ");
        th.setHintText("0");
//            kirimdata.setTotalHarga(1234.56); // Misalnya, setTotalHarga dijalankan di tempat yang sesuai

//        double totalHarga = kirimdata.getTotalHarga();
//        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
//        String formattedTotal = decimalFormat.format(totalHarga);
//        th.setText(formattedTotal);
//        System.out.println("detail " + totalHarga);
         tambahpesanbarang tpb = new tambahpesanbarang();  
    JScrollPane scrollPane = new JScrollPane(tpb);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    tbpesanbarang.setLayout(new BorderLayout());
    tbpesanbarang.add(scrollPane, BorderLayout.CENTER);
    tpb.fixTable(scrollPane);
    }
//    
//    public String getData() {
//    return th.getText();
//}
//    
//    public void setData(String data){
//        th.setText(data);
//        System.out.println("detail "+data);
//    }

//    public void init2(){
//    }
//    public void data(){
//    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pesanbarang = new javax.swing.JPanel();
        tbpesanbarang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel12 = new javax.swing.JLabel();
        th = new com.bengkel.swing.SearchText();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textAreaScroll2 = new com.bengkel.swing.TextAreaScroll();
        textArea2 = new com.bengkel.swing.TextArea();
        button5 = new com.bengkel.swing.Button();
        button7 = new com.bengkel.swing.Button();
        ipb = new com.bengkel.swing.SearchText();
        nbr = new com.bengkel.swing.SearchText();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        pesanbarang.setOpaque(false);

        tbpesanbarang.setOpaque(false);

        javax.swing.GroupLayout tbpesanbarangLayout = new javax.swing.GroupLayout(tbpesanbarang);
        tbpesanbarang.setLayout(tbpesanbarangLayout);
        tbpesanbarangLayout.setHorizontalGroup(
            tbpesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        tbpesanbarangLayout.setVerticalGroup(
            tbpesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Total Harga");

        jLabel13.setText("ID_PesanBarang");

        jLabel14.setText("Nama Barang");

        textAreaScroll2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textAreaScroll2.setLabelText("Keterangan");
        textAreaScroll2.setLineColor(new java.awt.Color(204, 204, 204));
        textAreaScroll2.setMinimumSize(new java.awt.Dimension(18, 20));

        textArea2.setColumns(20);
        textArea2.setRows(5);
        textArea2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textArea2MousePressed(evt);
            }
        });
        textAreaScroll2.setViewportView(textArea2);

        button5.setBackground(new java.awt.Color(0, 102, 204));
        button5.setText("Reset");

        button7.setBackground(new java.awt.Color(0, 51, 153));
        button7.setText("Simpan");

        ipb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pesanbarangLayout = new javax.swing.GroupLayout(pesanbarang);
        pesanbarang.setLayout(pesanbarangLayout);
        pesanbarangLayout.setHorizontalGroup(
            pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpesanbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pesanbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pesanbarangLayout.createSequentialGroup()
                        .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(th, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pesanbarangLayout.createSequentialGroup()
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ipb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nbr, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pesanbarangLayout.setVerticalGroup(
            pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesanbarangLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpesanbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(th, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(37, 37, 37)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(0, 51, 102));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Tambah Pesan Barang");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pesanbarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesanbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textArea2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textArea2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textArea2MousePressed

    private void ipbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button button5;
    private com.bengkel.swing.Button button7;
    private com.bengkel.swing.SearchText ipb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.bengkel.swing.SearchText nbr;
    private javax.swing.JPanel pesanbarang;
    private javax.swing.JPanel tbpesanbarang;
    private com.bengkel.swing.TextArea textArea2;
    private com.bengkel.swing.TextAreaScroll textAreaScroll2;
    private com.bengkel.swing.SearchText th;
    // End of variables declaration//GEN-END:variables
}
