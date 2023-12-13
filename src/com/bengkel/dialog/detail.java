
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





public class detail extends javax.swing.JDialog {
private String[] selectedRowData;

    public detail(java.awt.Frame parent, boolean modal,String[] selectedRowData) {
        super(parent, modal);
        initComponents();
         this.selectedRowData = selectedRowData;
        setBackground(new Color(0, 0, 0, 0));
        kembali.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table1.fixTable(jScrollPane1);
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

    DefaultTableModel tbModel = new DefaultTableModel();
    // Judul kolom
    tbModel.addColumn("Kode");
    tbModel.addColumn("Nama Barang");
    tbModel.addColumn("Jumlah");
    tbModel.addColumn("Harga");
    tbModel.addColumn("Total");
    table1.setModel(tbModel);
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
    ket = rsT.getString("Keterangan");
    totalhar = rsT.getString("Total_Harga");
}
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    double TParsed = Double.parseDouble(totalhar);
    String TFormatted = decimalFormat.format(TParsed);
    id.setText(idT);
    noticeBoard1.addNoticeBoard(new ModelNoticeBoard(new Color(0,0,0), "", "", ket));
        
    total.setText(TFormatted);
        Date date = inputFormat.parse(Tgl);
        String tglFormatted = outputFormat.format(date);
        tg.setText(tglFormatted);
        
         String sql = "SELECT * FROM pelanggan WHERE ID_Pelanggan='" + idP + "'";
        ResultSet rs = koneksi.stm.executeQuery(sql);
        if (rs.next()) {
                    Nam= rs.getString("Nama");
                }
        nama.setText(Nam);
        
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
            
            tbModel.addRow(new Object[]{
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
           
            tbModel.addRow(new Object[]{
                rsbarang.getString("ID_Barangjadi"),
                rsbarang.getString("Nama_Barang"),
                rsbarang.getString("Jumlah"),
                hargaFormatted,
                totalFormatted,
            });
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noticeBoard1 = new com.bengkel.swing.noticeboard.NoticeBoard();
        jLabel6 = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        tg = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel2.setText("Keterangan");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Total ");

        jLabel4.setText("ID Transaksi");

        jLabel5.setText("Kepada");

        noticeBoard1.setBackground(new java.awt.Color(0, 153, 255));
        noticeBoard1.setOpaque(false);

        jLabel6.setText("Tanggal");

        nama.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        tg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(noticeBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tg, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)))))
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noticeBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(kembali)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembali)
                .addContainerGap())
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 850, 520));

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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kembali;
    private javax.swing.JLabel nama;
    private com.bengkel.swing.noticeboard.NoticeBoard noticeBoard1;
    private com.bengkel.swing.PanelRound panelRound1;
    private com.bengkel.swing.PanelRound panelRound2;
    private com.bengkel.swing.table.Table table1;
    private javax.swing.JLabel tg;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
