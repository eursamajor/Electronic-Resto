/**
 * Kontroller untuk entitas Menu yang berinteraksi dengan database.
 */
package eresto.Controller;

import eresto.Model.Menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MenuController {
    
    /**
     * Mengambil semua data menu dari database.
     * @return ArrayList berisi objek-objek Menu.
     */
    public ArrayList<Menu> getAllMenu(){
        ArrayList<Menu> menu = new ArrayList<>();
        java.sql.Connection con = new GetConnection().getConnection();
        try {
            // Query SQL untuk mengambil semua data menu
            String sql = "SELECT * FROM menu";
            
            // Membuat statement SQL
            Statement stat = con.createStatement();
            
            // Menjalankan query dan mendapatkan hasilnya
            ResultSet hasil = stat.executeQuery(sql);
            
            // Membaca hasil query dan membuat objek Menu dari setiap baris hasil
            while(hasil.next()) {
                menu.add(
                        new Menu(
                                hasil.getInt("id"), 
                                hasil.getInt("tipe"), 
                                hasil.getString("nama"), 
                                hasil.getString("harga"), 
                                hasil.getInt("stok")
                        )
                );
            }
        } catch (SQLException e) {
            // Menangani kesalahan dan menampilkan pesan kesalahan menggunakan JOptionPane
            JOptionPane.showMessageDialog(null, "Something Wrong.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                // Menutup koneksi setelah selesai menggunakan
                con.close();
            } catch (SQLException ex) {
                // Menangani kesalahan penutupan koneksi (jika terjadi)
                JOptionPane.showMessageDialog(null, "Error Closing Connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return menu;
    }
}
