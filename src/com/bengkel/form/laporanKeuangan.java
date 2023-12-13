
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.bengkel.dialog.Message;
import com.bengkel.dialog.cetak;
import com.bengkel.dialog.editPelanggan;
import com.bengkel.model.ModelPelanggan;
import com.bengkel.model.ModelPesanBarang;
import com.bengkel.model.ModelStudent;
import com.bengkel.swing.SearchText;
import com.bengkel.swing.table.EventAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksii;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;




public class laporanKeuangan extends javax.swing.JPanel {

  
     public static Connection con;
    public static Statement stm;
    public static ResultSet res;
    public laporanKeuangan() {
      
         initComponents();
       table1.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
        dateChooser1.setTextRefernce(searchText1);
        dateChooser2.setTextRefernce(searchText2);
        searchText1.setHintText("Tanggal");
        searchText2.setHintText("Tanggal");
        search1.setSearchTextListener(new SearchText.SearchTextListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carilaporan();
            }

             @Override
             public void KeyReleased(KeyEvent e) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

       
            
        });
 
    }

    private void initData(){
         koneksimysql();
        tampiltabel();
        
    }
    public void koneksimysql(){
    try{
        String url="jdbc:mysql://localhost:3306/bengkel (2)";
        String user="root";
        String pass="";
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection(url, user, pass);
        stm = (Statement) con.createStatement();
//        JOptionPane.showMessageDialog(null, "berhasil koneksi");
    }catch (Exception e){
//        System.err.println("koneksi Gagal"+e.getMessage());
e.printStackTrace();
    }
}
    
    public void tampiltabel(){
       DefaultTableModel tb = new DefaultTableModel();
        //judul kolom
  tb.addColumn("NO");
    tb.addColumn("Tanggal");
    tb.addColumn("Jenis Transaksi");
    tb.addColumn("Total Pendapatan");
        table1.setModel(tb);
        try {
                     //m4ngwmbil data dari database
                     int no=1;
            res=stm.executeQuery("SELECT * FROM laporan_keuangan_transaksi");
            while (res.next()){
                tb.addRow(new Object[]{
                    no++,
                    res.getString("Tanggal"),
                    res.getString("Jenis_Transaksi"),
                    res.getString("Total_Pendapatan"),
                });
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
e.printStackTrace();

        }
    }
    
    public void carilaporan(){
        DefaultTableModel tb = new DefaultTableModel();
        //judul kolom
  tb.addColumn("NO");
    tb.addColumn("Tanggal");
    tb.addColumn("Jenis Transaksi");
    tb.addColumn("Total Pendapatan");
        table1.setModel(tb);
        try {
                     int no=1;
          res = stm.executeQuery("SELECT * FROM `laporan_keuangan_transaksi` laporan_keuangan_transaksi WHERE STR_TO_DATE(Tanggal, '%d-%m-%Y') BETWEEN STR_TO_DATE('"+searchText1.getText()+"', '%d-%m-%Y') AND STR_TO_DATE('"+searchText2.getText()+"', '%d-%m-%Y') AND laporan_keuangan_transaksi.Jenis_Transaksi COLLATE utf8mb4_general_ci = '"+search1.getSearchText()+"';");

            while (res.next()){
                tb.addRow(new Object[]{
                    no++,
                    res.getString("Tanggal"),
                    res.getString("Jenis_Transaksi"),
                    res.getString("Total_Pendapatan"),
                });
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
e.printStackTrace();
        }
    }
    
    
        public void cetak(){

try {
    
    InputStream inputStream = getClass().getResourceAsStream("/laporan/lp1.jrxml");
JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

    // Buat objek parameter
        Map<String, Object> parameters = new HashMap<>();
    parameters.put("tanggalawal", searchText1.getText());
    parameters.put("tanggalakhir", searchText2.getText());
    parameters.put("jenis", search1.getSearchText());

koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
    // Isi laporan dengan data menggunakan parameter
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, koneksi.con);

    // Tampilkan laporan di JasperViewer
    JasperViewer.viewReport(jasperPrint, false);
} catch (JRException e) {
    e.printStackTrace();
}
}
        
        public void cetak2(){
           try {
    
    InputStream inputStream = getClass().getResourceAsStream("/laporan/lptransaksi2.jrxml");
JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

    // Buat objek parameter
        Map<String, Object> parameters = new HashMap<>();
    parameters.put("tanggalawal", searchText1.getText());
    parameters.put("tanggalakhir", searchText2.getText());
   

koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
    // Isi laporan dengan data menggunakan parameter
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, koneksi.con);

    // Tampilkan laporan di JasperViewer
    JasperViewer.viewReport(jasperPrint, false);
} catch (JRException e) {
    e.printStackTrace();
} 
        }
        public void caritanggal(){
            DefaultTableModel tb = new DefaultTableModel();
        //judul kolom
  tb.addColumn("NO");
    tb.addColumn("Tanggal");
    tb.addColumn("Jenis Transaksi");
    tb.addColumn("Total Pendapatan");
        table1.setModel(tb);
              try {
            
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
Date tglawal = inputFormat.parse(searchText1.getText());
Date tglakhir = inputFormat.parse(searchText2.getText());
    String tglFormattedaw = outputFormat.format(tglawal);
    String tglFormattedhir = outputFormat.format(tglakhir);
       res = stm.executeQuery( "SELECT * FROM `laporan_keuangan_transaksi` WHERE STR_TO_DATE(Tanggal, '%d-%m-%Y') >= '"+tglFormattedaw+"' AND STR_TO_DATE(Tanggal, '%d-%m-%Y') <= ' "+tglFormattedhir+" ' ");
    int no=1;
       while (res.next()){
                tb.addRow(new Object[]{
                    no++,
                    res.getString("Tanggal"),
                    res.getString("Jenis_Transaksi"),
                    res.getString("Total_Pendapatan"),
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
     

          

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.datechooser.DateChooser();
        dateChooser2 = new com.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search1 = new com.bengkel.component.search();
        searchText1 = new com.bengkel.swing.SearchText();
        button1 = new com.bengkel.swing.Button();
        searchText2 = new com.bengkel.swing.SearchText();
        button2 = new com.bengkel.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.bengkel.swing.table.Table();

        dateChooser1.setForeground(new java.awt.Color(0, 102, 204));
        dateChooser1.setTextRefernce(searchText1);

        dateChooser2.setForeground(new java.awt.Color(0, 102, 204));

        setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Laporan Transaksi");

        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });

        button1.setForeground(new java.awt.Color(0, 51, 102));
        button1.setText("Cetak");
        button1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        searchText2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText2ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(0, 102, 255));
        button2.setText("cari");
        button2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchText2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(search1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setOpaque(false);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Tanggal", "Jenis Transaksi", "Total Pendapatan"
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (!search1.getSearchText().isEmpty()) {
            cetak();
        } else {
            cetak2();
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        
    }//GEN-LAST:event_searchText1ActionPerformed

    private void searchText2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText2ActionPerformed
        
    }//GEN-LAST:event_searchText2ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
caritanggal();
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button button1;
    private com.bengkel.swing.Button button2;
    private com.datechooser.DateChooser dateChooser1;
    private com.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bengkel.component.search search1;
    private com.bengkel.swing.SearchText searchText1;
    private com.bengkel.swing.SearchText searchText2;
    private com.bengkel.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
 

    
}
