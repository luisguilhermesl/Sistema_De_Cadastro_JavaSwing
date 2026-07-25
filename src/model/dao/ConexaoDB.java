package model.dao;

import java.sql.*;


/**
 *
 * @author Luís
 */
public class ConexaoDB {
    
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/java_swing_modc2";
        String user = "root";
        String password = "root";
        
        return DriverManager.getConnection(url,user,password);
        
    }
}