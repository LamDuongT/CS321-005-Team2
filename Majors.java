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
public class Majors {
	private List<Major> majorList = new LinkedList<Major>();
	private Major aMajor;
	
	/* Initialize the majorList with all major regardless catalogID
	 * */
	public Majors() {
		getMajorData(-1);
	}
	
	public Majors(int catalogID) {
		getMajorData(catalogID);
	}
	
	/* Get major by ID from the list of majors
	 * */
	public Major getMajorByID(int majorID) {
		aMajor = null;
		boolean isFound = false;
		
		for (int index = 0; index < this.majorList.size(); index ++) {
			aMajor = this.majorList.get(index);
			if (aMajor.getMajorID() == majorID) {
				isFound = true;
				break;
			}
		}
		if (!isFound)
			aMajor = new Major();
		
		return aMajor;
	}
	
	/* Get major by Name from the list of majors
	 * */
	public Major getMajorByName(String majorName) {
		aMajor = null;
		boolean isFound = false;
		
		for (int index = 0; index < this.majorList.size(); index ++) {
			aMajor = this.majorList.get(index);
			if (aMajor.getMajorName().toLowerCase().indexOf(majorName) != -1) {
				isFound = true;
				break;
			}
		}
		if (!isFound)
			aMajor = new Major();
		
		return aMajor;
	}
	
	
	/** fetch all data from the table Major and add to the list
	 *@param catalogID
	 *@return the list of majors belong to the catalogID if the catalogID is given (!= -1)
	 *otherwise, all majors will be returned
	 * */
	private void getMajorData (int catalogID) {
		ConnectDB connectdb = new ConnectDB();	
		
        try {
        	int _majorID;
        	String _majorName;
        	String _majorDesc;
        	int _catalogID;
        	
        	String queryString = "SELECT majorID, majorName, majorDesc, catalogID FROM `tblmajor` ";
        	
        	if (catalogID != -1) {
        		queryString += "WHERE catalogID = " + catalogID + " ";
        	}
        	
        	queryString += "ORDER BY catalogID ASC, majorID ASC";
        	
        	 System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            while (recordSet.next()) {
                _majorID = recordSet.getInt("majorID");
                _majorName = recordSet.getString("majorName");
                _majorDesc = recordSet.getString("majorDesc");
                _catalogID = recordSet.getInt("catalogID");
                
                aMajor = new Major(_majorID, _majorName, _majorDesc, _catalogID);
                this.majorList.add(aMajor);                
            }            
            statement.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        }
        finally {
        	connectdb.disconectDB();
		}		
	}
	
	public String toString () {
		String returnString = "";
		for (int index = 0; index < this.majorList.size(); index ++) {
			aMajor = this.majorList.get(index);
			returnString += aMajor.toString() + "\n";
		}
		
		return returnString;
	}
	
}
