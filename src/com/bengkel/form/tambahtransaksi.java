package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.barcodelib.barcode.BarcodeFactory;
import com.barcodelib.barcode.Linear;
import com.bengkel.dialog.cetak;
import com.bengkel.dialog.tambahPelanggann;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class tambahtransaksi extends javax.swing.JPanel {

    public static ResultSet res;

    public tambahtransaksi() {
        initComponents();
        setOpaque(false);
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
                if (detailTransaksi.isEditing()) {
                    detailTransaksi.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
                model.removeRow(row);
                TotalHarga();
            }
        };
        detailTransaksi.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailTransaksi.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));

        detailTransaksi.fixTable(jScrollPane1);
        init();
        init2();
        TotalHarga();
     
        tampil_combobox();
        aksipesanbarang();
        id_auto();
        idpb_auto();
    }
    
    public void id_auto() {
        try {
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
            String sql = "SELECT MAX(ID_Transaksi) AS lastID FROM transaksi";
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String lastID = rs.getString("lastID");
                if (lastID == null) {
                    lastID = "T00000";
                }

                int incrementValue = Integer.parseInt(lastID.substring(1)) + 1;
                String newID = "T" + String.format("%05d", incrementValue);

                idts.setText(newID);
            }

            rs.close();
            stmt.close();
            koneksi.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void idpb_auto() {
        try {
    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    String sql = "SELECT MAX(ID_Pesanbarang) AS lastID FROM pesan_barang";
    Statement stmt = koneksi.con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    if (rs.next()) {
        String lastID = rs.getString("lastID");
        if (lastID == null) {
            lastID = "PB0000";
        }

        int incrementValue;
        try {
            incrementValue = Integer.parseInt(lastID.substring(2)) + 1;
        } catch (NumberFormatException e) {
            incrementValue = 1;
        }
        String newID = "PB" + String.format("%04d", incrementValue);

        ipb.setText(newID);
    }

    rs.close();
    stmt.close();
    koneksi.con.close();
} catch (Exception e) {
    e.printStackTrace();
}

    }

    public void init() {
        idts.setHintText(" ");
        ipb.setHintText(" ");
        nbr.setHintText(" ");
        tg.setHintText(" ");
        tb.setHintText("0");
        th.setHintText("0");
        b.setHintText("0");
        k.setHintText("0");
        hg.setHintText("0");
        jm.setHintText("0");
        kd.setHintText("cari kode ");
        nb.setHintText("cari nama bahan");
         idjasa.setHintText("id jasa");
        namajasa.setHintText("nama jasa");
        hargajasa.setHintText("harga jasa");
        idbahan.setHintText("id bahan");
        namabahan.setHintText("nama bahan");
        hargabahan.setHintText("harga bahan");
        jumlahbahan.setHintText("jumlah bahan");

    }

    public void init2() {
        detailbahan.fixTable(jScrollPane2);
        detailjasa.fixTable(jScrollPane3);
    }

    public void aksipesanbarang(){
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
                TotalHargapesan();
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
        TotalHargapesan();
    }
};
       detailbahan.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailbahan.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
       detailjasa.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailjasa.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(eventJasa));
    }
    
    public void tampil_combobox(){
        
         koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
         try {
                         String sql = "SELECT * FROM pelanggan";
            ResultSet rs = koneksi.stm.executeQuery(sql);
            
            while(rs.next()){
                comboBoxSuggestion1.addItem(rs.getString("ID_Pelanggan")+" - "+rs.getString("Nama")+" - "+rs.getString("No_Telp"));
            }
            rs.last();
//            int jumlahdata = rs.getRow();
            rs.first();
         } catch (Exception e) {
         }
     }
    
    public void TotalHarga() {
        DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
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
    

    public void TotalHargapesan() {
        DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
        DefaultTableModel jasa= (DefaultTableModel) detailjasa.getModel();
        double total = 0;
        double totalj = 0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        for (int row = 0; row < model.getRowCount(); row++) {
            String rowTotalString = model.getValueAt(row, 5).toString();

            if (!rowTotalString.isEmpty()) {
                String rowTotalStringWithoutComma = rowTotalString.replace(".", ""); // Menghapus tanda koma dan titik desimal
                double rowTotal = Double.parseDouble(rowTotalStringWithoutComma);
                total += rowTotal;
            }
        }
        
        for (int row = 0; row < jasa.getRowCount(); row++) {
            String rowTotalString = jasa.getValueAt(row, 3).toString();

            if (!rowTotalString.isEmpty()) {
                String rowTotalStringWithoutComma = rowTotalString.replace(".", ""); // Menghapus tanda koma dan titik desimal
                double rowTotal = Double.parseDouble(rowTotalStringWithoutComma);
                totalj += rowTotal;
            }
        }
        double totalsm= total+totalj;
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        String formattedTotal = decimalFormat.format(totalsm);

        th.setText(formattedTotal);
        System.out.println(formattedTotal);

    }
    
    public void simpan(){
        DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
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
            String ts = "INSERT INTO `transaksi` (`ID_Transaksi`, `ID_Pelanggan`, `Tanggal`, `Keterangan`, `Total_Harga`, `status`) VALUES (?,?,?,?,?,?)";
            pst = koneksi.con.prepareStatement(ts);
            pst.setString(1, idtran);
            pst.setString(2, peidl);
            pst.setString(3, tglFormatted);
            pst.setString(4, ket);
            pst.setString(5, totalHG);
            pst.setString(6, stats);
            
              pst.executeUpdate();
    System.out.println("Data transaksi berhasil disimpan");

    for (int i = 0; i < rowCount; i++){
        String id = (String) model.getValueAt(i, 1);
        String kdo = (String) model.getValueAt(i, 2);
        String nama = (String) model.getValueAt(i, 3);
        String jumlah = (String) model.getValueAt(i, 4);
        String harga = (String) model.getValueAt(i, 5);
        String hargaF = harga.replaceAll("\\.", "");
        String total = (String) model.getValueAt(i, 6);
        String totalF = total.replaceAll("\\.", "");
            if (kdo.startsWith("BJ")) {
                // Data barangjadi
                String sql = "INSERT INTO `detail_transaksibarangjadi` (`ID_Transaksi`, `ID_Barangjadi`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                System.out.println("terkirim bj");
            } else if (kdo.startsWith("PB")) {
                // Data pesanbarang
                String sql = "INSERT INTO `detail_transaksipesanbarang` (`ID_Transaksi`, `ID_Pesanbarang`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                System.out.println("terkirim pb");
            }

            pst.executeUpdate();
            System.out.println("Data berhasil disimpan");
    }
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

        dateChooser1 = new com.datechooser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        materialTabbed1 = new com.bengkel.swing.MaterialTabbed();
        transaksi = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailTransaksi = new com.bengkel.swing.table.Table();
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
        button4 = new com.bengkel.swing.Button();
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
        pesanbarang = new javax.swing.JPanel();
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
        materialTabbed2 = new com.bengkel.swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        idbahan = new com.bengkel.swing.SearchText();
        namabahan = new com.bengkel.swing.SearchText();
        jumlahbahan = new com.bengkel.swing.SearchText();
        hargabahan = new com.bengkel.swing.SearchText();
        tbbahan = new com.bengkel.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailbahan = new com.bengkel.swing.table.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        idjasa = new com.bengkel.swing.SearchText();
        namajasa = new com.bengkel.swing.SearchText();
        hargajasa = new com.bengkel.swing.SearchText();
        btjasa = new com.bengkel.swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        detailjasa = new com.bengkel.swing.table.Table();

        dateChooser1.setForeground(new java.awt.Color(0, 153, 204));
        dateChooser1.setTextRefernce(tg);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("tambah transaksi");

        materialTabbed1.setBackground(new java.awt.Color(153, 153, 153));

        transaksi.setOpaque(false);

        jLabel2.setText("Pelanggan");

        detailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aksi", "ID_Transaksi", "Kode", "Nama Barang", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane1.setViewportView(detailTransaksi);

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

        button4.setBackground(new java.awt.Color(0, 153, 255));
        button4.setText("Reset");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
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

        javax.swing.GroupLayout transaksiLayout = new javax.swing.GroupLayout(transaksi);
        transaksi.setLayout(transaksiLayout);
        transaksiLayout.setHorizontalGroup(
            transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(transaksiLayout.createSequentialGroup()
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(idts, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(kd, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(nb, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(hg, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(211, 211, 211)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197)
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(transaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(223, 223, 223)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(lbbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19)
                                    .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(transaksiLayout.createSequentialGroup()
                                    .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiLayout.createSequentialGroup()
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))))
                .addContainerGap())
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
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(lbbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transaksiLayout.createSequentialGroup()
                        .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)))
                            .addGroup(transaksiLayout.createSequentialGroup()
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 37, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        materialTabbed1.addTab("Transaksi", transaksi);

        pesanbarang.setOpaque(false);

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
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        ipb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipbActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);

        jLabel16.setText("Masukan detail bahan yang dibutuhkan untuk membuat sebuah barang");

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

        detailbahan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aksi", "ID_Bahan", "Nama Bahan", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane2.setViewportView(detailbahan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namabahan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jumlahbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargabahan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbbahan, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 264, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namabahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargabahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        materialTabbed2.addTab("Detail Bahan", jPanel1);

        jPanel2.setOpaque(false);

        jLabel18.setText("Masukan jasa  yang dibutuhkan untuk membuat sebuah barang");

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

        detailjasa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aksi", "ID_Jasa", "Nama Jasa", "Harga"
            }
        ));
        jScrollPane3.setViewportView(detailjasa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 447, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 316, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        materialTabbed2.addTab("Detail Jasa", jPanel2);

        javax.swing.GroupLayout pesanbarangLayout = new javax.swing.GroupLayout(pesanbarang);
        pesanbarang.setLayout(pesanbarangLayout);
        pesanbarangLayout.setHorizontalGroup(
            pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesanbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pesanbarangLayout.createSequentialGroup()
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ipb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nbr, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pesanbarangLayout.createSequentialGroup()
                        .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(th, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pesanbarangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pesanbarangLayout.setVerticalGroup(
            pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesanbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ipb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(nbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(th, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(37, 37, 37)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pesanbarangLayout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(124, 124, 124)))
        );

        materialTabbed1.addTab("Pesan Barang", pesanbarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        new tambahPelanggann(null, true,null).setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

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

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        tambahtransaksi tambahTransaksiPanel = new tambahtransaksi();
        JPanel parent = (JPanel) getParent();
        parent.remove(this);
        parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_button4ActionPerformed

    private void tbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        cetak();
    }//GEN-LAST:event_button6ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        simpan();
        cetak();
    }//GEN-LAST:event_button2ActionPerformed

    private void textArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textArea1MousePressed
        textAreaScroll1.setVisible(true);
    }//GEN-LAST:event_textArea1MousePressed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
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

    private void textArea2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textArea2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textArea2MousePressed

    private void ipbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipbActionPerformed

    private void kdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdActionPerformed
        try {
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();

            String search = kd.getText();

            // Panggil prosedur cari pada tabel barang_jadi
            String procedureCallBarangjadi = "CALL cari_barang_jadi(' " + search + " ');";
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

    private void comboBoxSuggestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1ActionPerformed
//         koneksii koneksi = new koneksii();
//            koneksi.koneksimysql();
//        try {
//              res = koneksi.stm.executeQuery("CALL cari_pelanggan('"  + "');");
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_comboBoxSuggestion1ActionPerformed

    private void comboBoxSuggestion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1MouseClicked
//       tambahtransaksi tambahTransaksiPanel = new tambahtransaksi();
//        JPanel parent = (JPanel) getParent();
//        parent.remove(this);
//        parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
//        parent.revalidate();
//        parent.repaint();
    }//GEN-LAST:event_comboBoxSuggestion1MouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
    DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
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
            String ts = "INSERT INTO `transaksi` (`ID_Transaksi`, `ID_Pelanggan`, `Tanggal`, `Keterangan`, `Total_Harga`, `status`) VALUES (?,?,?,?,?,?)";
            pst = koneksi.con.prepareStatement(ts);
            pst.setString(1, idtran);
            pst.setString(2, peidl);
            pst.setString(3, tglFormatted);
            pst.setString(4, ket);
            pst.setString(5, totalHG);
            pst.setString(6, stats);
            
              pst.executeUpdate();
    System.out.println("Data transaksi berhasil disimpan");

    for (int i = 0; i < rowCount; i++){
        String id = (String) model.getValueAt(i, 1);
        String kdo = (String) model.getValueAt(i, 2);
        String nama = (String) model.getValueAt(i, 3);
        String jumlah = (String) model.getValueAt(i, 4);
        String harga = (String) model.getValueAt(i, 5);
        String hargaF = harga.replaceAll("\\.", "");
        String total = (String) model.getValueAt(i, 6);
        String totalF = total.replaceAll("\\.", "");
            if (kdo.startsWith("BJ")) {
                // Data barangjadi
                String sql = "INSERT INTO `detail_transaksibarangjadi` (`ID_Transaksi`, `ID_Barangjadi`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                System.out.println("terkirim bj");
            } else if (kdo.startsWith("PB")) {
                // Data pesanbarang
                String sql = "INSERT INTO `detail_transaksipesanbarang` (`ID_Transaksi`, `ID_Pesanbarang`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES (?,?,?,?,?,?)";
                pst = koneksi.con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, kdo);
                pst.setString(3, nama);
                pst.setString(4, jumlah);
                pst.setString(5, hargaF);
                pst.setString(6, totalF);
                System.out.println("terkirim pb");
            }

            pst.executeUpdate();
            System.out.println("Data berhasil disimpan");
    }
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
    

    }//GEN-LAST:event_button3ActionPerformed

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
                String hargab = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                idbahan.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
                namabahan.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
                hargabahan.setText(hargaFormatted); // Ganti "textField" dengan objek JTextField yang diinginkan
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_idbahanActionPerformed

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
                String hargab = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                idbahan.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
                namabahan.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
                hargabahan.setText(hargaFormatted); // Ganti "textField" dengan objek JTextField yang diinginkan
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_namabahanActionPerformed

    private void jumlahbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbahanActionPerformed
               DefaultTableModel model=(DefaultTableModel) detailbahan.getModel();
        int jumlah = Integer.parseInt(jumlahbahan.getText());
        double harga = Double.parseDouble(hargabahan.getText());

        double total = jumlah * harga;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        Object[] row ={"Aksi", idbahan.getText(),namabahan.getText(),jumlahbahan.getText(),hargabahan.getText(),decimalFormat.format(total),};
        model.addRow(row);
        TotalHargapesan();
    }//GEN-LAST:event_jumlahbahanActionPerformed

    private void tbbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbbahanActionPerformed
        DefaultTableModel model=(DefaultTableModel) detailbahan.getModel();
        int jumlah = Integer.parseInt(jumlahbahan.getText());
        double harga = Double.parseDouble(hargabahan.getText());

        double total = jumlah * harga;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        Object[] row ={"Aksi", idbahan.getText(),namabahan.getText(),jumlahbahan.getText(),hargabahan.getText(),decimalFormat.format(total),};
        model.addRow(row);
        TotalHargapesan();
    }//GEN-LAST:event_tbbahanActionPerformed

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
                String hargab = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                idjasa.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
                namajasa.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
                hargajasa.setText(hargaFormatted); // Ganti "textField" dengan objek JTextField yang diinginkan
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
                String hargab = res.getString("Harga"); // Ganti "nama_kolom" dengan nama kolom yang sesuai
                double harga = Double.parseDouble(hargab);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                String hargaFormatted = decimalFormat.format(harga);
                idjasa.setText(ID); // Ganti "textField" dengan objek JTextField yang diinginkan
                namajasa.setText(nama); // Ganti "textField" dengan objek JTextField yang diinginkan
                hargajasa.setText(hargaFormatted); // Ganti "textField" dengan objek JTextField yang diinginkan
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_namajasaActionPerformed

    private void btjasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btjasaActionPerformed
        DefaultTableModel model=(DefaultTableModel) detailjasa.getModel();
        Object[] row ={"Aksi", idjasa.getText(),namajasa.getText(),hargajasa.getText()};
        model.addRow(row);
        TotalHargapesan();
    }//GEN-LAST:event_btjasaActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
         DefaultTableModel bahan = (DefaultTableModel) detailbahan.getModel();
        DefaultTableModel jasa = (DefaultTableModel) detailjasa.getModel();
    int rowbahan = bahan.getRowCount();
    int rowjasa = jasa.getRowCount();
    System.out.println(rowbahan);
    System.out.println(rowjasa);
    
        

        
        koneksii koneksi = new koneksii();
        koneksi.koneksimysql();
        java.sql.PreparedStatement pst = null;
        
        String idpb = ipb.getText();
        String nmb = nbr.getText();      
        String ket = textArea2.getText();
        String totalh = th.getText();
        String totalHG = totalh.replaceAll("\\.", "");

        try {
            String ts = "INSERT INTO `pesan_barang` (`ID_Pesanbarang`, `Nama_Barang`, `Keterangan`, `Total_Harga`) VALUES (?,?,?,?)";
            pst = koneksi.con.prepareStatement(ts);
            pst.setString(1, idpb);
            pst.setString(2, nmb);
            pst.setString(3, ket);
            pst.setString(4, totalHG);
            
              pst.executeUpdate();
    System.out.println("Data pesanbarang berhasil disimpan");

    for (int i = 0; i < rowbahan; i++){
        String idb = (String) bahan.getValueAt(i, 1);
        String namab = (String) bahan.getValueAt(i, 2);
        String jumlahb = (String) bahan.getValueAt(i, 3);
        String hargab = (String) bahan.getValueAt(i, 4);
        String hargaF = hargab.replaceAll("\\.", "");
        String totalb = (String) bahan.getValueAt(i, 5);
        String totalF = totalb.replaceAll("\\.", "");
                String sql = "INSERT INTO `detail_bahan` (`ID_Pesanbarang`, `ID_Bahan`, `Nama_Bahan`, `Jumlah`, `Harga`, `Total`) VALUES (?,?,?,?,?,?)";
pst = koneksi.con.prepareStatement(sql);
pst.setString(1, idpb);
pst.setString(2, idb);
pst.setString(3, namab);
pst.setString(4, jumlahb);
pst.setString(5, hargaF);
pst.setString(6, totalF);
System.out.println("terkirim bahan");

pst.executeUpdate();
System.out.println("Data berhasil disimpan");


    }
    for (int i = 0; i < rowjasa; i++){
        String idj = (String) jasa.getValueAt(i, 1);
        String namaj = (String) jasa.getValueAt(i, 2);
        String hargaj = (String) jasa.getValueAt(i, 3);
        String hargaFj = hargaj.replaceAll("\\.", "");
                String sqlj = "INSERT INTO `detail_jasa` (`ID_Pesanbarang`, `ID_Jasa`, `Nama_Jasa`, `Harga`) VALUES (?,?,?,?)";
                pst = koneksi.con.prepareStatement(sqlj);
                pst.setString(1, idpb);
                pst.setString(2, idj);
                pst.setString(3, namaj);
                pst.setString(4, hargaFj);
                System.out.println("terkirim js");

            pst.executeUpdate();
            System.out.println("Data berhasil disimpan");
    }
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
    }//GEN-LAST:event_button7ActionPerformed

    private void jmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmActionPerformed
        DefaultTableModel model = (DefaultTableModel) detailTransaksi.getModel();
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
    }//GEN-LAST:event_jmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.SearchText b;
    private com.bengkel.swing.Button btjasa;
    private com.bengkel.swing.Button button1;
    private com.bengkel.swing.Button button2;
    private com.bengkel.swing.Button button3;
    private com.bengkel.swing.Button button4;
    private com.bengkel.swing.Button button5;
    private com.bengkel.swing.Button button6;
    private com.bengkel.swing.Button button7;
    private combo_suggestion.ComboBoxSuggestion comboBoxSuggestion1;
    private combo_suggestion.ComboBoxSuggestion comboBoxSuggestion2;
    private com.datechooser.DateChooser dateChooser1;
    private com.bengkel.swing.table.Table detailTransaksi;
    private com.bengkel.swing.table.Table detailbahan;
    private com.bengkel.swing.table.Table detailjasa;
    private com.bengkel.swing.SearchText hargabahan;
    private com.bengkel.swing.SearchText hargajasa;
    private com.bengkel.swing.SearchText hg;
    private com.bengkel.swing.SearchText idbahan;
    private com.bengkel.swing.SearchText idjasa;
    private com.bengkel.swing.SearchText idts;
    private com.bengkel.swing.SearchText ipb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.bengkel.swing.SearchText jm;
    private com.bengkel.swing.SearchText jumlahbahan;
    private com.bengkel.swing.SearchText k;
    private com.bengkel.swing.SearchText kd;
    private javax.swing.JLabel lbbarcode;
    private com.bengkel.swing.MaterialTabbed materialTabbed1;
    private com.bengkel.swing.MaterialTabbed materialTabbed2;
    private com.bengkel.swing.SearchText namabahan;
    private com.bengkel.swing.SearchText namajasa;
    private com.bengkel.swing.SearchText nb;
    private com.bengkel.swing.SearchText nbr;
    private javax.swing.JPanel pesanbarang;
    private com.bengkel.swing.Button tambah;
    private com.bengkel.swing.SearchText tb;
    private com.bengkel.swing.Button tbbahan;
    private com.bengkel.swing.TextArea textArea1;
    private com.bengkel.swing.TextArea textArea2;
    private com.bengkel.swing.TextAreaScroll textAreaScroll1;
    private com.bengkel.swing.TextAreaScroll textAreaScroll2;
    private com.bengkel.swing.SearchText tg;
    private com.bengkel.swing.SearchText th;
    private javax.swing.JPanel transaksi;
    // End of variables declaration//GEN-END:variables
}
