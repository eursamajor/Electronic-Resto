/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eresto.Model;

/**
 *
 * @author User
 */
// Kelas TransaksiDetail memiliki constructor dan getter
public class TransaksiDetail {
    int id;
    int transaksi_id;
    int menu_id;
    int jumlah;
    String harga;
    String menu_nama;

    public TransaksiDetail(int id, int transaksi_id, int menu_id, int jumlah, String harga, String menu_nama) {
        this.id = id;
        this.transaksi_id = transaksi_id;
        this.menu_id = menu_id;
        this.jumlah = jumlah;
        this.harga = harga;
        this.menu_nama = menu_nama;
    }

    public int getId() {
        return id;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public String getMenu_nama() {
        return menu_nama;
    }
    
}
