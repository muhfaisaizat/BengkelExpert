/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bengkel.form;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksii;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;

public class editpesanbarang extends javax.swing.JPanel {

    private String[] selectedRowData;
    ResultSet res;
    Statement stm;

    public editpesanbarang(String[] selectedRowData) {
        initComponents();
        this.selectedRowData = selectedRowData;
        setOpaque(false);
        ipb.setHintText(" ");
        nbr.setHintText(" ");
        th.setHintText("0");
        idjasa.setHintText("id jasa");
        namajasa.setHintText("nama jasa");
        hargajasa.setHintText("harga jasa");
        idbahan.setHintText("id bahan");
        namabahan.setHintText("nama bahan");
        hargabahan.setHintText("harga bahan");
        jumlahbahan.setHintText("jumlah bahan");
        detailbahan.fixTable(jScrollPane2);
        detailjasa.fixTable(jScrollPane1);
        aksitabel();
        tampiledit();
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
                if (detailbahan.isEditing()) {
                    detailbahan.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
                String idbahan = (String) model.getValueAt(row, 1);

                // Hapus data dari SQL
                try {
                    koneksii koneksi = new koneksii();
                    koneksi.koneksimysql();
                    java.sql.Connection con = koneksi.con;// Panggil metode koneksimysql() untuk menginisialisasi koneksi
                    java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM detail_bahan WHERE detail_bahan.ID_Pesanbarang = '" + ipb.getText() + "' AND detail_bahan.ID_Bahan = '" + idbahan + "' ");
                    pst.executeUpdate();
                    System.out.println("Data berhasil dihapus dari SQL");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                model.removeRow(row);
                TotalHarga();
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
                String idjasa = (String) model.getValueAt(row, 1);

                // Hapus data dari SQL
                try {
                    koneksii koneksi = new koneksii();
                    koneksi.koneksimysql();
                    java.sql.Connection con = koneksi.con;// Panggil metode koneksimysql() untuk menginisialisasi koneksi
                    java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM detail_jasa WHERE detail_jasa.ID_Pesanbarang = '" + ipb.getText() + "' AND detail_jasa.ID_Jasa = '" + idjasa + "' ");
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

        DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);

        String idpesanbarang = selectedRowData[0];
        String namabarang = selectedRowData[1];
        String keterangan = selectedRowData[2];
        String totalhar = selectedRowData[3];

//        double TParsed = Double.parseDouble(totalhar);
//        String TFormatted = decimalFormat.format(TParsed);

        ipb.setText(idpesanbarang);
        nbr.setText(namabarang);
        textArea2.setText(keterangan);
        th.setText(totalhar);

        DefaultTableModel tb = new DefaultTableModel();
        DefaultTableModel tj = new DefaultTableModel();
        //judul kolom
        tb.addColumn("Aksi");
        tb.addColumn("ID Bahan");
        tb.addColumn("Nama Bahan");
        tb.addColumn("Jumlah");
        tb.addColumn("Harga");
        tb.addColumn("Total");
        detailbahan.setModel(tb);

        tj.addColumn("Aksi");
        tj.addColumn("Nama Bahan");
        tj.addColumn("Jumlah");
        tj.addColumn("Harga");
        detailjasa.setModel(tj);

        try {
            String sql = ("Select * from detail_bahan WHERE ID_Pesanbarang='" + idpesanbarang + " ' ");
            String sql2 = ("Select * from detail_jasa WHERE ID_Pesanbarang='" + idpesanbarang + " ' ");
            koneksii koneksi = new koneksii();
            koneksi.koneksimysql();
            java.sql.Connection con = koneksi.con;
            java.sql.ResultSet res = con.prepareStatement(sql).executeQuery();
            java.sql.ResultSet res2 = con.prepareStatement(sql2).executeQuery();
            detailbahan.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
            detailbahan.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
            detailjasa.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
            detailjasa.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(eventJasa));

            while (res.next()) {
                String harga = res.getString("Harga");
                String total = res.getString("Total");
                double hargaParsed = Double.parseDouble(harga);
                double totalParsed = Double.parseDouble(total);
                String hargaFormatted = decimalFormat.format(hargaParsed);
                String totalFormatted = decimalFormat.format(totalParsed);

                tb.addRow(new Object[]{
                    null,
                    res.getString("ID_Bahan"),
                    res.getString("Nama_Bahan"),
                    res.getString("Jumlah"),
                    hargaFormatted,
                    totalFormatted,});
            }

            while (res2.next()) {
                String harga = res2.getString("Harga");
                double hargaParsed = Double.parseDouble(harga);
                String hargaFormatted = decimalFormat.format(hargaParsed);
                tj.addRow(new Object[]{
                    null,
                    res2.getString("ID_Jasa"),
                    res2.getString("Nama_Jasa"),
                    hargaFormatted,});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aksitabel() {
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
                if (detailbahan.isEditing()) {
                    detailbahan.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
                model.removeRow(row);
                TotalHarga();
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
                TotalHarga();
            }
        };
        detailbahan.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailbahan.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
        detailjasa.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        detailjasa.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(eventJasa));
    }

    public void TotalHarga() {
        DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
        DefaultTableModel jasa = (DefaultTableModel) detailjasa.getModel();
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
        double totalsm = total + totalj;
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        String formattedTotal = decimalFormat.format(totalsm);

        th.setText(formattedTotal);
        System.out.println(formattedTotal);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pesanbarang = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        th = new com.bengkel.swing.SearchText();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textAreaScroll2 = new com.bengkel.swing.TextAreaScroll();
        textArea2 = new com.bengkel.swing.TextArea();
        button7 = new com.bengkel.swing.Button();
        ipb = new com.bengkel.swing.SearchText();
        nbr = new com.bengkel.swing.SearchText();
        materialTabbed1 = new com.bengkel.swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idbahan = new com.bengkel.swing.SearchText();
        namabahan = new com.bengkel.swing.SearchText();
        jumlahbahan = new com.bengkel.swing.SearchText();
        hargabahan = new com.bengkel.swing.SearchText();
        tbbahan = new com.bengkel.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailbahan = new com.bengkel.swing.table.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        idjasa = new com.bengkel.swing.SearchText();
        namajasa = new com.bengkel.swing.SearchText();
        hargajasa = new com.bengkel.swing.SearchText();
        btjasa = new com.bengkel.swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailjasa = new com.bengkel.swing.table.Table();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Edit Pesan Barang");

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

        jLabel2.setText("Masukan detail bahan yang dibutuhkan untuk membuat sebuah barang");

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
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 288, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        materialTabbed1.addTab("Detail Bahan", jPanel1);

        jPanel2.setOpaque(false);

        jLabel3.setText("Masukan jasa  yang dibutuhkan untuk membuat sebuah barang");

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
        jScrollPane1.setViewportView(detailjasa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 471, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btjasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 408, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        materialTabbed1.addTab("Detail Jasa", jPanel2);

        javax.swing.GroupLayout pesanbarangLayout = new javax.swing.GroupLayout(pesanbarang);
        pesanbarang.setLayout(pesanbarangLayout);
        pesanbarangLayout.setHorizontalGroup(
            pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesanbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pesanbarangLayout.createSequentialGroup()
                        .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesanbarangLayout.createSequentialGroup()
                        .addGroup(pesanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(th, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(37, 37, 37)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pesanbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesanbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textArea2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textArea2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textArea2MousePressed

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
            String ts = "UPDATE `pesan_barang` SET `Nama_Barang` = ?, `Keterangan` = ?, `Total_Harga` = ? WHERE `ID_Pesanbarang` = ?";
            pst = koneksi.con.prepareStatement(ts);
            pst.setString(1, nmb);
            pst.setString(2, ket);
            pst.setString(3, totalHG);
            pst.setString(4, idpb);

            pst.executeUpdate();
            System.out.println("Data pesanbarang berhasil diupdate");

            for (int i = 0; i < rowbahan; i++) {
                String idb = (String) bahan.getValueAt(i, 1);
                String namab = (String) bahan.getValueAt(i, 2);
                String jumlahb = (String) bahan.getValueAt(i, 3);
                String hargab = (String) bahan.getValueAt(i, 4);
                String hargaF = hargab.replaceAll("\\.", "");
                String totalb = (String) bahan.getValueAt(i, 5);
                String totalF = totalb.replaceAll("\\.", "");

                // Periksa apakah data sudah ada di tabel detail_bahan
                String checkSql = "SELECT COUNT(*) FROM `detail_bahan` WHERE `ID_Pesanbarang` = ? AND `ID_Bahan` = ?";
                pst = koneksi.con.prepareStatement(checkSql);
                pst.setString(1, idpb);
                pst.setString(2, idb);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    // Jika data sudah ada, lakukan UPDATE
                    String sql = "UPDATE `detail_bahan` SET `Nama_Bahan` = ?, `Jumlah` = ?, `Harga` = ?, `Total` = ? WHERE `ID_Pesanbarang` = ? AND `ID_Bahan` = ?";
                    pst = koneksi.con.prepareStatement(sql);
                    pst.setString(1, namab);
                    pst.setString(2, jumlahb);
                    pst.setString(3, hargaF);
                    pst.setString(4, totalF);
                    pst.setString(5, idpb);
                    pst.setString(6, idb);
                    System.out.println("terkirim bahan");

                    pst.executeUpdate();
                    System.out.println("Data berhasil diupdate");
                } else {
                    // Jika data belum ada, lakukan INSERT INTO
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
            }
            for (int i = 0; i < rowjasa; i++) {
                String idj = (String) jasa.getValueAt(i, 1);
                String namaj = (String) jasa.getValueAt(i, 2);
                String hargaj = (String) jasa.getValueAt(i, 3);
                String hargaFj = hargaj.replaceAll("\\.", "");
                
                String checkSql = "SELECT COUNT(*) FROM `detail_jasa` WHERE `ID_Pesanbarang` = ? AND `ID_Jasa` = ?";
                pst = koneksi.con.prepareStatement(checkSql);
                pst.setString(1, idpb);
                pst.setString(2, idj);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                
                if (count > 0) {
                String sqlj = "UPDATE `detail_jasa` SET `Nama_Jasa` = ?, `Harga` = ? WHERE `ID_Pesanbarang` = ? AND `ID_Jasa` = ?";
                pst = koneksi.con.prepareStatement(sqlj);
                pst.setString(1, namaj);
                pst.setString(2, hargaFj);
                pst.setString(3, idpb);
                pst.setString(4, idj);
                System.out.println("terkirim js");

                pst.executeUpdate();
                System.out.println("Data berhasil diupdate");
                    
                } else {
                     String sqlj = "INSERT INTO `detail_jasa` (`ID_Pesanbarang`, `ID_Jasa`, `Nama_Jasa`, `Harga`) VALUES (?,?,?,?)";
                pst = koneksi.con.prepareStatement(sqlj);
                pst.setString(1, idpb);
                pst.setString(2, idj);
                pst.setString(3, namaj);
                pst.setString(4, hargaFj);
                System.out.println("terkirim js");

                pst.executeUpdate();
                System.out.println("Data berhasil diupdate");
                }
                
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
                 pesanBarang tambahTransaksiPanel = new pesanBarang();
    JPanel parent = (JPanel)getParent();
    parent.remove(this);
    parent.add(tambahTransaksiPanel, BorderLayout.CENTER);
    parent.revalidate();
    parent.repaint();
    }//GEN-LAST:event_button7ActionPerformed

    private void ipbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipbActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahbahanActionPerformed

    private void tbbahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbbahanActionPerformed
        DefaultTableModel model = (DefaultTableModel) detailbahan.getModel();
        int jumlah = Integer.parseInt(jumlahbahan.getText());
        double harga = Double.parseDouble(hargabahan.getText());

        double total = jumlah * harga;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        Object[] row = {"Aksi", idbahan.getText(), namabahan.getText(), jumlahbahan.getText(), hargabahan.getText(), decimalFormat.format(total),};
        model.addRow(row);
        TotalHarga();
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
        DefaultTableModel model = (DefaultTableModel) detailjasa.getModel();
        Object[] row = {"Aksi", idjasa.getText(), namajasa.getText(), hargajasa.getText()};
        model.addRow(row);
        TotalHarga();
    }//GEN-LAST:event_btjasaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bengkel.swing.Button btjasa;
    private com.bengkel.swing.Button button7;
    private com.bengkel.swing.table.Table detailbahan;
    private com.bengkel.swing.table.Table detailjasa;
    private com.bengkel.swing.SearchText hargabahan;
    private com.bengkel.swing.SearchText hargajasa;
    private com.bengkel.swing.SearchText idbahan;
    private com.bengkel.swing.SearchText idjasa;
    private com.bengkel.swing.SearchText ipb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.bengkel.swing.SearchText jumlahbahan;
    private com.bengkel.swing.MaterialTabbed materialTabbed1;
    private com.bengkel.swing.SearchText namabahan;
    private com.bengkel.swing.SearchText namajasa;
    private com.bengkel.swing.SearchText nbr;
    private javax.swing.JPanel pesanbarang;
    private com.bengkel.swing.Button tbbahan;
    private com.bengkel.swing.TextArea textArea2;
    private com.bengkel.swing.TextAreaScroll textAreaScroll2;
    private com.bengkel.swing.SearchText th;
    // End of variables declaration//GEN-END:variables
}
