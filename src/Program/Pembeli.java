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
public class Pembeli extends TokoElektro{

    public Pembeli() {
        super(0, null, null, null, null);
    }
        
    public Pembeli(int idUser, String namaUser, String jkUser, String teleponUser, String alamatUser) {
        super(idUser, namaUser, jkUser, teleponUser, alamatUser);
    }

    public void insertPembeli() {
        try {
            String Query = "INSERT INTO `tbl_pembeli` "
                    + "(`id_user`,`nama_user`, `jk_user`, `telepon_user`, `alamat_user`) "
                    + "VALUES "
                    + "('"+getIdUser()+"','"+getNamaUser()+"',"
                    + "'"+getJkUser()+"', '"+getTeleponUser()+"',"
                    + " '"+getAlamatUser()+"');";
            stat.executeUpdate(Query);
            
            System.out.println("berhasil input database tabel pembeli");
        } catch (SQLException e) {
            System.out.println("gagal input data ke database tabel pembeli");
        }
    }
    
    public void updatePembeli(){
        try {
            String QueryEdit = "UPDATE `tbl_pembeli` "
                    + "SET "
                    + "`nama_user` = '"+getNamaUser()+"', "
                    + "`jk_user` = '"+getJkUser()+"', "
                    + "`telepon_user` = '"+getTeleponUser()+"', "
                    + "`alamat_user` = '"+getAlamatUser()+"' "
                    + "WHERE "
                    + "`tbl_pembeli`.`id_user` = '"+getIdUser()+"'";
            stat.executeUpdate(QueryEdit);
            System.out.println("data berhasil di edit");
        } catch (SQLException e) {
            System.out.println("data gagal di edit");
        }
    }
            
}
