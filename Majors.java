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
 */
public class Majors {
	private LinkedList<Major> = new LinkedList<Major>; // list of majors within a plan
	private Major aMajor;

	/*
	 * Initialize the majorList with all major regardless catalogID
	 */
	public Majors() {
		getMajorData(-1);
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Majors(int catalogID) {
		getMajorData(catalogID);
	}
	
	/**
	 * @author Lam Duong
	 *Retrieve a major object within majorList using position within list
	 */
	public Major getMajorByNum(int listPosition) {
		return this.majorList[listPosition];
	}

	/**
	 * Get major by ID from the list of majors
	 * 
	 * @param majorID
	 *            the majorID
	 * @return Major Object *
	 */
	public Major getMajorByID(int majorID) {
		aMajor = null;
		boolean isFound = false;

		for (int index = 0; index < this.majorList[index]; index++) {
			aMajor = this.majorList.get(index);
			if (aMajor.getMajorID() == majorID) {
				isFound = true;
				break;
			}
		}
		return aMajor;
	}

	/**
	 * Get major by Name from the list of majors
	 * 
	 * @param majorID
	 *            the majorID
	 * @return Major Object *
	 */
	public Major getMajorByName(String majorName) {
		aMajor = null;
		boolean isFound = false;

		for (int index = 0; index < this.majorList.size(); index++) {
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

	/**
	 * fetch all data from the table Major and add to the list
	 * 
	 * @param catalogID
	 * @return the list of majors belong to the catalogID if the catalogID is given
	 *         (!= -1) otherwise, all majors will be returned
	 */
	private void getMajorData(int catalogID) {
		ConnectDB connectdb = new ConnectDB();

		try {
			// local variable
			int _majorID;
			String _majorName;
			String _majorDesc;
			int _catalogID;
			// query string
			String queryString = "SELECT majorID, majorName, majorDesc, catalogID FROM `tblmajor` ";
			// if the catalogID is not -1, then we get the list of major for that catalogID
			if (catalogID != -1) {
				queryString += "WHERE catalogID = " + catalogID + " ";
			}
			queryString += "ORDER BY catalogID ASC, majorID ASC";

			System.out.println(queryString);

			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);
			// loop through each record in the data table
			while (recordSet.next()) {
				// assign value for local variable accordingly to the query field
				_majorID = recordSet.getInt("majorID");
				_majorName = recordSet.getString("majorName");
				_majorDesc = recordSet.getString("majorDesc");
				_catalogID = recordSet.getInt("catalogID");
				// instantiate a Major object
				aMajor = new Major(_majorID, _majorName, _majorDesc, _catalogID);
				this.majorList.add(aMajor);
			}
			statement.close();

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			// close the connection
			// NOTE: the close connection method need to be called in finally block to
			// ensure the connection is closed
			connectdb.disconectDB();
		}
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "";
		for (int index = 0; index < this.majorList.size(); index++) {
			aMajor = this.majorList.get(index);
			returnString += aMajor.toString() + "\n";
		}

		return returnString;
	}

}
