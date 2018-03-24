
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
 * @author tnguyen
 */
public class testConnection {

    public static void main(String args[]) {
        connectDB connectdb = new connectDB();
        int studentID;
        String studentName;
        String studentEmail;
        
        try {
            Statement stmt = connectdb.theConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `tblstudent`");
            
            System.out.println("studentID|studentName|studentEmail");
            
            while (rs.next()) {
                studentID = rs.getInt("studentID");
                studentName = rs.getString("studentName");
                studentEmail = rs.getString("studentEmail");
                
                System.out.print(studentID + "|" + studentName + "|" + studentEmail + "|");
                
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
