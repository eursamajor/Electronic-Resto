/**
 * Kontroller untuk entitas Keranjang yang berinteraksi dengan database.
 */
package eresto.Controller;

import eresto.Model.Akun;
import eresto.Model.Keranjang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class KeranjangController {
    
    /**
     * Melakukan proses checkout, membuat transaksi dan detail transaksi dari data keranjang.
     * @return True jika checkout berhasil, False jika terjadi kesalahan.
     */
    public boolean checkout() {
        java.sql.Connection con = new GetConnection().getConnection();
        String akun_id = String.valueOf(Akun.getInstance().getId());
       
        try {
            // Menyimpan transaksi
            String sql = "INSERT INTO transaksi(akun_id, total) VALUES(?, ?)";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, akun_id);
            pst.setInt(2, 0);
            pst.executeUpdate();
            
            // Mendapatkan ID transaksi yang baru saja dibuat
            ResultSet generatedKeys = pst.getGeneratedKeys();
            int TransaksiID = -1;
            if (generatedKeys.next()) {
                TransaksiID = generatedKeys.getInt(1);
            } else {
                return false;
            }
            
            // Mendapatkan data keranjang untuk diubah menjadi detail transaksi
            sql = "SELECT menu.harga as harga, keranjang.* FROM keranjang LEFT JOIN menu ON menu.id = keranjang.menu_id WHERE akun_id = '"+akun_id+"'";
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet hasil = stat.executeQuery(sql);
            
            double total = 0;
            while (hasil.next()) {
                // Menghitung total harga transaksi
                total += (hasil.getInt("harga") * hasil.getInt("jumlah"));
                
                // Menyimpan detail transaksi
                sql = "INSERT INTO transaksi_detail(transaksi_id, menu_id, jumlah, harga) VALUES(?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, TransaksiID);
                pst.setInt(2, hasil.getInt("menu_id"));
                pst.setInt(3, hasil.getInt("jumlah"));
                pst.setInt(4, hasil.getInt("harga"));
                pst.executeUpdate();
            }
            
            // Mengupdate total harga transaksi
            sql = "UPDATE transaksi SET total = ? WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setDouble(1, total);
            pst.setInt(2, TransaksiID);
            pst.executeUpdate();
            
            // Menghapus data keranjang setelah checkout
            sql = "DELETE FROM keranjang WHERE akun_id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, akun_id);
            pst.executeUpdate();
            
            pst.close();
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Menambahkan item ke dalam keranjang.
     * @param keranjang Objek Keranjang yang akan ditambahkan.
     * @return True jika penambahan berhasil, False jika terjadi kesalahan.
     */
    public boolean add(Keranjang keranjang) {
        java.sql.Connection con = new GetConnection().getConnection();
        String sql = "SELECT * FROM menu WHERE id = '"+keranjang.getMenu_id()+"'";
        
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet hasil = stat.executeQuery(sql);
            hasil.last();
            
            // Mengecek apakah stok mencukupi
            if (hasil.getInt("stok") - keranjang.getJumlah() < 0) {
                JOptionPane.showMessageDialog(null, "Insufficient Stock.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Mengecek apakah item sudah ada di dalam keranjang
            sql = "SELECT menu.stok as stok, keranjang.* FROM keranjang LEFT JOIN menu ON menu.id = keranjang.menu_id WHERE akun_id = '"+keranjang.getAkun_id()+"' AND menu_id = '"+keranjang.getMenu_id()+"'";
            hasil = stat.executeQuery(sql);
            hasil.last();
            if (hasil.getRow() > 0) {
                // Jika item sudah ada, update jumlahnya
                sql = "UPDATE keranjang SET jumlah = (jumlah+?) WHERE akun_id = ? AND menu_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, keranjang.getJumlah());
                pst.setInt(2, keranjang.getAkun_id());
                pst.setInt(3, keranjang.getMenu_id());
                pst.executeUpdate();
            } else {
                // Jika item belum ada, tambahkan ke keranjang
                sql = "INSERT INTO keranjang(akun_id, menu_id, jumlah) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, keranjang.getAkun_id());
                pst.setInt(2, keranjang.getMenu_id());
                pst.setInt(3, keranjang.getJumlah());
                pst.executeUpdate();
            }
            
            // Mengurangi stok item di menu setelah ditambahkan ke keranjang
            sql = "UPDATE menu SET stok = (stok-?) WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, keranjang.getJumlah());
            pst.setInt(2, keranjang.getMenu_id());
            pst.executeUpdate();
            
            pst.close();
            con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something Wrong."+e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Menghapus item dari keranjang berdasarkan ID.
     * @param id ID Keranjang yang akan dihapus.
     * @return True jika penghapusan berhasil, False jika terjadi kesalahan.
     */
    public boolean delete(int id) {
        java.sql.Connection con = new GetConnection().getConnection();
        String sql = "DELETE FROM keranjang WHERE id = ?";
       
        try {
            // Menghapus item berdasarkan ID
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            
            pst.close();
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Mengambil semua data keranjang untuk akun yang sedang login.
     * @return ArrayList berisi objek-objek Keranjang.
     */
    public ArrayList<Keranjang> getAllKeranjang() {
        ArrayList<Keranjang> keranjang = new ArrayList<>();
        
        java.sql.Connection con = new GetConnection().getConnection();
        try {
            String sql = "SELECT menu.nama as menu_nama, menu.harga, keranjang.* FROM keranjang LEFT JOIN menu ON menu.id = keranjang.menu_id WHERE akun_id = "+String.valueOf(Akun.getInstance().getId());
            
            Statement stat = con.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()) {
                // Membuat objek Keranjang dari data hasil query
                Keranjang keranjang_data = new Keranjang(
                        hasil.getInt("id"), 
                        hasil.getInt("akun_id"), 
                        hasil.getInt("menu_id"), 
                        hasil.getInt("jumlah")
                );
                keranjang_data.setMenu_nama(hasil.getString("menu_nama"));
                keranjang_data.setHarga(hasil.getString("harga"));
                keranjang.add(keranjang_data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something Wrong.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return keranjang;
    }
}
