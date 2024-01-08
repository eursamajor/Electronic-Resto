/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eresto.Controller;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class GetConnection {
     java.sql.Connection con;
    /**
    * Mendapatkan koneksi ke database MySQL.
    * 
    * @return Koneksi ke database MySQL.
    */
    public java.sql.Connection getConnection() {
        try {
            // Mencoba untuk mendapatkan koneksi menggunakan URL, username, dan password database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3066/db_eresto", "root", "");
        } catch (SQLException se) {
            // Menangani pengecualian jika koneksi tidak dapat dibuka
            System.out.println("No Connection open");
        } catch (Exception ex) {
            // Menangani pengecualian umum selain SQLException
            System.out.println("Cound not open connection");
        }

        // Mengembalikan objek koneksi
        return con;
    }
}
