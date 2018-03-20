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
 * @author Huan Nguyen
 */
public class ConnectDB {
	// these variables can be modified
    private String SERVER = "jdbc:mysql://localhost:3306/";
    private String DATABASE = "collegespdb";
    private String USERNAME = "team2";
    private String PASSWORD = "cs321-005";
    
    // Do not modify
    Connection theConnection;
    private String HOST_NAME = this.SERVER + this.DATABASE;
    
    public ConnectDB() {
    	doConnection();
    }
    
    public void reconnect() {
    	doConnection();
    }
    
    private void doConnection () {
//        System.out.println("Connecting database:: [" + HOST_NAME + "]");
        this.HOST_NAME += "?autoReconnect=true&useSSL=false";
        try {                         
            theConnection = (Connection) DriverManager.getConnection(HOST_NAME, this.USERNAME, this.PASSWORD);            

            // other way of connecting database using ODBC
//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUser(this.USERNAME);
//            dataSource.setPassword(this.PASSWORD);
//            dataSource.setServerName(URL);
//            theConnection = (Connection)dataSource.getConnection();
            
            System.out.println("==>Successful connected to database [" + this.DATABASE + "]");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    
    
    public void disconectDB() {
        try {
            this.theConnection.close();
            this.theConnection = null;
            
            System.out.println("==>Successful disconnected to database [" + this.DATABASE + "]");
            System.out.println("------------------------------------------------------------");
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
