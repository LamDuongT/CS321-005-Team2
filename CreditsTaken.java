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
	 * Empty constructor for empty creditsTakenList
	 */
	public CreditsTaken() {
		
	}
	
	/**
	 * Initialize the list of CreditTaken courses for the for studentID
	 */
	public CreditsTaken(int studentID) {
		getCreditsTakenData(studentID, -1);
	}

	/**
	 * Initialize the list of CreditTaken courses for the for studentID for
	 * semesterID
	 */
	public CreditsTaken(int studentID, int semesterID) {
		getCreditsTakenData(studentID, semesterID);
	}
	
	/**
	 * This is to retrieve a single CreditTaken class (singular)
	 * within the list creditsTakenList by ID. Will return an empty
	 * CreditTaken Object if not found.
	 * @author Lam Duong
	 * @param creditID
	 * @return aCreditTaken or new CreditTaken()
	 */
	public CreditTaken getCreditTakenByID(int creditID) {
		aCreditTaken = new CreditTaken();
		for (int i = 0; i < creditsTakenList.size(); i++) {
			aCreditTaken = creditsTakenList.get(i);
			if (aCreditTaken.getCreditTakenID() == creditID) {
				break;
			}
		}
		return aCreditTaken;
	}

	/**
	 * This is to retrieve a single CreditTaken class (singular)
	 * within the list creditsTakenList by name. Will return an empty
	 * CreditTaken Object if not found.
	 * @author Lam Duong
	 * @param creditName
	 * @return aCreditTaken or new CreditTaken()
	 */
	public CreditTaken getCreditTakenByCourseID(int courseID) {
		CreditTaken aCreditTaken = new CreditTaken();
		for (int index = 0; index < this.creditsTakenList.size(); index++) {
			aCreditTaken = this.creditsTakenList.get(index);
			if (aCreditTaken.getCourseID() == courseID) {
				break;
			}
		}
		return aCreditTaken;
	}
	
	// Return the LinkedList of creditsTaken
	public List<CreditTaken> getCreditsTakenList() {
		return this.creditsTakenList;
	}
	
	/**
	 * This method will add a course to a semester and save it as a CreditTaken object.
	 * It will update the CreditTaken Object to the database. Then, it will add that Object
	 * to creditsTakenList in this class in Java.
	 * @author Lam Duong
	 * @param profileID
	 * @param courseToBeAdded
	 * @param targetSemester
	 * @return successfulAdd
	 */
	public boolean addCourseToCreditsTaken(int profileID, Course courseToBeAdded, Semester targetSemester) {
		boolean successfulAdd = false;
		ConnectDB connectDB = new ConnectDB(); // connect to the Database
		String queryString = "";
		int newCreditTakenID;
		try {
			Statement statement = connectDB.theConnection.createStatement();

			queryString = "INSERT INTO collegespdb.tblcreditstaken (studentID, courseID, semesterID) ";
            queryString += "VALUES (" + profileID + ", " + courseToBeAdded.getCourseID()
                    + ", " + targetSemester.getSemesterID() + ");";
            queryString += "SELECT LAST_INSERT_ID() as creditstakenID";
            // System.out.println(queryString); Don't want to be printing out everything
            
            statement.executeQuery(queryString);
            ResultSet recordSet = statement.executeQuery(queryString);
            
            recordSet.next();
			newCreditTakenID = recordSet.getInt("creditstakenID");
			
			CreditTaken course = new CreditTaken(newCreditTakenID, profileID, courseToBeAdded.getCourseID(), targetSemester.getSemesterID());
			creditsTakenList.add(course);
			successfulAdd = true;
		}
		catch (SQLException e) {
			System.out.println("ERROR: There was an SQL Insertion error");
			successfulAdd = false;
		} finally {
			connectDB.disconectDB();
		}
		return successfulAdd;
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
			int _semesterID;
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
				_semesterID = recordSet.getInt("semesterID");

				aCreditTaken = new CreditTaken(_creditstakenID, _studentID, _courseID, _semesterID);
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
