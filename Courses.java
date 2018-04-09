import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huan Nguyen
 * @author Lam Duong
 */
public class Courses {
	// the list of Course objects
	private List<Course> courseList = new LinkedList<>();
	private Course aCourse;

	/**
	 * Initialize the list of Course objects from database
	 */
	public Courses() {
		getCourseData(-1, -1, ' ');
	}

	/**
	 * Initialize the list of Course for a catalog year from database
	 */
	public Courses(int catalogID) {
		getCourseData(catalogID, -1, ' ');
	}

	/**
	 * Initialize the list of Course for a catalog year from database
	 * 
	 * @param catalogID
	 *            the catalog year
	 * @param majorOrMinorID
	 *            the major or minor ID
	 * @param majorOrMinor
	 *            based on this parameter, the list of course will be fetched
	 *            accordingly to majorOrMinorID
	 *            value: 'm' for major and 'n' for minor
	 */
	public Courses(int catalogID, int majorOrMinorID, char majorOrMinor) {
		getCourseData(catalogID, majorOrMinorID, majorOrMinor);
	}

	public List getCoursesList() {
		return this.courseList;
	}

	/**
	 * fetch all data from the table course and add to the list
	 * 
	 * @return
	 */
	private void getCourseData(int catalogID, int majorOrMinorID, char majorOrMinor) {
		ConnectDB connectdb = new ConnectDB();

		try {
			// local variable for course object
			int courseID;
			String courseName;
			String courseDesc;
			int creditHours;
			int _catalogID;
			String _majorID;
			String _minorID;
			
			String whereCondition = "";

			// if the catalogID is different from 1, meaning that we need to fetch data for courses by the catalogID
			// also, if majorOrMinorID != -1, meaning that we need to constrain the major or minor as well
			if (catalogID != -1 && majorOrMinorID != -1 && majorOrMinor != ' ') {
				if (majorOrMinor == 'm') {
					whereCondition = "WHERE catalogID = '" + catalogID + "' AND majorID like '%" + majorOrMinorID + "%' ";
				}
				else if (majorOrMinor == 'n') {
					whereCondition = "WHERE catalogID = '" + catalogID + "' AND minorID = '" + majorOrMinorID + "' ";
				}
			}
			else if (catalogID == -1 && majorOrMinorID == -1 && majorOrMinor == ' ') {
				whereCondition = "";
			}
			// otherwise, we will get courses list for the catalogID
			else {
				whereCondition = "WHERE catalogID = '" + catalogID + "'";
			}
			
			// the query string
			String queryString = "SELECT courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID FROM `tblcourse` ";
			queryString += whereCondition;
			queryString += "ORDER BY courseID ASC";

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
				courseID = recordSet.getInt("courseID");
				courseName = recordSet.getString("courseName");
				courseDesc = recordSet.getString("courseDesc");
				creditHours = recordSet.getInt("creditHours");
				_catalogID = recordSet.getInt("catalogID");
				_majorID = recordSet.getString("majorID");
				_minorID = recordSet.getString("minorID");

				// instantiate aCourse object with all local variable
				// the prereqList will be instantiated in the constructor of Course class
				aCourse = new Course(courseID, courseName, courseDesc, creditHours, _catalogID, _majorID, _minorID);
				// add the aCourse to the courseList for later uses
				this.courseList.add(aCourse);
			}
			// close the sql statement
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
	 * Override the toString method for testing purpose
	 */
	public String toString() {
		String returnString = "courseID | courseName     | creditHours | catalogID  | majorID   | minorID   | Prereq List              | courseDesc\n";
		// loop through the list and print out all elements
		for (int index = 0; index < this.courseList.size(); index++) {
			aCourse = this.courseList.get(index);
			returnString += aCourse.toString() + "\n";
		}
		return returnString;
	}
}
