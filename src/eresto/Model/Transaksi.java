/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eresto.Model;

import java.util.ArrayList;

/**
 *
 * @author User
 */
// Kelas Transaksi memiliki constructor dan setter getter
public class Transaksi {
    int id;
    int akun_id;
    String total;
    ArrayList<TransaksiDetail> transaksi_detail = new ArrayList<TransaksiDetail>();

    public Transaksi(int id, int akun_id, String total) {
        this.id = id;
        this.akun_id = akun_id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getAkun_id() {
        return akun_id;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<TransaksiDetail> getTransaksi_detail() {
        return transaksi_detail;
    }

    public void addListOfTransaksi_detail(TransaksiDetail transaksi_detail) {
        this.transaksi_detail.add(transaksi_detail);
    }
    
}
