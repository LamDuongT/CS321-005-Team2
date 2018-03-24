
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tnguyen
 */
public class connectDB {
    private String SERVER = "jdbc:mysql://localhost:3306/";
    private String DATABASE = "collegesp";
    private String USERNAME = "team2";
    private String PASSWORD = "cs321-005";
    
    Connection theConnection;
        
    public connectDB() {
        System.out.println("Connecting database...");

        try {
            String URL = this.SERVER + this.DATABASE;            
//            theConnection = (Connection) DriverManager.getConnection(URL, this.USERNAME, this.PASSWORD);            
            
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(this.USERNAME);
            dataSource.setPassword(this.PASSWORD);
            dataSource.setServerName(URL);
            
            theConnection = (Connection)dataSource.getConnection();
            
            System.out.println("Successful connected to database [" + this.DATABASE + "]");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }      
    }
    
    public void disconectDB() {
        try {
            this.theConnection.close();
            this.theConnection = null;
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
