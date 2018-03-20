
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Huan Nguyen
 */
public class TestConnection {

    public static void main(String args[]) {
        ConnectDB connectdb = new ConnectDB();
        int studentID;
        String studentName;
        String studentEmail;
        
        try {
        	String queryString = "SELECT * FROM `tblprofile`";
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            System.out.println("studentID|studentName|studentEmail");
            
            while (recordSet.next()) {
                studentID = recordSet.getInt("studentID");
                studentName = recordSet.getString("studentName");
                studentEmail = recordSet.getString("studentEmail");
                
                System.out.println(studentID + "|" + studentName + "|" + studentEmail + "|");
                
            }
            // always close the statement and database
            statement.close();            
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        finally {
        	connectdb.disconectDB();
		}
        
    }
}
