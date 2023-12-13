
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.bengkel.component.search;
import com.bengkel.dialog.editBahan;
import com.bengkel.dialog.tambahBahan;
import static com.bengkel.form.jasa.res;
import static com.bengkel.form.jasa.stm;
import com.bengkel.model.ModelTambah;
import com.bengkel.swing.SearchText;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class bahan extends javax.swing.JPanel {

    public static Connection con;
    public static Statement stm;
    public static ResultSet res;
//    private com.raven.component.search search1;
  
    public bahan() {
      
         initComponents();
       table1.fixTable(jScrollPane1);
        setOpaque(false);
        initData();


       search1.setSearchTextListener(new SearchText.SearchTextListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caribahan();
            }

             @Override
             public void KeyReleased(KeyEvent e) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

            
        });
    }
    


    private void initData(){
       
        initTambahData();
        koneksimysql();
 tampiltabel();
//        caribahan();
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
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            editBahan editDialog = new editBahan(null, true, rowDataString,bahan.this);
            editDialog.setVisible(true);
             }

             @Override
             public void onHapus(int row) {
                if (table1.isEditing()) {
        table1.getCellEditor().stopCellEditing();
    }

    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String idbahan = (String) model.getValueAt(row, 0); // Ambil nilai ID Pelanggan dari kolom pertama (index 0)
    
    // Hapus data dari SQL
    try {
        koneksimysql(); // Panggil metode koneksimysql() untuk menginisialisasi koneksi
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM bahan WHERE ID_Bahan= ?");
        pst.setString(1, idbahan);
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
  tb.addColumn("ID Bahan");
    tb.addColumn("Nama ");
    tb.addColumn("Harga");
    tb.addColumn("Kategori");
    tb.addColumn("Stok");
    tb.addColumn("Keterangan");
    tb.addColumn("Aksi");
        table1.setModel(tb);
        try {

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                     //m4ngwmbil data dari database
            res=stm.executeQuery("Select * from bahan");
            while (res.next()){
                String hargab = res.getString("Harga");
            double harga = Double.parseDouble(hargab);
                String hargaFormatted = decimalFormat.format(harga);
                tb.addRow(new Object[]{
                    res.getString("ID_Bahan"),
                    res.getString("Nama_Bahan"),
                    hargaFormatted,
                    res.getString("Kategori"),
                    res.getString("Stok"),
                    res.getString("Keterangan"),
                });
        table1.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        
        
        
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
        }
    }
    
   
    
    private void initTambahData(){
        tambah1.setData(new ModelTambah("Tambah Bahan", new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-plus-40.png"))));

          tambah1.addMouseListener(new MouseAdapter(){
             public void mousePressed(MouseEvent e) {
        tambah1.setBackground(new Color(242,242,242));
    }

    public void mouseReleased(MouseEvent e) {
        tambah1.setBackground(Color.white);
    }});
    }
    
    public void caribahan(){
         TableActionEvent event=new TableActionEvent() {
             @Override
             public void onDetail(int row) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void onEdit(int row) {
 DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String[] rowData = new String[model.getColumnCount()]; // Create an array to hold the row data

    // Get the values from each column in the selected row and store them in the rowData array
    for (int i = 0; i < model.getColumnCount(); i++) {
        rowData[i] = model.getValueAt(row, i).toString();
    }

    new editBahan(null, true, rowData,bahan.this).setVisible(true);
             }

             @Override
             public void onHapus(int row) {
                if (table1.isEditing()) {
        table1.getCellEditor().stopCellEditing();
    }

    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String idbahan = (String) model.getValueAt(row, 0); // Ambil nilai ID Pelanggan dari kolom pertama (index 0)
    
    // Hapus data dari SQL
    try {
        koneksimysql(); // Panggil metode koneksimysql() untuk menginisialisasi koneksi
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM bahan WHERE ID_Bahan= ?");
        pst.setString(1, idbahan);
        pst.executeUpdate();
        System.out.println("Data berhasil dihapus dari SQL");
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Hapus baris dari DefaultTableModel
    model.removeRow(row);
             }
        };
         DefaultTableModel tbl = new DefaultTableModel();
        //judul kolom
  tbl.addColumn("ID Bahan");
    tbl.addColumn("Nama ");
    tbl.addColumn("Harga");
    tbl.addColumn("Kategori");
    tbl.addColumn("Stok");
    tbl.addColumn("Keterangan");
    tbl.addColumn("Aksi");
        table1.setModel(tbl);
try {
    // Mengambil data dari database menggunakan prosedur MySQL
    res = stm.executeQuery("CALL cari_bahan('" + search1.getSearchText() + "');");

    while (res.next()) {
        tbl.addRow(new Object[]{
            res.getString("ID_Bahan"),
            res.getString("Nama_Bahan"),
            res.getString("Harga"),
            res.getString("Kategori"),
            res.getString("Stok"),
            res.getString("Keterangan"),
        });
    }

    // Mengatur renderer dan editor untuk kolom aksi pada tabel
    table1.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
    table1.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
    System.out.println("terkirim");
} catch (Exception e) {
    // JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
}
    }
     

          

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tambah1 = new com.bengkel.component.tambah();
        search1 = new com.bengkel.component.search();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.bengkel.swing.table.Table();

        setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Bahan");

        tambah1.setBackground(new java.awt.Color(255, 255, 255));
        tambah1.setColorGradient(new java.awt.Color(242, 242, 242));
        tambah1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambah1MouseClicked(evt);
            }
        });

        search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                search1MousePressed(evt);
            }
        });
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tambah1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 621, Short.MAX_VALUE)
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setOpaque(false);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Bahan", "Nama ", "Harga", "kategori", "stok", "keterangan", "Aksi"
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
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

    private void tambah1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambah1MouseClicked
    new tambahBahan(null, true,this).setVisible(true);
    }//GEN-LAST:event_tambah1MouseClicked

    private void search1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search1MousePressed
   
    }//GEN-LAST:event_search1MousePressed

    private void search1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyPressed
        caribahan();
    }//GEN-LAST:event_search1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bengkel.component.search search1;
    private com.bengkel.swing.table.Table table1;
    private com.bengkel.component.tambah tambah1;
    // End of variables declaration//GEN-END:variables
    


    
}
