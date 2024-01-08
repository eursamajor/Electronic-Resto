/**
 * Kontroller untuk entitas Transaksi yang berinteraksi dengan database.
 */
package eresto.Controller;

import eresto.Model.Akun;
import eresto.Model.Transaksi;
import eresto.Model.TransaksiDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TransaksiController {
    
    /**
     * Mengambil semua data transaksi berdasarkan akun_id dari database.
     * @return ArrayList berisi objek-objek Transaksi.
     */
    public ArrayList<Transaksi> getAllTransaksi() {
        ArrayList<Transaksi> transaksi = new ArrayList<>();
        
        // Mendapatkan koneksi ke database
        java.sql.Connection con = new GetConnection().getConnection();
        try {
            // Query SQL untuk mengambil semua data transaksi berdasarkan akun_id
            String sql = "SELECT * FROM transaksi WHERE akun_id = " + String.valueOf(Akun.getInstance().getId());
            
            // Membuat statement SQL
            Statement stat = con.createStatement();
            
            // Menjalankan query dan mendapatkan hasilnya
            ResultSet hasil = stat.executeQuery(sql);
            
            // Membaca hasil query dan membuat objek Transaksi dari setiap baris hasil
            while (hasil.next()) {
                Transaksi transaksi_data = new Transaksi(
                        hasil.getInt("id"), 
                        hasil.getInt("akun_id"),
                        hasil.getString("total")
                );
                
                // Query SQL untuk mengambil data transaksi_detail berdasarkan transaksi_id
                sql = "SELECT menu.nama as menu_nama, transaksi_detail.* FROM transaksi_detail LEFT JOIN menu ON menu.id = transaksi_detail.menu_id "
                        + "WHERE transaksi_id = " + hasil.getString("id");
                
                // Membuat statement SQL untuk query transaksi_detail
                stat = con.createStatement();
                
                // Menjalankan query transaksi_detail dan mendapatkan hasilnya
                ResultSet hasil_detail = stat.executeQuery(sql);
                
                // Membaca hasil query transaksi_detail dan membuat objek TransaksiDetail
                while (hasil_detail.next()) {
                    transaksi_data.addListOfTransaksi_detail(new TransaksiDetail(
                            hasil_detail.getInt("id"),
                            hasil_detail.getInt("transaksi_id"),
                            hasil_detail.getInt("menu_id"),
                            hasil_detail.getInt("jumlah"),
                            hasil_detail.getString("harga"),
                            hasil_detail.getString("menu_nama")
                    ));
                }
                
                // Menambahkan objek Transaksi ke ArrayList transaksi
                transaksi.add(transaksi_data);
            }
        } catch (SQLException e) {
            // Menangani kesalahan dan menampilkan pesan kesalahan menggunakan JOptionPane
            JOptionPane.showMessageDialog(null, "Something Wrong." + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                // Menutup koneksi setelah selesai menggunakan
                con.close();
            } catch (SQLException ex) {
                // Menangani kesalahan penutupan koneksi (jika terjadi)
                JOptionPane.showMessageDialog(null, "Error Closing Connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return transaksi;
    }
}
