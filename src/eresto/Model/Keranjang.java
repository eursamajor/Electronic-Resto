/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eresto.Model;

/**
 *
 * @author User
 */
// Kelas Keranjang memiliki constructor dan setter getter
public class Keranjang {
    int id;
    int akun_id;
    int menu_id;
    int jumlah;
    
    String menu_nama;
    String harga;

    public Keranjang(int id, int akun_id, int menu_id, int jumlah) {
        this.id = id;
        this.akun_id = akun_id;
        this.menu_id = menu_id;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public int getAkun_id() {
        return akun_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getMenu_nama() {
        return menu_nama;
    }

    public void setMenu_nama(String menu_nama) {
        this.menu_nama = menu_nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
    
    
    
}
