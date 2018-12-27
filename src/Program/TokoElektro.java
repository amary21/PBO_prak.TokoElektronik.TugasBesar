/*
 * Copyrigth (c) 2018, All right reserved
 * Written by tauf21, email : taufik.amary@gmail.com
 * Github : tauf21
 */

package Program;

/**
 *
 * @author Taufik Amaryansyah
 */
public class TokoElektro extends Koneksi{
    private int idUserBrng;
    private int id_Barang;
    private String namaBarang;
    private int jumBarang;
    private int hargaBarang;
    private int diskonBarang;
    private int totalHarga;
    
    private int idUser;
    private String namaUser;
    private String jkUser;
    private String teleponUser;
    private String alamatUser;
    
    public TokoElektro(int idUser, String namaUser, String jkUser, String teleponUser, String alamatUser) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.jkUser = jkUser;
        this.teleponUser = teleponUser;
        this.alamatUser = alamatUser;
    }

    public TokoElektro(String namaBarang, int jumBarang, int hargaBarang, int diskonBarang, int totalHarga, int idUserBrng ) {
        this.idUserBrng = idUserBrng;
        this.namaBarang = namaBarang;
        this.jumBarang = jumBarang;
        this.hargaBarang = hargaBarang;
        this.diskonBarang = diskonBarang;
        this.totalHarga = totalHarga;
    }
    
    public int getIdUserBrng() {
        return idUserBrng;
    }

    public void setIdUserBrng(int idUserBrng) {
        this.idUserBrng = idUserBrng;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumBarang() {
        return jumBarang;
    }

    public void setJumBarang(int jumBarang) {
        this.jumBarang = jumBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getDiskonBarang() {
        return diskonBarang;
    }

    public void setDiskonBarang(int diskonBarang) {
        this.diskonBarang = diskonBarang;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getJkUser() {
        return jkUser;
    }

    public void setJkUser(String jkUser) {
        this.jkUser = jkUser;
    }

    public String getTeleponUser() {
        return teleponUser;
    }

    public void setTeleponUser(String teleponUser) {
        this.teleponUser = teleponUser;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
    }
    
    
}
