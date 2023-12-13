
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.bengkel.model.kirimdata;
import com.bengkel.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksii;


public class tambahpesanbarang extends javax.swing.JPanel {

   private koneksii koneksi;
    public static ResultSet res;
    private final tambah1pesanbarang tp1= new tambah1pesanbarang();
  
    public tambahpesanbarang() {
        initComponents();
       
        TableActionEvent event=new TableActionEvent() {
             @Override
             public void onDetail(int row) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void onEdit(int row) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void onHapus(int row) {
                 if (detailbahan.isEditing()) {
                    detailbahan.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
                model.removeRow(row);
             }
        };
        TableActionEvent eventJasa = new TableActionEvent() {
    @Override
    public void onDetail(int row) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void onEdit(int row) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void onHapus(int row) {
        if (detailjasa.isEditing()) {
            detailjasa.getCellEditor().stopCellEditing();
        }
        DefaultTableModel model = (DefaultTableModel) detailjasa.getModel();
        model.removeRow(row);
    }
};
       detailbahan.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailbahan.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
       detailjasa.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailjasa.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(eventJasa));
        detailbahan.fixTable(jScrollPane2);
        detailjasa.fixTable(jScrollPane1);
        init();
        TotalHarga();
    }
    
    public void init(){
        idbahan.setHintText("ID_Bahan");
        namabahan.setHintText("Nama Bahan");
        jumlahbahan.setHintText("Jumlah");
        hargabahan.setHintText("Harga");
        idjasa.setHintText("ID_Jasa");
        namajasa.setHintText("Nama Jasa");
        hargajasa.setHintText("Harga");
        
    }
   
