
package koneksi;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class konek {
       public static Connection con;
    public static Statement stm;
    public static Runnable res;
    public Object konek;
    
    public void konekmysql(){
        try {
            String url ="jdbc:mysql://localhost:3306/bengkel";
            String user="root";
            String pass="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =(Connection) DriverManager.getConnection(url,user,pass);
            stm = (Statement) con.createStatement();
            JOptionPane.showMessageDialog(null, "berhasil koneksi");
        } catch (Exception e) {
        }
}
    public static void main(String[] args) {
        koneksii test = new koneksii();
        test.koneksimysql();
        System.exit(0);
}
}
