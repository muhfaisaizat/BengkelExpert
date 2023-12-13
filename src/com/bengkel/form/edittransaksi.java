/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.barcodelib.barcode.Linear;
import com.bengkel.dialog.cetak;
import com.bengkel.dialog.tambahPelanggann;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksii;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;




/**
 *
 * @author user
 */
public class edittransaksi extends javax.swing.JPanel {

    private String[] selectedRowData;
    ResultSet res;

    public edittransaksi(String[] selectedRowData) {
        initComponents();
        this.selectedRowData = selectedRowData;
        setOpaque(false);
        tampiledit();
        tampil_combobox();
        idts.setHintText(" ");
        tg.setHintText(" ");
        tb.setHintText("0");
        b.setHintText("0");
        k.setHintText("0");
        hg.setHintText("0");
        jm.setHintText("0");
        kd.setHintText("cari kode ");
        nb.setHintText("cari nama bahan");
        TableActionEvent event = new TableActionEvent() {
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
               if (detailT.isEditing()) {
                    detailT.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) detailT.getModel();
String idTr = (String) model.getValueAt(row, 1);
String kd = (String) model.getValueAt(row, 2);

// Hapus data dari SQL
try {
    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    java.sql.Connection con = koneksi.con; // Panggil metode koneksimysql() untuk menginisialisasi koneksi
    java.sql.PreparedStatement pst = null;

    if (kd.startsWith("PB")) {
        // Hapus data transaksi pesanbarang
        pst = con.prepareStatement("DELETE FROM detail_transaksipesanbarang WHERE detail_transaksipesanbarang.ID_Transaksi=? AND detail_transaksipesanbarang.ID_Pesanbarang=?");
    } else if (kd.startsWith("BJ")) {
        // Hapus data transaksi barangjadi
        pst = con.prepareStatement("DELETE FROM detail_transaksibarangjadi WHERE detail_transaksibarangjadi.ID_Transaksi=? AND detail_transaksibarangjadi.ID_Barangjadi=?");
    }

    pst.setString(1, idTr);
    pst.setString(2, kd);
    pst.executeUpdate();
    System.out.println("Data berhasil dihapus dari SQL");
} catch (Exception e) {
    e.printStackTrace();
}

                model.removeRow(row);
                TotalHarga();
            }
        };
        detailT.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailT.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));

        detailT.fixTable(jScrollPane2);

