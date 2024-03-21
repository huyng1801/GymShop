package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KetNoi {
    private final String classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String url = "jdbc:sqlserver://HUYNGUYENDEV\\SQLEXPRESS:1433;databaseName=GymShop;encrypt=true;trustServerCertificate=true;";
    private final String user = "sa";
    private final String pass = "123456";

    private Connection connection;
    //Hàm này sẽ trả về một Connection để sử dụng truy vấn SQL
    public Connection getConnect() {
        Connection conn;
        try {
            Class.forName(classname);
            connection = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        }
        return connection;
    }
    public void getClose(){            

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
