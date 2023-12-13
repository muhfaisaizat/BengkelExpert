
package com.bengkel.dialog;

import com.bengkel.swing.noticeboard.ModelNoticeBoard;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksii;

public class detaildasbor extends javax.swing.JDialog {
private String[] selectedRowData;

    public detaildasbor(java.awt.Frame parent, boolean modal,String[] selectedRowData) {
        super(parent, modal);
        initComponents();
          this.selectedRowData = selectedRowData;
        setBackground(new Color(0, 0, 0, 0));
        kembali.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table1.fixTable(jScrollPane1);
        table2.fixTable(jScrollPane2);
        table3.fixTable(jScrollPane3);
        noticeBoard1.setOpaque(false);
        tampiledit();
    }
    
    public void tampiledit(){
       DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator('.');
    symbols.setDecimalSeparator(',');

    koneksii koneksi = new koneksii();
    koneksi.koneksimysql();
    
    DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);

    String idT = selectedRowData[0];
    String barang = selectedRowData[2];

    DefaultTableModel tbModel = new DefaultTableModel();
    DefaultTableModel tb = new DefaultTableModel();
        DefaultTableModel tj = new DefaultTableModel();
    // Judul kolom
    tbModel.addColumn("Kode");
    tbModel.addColumn("Nama Barang");
    tbModel.addColumn("Jumlah");
    tbModel.addColumn("Harga");
    tbModel.addColumn("Total");
    table1.setModel(tbModel);
  
        
        tb.addColumn("ID Bahan");
        tb.addColumn("Nama Bahan");
        tb.addColumn("Jumlah");
        tb.addColumn("Harga");
        tb.addColumn("Total");
        table2.setModel(tb);

      
        tj.addColumn("ID jasa");
        tj.addColumn("Nama jasa");
        tj.addColumn("Harga");
        table3.setModel(tj);
        try {
            String sqlT = "SELECT * FROM transaksi WHERE ID_Transaksi= ? ";
            java.sql.PreparedStatement statementT = koneksi.con.prepareStatement(sqlT);
        statementT.setString(1, idT);
        ResultSet rsT = statementT.executeQuery();
        String idP = "";
String Tgl = "";
String ket = "";
String totalhar = "";
String Nam = "";

if (rsT.next()) {
    idP = rsT.getString("ID_Pelanggan");
    Tgl = rsT.getString("Tanggal");
    totalhar = rsT.getString("Total_Harga");
}
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    double TParsed = Double.parseDouble(totalhar);
    String TFormatted = decimalFormat.format(TParsed);
    id.setText(idT);
        
   
        Date date = inputFormat.parse(Tgl);
        String tglFormatted = outputFormat.format(date);
        tg.setText(tglFormatted);
        
         String sql = "SELECT * FROM pelanggan WHERE ID_Pelanggan='" + idP + "'";
        ResultSet rs = koneksi.stm.executeQuery(sql);
        if (rs.next()) {
                    Nam= rs.getString("Nama");
                }
        nama.setText(Nam);
        
         String ketrangan = "SELECT * FROM pesan_barang WHERE Nama_Barang='" + barang + "'";
        ResultSet ketera = koneksi.stm.executeQuery(ketrangan);
            if (ketera.next()) {
                ket = ketera.getString("Keterangan");            
            }
    noticeBoard1.addNoticeBoard(new ModelNoticeBoard(new Color(0,0,0), "", "", ket));
        
         String pesan = "SELECT * FROM `detail_transaksipesanbarang` WHERE detail_transaksipesanbarang.ID_Transaksi=? AND detail_transaksipesanbarang.Nama_Barang= ?";
        java.sql.PreparedStatement statementDetail = koneksi.con.prepareStatement(pesan);
        System.out.println("detail"+idT);
        statementDetail.setString(1, idT);
        statementDetail.setString(2, barang);
        ResultSet rsDetail = statementDetail.executeQuery();
        while (rsDetail.next()) {
        String idpesanbarang = rsDetail.getString("ID_Pesanbarang");
            String harga = rsDetail.getString("Harga");
                String total = rsDetail.getString("Total");
                double hargaParsed = Double.parseDouble(harga);
                double totalParsed = Double.parseDouble(total);
                String hargaFormatted = decimalFormat.format(hargaParsed);
                String totalFormatted = decimalFormat.format(totalParsed);
            
            tbModel.addRow(new Object[]{
                idpesanbarang,
                rsDetail.getString("Nama_Barang"),
                rsDetail.getString("Jumlah"),
                hargaFormatted,
                totalFormatted,
            });
        String bahan = ("Select * from detail_bahan WHERE ID_Pesanbarang='" + idpesanbarang + " ' ");
            String jasa = ("Select * from detail_jasa WHERE ID_Pesanbarang='" + idpesanbarang + " ' ");
              java.sql.ResultSet bhn = koneksi.con.prepareStatement(bahan).executeQuery();
            java.sql.ResultSet jsa = koneksi.con.prepareStatement(jasa).executeQuery();
            
            while (bhn.next()) {                
                String hargabahan = bhn.getString("Harga");
                String totalbahan = bhn.getString("Total");
                double hargaParse = Double.parseDouble(hargabahan);
                double totalParse = Double.parseDouble(totalbahan);
                String hargaFormatte = decimalFormat.format(hargaParse);
                String totalFormatte = decimalFormat.format(totalParse);

                tb.addRow(new Object[]{
                    bhn.getString("ID_Bahan"),
                    bhn.getString("Nama_Bahan"),
                    bhn.getString("Jumlah"),
                    hargaFormatte,
                    totalFormatte,
                });
            }
            
            while (jsa.next()) {                
                 String hargajasa = jsa.getString("Harga");
                double hargaParsedjasa = Double.parseDouble(hargajasa);
                String hargaFormattedjasa = decimalFormat.format(hargaParsedjasa);
                tj.addRow(new Object[]{
                    jsa.getString("ID_Jasa"),
                    jsa.getString("Nama_Jasa"),
                    hargaFormattedjasa,
                });
            }
        }
        
          
        
        } catch (Exception e) {
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new com.bengkel.swing.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound1 = new com.bengkel.swing.PanelRound();
        kembali = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.bengkel.swing.table.Table();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noticeBoard1 = new com.bengkel.swing.noticeboard.NoticeBoard();
        jLabel6 = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        tg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.bengkel.swing.table.Table();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table3 = new com.bengkel.swing.table.Table();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(0, 51, 204));
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Detail");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 30));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        kembali.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        kembali.setForeground(new java.awt.Color(0, 51, 255));
        kembali.setText("Kembali");
        kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kembaliMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel2.setText("Keterangan");

        jLabel4.setText("ID Transaksi");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kepada");

        noticeBoard1.setBackground(new java.awt.Color(0, 153, 255));
        noticeBoard1.setOpaque(false);

        jLabel6.setText("Tanggal");

        nama.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        tg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table2);

        jLabel7.setText("Detail bahan ");

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(table3);

        jLabel8.setText("Detail jasa ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addComponent(noticeBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noticeBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kembali)
                .addGap(34, 34, 34))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembali)
                .addGap(39, 39, 39))
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1020, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliMouseClicked
        setVisible(false);
    }//GEN-LAST:event_kembaliMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel kembali;
    private javax.swing.JLabel nama;
    private com.bengkel.swing.noticeboard.NoticeBoard noticeBoard1;
    private com.bengkel.swing.PanelRound panelRound1;
    private com.bengkel.swing.PanelRound panelRound2;
    private com.bengkel.swing.table.Table table1;
    private com.bengkel.swing.table.Table table2;
    private com.bengkel.swing.table.Table table3;
    private javax.swing.JLabel tg;
    // End of variables declaration//GEN-END:variables
}
