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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
// import java.beans.Statement; <- ini bukan
/**
 *
 * @author Crack
 */
public class Koneksi {
    public Statement stat;
    public ResultSet res;
    public Connection con;
    public PreparedStatement pst;
    
    public Koneksi(){
                
        if (con==null){
            try {
                Class.forName("com.mysql.jdbc.Driver"); // driver yang diimport
                con = DriverManager.getConnection(
                   "jdbc:mysql://localhost/toko_elektro","root","");                
                stat = con.createStatement();
                
            } catch (Exception e) {                
                JOptionPane.showMessageDialog(null, 
                        "Koneksi Gagal : "+e);
            }
        }
    }
}
