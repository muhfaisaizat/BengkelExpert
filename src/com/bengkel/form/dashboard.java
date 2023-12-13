
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.bengkel.chart.ModelChart;
import com.bengkel.dialog.Message;
import com.bengkel.dialog.detail;
import com.bengkel.dialog.detaildasbor;
import com.bengkel.main.tess;
import com.bengkel.model.ModelCek;
import com.bengkel.model.ModelProfit;
import com.bengkel.swing.SearchText;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.scene.chart.XYChart;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import koneksi.koneksii;



public class dashboard extends javax.swing.JPanel {

    public static Connection con;
    public static Statement stm;
    public static ResultSet res;
    public dashboard() {
        initComponents();
        table1.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
           cari.setSearchTextListener(new SearchText.SearchTextListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caritransaksi();
            }

            @Override
            public void KeyReleased(KeyEvent e) {
                 DefaultTableModel obj = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> obj1= new TableRowSorter<>(obj);
        table1.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(cari.getSearchText()));
            }
            
            

         
            
            
        });
    }
    
    private void initData() {
       initProfitData();
       init();
        koneksimysql();
        tampiltabel();
        initCekData();
        
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
    }
}
    
    public void tampiltabel(){
        TableActionEvent event=new TableActionEvent() {
            @Override
            public void onDetail(int row) {
               DefaultTableModel model = (DefaultTableModel) table1.getModel();
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(row, i);
            }
            String[] rowDataString = new String[rowData.length];
            for (int i = 0; i < rowData.length; i++) {
                rowDataString[i] = String.valueOf(rowData[i]);
            }
                detaildasbor editDialog = new detaildasbor(null, true, rowDataString);
            editDialog.setVisible(true);
            }

            @Override
            public void onEdit(int row) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(row, i);
            }
            String[] rowDataString = new String[rowData.length];
            for (int i = 0; i < rowData.length; i++) {
                rowDataString[i] = String.valueOf(rowData[i]);
            }
