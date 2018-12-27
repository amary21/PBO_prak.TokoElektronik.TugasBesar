/*
 * Copyrigth (c) 2018, All right reserved
 * Written by tauf21, email : taufik.amary@gmail.com
 * Github : tauf21
 */

package Program;

import java.sql.SQLException;

/**
 *
 * @author Taufik Amaryansyah
 */
public class Barang extends TokoElektro {  

    public Barang() {
        super(0, null, null, null, null);
    }
    
    public Barang(String namaBarang, int jumBarang, int hargaBarang, int diskonBarang, int totalHarga, int idUserBrng) {
        super(namaBarang, jumBarang, hargaBarang, diskonBarang, totalHarga, idUserBrng);
    }    
    
    public void insertBarang() {
        try {
            String Query = "INSERT INTO `tbl_barang` "
                    + "(`nama_barang`, `jumlah_barang`, "
                    + "`harga_barang`, `diskon_barang`, "
                    + "`total_harga`, `id_user`) "
                    + "VALUES "
                    + "('"+getNamaBarang()+"', '"+getJumBarang()+"', "
                    + "'"+getHargaBarang()+"', '"+getDiskonBarang()+"',"
                    + " '"+getTotalHarga()+"', '"+getIdUserBrng()+"')";
            stat.executeUpdate(Query);
            
            System.out.println("berhasil input database tanel barang");
        } catch (SQLException e) {
            System.out.println("gagal input data ke database tabel barang");
        }
    }
    
    public void DeleteBarang(int getID){
        try {
                String Q_Hapus = "DELETE FROM `tbl_barang` WHERE `tbl_barang`.`id_barang` = '"+getID+"'";
                stat.executeUpdate(Q_Hapus);                

            } catch (SQLException e) {
                System.out.println("gagal Delete");
            }
    }
        
}
