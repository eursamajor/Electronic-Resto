/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package eresto.View;

import eresto.Controller.MenuController;
import eresto.Controller.TransaksiController;
import eresto.Model.Menu;
import eresto.Model.Transaksi;
import eresto.Model.TransaksiDetail;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class PnlTransaksi extends javax.swing.JPanel {

    /**
     * Creates new form PnlTransaksi
     */
    ArrayList<Transaksi> transaksi;
    public PnlTransaksi() {
        initComponents();
        getTransaksi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTransaksiDetail = new javax.swing.JTable();
        btnCheckout = new javax.swing.JButton();

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTransaksiMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblTransaksi);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("List Transaksi :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Detail Transaksi :");

        tblTransaksiDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTransaksiDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTransaksiDetailMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblTransaksiDetail);

        btnCheckout.setBackground(new java.awt.Color(51, 51, 51));
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setText("Keranjang");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMousePressed
        // TODO add your handling code here:
        // Mengambil indeks baris terpilih dari tabel transaksi
        int selectedRowIndex = tblTransaksi.getSelectedRow();

        // Mengonversi indeks baris ke indeks model
        int modelRowIndex = tblTransaksi.convertRowIndexToModel(selectedRowIndex);

        // Mengambil daftar transaksi detail dari objek transaksi pada indeks model tersebut
        ArrayList<TransaksiDetail> transaksiDetail = transaksi.get(modelRowIndex).getTransaksi_detail();

        // Mendefinisikan kolom untuk tabel transaksi detail
        Object[] rows = {"Menu", "Jumlah", "Harga", "Total"};

        // Membuat model tabel untuk menampilkan transaksi detail
        DefaultTableModel model = new DefaultTableModel(null, rows){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Mengatur model tabel untuk tabel tblTransaksiDetail
        tblTransaksiDetail.setModel(model);
        tblTransaksiDetail.setRowHeight(20);

        // Mengisi tabel dengan data transaksi detail
        for (TransaksiDetail item : transaksiDetail) {
            // Menambahkan baris dengan data transaksi detail
            String[] data = {item.getMenu_nama(), String.valueOf(item.getJumlah()), String.valueOf(item.getHarga()), String.valueOf(item.getJumlah() * Integer.parseInt(item.getHarga()))};
            model.addRow(data);
        }
    }//GEN-LAST:event_tblTransaksiMousePressed

    private void tblTransaksiDetailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiDetailMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTransaksiDetailMousePressed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        // Mendapatkan kontainer induk (dialog) dari PnlTransaksi
        Container container = SwingUtilities.getAncestorOfClass(JDialog.class, PnlTransaksi.this);
        
        // Menutup dialog jika ditemukan
        if (container instanceof JDialog) {
            JDialog dialog = (JDialog) container;
            dialog.dispose();
        }
        
        // Membuka Option Dialog dengan isi PnlKeranjang
        JOptionPane.showOptionDialog(
            null,
            new PnlKeranjang(),
            "Keranjang",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{}, // Custom Button Option Agar Kosong
            null
        );
    }//GEN-LAST:event_btnCheckoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTable tblTransaksiDetail;
    // End of variables declaration//GEN-END:variables

    private void getTransaksi() {
        // Mengambil data transaksi dari TransaksiController
        transaksi = new TransaksiController().getAllTransaksi();

        // Mendefinisikan kolom untuk tabel transaksi
        Object[] rows = {"ID", "Total"};

        // Membuat model tabel untuk menampilkan data transaksi
        DefaultTableModel model = new DefaultTableModel(null, rows){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Mengatur model tabel untuk tabel tblTransaksi
        tblTransaksi.setModel(model);
        tblTransaksi.setRowHeight(20);

        // Mengisi tabel dengan data transaksi
        for (Transaksi item : transaksi) {
            // Menambahkan baris dengan data transaksi
            String[] data = {String.valueOf(item.getId()), item.getTotal()};
            model.addRow(data);
        }
    }
}
