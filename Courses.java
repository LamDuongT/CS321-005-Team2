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
	// the list of course
	private List<Course> courseList = new LinkedList<Course>();

	private Course aCourse;

	/**
	 * Initialize the list of course from database
	 */
	public Courses() {
		getCourseData();
	}

	/**
	 * fetch all data from the table catalog and add to the list
	 */
	private void getCourseData() {
		ConnectDB connectdb = new ConnectDB();

		try {
			// local variable for course object
			int courseID;
			String courseName;
			String courseDesc;
			int creditHours;
			int catalogID;
			int majorID;
			int minorID;

			// the query string
			String queryString = "SELECT courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID FROM `tblcourse` ";
			queryString += "ORDER BY courseID ASC";

			// System.out.println(queryString);

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
				catalogID = recordSet.getInt("catalogID");
				majorID = recordSet.getInt("majorID");
				minorID = recordSet.getInt("minorID");

				// instantiate aCourse object with all local variable
				// the prereqList will be instantiated in the constructor of Course class
				aCourse = new Course(courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID);
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
		String returnString = "";
		// loop through the list and print out all elements
		for (int index = 0; index < this.courseList.size(); index++) {
			aCourse = this.courseList.get(index);
			returnString += aCourse.toString() + "\n";
		}

		return returnString;
	}
}