//        TotalHarga();
//        tampiledittabel();

    }

    public void tampiledit() {
        TableActionEvent event = new TableActionEvent() {
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
               if (detailT.isEditing()) {
                    detailT.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) detailT.getModel();
String idTr = (String) model.getValueAt(row, 1);
String kd = (String) model.getValueAt(row, 2);

// Hapus data dari SQL
try {
    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    java.sql.Connection con = koneksi.con; // Panggil metode koneksimysql() untuk menginisialisasi koneksi
    java.sql.PreparedStatement pst = null;

    if (kd.startsWith("PB")) {
        // Hapus data transaksi pesanbarang
        pst = con.prepareStatement("DELETE FROM detail_transaksipesanbarang WHERE detail_transaksipesanbarang.ID_Transaksi=? AND detail_transaksipesanbarang.ID_Pesanbarang=?");
    } else if (kd.startsWith("BJ")) {
        // Hapus data transaksi barangjadi
        pst = con.prepareStatement("DELETE FROM detail_transaksibarangjadi WHERE detail_transaksibarangjadi.ID_Transaksi=? AND detail_transaksibarangjadi.ID_Barangjadi=?");
    }

    pst.setString(1, idTr);
    pst.setString(2, kd);
    pst.executeUpdate();
    System.out.println("Data berhasil dihapus dari SQL");
} catch (Exception e) {
    e.printStackTrace();
}

                model.removeRow(row);
                TotalHarga();
            }
        };
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator('.');
    symbols.setDecimalSeparator(',');

    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    
    DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);

    String idT = selectedRowData[0];

    DefaultTableModel tbModel = new DefaultTableModel();
    // Judul kolom
    tbModel.addColumn("Aksi");
    tbModel.addColumn("ID Transaksi");
    tbModel.addColumn("Kode");
    tbModel.addColumn("Nama Barang");
    tbModel.addColumn("Jumlah");
    tbModel.addColumn("Harga");
    tbModel.addColumn("Total");
    detailT.setModel(tbModel);

    try {
            String sqlT = "SELECT * FROM transaksi WHERE ID_Transaksi= ? ";
            java.sql.PreparedStatement statementT = koneksi.con.prepareStatement(sqlT);
        statementT.setString(1, idT);
        ResultSet rsT = statementT.executeQuery();
        String idP = "";
String Tgl = "";
String ket = "";
String totalhar = "";

if (rsT.next()) {
    idP = rsT.getString("ID_Pelanggan");
    Tgl = rsT.getString("Tanggal");
    ket = rsT.getString("Keterangan");
    totalhar = rsT.getString("Total_Harga");
}
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    double TParsed = Double.parseDouble(totalhar);
    String TFormatted = decimalFormat.format(TParsed);
    idts.setText(idT);
    textArea1.setText(ket);
    tb.setText(TFormatted);
        Date date = inputFormat.parse(Tgl);
        String tglFormatted = outputFormat.format(date);
        tg.setText(tglFormatted);

        String sql = "SELECT * FROM pelanggan WHERE ID_Pelanggan='" + idP + "'";
        ResultSet rs = koneksi.stm.executeQuery(sql);

        while (rs.next()) {
            comboBoxSuggestion1.setSelectedItem(rs.getString("ID_Pelanggan") + " - " + rs.getString("Nama") + " - " + rs.getString("No_Telp"));
        }

        String brc = "SELECT * FROM barcodetransaksi WHERE barcodetransaksi.id_transaksi = ?";
        java.sql.PreparedStatement statementBarcode = koneksi.con.prepareStatement(brc);
        statementBarcode.setString(1, idT);
        ResultSet rsBarcode = statementBarcode.executeQuery();

        if (rsBarcode.next()) {
              Blob photoBlob = rsBarcode.getBlob("barcode");
    InputStream is = photoBlob.getBinaryStream();
    
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[1024];
    
    while ((nRead = is.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
    }
    
    buffer.flush();
    byte[] photoData = buffer.toByteArray();

    // Mengubah data byte menjadi gambar ImageIcon
    ImageIcon icon = new ImageIcon(photoData);

    // Menampilkan gambar di JLabel
    lbbarcode.setIcon(icon);
        } else {
            System.out.println("Data barcode tidak ditemukan");
        }

        String pesan = "SELECT * FROM `detail_transaksipesanbarang` WHERE detail_transaksipesanbarang.ID_Transaksi= ?";
        java.sql.PreparedStatement statementDetail = koneksi.con.prepareStatement(pesan);
        System.out.println("detail"+idT);
        statementDetail.setString(1, idT);
        ResultSet rsDetail = statementDetail.executeQuery();
        
        while (rsDetail.next()) {
            String harga = rsDetail.getString("Harga");
                String total = rsDetail.getString("Total");
                double hargaParsed = Double.parseDouble(harga);
                double totalParsed = Double.parseDouble(total);
                String hargaFormatted = decimalFormat.format(hargaParsed);
                String totalFormatted = decimalFormat.format(totalParsed);
            
        detailT.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailT.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
            tbModel.addRow(new Object[]{
                null,
                rsDetail.getString("ID_Transaksi"),
                rsDetail.getString("ID_Pesanbarang"),
                rsDetail.getString("Nama_Barang"),
                rsDetail.getString("Jumlah"),
                hargaFormatted,
                totalFormatted,
            });
        }
        
        
        String barang = "SELECT * FROM `detail_transaksibarangjadi` WHERE detail_transaksibarangjadi.ID_Transaksi= ?";
        java.sql.PreparedStatement statementbarang = koneksi.con.prepareStatement(barang);
//        System.out.println("detail"+idT);
        statementbarang.setString(1, idT);
        ResultSet rsbarang = statementbarang.executeQuery();
        
        while (rsbarang.next()) {
            String harga = rsbarang.getString("Harga");
                String total = rsbarang.getString("Total");
                double hargaParsed = Double.parseDouble(harga);
                double totalParsed = Double.parseDouble(total);
                String hargaFormatted = decimalFormat.format(hargaParsed);
                String totalFormatted = decimalFormat.format(totalParsed);
            
        detailT.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailT.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
            tbModel.addRow(new Object[]{
                null,
                rsbarang.getString("ID_Transaksi"),
                rsbarang.getString("ID_Barangjadi"),
                rsbarang.getString("Nama_Barang"),
                rsbarang.getString("Jumlah"),
                hargaFormatted,
                totalFormatted,
            });
        }
        
        
        String status = "SELECT * FROM `transaksi` WHERE transaksi.ID_Transaksi= ?";
        java.sql.PreparedStatement statementstatus = koneksi.con.prepareStatement(status);
//        System.out.println("detail"+idT);
        statementstatus.setString(1, idT);
        ResultSet rsstatus = statementstatus.executeQuery();
        
        while (rsstatus.next()) {
    String statusValue = rsstatus.getString("status");
//    comboBoxSuggestion2.addItem(statusValue);
    comboBoxSuggestion2.setSelectedItem(statusValue);
}
        

        
        statementBarcode.close();
        statementDetail.close();
        statementbarang.close();
        statementstatus.close();
        koneksi.con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    

    public void tampil_combobox() {

        koneksii koneksi = new koneksii();
        koneksi.koneksimysql();
        try {
            String sql = "SELECT * FROM pelanggan";
            ResultSet rs = koneksi.stm.executeQuery(sql);

            while (rs.next()) {
                comboBoxSuggestion1.addItem(rs.getString("ID_Pelanggan") + " - " + rs.getString("Nama") + " - " + rs.getString("No_Telp"));
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
        } catch (Exception e) {
        }
    }

    public void TotalHarga() {
        DefaultTableModel model = (DefaultTableModel) detailT.getModel();
        double total = 0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        for (int row = 0; row < model.getRowCount(); row++) {
            String rowTotalString = model.getValueAt(row, 6).toString();

            if (!rowTotalString.isEmpty()) {
                String rowTotalStringWithoutComma = rowTotalString.replace(".", ""); // Menghapus tanda koma dan titik desimal
                double rowTotal = Double.parseDouble(rowTotalStringWithoutComma);
                total += rowTotal;
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        String formattedTotal = decimalFormat.format(total);

        tb.setText(formattedTotal);
        System.out.println(formattedTotal);

    }
    
    public void simpan(){
          DefaultTableModel model = (DefaultTableModel) detailT.getModel();
int rowCount = model.getRowCount();
System.out.println(rowCount);

koneksii koneksi = new koneksii();
koneksi.koneksimysql();
java.sql.PreparedStatement pst = null;

String idtran = idts.getText();
String idpel = (String) comboBoxSuggestion1.getSelectedItem();
String[] parts = idpel.split("-");
String peidl = parts[0].trim();
System.out.println(peidl);
String tgl = tg.getText();
SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
String ket = textArea1.getText();
String totalh = tb.getText();
String totalHG = totalh.replaceAll("\\.", "");
String stats = (String) comboBoxSuggestion2.getSelectedItem();

try {
    Date date = inputFormat.parse(tgl);
    String tglFormatted = outputFormat.format(date);
    String ts = "UPDATE `transaksi` SET `ID_Pelanggan`=?, `Tanggal`=?, `Keterangan`=?, `Total_Harga`=?, `status`=? WHERE `ID_Transaksi`=?";
    pst = koneksi.con.prepareStatement(ts);
    pst.setString(1, peidl);
    pst.setString(2, tglFormatted);
    pst.setString(3, ket);
    pst.setString(4, totalHG);
    pst.setString(5, stats);
    pst.setString(6, idtran);

    pst.executeUpdate();
    System.out.println("Data transaksi berhasil diupdate");

    for (int i = 0; i < rowCount; i++) {
        String id = (String) model.getValueAt(i, 1);
        String kdo = (String) model.getValueAt(i, 2);
        String nama = (String) model.getValueAt(i, 3);
        String jumlah = (String) model.getValueAt(i, 4);
        String harga = (String) model.getValueAt(i, 5);
        String hargaF = harga.replaceAll("\\.", "");
        String total = (String) model.getValueAt(i, 6);
        String totalF = total.replaceAll("\\.", "");
        if (kdo.startsWith("BJ")) {
            // Check if data exists in detail_transaksibarangjadi
            String checkSql = "SELECT * FROM detail_transaksibarangjadi WHERE ID_Transaksi=? AND ID_Barangjadi=?";
            pst = koneksi.con.prepareStatement(checkSql);
            pst.setString(1, id);
            pst.setString(2, kdo);
            ResultSet rs = pst.executeQuery();
            boolean dataExists = rs.next();

            if (dataExists) {
                // Data barangjadi exists, perform update
                String updateSql = "UPDATE detail_transaksibarangjadi SET Nama_Barang=?, Jumlah=?, Harga=?, Total=? WHERE ID_Transaksi=? AND ID_Barangjadi=?";
                pst = koneksi.con.prepareStatement(updateSql);
                pst.setString(1, nama);
                pst.setString(2, jumlah);
                pst.setString(3, hargaF);
                pst.setString(4, totalF);
                pst.setString(5, id);
                pst.setString(6, kdo);
                pst.executeUpdate();
                System.out.println("Data barangjadi berhasil diupdate");
            } else {
                // Data barangjadi doesn't exist, perform insert
                String insertSql = "INSERT INTO detail_transaksibarangjadi (ID_Transaksi, ID_Barangjadi, Nama_Barang, Jumlah, Harga, Total) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(insertSql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                pst.executeUpdate();
                System.out.println("Data barangjadi berhasil ditambahkan");
            }
        } else if (kdo.startsWith("PB")) {
            // Check if data exists in detail_transaksipesanbarang
            String checkSql = "SELECT * FROM detail_transaksipesanbarang WHERE ID_Transaksi=? AND ID_Pesanbarang=?";
            pst = koneksi.con.prepareStatement(checkSql);
            pst.setString(1, id);
            pst.setString(2, kdo);
            ResultSet rs = pst.executeQuery();
            boolean dataExists = rs.next();

            if (dataExists) {
                // Data pesanbarang exists, perform update
                String updateSql = "UPDATE detail_transaksipesanbarang SET Nama_Barang=?, Jumlah=?, Harga=?, Total=? WHERE ID_Transaksi=? AND ID_Pesanbarang=?";
                pst = koneksi.con.prepareStatement(updateSql);
                pst.setString(1, nama);
                pst.setString(2, jumlah);
                pst.setString(3, hargaF);
                pst.setString(4, totalF);
                pst.setString(5, id);
                pst.setString(6, kdo);
                pst.executeUpdate();
                System.out.println("Data pesanbarang berhasil diupdate");
            } else {
                // Data pesanbarang doesn't exist, perform insert
                String insertSql = "INSERT INTO detail_transaksipesanbarang (ID_Transaksi, ID_Pesanbarang, Nama_Barang, Jumlah, Harga, Total) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(insertSql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                pst.executeUpdate();
                System.out.println("Data pesanbarang berhasil ditambahkan");
            }
        }
    }

    System.out.println("Data berhasil disimpan");
} catch (Exception e) {
    e.printStackTrace();
} finally {
    // Tutup PreparedStatement
    if (pst != null) {
        try {
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    }

    public void cetak(){
          String idtran = idts.getText();
String totalh = tb.getText();
String byar= b.getText();
String kembl=k.getText();

try {
    
    InputStream inputStream = getClass().getResourceAsStream("/laporan/struk.jrxml");
JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

    // Buat objek parameter
        Map<String, Object> parameters = new HashMap<>();
    parameters.put("idT", idtran);
parameters.put("TotalH", totalh);
parameters.put("bayar", byar);
parameters.put("kembali", kembl);

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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        materialTabbed1 = new com.bengkel.swing.MaterialTabbed();
        transaksi = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idts = new com.bengkel.swing.SearchText();
        tg = new com.bengkel.swing.SearchText();
        button1 = new com.bengkel.swing.Button();
        comboBoxSuggestion1 = new combo_suggestion.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tb = new com.bengkel.swing.SearchText();
        b = new com.bengkel.swing.SearchText();
        k = new com.bengkel.swing.SearchText();
        button2 = new com.bengkel.swing.Button();
        button3 = new com.bengkel.swing.Button();
        kd = new com.bengkel.swing.SearchText();
        nb = new com.bengkel.swing.SearchText();
        hg = new com.bengkel.swing.SearchText();
        jm = new com.bengkel.swing.SearchText();
        tambah = new com.bengkel.swing.Button();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        button6 = new com.bengkel.swing.Button();
        textAreaScroll1 = new com.bengkel.swing.TextAreaScroll();
        textArea1 = new com.bengkel.swing.TextArea();
        jLabel15 = new javax.swing.JLabel();
        comboBoxSuggestion2 = new combo_suggestion.ComboBoxSuggestion();
        lbbarcode = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailT = new com.bengkel.swing.table.Table();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Edit transaksi");

        materialTabbed1.setBackground(new java.awt.Color(153, 153, 153));

        transaksi.setOpaque(false);

        jLabel2.setText("Pelanggan");

        jLabel3.setText("Tanggal");

        jLabel5.setText("id_transaksi");

        idts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        idts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtsActionPerformed(evt);
            }
        });

        tg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        button1.setBackground(new java.awt.Color(242, 242, 242));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bengkel/icon/icons8-plus-40.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        comboBoxSuggestion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxSuggestion1MouseClicked(evt);
            }
        });
        comboBoxSuggestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Total Bayar");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Bayar");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Kembalian");

        tb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbActionPerformed(evt);
            }
        });

        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(51, 51, 255));
        button2.setText("Simpan & Cetak");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(0, 102, 255));
        button3.setText("Simpan");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        kd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdActionPerformed(evt);
            }
        });

        nb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbActionPerformed(evt);
            }
        });

        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmActionPerformed(evt);
            }
        });

        tambah.setBackground(new java.awt.Color(0, 204, 204));
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        jLabel8.setText("Kode");

        jLabel9.setText("Nama Barang");

        jLabel10.setText("Harga");

        jLabel11.setText("Jumlah");

        button6.setBackground(new java.awt.Color(0, 102, 204));
        button6.setText("Cetak");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        textAreaScroll1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textAreaScroll1.setLabelText("Keterangan");
        textAreaScroll1.setLineColor(new java.awt.Color(204, 204, 204));
        textAreaScroll1.setMinimumSize(new java.awt.Dimension(18, 20));

        textArea1.setColumns(20);
        textArea1.setRows(5);
        textArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textArea1MousePressed(evt);
            }
        });
        textAreaScroll1.setViewportView(textArea1);

        jLabel15.setText("Status");

        comboBoxSuggestion2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baru", "Proses", "Selesai", "Diambil", " " }));

        lbbarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setText("barcode Transaksi");

        detailT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane2.setViewportView(detailT);

        javax.swing.GroupLayout transaksiLayout = new javax.swing.GroupLayout(transaksi);
        transaksi.setLayout(transaksiLayout);
        transaksiLayout.setHorizontalGroup(
            transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(idts, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(transaksiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kd, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nb, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hg, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(jm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tb, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        transaksiLayout.setVerticalGroup(
            transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(lbbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(0, 0, 0))
        );

        materialTabbed1.addTab("Transaksi", transaksi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void idtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtsActionPerformed
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(idts.getText());
            barcode.setI(11.0f);

            // Membuat ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Render barcode ke ByteArrayOutputStream
            barcode.renderBarcode(baos);

            // Mengambil data barcode dari ByteArrayOutputStream
            byte[] barcodeData = baos.toByteArray();

            // Membuat koneksi ke database
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();

            // Membuat pernyataan SQL untuk memasukkan data ke tabel
            String sql = "INSERT INTO barcodetransaksi (id_transaksi, barcode) VALUES (?, ?)";

            // Mempersiapkan pernyataan PreparedStatement
            java.sql.PreparedStatement statement = koneksi.con.prepareStatement(sql);
            statement.setString(1, idts.getText());
            statement.setBytes(2, barcodeData);

            // Menjalankan pernyataan SQL untuk menyimpan data ke database
            statement.executeUpdate();

            // Menutup koneksi dan pernyataan
            statement.close();
            koneksi.con.close();

            // Menampilkan barcode di JLabel
            ByteArrayInputStream bais = new ByteArrayInputStream(barcodeData);
            BufferedImage barcodeImage = ImageIO.read(bais);

            // Mengubah ukuran gambar barcode sesuai dengan ukuran JLabel
            int labelWidth = lbbarcode.getWidth();
            int labelHeight = lbbarcode.getHeight();
            Image scaledBarcodeImage = barcodeImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Membuat ImageIcon dari gambar barcode yang sudah diubah ukurannya
            ImageIcon icon = new ImageIcon(scaledBarcodeImage);
            lbbarcode.setIcon(icon);

            System.out.println("Barcode berhasil dikirim ke database dan ditampilkan di JLabel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_idtsActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        new tambahPelanggann(null, true,null).setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void comboBoxSuggestion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1MouseClicked
        tambahtransaksi tambahTransaksiPanel = new tambahtransaksi();
        JPanel parent = (JPanel) getParent();
        parent.remove(this);
        parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_comboBoxSuggestion1MouseClicked

    private void comboBoxSuggestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1ActionPerformed
        //         koneksii koneksi = new koneksii();
        //            koneksi.koneksimysql();
        //        try {
        //              res = koneksi.stm.executeQuery("CALL cari_pelanggan('"  + "');");
        //        } catch (Exception e) {
        //        }
    }//GEN-LAST:event_comboBoxSuggestion1ActionPerformed

    private void tbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        String totalHargaStr = tb.getText().replace(",", "").replace(".", ""); // Menghilangkan tanda baca dari string
        String bayarstr = b.getText().replace(",", "").replace(".", ""); // Menghilangkan tanda baca dari string
        double totalharga = Double.parseDouble(totalHargaStr);
        double bayar = Double.parseDouble(bayarstr);
        double kembalian = bayar - totalharga;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        k.setText(decimalFormat.format(kembalian));
    }//GEN-LAST:event_bActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        simpan();
        cetak();
         transaksi tambahTransaksiPanel = new transaksi();
    JPanel parent = (JPanel)getParent();
    parent.remove(this);
    parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
    parent.revalidate();
    parent.repaint();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) detailT.getModel();
int rowCount = model.getRowCount();
System.out.println(rowCount);

koneksii koneksi = new koneksii();
koneksi.koneksimysql();
java.sql.PreparedStatement pst = null;

String idtran = idts.getText();
String idpel = (String) comboBoxSuggestion1.getSelectedItem();
String[] parts = idpel.split("-");
String peidl = parts[0].trim();
System.out.println(peidl);
String tgl = tg.getText();
SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
String ket = textArea1.getText();
String totalh = tb.getText();
String totalHG = totalh.replaceAll("\\.", "");
String stats = (String) comboBoxSuggestion2.getSelectedItem();

try {
    Date date = inputFormat.parse(tgl);
    String tglFormatted = outputFormat.format(date);
    String ts = "UPDATE `transaksi` SET `ID_Pelanggan`=?, `Tanggal`=?, `Keterangan`=?, `Total_Harga`=?, `status`=? WHERE `ID_Transaksi`=?";
    pst = koneksi.con.prepareStatement(ts);
    pst.setString(1, peidl);
    pst.setString(2, tglFormatted);
    pst.setString(3, ket);
    pst.setString(4, totalHG);
    pst.setString(5, stats);
    pst.setString(6, idtran);

    pst.executeUpdate();
    System.out.println("Data transaksi berhasil diupdate");

    for (int i = 0; i < rowCount; i++) {
        String id = (String) model.getValueAt(i, 1);
        String kdo = (String) model.getValueAt(i, 2);
        String nama = (String) model.getValueAt(i, 3);
        String jumlah = (String) model.getValueAt(i, 4);
        String harga = (String) model.getValueAt(i, 5);
        String hargaF = harga.replaceAll("\\.", "");
        String total = (String) model.getValueAt(i, 6);
        String totalF = total.replaceAll("\\.", "");
        if (kdo.startsWith("BJ")) {
            // Check if data exists in detail_transaksibarangjadi
            String checkSql = "SELECT * FROM detail_transaksibarangjadi WHERE ID_Transaksi=? AND ID_Barangjadi=?";
            pst = koneksi.con.prepareStatement(checkSql);
            pst.setString(1, id);
            pst.setString(2, kdo);
            ResultSet rs = pst.executeQuery();
            boolean dataExists = rs.next();

            if (dataExists) {
                // Data barangjadi exists, perform update
                String updateSql = "UPDATE detail_transaksibarangjadi SET Nama_Barang=?, Jumlah=?, Harga=?, Total=? WHERE ID_Transaksi=? AND ID_Barangjadi=?";
                pst = koneksi.con.prepareStatement(updateSql);
                pst.setString(1, nama);
                pst.setString(2, jumlah);
                pst.setString(3, hargaF);
                pst.setString(4, totalF);
                pst.setString(5, id);
                pst.setString(6, kdo);
                pst.executeUpdate();
                System.out.println("Data barangjadi berhasil diupdate");
            } else {
                // Data barangjadi doesn't exist, perform insert
                String insertSql = "INSERT INTO detail_transaksibarangjadi (ID_Transaksi, ID_Barangjadi, Nama_Barang, Jumlah, Harga, Total) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(insertSql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                pst.executeUpdate();
                System.out.println("Data barangjadi berhasil ditambahkan");
            }
        } else if (kdo.startsWith("PB")) {
            // Check if data exists in detail_transaksipesanbarang
            String checkSql = "SELECT * FROM detail_transaksipesanbarang WHERE ID_Transaksi=? AND ID_Pesanbarang=?";
            pst = koneksi.con.prepareStatement(checkSql);
            pst.setString(1, id);
            pst.setString(2, kdo);
            ResultSet rs = pst.executeQuery();
            boolean dataExists = rs.next();

            if (dataExists) {
                // Data pesanbarang exists, perform update
                String updateSql = "UPDATE detail_transaksipesanbarang SET Nama_Barang=?, Jumlah=?, Harga=?, Total=? WHERE ID_Transaksi=? AND ID_Pesanbarang=?";
                pst = koneksi.con.prepareStatement(updateSql);
                pst.setString(1, nama);
                pst.setString(2, jumlah);
                pst.setString(3, hargaF);
                pst.setString(4, totalF);
                pst.setString(5, id);
                pst.setString(6, kdo);
                pst.executeUpdate();
                System.out.println("Data pesanbarang berhasil diupdate");
            } else {
                // Data pesanbarang doesn't exist, perform insert
                String insertSql = "INSERT INTO detail_transaksipesanbarang (ID_Transaksi, ID_Pesanbarang, Nama_Barang, Jumlah, Harga, Total) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(insertSql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                pst.executeUpdate();
                System.out.println("Data pesanbarang berhasil ditambahkan");
            }
        }
    }

    System.out.println("Data berhasil disimpan");
    
} catch (Exception e) {
    e.printStackTrace();
} finally {
    // Tutup PreparedStatement
    if (pst != null) {
        try {
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
         transaksi tambahTransaksiPanel = new transaksi();
    JPanel parent = (JPanel)getParent();
    parent.remove(this);
    parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
    parent.revalidate();
    parent.repaint();

    }//GEN-LAST:event_button3ActionPerformed

    private void kdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdActionPerformed
        try {
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();

            String search = kd.getText();

            // Panggil prosedur cari pada tabel barang_jadi
            String procedureCallBarangjadi = "CALL cari_barang_jadi('" + search + "');";
            res = koneksi.stm.executeQuery(procedureCallBarangjadi);
            if (res.next()) {
                String ID = res.getString("ID_Barangjadi");
                String nama = res.getString("Nama_Barang");
                String hargab = res.getString("Harga");
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                kd.setText(ID);
                nb.setText(nama);
                hg.setText(hargaFormatted);
            } else {
                // Jika tidak ditemukan di tabel barang_jadi, panggil prosedur cari pada tabel pesan_barang
                String procedureCallPesanbarang = "CALL cari_pesanbarang('" + search + "');";
                res = koneksi.stm.executeQuery(procedureCallPesanbarang);

                if (res.next()) {
                    String IDpesan = res.getString("ID_Pesanbarang");
                    String nama = res.getString("Nama_Barang");
                    String hargapesan = res.getString("Total_Harga");
                    double harga = Double.parseDouble(hargapesan);

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setGroupingSeparator('.');
                    symbols.setDecimalSeparator(',');

                    DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                    String hargaFormatted = decimalFormat.format(harga);
                    kd.setText(IDpesan);
                    nb.setText(nama);
                    hg.setText(hargaFormatted);
                    System.out.println("pesanbarang");
                } else {
                    System.out.println("Data tidak ditemukan");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_kdActionPerformed

    private void nbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbActionPerformed
        try {
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();

            String search = nb.getText();

            // Panggil prosedur cari pada tabel barang_jadi
            String procedureCallBarangjadi = "CALL cari_barang_jadi('" + search + "');";
            res = koneksi.stm.executeQuery(procedureCallBarangjadi);
            if (res.next()) {
                String ID = res.getString("ID_Barangjadi");
                String nama = res.getString("Nama_Barang");
                String hargab = res.getString("Harga");
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                kd.setText(ID);
                nb.setText(nama);
                hg.setText(hargaFormatted);
            } else {
                // Jika tidak ditemukan di tabel barang_jadi, panggil prosedur cari pada tabel pesan_barang
                String procedureCallPesanbarang = "CALL cari_pesanbarang('" + search + "');";
                res = koneksi.stm.executeQuery(procedureCallPesanbarang);

                if (res.next()) {
                    String IDpesan = res.getString("ID_Pesanbarang");
                    String nama = res.getString("Nama_Barang");
                    String hargapesan = res.getString("Total_Harga");
                    double harga = Double.parseDouble(hargapesan);

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setGroupingSeparator('.');
                    symbols.setDecimalSeparator(',');

                    DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                    String hargaFormatted = decimalFormat.format(harga);
                    kd.setText(IDpesan);
                    nb.setText(nama);
                    hg.setText(hargaFormatted);
                    System.out.println("pesanbarang");
                } else {
                    System.out.println("Data tidak ditemukan");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nbActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        DefaultTableModel model = (DefaultTableModel) detailT.getModel();
        int jumlah = Integer.parseInt(jm.getText());
        String harg = hg.getText().replaceAll("\\.", ""); // Menghapus titik desimal
        double harga = Double.parseDouble(harg);
        double total = jumlah * harga;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        Object[] row = {"Aksi", idts.getText(), kd.getText(), nb.getText(), jm.getText(), hg.getText(), decimalFormat.format(total)};
        model.addRow(row);
        TotalHarga();
    }//GEN-LAST:event_tambahActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
  cetak();
    }//GEN-LAST:event_button6ActionPerformed

    private void textArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textArea1MousePressed
        textAreaScroll1.setVisible(true);
    }//GEN-LAST:event_textArea1MousePressed

    private void jmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.SearchText b;
    private com.bengkel.swing.Button button1;
    private com.bengkel.swing.Button button2;
    private com.bengkel.swing.Button button3;
    private com.bengkel.swing.Button button6;
    private combo_suggestion.ComboBoxSuggestion comboBoxSuggestion1;
    private combo_suggestion.ComboBoxSuggestion comboBoxSuggestion2;
    private com.bengkel.swing.table.Table detailT;
    private com.bengkel.swing.SearchText hg;
    private com.bengkel.swing.SearchText idts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private com.bengkel.swing.SearchText jm;
    private com.bengkel.swing.SearchText k;
    private com.bengkel.swing.SearchText kd;
    private javax.swing.JLabel lbbarcode;
    private com.bengkel.swing.MaterialTabbed materialTabbed1;
    private com.bengkel.swing.SearchText nb;
    private com.bengkel.swing.Button tambah;
    private com.bengkel.swing.SearchText tb;
    private com.bengkel.swing.TextArea textArea1;
    private com.bengkel.swing.TextAreaScroll textAreaScroll1;
    private com.bengkel.swing.SearchText tg;
    private javax.swing.JPanel transaksi;
    // End of variables declaration//GEN-END:variables
}
