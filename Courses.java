import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Courses {
	private List<Course> courseList = new LinkedList<Course>();
	
	Course aCourse;
	
	public Courses() {
		
	}
	
	/* fetch all data from the table catalog and add to the list
	 * */
	private void getCatalogData () {
		ConnectDB connectdb = new ConnectDB();	
		
        try {
            int courseID;
            String courseName;
            String courseDesc;
            int creditHours;
            int catalogID;
            int majorID;
            int minorID;
        	
        	String queryString = "SELECT courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID FROM `tblcourse` ";        	
        	queryString += "ORDER BY courseID ASC";
        	
        	 System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            while (recordSet.next()) {
            	courseID = recordSet.getInt("courseID");
                courseName = recordSet.getString("courseName");
                courseDesc = recordSet.getString("courseDesc");
                
                aCatalog = new Catalog(catalogID, catalogName, catalogDesc);
                this.catalogList.add(aCatalog);                
            }            
            statement.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        }
        finally {
        	connectdb.disconectDB();
		}		
	}
}
