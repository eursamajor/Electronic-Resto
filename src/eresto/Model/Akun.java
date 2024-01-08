package eresto.Model;

/**
 * Kelas `Akun` mewakili akun pengguna dan mengikuti pola singleton.
 * Ini memastikan bahwa hanya satu instance kelas ada sepanjang aplikasi.
 * Kelas ini berisi bidang privat untuk informasi pengguna seperti ID, email, kata sandi, dan nama.
 */
public class Akun {

    // Instance singleton dari kelas Akun
    private static Akun instance;

    // Bidang untuk informasi pengguna
    private int id;
    private String email;
    private String password;
    private String nama;

    // Konstruktor privat untuk membatasi instansiasi
    private Akun() {
    }

    /**
     * Mengembalikan instance singleton dari kelas Akun.
     * Jika instansinya tidak ada, maka membuat yang baru.
     *
     * @return Instance singleton dari kelas Akun.
     */
    public static Akun getInstance() {
        if (instance == null) {
            instance = new Akun();
        }
        return instance;
    }

    /**
     * Mendapatkan ID pengguna.
     *
     * @return ID pengguna.
     */
    public int getId() {
        return id;
    }

    /**
     * Menetapkan ID pengguna.
     *
     * @param id ID pengguna yang akan ditetapkan.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mendapatkan email pengguna.
     *
     * @return Email pengguna.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Menetapkan email pengguna.
     *
     * @param email Email pengguna yang akan ditetapkan.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mendapatkan kata sandi pengguna.
     *
     * @return Kata sandi pengguna.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Menetapkan kata sandi pengguna.
     *
     * @param password Kata sandi pengguna yang akan ditetapkan.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Mendapatkan nama pengguna.
     *
     * @return Nama pengguna.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Menetapkan nama pengguna.
     *
     * @param nama Nama pengguna yang akan ditetapkan.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
}