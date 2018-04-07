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
public class GradreqCourses {
	private List<GradreqCourse> gradreqCourseList = new LinkedList<GradreqCourse>();
	private GradreqCourse aGradreqCourse;

	public GradreqCourses() {
		getGradreqcourseData(-1, -1);
	}

	public GradreqCourses(int majorID) {
		getGradreqcourseData(majorID, -1);
	}

	public GradreqCourses(int majorID, int minorID) {
		getGradreqcourseData(majorID, minorID);
	}
	
	public List<GradreqCourse> getGradreqCourseList () {
		return this.gradreqCourseList;
	}

	/**
	 * fetch all data from the table gradreqcourse and add to the list
	 */
	private void getGradreqcourseData(int majorID, int minorID) {
		ConnectDB connectdb = new ConnectDB();

		try {
			int _gradreqcourseID;
			int _majorID;
			int _minorID;
			int _courseID;
			String _courseName;
			String whereCause = "";

			if (majorID != -1 && minorID != -1) {
				whereCause = "WHERE tblgradreqcourse.majorID = '" + majorID + "' OR tblgradreqcourse.minorID = '" + minorID + "' ";
			} else if (majorID != -1) {
				whereCause = "WHERE tblgradreqcourse.majorID = '" + majorID + "' ";
			} else if (minorID != -1) {
				whereCause = "WHERE tblgradreqcourse.minorID = '" + minorID + "' ";
			}

			String queryString = "SELECT tblgradreqcourse.gradreqcourseID, tblgradreqcourse.majorID, tblgradreqcourse.minorID, tblgradreqcourse.courseID, tblcourse.courseName ";
			queryString += "FROM tblgradreqcourse INNER JOIN tblcourse ON tblgradreqcourse.courseID = tblcourse.courseID ";
			queryString += whereCause;
			queryString += "ORDER BY gradreqcourseID ASC";

			System.out.println(queryString);

			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);

			while (recordSet.next()) {
				_gradreqcourseID = recordSet.getInt("gradreqcourseID");
				_majorID = recordSet.getInt("majorID");
				_minorID = recordSet.getInt("minorID");
				_courseID = recordSet.getInt("courseID");
				_courseName = recordSet.getString("courseName");

				aGradreqCourse = new GradreqCourse(_gradreqcourseID, _majorID, _minorID, _courseID, _courseName);
				this.gradreqCourseList.add(aGradreqCourse);
			}
			statement.close();

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "gradreqcourseID | majorID   | minorID    | courseID  | courseName\n";
		for (int index = 0; index < this.gradreqCourseList.size(); index++) {
			aGradreqCourse = this.gradreqCourseList.get(index);
			returnString += aGradreqCourse.toString() + "\n";
		}

		return returnString;
	}
}
