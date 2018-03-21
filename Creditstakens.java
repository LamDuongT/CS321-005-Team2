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
public class Creditstakens {
	List<Creditstaken> creditstakenList = new LinkedList<Creditstaken>();
	private Creditstaken aCreditstaken;
	
	/**
	 * Initialize the list of creditstaken courses for the for studentID
	 */
	public Creditstakens(int studentID) {
		getCreditstakenData(studentID, -1);
	}

	/**
	 * Initialize the list of creditstaken courses for the for studentID for
	 * semesterID
	 */
	public Creditstakens(int studentID, int semesterID) {
		getCreditstakenData(studentID, semesterID);
	}

	/**
	 * get creditstaken for studentID
	 * 
	 * @param studentID
	 *            the student ID
	 * @param semesterID
	 *            if given the list will hold creditstaken for studentID for
	 *            semesterID
	 *            otherwise, the list will hold creditstaken for studentID
	 * 
	 */
	private void getCreditstakenData(int studentID, int semesterID) {
		ConnectDB connectdb = new ConnectDB();

		try {
			int _creditstakenID;
			int _studentID;
			int _courseID;
			String _courseName;
			int _semesterID;
			boolean _isChangable;

			String queryString = "SELECT tblcreditstaken.*, tblcourse.courseName "; 
			queryString += "FROM tblcreditstaken INNER JOIN tblcourse on tblcreditstaken.courseID = tblcourse.courseID ";
			queryString += "WHERE studentID = " + studentID + " ";
			
			if (semesterID != -1) {
				queryString += "AND semesterID = " + semesterID;
			}

			System.out.println(queryString);

			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);

			while (recordSet.next()) {
				_creditstakenID = recordSet.getInt("creditstakenID");
				_studentID = recordSet.getInt("studentID");
				_courseID = recordSet.getInt("courseID");
				_courseName = recordSet.getString("courseName");
				_semesterID = recordSet.getInt("semesterID");
				_isChangable = recordSet.getBoolean("isChangable");

				aCreditstaken = new Creditstaken(_creditstakenID, _studentID, _courseID, _courseName, _semesterID, _isChangable);
				this.creditstakenList.add(aCreditstaken);
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
		String returnString = "";
		for (int index = 0; index < this.creditstakenList.size(); index++) {
			aCreditstaken = this.creditstakenList.get(index);
			returnString += aCreditstaken.toString() + "\n";
		}

		return returnString;
	}
}
