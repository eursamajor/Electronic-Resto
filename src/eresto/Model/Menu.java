/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eresto.Model;

/**
 *
 * @author User
 */
// Kelas Menu memiliki constructor dan getter
public class Menu {
    int id;
    int tipe;
    String nama;
    String harga;
    int stok;

    public Menu(int id, int tipe, String nama, String harga, int stok) {
        this.id = id;
        this.tipe = tipe;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public int getTipe() {
        return tipe;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }
    
    
}
