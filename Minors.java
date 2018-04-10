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
 * @author Lam Duong
 * @author Bobby Tagliaferri
 *
 */
public class Minors {
	private List<Minor> minorList = new LinkedList<Minor>();
	private Minor aMinor;
	
	/** Initialize the minorList with all Minor regardless catalogID
	 * */
	public Minors() {
		getMinorData(-1);
	}
	
	public Minors(int catalogID) {
		getMinorData(catalogID);
	}
	public List<Minor> getMinorList(){
            return minorList;
        }
	/** Get Minor by ID from the list of minors
	 * */
	public Minor getMinorByID(int minorID) {
		aMinor = null;
		boolean isFound = false;
		
		for (int index = 0; index < this.minorList.size(); index ++) {
			aMinor = this.minorList.get(index);
			if (aMinor.getMinorID() == minorID) {
				isFound = true;
				break;
			}
		}
		if (!isFound)
			aMinor = new Minor();
		
		return aMinor;
	}
	
	/**
	 * Get Minor by the position within the Minors List
	 * @param minorName
	 * @return
	 */
	
	/* Get Minor by Name from the list of minors
	 * */
	public Minor getMinorByName(String minorName) {
		aMinor = null;		
		for (int index = 0; index < this.minorList.size(); index ++) {
			aMinor = this.minorList.get(index);
			if (aMinor.getMinorName().toLowerCase().contains(minorName.toLowerCase())) {
                            return aMinor;
			}
		}
		
		return new Minor();
	}
	
	
	/** fetch all data from the table Minor and add to the list
	 *@param catalogID
	 *@return the list of minors belong to the catalogID if the catalogID is given (!= -1)
	 *otherwise, all minors will be returned
	 * */
	private void getMinorData (int catalogID) {
		ConnectDB connectdb = new ConnectDB();	
		
        try {
        	int _minorID;
        	String _minorName;
        	String _minorDesc;
        	int _catalogID;
        	
        	String queryString = "SELECT minorID, minorName, minorDesc, catalogID FROM `tblminor` ";
        	
        	if (catalogID != -1) {
        		queryString += "WHERE catalogID = " + catalogID + " ";
        	}
        	
        	queryString += "ORDER BY catalogID ASC, minorID ASC";
        	
        	 System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            while (recordSet.next()) {
                _minorID = recordSet.getInt("minorID");
                _minorName = recordSet.getString("minorName");
                _minorDesc = recordSet.getString("minorDesc");
                _catalogID = recordSet.getInt("catalogID");
                
                aMinor = new Minor(_minorID, _minorName, _minorDesc, _catalogID);
                this.minorList.add(aMinor);                
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
		for (int index = 0; index < this.minorList.size(); index ++) {
			aMinor = this.minorList.get(index);
			returnString += aMinor.toString() + "\n";
		}
		
		return returnString;
	}
	
}
