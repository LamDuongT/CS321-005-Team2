import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huan Nguyen
 * @author Lam Duong
 *
 */
public class CreditsTaken {
	List<CreditTaken> creditsTakenList = new LinkedList<CreditTaken>();
	private CreditTaken aCreditTaken;
	
	/**
	 * Initialize the list of CreditTaken courses for the for studentID
	 */
	public CreditsTaken(int studentID) {
		getCreditsTakenData(studentID, -1);
	}

	/**
	 * Initialize the list of creditstaken courses for the for studentID for
	 * semesterID
	 */
	public CreditsTaken(int studentID, int semesterID) {
		getCreditsTakenData(studentID, semesterID);
	}
	
	/**
	 * This is to retrieve a single CreditTaken class (singular)
	 * within the list creditsTakenList by ID. Will return an empty
	 * CreditTaken Object if not found.
	 * @param creditID
	 * @return aCreditTaken or new CreditTaken()
	 */
	public CreditTaken getCreditTakenByID(int creditID) {
		for (int i = 0; i < creditsTakenList.size(); i++) {
			aCreditTaken = creditsTakenList.get(i);
			if (aCreditTaken.getCreditTakenID() == creditID) {
				return aCreditTaken;
			}
		}
		return new CreditTaken();
	}

	/**
	 * This is to retrieve a single CreditTaken class (singular)
	 * within the list creditsTakenList by name. Will return an empty
	 * CreditTaken Object if not found.
	 * @param creditName
	 * @return aCreditTaken or new CreditTaken()
	 */
	public CreditTaken getCreditTakenByName(String creditName) {
		CreditTaken aCreditTaken = new CreditTaken();
		for (int index = 0; index < this.creditsTakenList.size(); index++) {
			aCreditTaken = this.creditsTakenList.get(index);
			if (aCreditTaken.getCreditTakenName().toLowerCase().contains(creditName.toLowerCase())) {
				return aCreditTaken;
			}
		}
		return new CreditTaken();
	}
	
	
	/**
	 * get CreditsTaken for studentID
	 * 
	 * @param studentID
	 *            the student ID
	 * @param semesterID
	 *            if given the list will hold CreditsTaken for studentID for
	 *            semesterID
	 *            otherwise, the list will hold CreditsTaken for studentID
	 * 
	 */
	private void getCreditsTakenData(int studentID, int semesterID) {
		ConnectDB connectdb = new ConnectDB();

		try {
			int _creditstakenID;
			int _studentID;
			int _courseID;
			String _courseName;
			int _semesterID;
			boolean _isChangable;
			int _planID;

			String queryString = "SELECT tblcreditstaken.*, tblcourse.courseName "; 
			queryString += "FROM tblcreditstaken INNER JOIN tblcourse on tblcreditstaken.courseID = tblcourse.courseID ";
			queryString += "WHERE studentID = " + studentID + " ";
			
			if (semesterID != -1) {
				queryString += "AND semesterID = " + semesterID;
			}

			System.out.println(queryString);

			// Initialize an SQL statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mySQL Workbench
			ResultSet recordSet = statement.executeQuery(queryString);

			while (recordSet.next()) {
				_creditstakenID = recordSet.getInt("creditstakenID");
				_studentID = recordSet.getInt("studentID");
				_courseID = recordSet.getInt("courseID");
				_courseName = recordSet.getString("courseName");
				_semesterID = recordSet.getInt("semesterID");
				_isChangable = recordSet.getBoolean("isChangable");
				_planID = recordSet.getInt("planID");

				aCreditTaken = new CreditTaken(_creditstakenID, _studentID, _courseID, _courseName, _semesterID, _isChangable, _planID);
				this.creditsTakenList.add(aCreditTaken);
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
		String returnString = "creditstakenID  | studentID | courseID	| courseName | semesterID | isChangable\n";
		for (int index = 0; index < this.creditsTakenList.size(); index++) {
			aCreditTaken = this.creditsTakenList.get(index);
			returnString += aCreditTaken.toString() + "\n";
		}

		return returnString;
	}
}