    public void TotalHarga(){
              DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
    double total = 0;
    DecimalFormat decimalFormat = new DecimalFormat("#,###.000");

    for (int row = 0; row < model.getRowCount(); row++) {
        String rowTotalString = model.getValueAt(row, 5).toString();

        if (!rowTotalString.isEmpty()) {
            String rowTotalStringWithoutComma = rowTotalString.replace(",", "");
            double rowtotal = Double.parseDouble(rowTotalStringWithoutComma);
            total += rowtotal;
        }
    }
    String formattedTotal = decimalFormat.format(total);
    System.out.println(decimalFormat.format(total));
//    tp1.getdata(formattedTotal);
//        tp1.setData(formattedTotal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailjasa = new com.bengkel.swing.table.Table();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailbahan = new com.bengkel.swing.table.Table();
        idbahan = new com.bengkel.swing.SearchText();
        namabahan = new com.bengkel.swing.SearchText();
        jumlahbahan = new com.bengkel.swing.SearchText();
        hargabahan = new com.bengkel.swing.SearchText();
        tbbahan = new com.bengkel.swing.Button();
        idjasa = new com.bengkel.swing.SearchText();
        namajasa = new com.bengkel.swing.SearchText();
        hargajasa = new com.bengkel.swing.SearchText();
        btjasa = new com.bengkel.swing.Button();

        setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setText("Masukan detail bahan yang dibutuhkan untuk membuat sebuah barang");

        detailjasa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aksi", "ID_Jasa", "Nama Jasa", "Harga"
            }
        ));
        jScrollPane1.setViewportView(detailjasa);

        jLabel2.setText("Masukan jasa  yang dibutuhkan untuk membuat sebuah barang");

        detailbahan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aksi", "ID_Bahan", "Nama Bahan", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane2.setViewportView(detailbahan);

        idbahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbahanActionPerformed(evt);
            }
        });

        namabahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabahanActionPerformed(evt);
            }
        });

        jumlahbahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahbahanActionPerformed(evt);
            }
        });

        tbbahan.setBackground(new java.awt.Color(0, 153, 204));
        tbbahan.setText("Tambah");
        tbbahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbbahanActionPerformed(evt);
            }
        });

        idjasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idjasaActionPerformed(evt);
            }
        });

        namajasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namajasaActionPerformed(evt);
            }
        });

        btjasa.setBackground(new java.awt.Color(0, 153, 204));
        btjasa.setText("Tambah");
        btjasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btjasaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hargajasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(idbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(namabahan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jumlahbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hargabahan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(24, 24, 24)
                        .addComponent(tbbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namabahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargabahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(375, 375, 375)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(434, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void namabahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabahanActionPerformed
         try {
            koneksii koneksi = new koneksii();
koneksi.koneksimysql();

String search = namabahan.getText();
String procedureCall = "CALL cari_bahan('" + search + "');";
res = koneksi.stm.executeQuery(procedureCall);

if (res.next()) {
    String ID = res.getString("ID_Bahan"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String nama = res.getString("Nama_Bahan"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String harga = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    idbahan.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
    namabahan.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
    hargabahan.setText(harga); // Ganti "textField" dengan objek JTextField yang diinginkan
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_namabahanActionPerformed

    private void jumlahbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahbahanActionPerformed

    private void tbbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbbahanActionPerformed
       DefaultTableModel model=(DefaultTableModel) detailbahan.getModel();
       int jumlah = Integer.parseInt(jumlahbahan.getText());
double harga = Double.parseDouble(hargabahan.getText());

double total = jumlah * harga;
DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        Object[] row ={"Aksi", idbahan.getText(),namabahan.getText(),jumlahbahan.getText(),hargabahan.getText(),decimalFormat.format(total),};
    model.addRow(row);
    TotalHarga();
    }//GEN-LAST:event_tbbahanActionPerformed

    private void btjasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btjasaActionPerformed
       DefaultTableModel model=(DefaultTableModel) detailjasa.getModel();
        Object[] row ={"Aksi", idjasa.getText(),namajasa.getText(),hargajasa.getText()};
    model.addRow(row);
     
    }//GEN-LAST:event_btjasaActionPerformed

    private void idbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbahanActionPerformed
        try {
            koneksii koneksi = new koneksii();
koneksi.koneksimysql();

String search = idbahan.getText();
String procedureCall = "CALL cari_bahan('" + search + "');";
res = koneksi.stm.executeQuery(procedureCall);

if (res.next()) {
    String ID = res.getString("ID_Bahan"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String nama = res.getString("Nama_Bahan"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String harga = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    idbahan.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
    namabahan.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
    hargabahan.setText(harga); // Ganti "textField" dengan objek JTextField yang diinginkan
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_idbahanActionPerformed

    private void idjasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idjasaActionPerformed
         try {
            koneksii koneksi = new koneksii();
koneksi.koneksimysql();

String search = idjasa.getText();
String procedureCall = "CALL cari_jasa('" + search + "');";
res = koneksi.stm.executeQuery(procedureCall);

if (res.next()) {
    String ID = res.getString("ID_Jasa"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String nama = res.getString("Nama"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String harga = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    idjasa.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
    namajasa.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
    hargajasa.setText(harga); // Ganti "textField" dengan objek JTextField yang diinginkan
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_idjasaActionPerformed

    private void namajasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namajasaActionPerformed
          try {
            koneksii koneksi = new koneksii();
koneksi.koneksimysql();

String search = namajasa.getText();
String procedureCall = "CALL cari_jasa('" + search + "');";
res = koneksi.stm.executeQuery(procedureCall);

if (res.next()) {
    String ID = res.getString("ID_Jasa"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String nama = res.getString("Nama"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    String harga = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
    idjasa.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
    namajasa.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
    hargajasa.setText(harga); // Ganti "textField" dengan objek JTextField yang diinginkan
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_namajasaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button btjasa;
    private com.bengkel.swing.table.Table detailbahan;
    private com.bengkel.swing.table.Table detailjasa;
    private com.bengkel.swing.SearchText hargabahan;
    private com.bengkel.swing.SearchText hargajasa;
    private com.bengkel.swing.SearchText idbahan;
    private com.bengkel.swing.SearchText idjasa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.bengkel.swing.SearchText jumlahbahan;
    private com.bengkel.swing.SearchText namabahan;
    private com.bengkel.swing.SearchText namajasa;
    private com.bengkel.swing.Button tbbahan;
    // End of variables declaration//GEN-END:variables

    void fixTable(JScrollPane scrollPane) {
       scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    }
}
