package servlet;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
    // table  
    public static final String TABLE_PASSWORD = "table_user_password";  
    public static final String TABLE_USERINFO = "table_user_info"; 
    public static final String TABLE_BANKCODE = "table_user_bank_verifycode";
  
    // connect to MySql database  
    public static Connection getConnect() {  
        String url = "jdbc:mysql://localhost:3306/first_mysql_test"; // ���ݿ��Url  
        Connection connecter = null;  
        try {  
            Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��  
            connecter = (Connection) DriverManager.getConnection(url, "root", "root");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            System.out.println("SQLException: " + e.getMessage());  
            System.out.println("SQLState: " + e.getSQLState());  
            System.out.println("VendorError: " + e.getErrorCode());  
        }  
        return connecter;  
    }  
}