////                 System.out.println("edit "+row);
                edittransaksi tambah = new edittransaksi(rowDataString);            
                JPanel parent = (JPanel)getParent();
                parent.remove(dashboard.this);  
                parent.add(tambah, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint();
            }

            @Override
            public void onHapus(int row) {
                if (table1.isEditing()) {
        table1.getCellEditor().stopCellEditing();
    }

    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String idPelanggan = (String) model.getValueAt(row, 0); // Ambil nilai ID Pelanggan dari kolom pertama (index 0)
    
    // Hapus data dari SQL
    try {
        koneksimysql(); // Panggil metode koneksimysql() untuk menginisialisasi koneksi
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM transaksi WHERE ID_Transaksi = ?");
        pst.setString(1, idPelanggan);
        pst.executeUpdate();
        System.out.println("Data berhasil dihapus dari SQL");
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Hapus baris dari DefaultTableModel
    model.removeRow(row);
            }
        };
           DefaultTableModel tb = new DefaultTableModel();
        //judul kolom
  tb.addColumn("ID Transaksi");
    tb.addColumn("Nama Pelanggan");
    tb.addColumn("Nama Barang");
    tb.addColumn("Jumlah");
    tb.addColumn("Status");
    tb.addColumn("Aksi");
        table1.setModel(tb);
        try {
                     //m4ngwmbil data dari database
            res=stm.executeQuery("Select * from jadwal_pesan_barang");
            while (res.next()){
                tb.addRow(new Object[]{
                    res.getString("ID_transaksi"),
                    res.getString("Nama_Pelanggan"),
                    res.getString("Nama_Barang"),
                    res.getString("jumlah"),
                    res.getString("status"),
                });
        
        table1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
//e.printStackTrace();

        }
        
    }
  
    
    private void initCekData(){
  try {
        String bahan = "select * from stokbahan";
        String barang = "select * from stokbarangjadi";

        ResultSet bhn = stm.executeQuery(bahan);
        int stokBahan = 0;
        while (bhn.next()) {
            stokBahan = bhn.getInt("total_stok");
        }
        bhn.close(); // Close the ResultSet after retrieving the data

        ResultSet brg = stm.executeQuery(barang);
        int stokBarang = 0;
        while (brg.next()) {
            stokBarang = brg.getInt("total_stok");
        }
        brg.close(); // Close the ResultSet after retrieving the data

        cek1.setData(new ModelCek("Stok", "Barang Jadi", "Bahan", stokBarang, stokBahan, new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-scan-stock-96.png"))));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private void initProfitData(){
       koneksimysql(); // Panggil metode koneksimysql() untuk mendapatkan objek koneksi yang valid

        try {
            String pt = "select * from profit";
            ResultSet pro = stm.executeQuery(pt);

            double prof = 0.0; // Initialize with a default value

            while (pro.next()) {
                prof = pro.getDouble("profit");
            }
            pro.close();

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');

            DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
            String formattedProfit = decimalFormat.format(prof); // Format the double value as a String
            

            profit2.setData(new ModelProfit("Profit", "Rp."+formattedProfit+",00",
        new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-total-sales-96.png"))));

//            profit2.setData(new ModelProfit("Profit", new double(formattedProfit),new ImageIcon(getClass().getResource("/com/raven/icon/icons8-total-sales-96.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    




    }
    
      private void init(){
         try {
    res = stm.executeQuery("SELECT * FROM transaksi");
    
    while (res.next()) {
//        String idT = res.getString("Tanggal");
        double total = res.getDouble("Total_Harga");
        
        ModelChart modelChart = new ModelChart("", new double[]{total});
        curveChart1.addData(modelChart);
    }
    
    res.close();
    
    curveChart1.addLegend("Profit", new Color(0, 255, 0), new Color(95, 209, 69));
    curveChart1.start();
} catch (Exception e) {
    e.printStackTrace();
}

      }
   
     public void caritransaksi(){
        TableActionEvent event=new TableActionEvent() {
             @Override
             public void onDetail(int row) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(row, i);
            }
            String[] rowDataString = new String[rowData.length];
            for (int i = 0; i < rowData.length; i++) {
                rowDataString[i] = String.valueOf(rowData[i]);
            }
            detail editDialog = new detail(null, true, rowDataString);
            editDialog.setVisible(true);
             }

             @Override
             public void onEdit(int row) {
              DefaultTableModel model = (DefaultTableModel) table1.getModel();
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(row, i);
            }
            String[] rowDataString = new String[rowData.length];
            for (int i = 0; i < rowData.length; i++) {
                rowDataString[i] = String.valueOf(rowData[i]);
            }
////                 System.out.println("edit "+row);
                edittransaksi tambah = new edittransaksi(rowDataString);            
                JPanel parent = (JPanel)getParent();
                parent.remove(dashboard.this);  
                parent.add(tambah, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint(); }

             @Override
             public void onHapus(int row) {
                  if (table1.isEditing()) {
        table1.getCellEditor().stopCellEditing();
    }

    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String idPelanggan = (String) model.getValueAt(row, 0); // Ambil nilai ID Pelanggan dari kolom pertama (index 0)
    
    // Hapus data dari SQL
    try {
        koneksimysql(); // Panggil metode koneksimysql() untuk menginisialisasi koneksi
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM transaksi WHERE ID_Transaksi = ?");
        pst.setString(1, idPelanggan);
        pst.executeUpdate();
        System.out.println("Data berhasil dihapus dari SQL");
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Hapus baris dari DefaultTableModel
    model.removeRow(row);
             }
        };
         DefaultTableModel tb = new DefaultTableModel();
        //judul kolom
   tb.addColumn("ID Transaksi");
    tb.addColumn("Nama Pelanggan");
    tb.addColumn("Nama Barang");
    tb.addColumn("Jumlah");
    tb.addColumn("Status");
    tb.addColumn("Aksi");
        table1.setModel(tb);
        try {
                     //m4ngwmbil data dari database
            res = stm.executeQuery("CALL carijadwal('" + cari.getSearchText() + "');");
            while (res.next()){
                tb.addRow(new Object[]{
                     res.getString("ID_transaksi"),
                    res.getString("Nama_Pelanggan"),
                    res.getString("Nama_Barang"),
                    res.getString("jumlah"),
                    res.getString("status"),
                });
        table1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
 
       
        
        
        
        
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
        }
    }
    
   
        


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cek1 = new com.bengkel.component.cek();
        profit2 = new com.bengkel.component.Profit();
        curveChart1 = new com.bengkel.chart.CurveChart();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.bengkel.swing.table.Table();
        cari = new com.bengkel.component.search();

        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Dashboard");

        cek1.setBackground(new java.awt.Color(255, 153, 0));
        cek1.setColorGradient(new java.awt.Color(255, 153, 153));

        profit2.setBackground(new java.awt.Color(0, 153, 51));
        profit2.setColorGradient(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cek1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profit2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(curveChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cek1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profit2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(curveChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Data Pesan Barang");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Transaksi", "Nama Pelanggan", "Nama Barang", "Jumlah", "Status", "Aksi"
            }
        ));
        jScrollPane1.setViewportView(table1);
        table1.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.component.search cari;
    private com.bengkel.component.cek cek1;
    private com.bengkel.chart.CurveChart curveChart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bengkel.component.Profit profit2;
    private com.bengkel.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables

    private boolean showMessage(String message) {
        Message obj = new Message(tess.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }
       

    
       
}
