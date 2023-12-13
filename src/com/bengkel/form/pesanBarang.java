
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.bengkel.dialog.Message;
import com.bengkel.dialog.editPelanggan;
import com.bengkel.dialog.tambahBahan;
import static com.bengkel.form.pelanggan.con;
import static com.bengkel.form.pelanggan.stm;
import com.bengkel.model.ModelPelanggan;
import com.bengkel.model.ModelPesanBarang;
import com.bengkel.model.ModelStudent;
import com.bengkel.model.ModelTambah;

import com.bengkel.swing.MaterialTabbed;
import com.bengkel.swing.SearchText;
import com.bengkel.swing.table.EventAction;
import com.bengkel.swing.table.TableCellAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import glasspanepopup.GlassPanePopup;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;




public class pesanBarang extends javax.swing.JPanel {
    public static Connection con;
    public static Statement stm;
    public static ResultSet res;
  
    public pesanBarang() {
      
         initComponents();
       table1.fixTable(jScrollPane1);
        setOpaque(false);
        initData();

        search1.setSearchTextListener(new SearchText.SearchTextListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caripesanbarang();
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
//                 System.out.println("edit "+row);
                editpesanbarang tambah = new editpesanbarang(rowDataString);            
                JPanel parent = (JPanel)getParent();
                parent.remove(pesanBarang.this);
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
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM pesan_barang WHERE ID_Pesanbarang = ?");
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
  tb.addColumn("ID Pemesanan");
    tb.addColumn("Nama Barang");
    tb.addColumn("Keterangan");
    tb.addColumn("Total Harga");
    tb.addColumn("Aksi");
        table1.setModel(tb);
        try {
                     //m4ngwmbil data dari database
            res=stm.executeQuery("Select * from pesan_barang");

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
            while (res.next()){
                   String hargab= res.getString("Total_Harga");
            double harga = Double.parseDouble(hargab);
                String hargaFormatted = decimalFormat.format(harga);
                tb.addRow(new Object[]{
                    res.getString("ID_Pesanbarang"),
                    res.getString("Nama_Barang"),
                    res.getString("Keterangan"),
                    hargaFormatted,
                });
        table1.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
        }
    }
    

    public void caripesanbarang(){
         TableActionEvent event=new TableActionEvent() {
             @Override
             public void onDetail(int row) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void onEdit(int row) {
//                 System.out.println("edit "+row);
//                editpesanbarang tambah = new editpesanbarang();            
//                JPanel parent = (JPanel)getParent();
//                parent.remove(pesanBarang.this);
//                parent.add(tambah, BorderLayout.CENTER);
//                parent.revalidate();
//                parent.repaint();
             }

             @Override
             public void onHapus(int row) {
                   if (table1.isEditing()) {
        table1.getCellEditor().stopCellEditing();
    }

    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    String idpesanbarang = (String) model.getValueAt(row, 0); // Ambil nilai ID Pelanggan dari kolom pertama (index 0)
    
    // Hapus data dari SQL
    try {
        koneksimysql(); // Panggil metode koneksimysql() untuk menginisialisasi koneksi
        java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM pesan_barang WHERE ID_Pesanbarang= ?");
        pst.setString(1, idpesanbarang);
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
  tb.addColumn("ID Pemesanan");
    tb.addColumn("Nama Barang");
    tb.addColumn("Keterangan");
    tb.addColumn("Total Harga");
    tb.addColumn("Aksi");
        table1.setModel(tb);
        try {
                     //m4ngwmbil data dari database
            res = stm.executeQuery("CALL cari_pesanbarang('" + search1.getSearchText() + "');");
            while (res.next()){
                tb.addRow(new Object[]{
                    res.getString("ID_Pesanbarang"),
                    res.getString("Nama_Barang"),
                    res.getString("Keterangan"),
                    res.getString("Total_Harga"),
                });
        table1.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        
            }
              
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "data gagal ditampilkan" + e.getMessage());
        }
    }
    
    private void initTambahData(){
        tambah1.setData(new ModelTambah("Tambah pemesanan", new ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-plus-40.png"))));

          tambah1.addMouseListener(new MouseAdapter(){
             public void mousePressed(MouseEvent e) {
        tambah1.setBackground(new Color(242,242,242));
    }

    public void mouseReleased(MouseEvent e) {
        tambah1.setBackground(Color.white);
    }});
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
        jLabel1.setText("Pesan Barang");

        tambah1.setBackground(new java.awt.Color(255, 255, 255));
        tambah1.setColorGradient(new java.awt.Color(242, 242, 242));
        tambah1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambah1MouseClicked(evt);
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
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_Pemesanan", "Nama Barang", "Keteranagn", "Total Harga", "Aksi"
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
        tambahpesanbarangfinal1 tambahTransaksiPanel = new tambahpesanbarangfinal1();
    JPanel parent = (JPanel)getParent();
    parent.remove(this);
    parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
    parent.revalidate();
    parent.repaint();
// new tambahBahan(null, true).setVisible(true);
    }//GEN-LAST:event_tambah1MouseClicked


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
