import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huan Nguyen
 *
 */
public class Prereqs {
	private List<Prereq> prereqList = new LinkedList<Prereq>();
	private Prereq aPrereq;

	/**
	 * Initialize the list of prereq course for the courseID
	 */
	public Prereqs(int courseID) {
		getPrereqData(courseID);
	}

	/**
	 * fetch all data from the table Prereq and add to the list
	 * 
	 * @param courseID
	 * @return the list of majors belong to the catalogID if the catalogID is given
	 *         (!= -1) otherwise, all majors will be returned
	 */
	private void getPrereqData(int courseID) {
		ConnectDB connectdb = new ConnectDB();

		try {
			int _prereqID;
			int _courseID;
			int _prereqCourseID;
			String _courseName;
			String _prereqCourseName;

			String queryString = "SELECT c1.courseID, c1.courseName, p.prereqID, p.prereqCourseID, c2.courseName as prereqCourseName"; 
			queryString += "FROM tblcourse c1 inner join tblprereq p ON c1.courseID = p.courseID inner join tblcourse c2 on c2.courseID = p.prereqCourseID";
			if (courseID != -1) {
				queryString += "WHERE tblcourse.courseID = " + courseID;
			}

			System.out.println(queryString);

			Statement statement = connectdb.theConnection.createStatement();
			ResultSet recordSet = statement.executeQuery(queryString);

			while (recordSet.next()) {
				_prereqID = recordSet.getInt("prereqID");
				_courseID = recordSet.getInt("courseID");
				_prereqCourseID = recordSet.getInt("prereqCourseID");
				_courseName = recordSet.getString("courseName");
				_prereqCourseName = recordSet.getString("prereqCourseName");

				aPrereq = new Prereq(_prereqID, _courseID, _prereqCourseID, _courseName, _prereqCourseName);
				this.prereqList.add(aPrereq);
			}
			statement.close();

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
}
