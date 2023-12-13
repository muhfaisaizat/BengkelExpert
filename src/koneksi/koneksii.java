
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;





public class koneksii {
   public static Connection con;
    public static Statement stm;
    public static Runnable res;
    public Object koneksii;
            
    
    
public void koneksimysql(){
    try{
        String url="jdbc:mysql://localhost:3306/bengkel (2)";
        String user="root";
        String pass="";
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection(url, user, pass);
        stm = (Statement) con.createStatement();
//        JOptionPane.showMessageDialog(null, "berhasil koneksi");
System.out.println("terhubung");
    }catch (Exception e){
        e.printStackTrace();
//        System.err.println("koneksi Gagal"+e.getMessage());
    }
}
    public static void main(String[] args) {
        koneksii test = new koneksii();
        test.koneksimysql();
        System.exit(0);
}
}
