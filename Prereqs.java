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
public class Prereqs {
	private List<Prereq> prereqList = new LinkedList<Prereq>();
	private Prereq aPrereq;
	
	/** Initialize the list of prereq course for the courseID
	 * */
	public Prereqs(int courseID) {
		getPrereqData(courseID);
	}
	
	/** fetch all data from the table Prereq and add to the list
	 *@param courseID
	 *@return the list of majors belong to the catalogID if the catalogID is given (!= -1)
	 *otherwise, all majors will be returned
	 * */
	private void getPrereqData (int courseID) {
		ConnectDB connectdb = new ConnectDB();	
		
        try {
        	int _prereqID;
        	int _courseID;
        	int _prereqCourseID;
        	
        	String queryString = "SELECT prereqID, courseID, prereqCourseID FROM `tblprereq` ";        	
       		queryString += "WHERE courseID = " + courseID + " ";
        	queryString += "ORDER BY prereqCourseID ASC";
        	
        	System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            while (recordSet.next()) {
            	_prereqID = recordSet.getInt("_prereqID");
            	_courseID = recordSet.getInt("maj_prereqIDorName");
            	_prereqCourseID = recordSet.getInt("_prereqCourseID");
                
                aPrereq = new Prereq(_prereqID, _courseID, _prereqCourseID);
                this.prereqList.add(aPrereq);                
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
