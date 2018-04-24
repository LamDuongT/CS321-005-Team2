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
	 * Check to see if a course is in the list of CreditsTaken
	 * @param course
	 * @return
	 */
	public boolean contains(int courseID) {
		boolean hasCourse = false;
//		for (int i = 0; i < creditsTakenList.size(); i++) {
//			if (courseID == creditsTakenList.get(i).getCourseID()) {
//				hasCourse = true;
//			}
//		}
                for (CreditTaken acreditTaken : creditsTakenList){
                    if (courseID == acreditTaken.getCourseID()){
                        hasCourse = true;
                    }
                }
		return hasCourse;
	}
	
	/**
	 * Method to retrieve the creditTakenID from CreditsTakenList
	 * given a Course Object.
	 * METHOD RETURNS 9999 IF NO COURSE IS FOUND
	 * @param course
	 * @return
	 */
	public int getCreditTakenIDOfCourse(Course course) {
		int creditTakenID= 9999;
		for (CreditTaken aCreditTaken : creditsTakenList) {
			if(course.getCourseID() == aCreditTaken.getCourseID()) {
				creditTakenID = aCreditTaken.getCreditTakenID();
			}
		}
		return creditTakenID;
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
	 * O(N) runtime.
	 * @author Lam Duong
	 * @param courseID
	 * @return aCreditTaken or new CreditTaken()
	 */
	public CreditTaken getCreditTakenByCourseID(int courseID) {
		CreditTaken aCreditTaken = new CreditTaken();
		for (CreditTaken course : creditsTakenList) {
			if (course.getCourseID() == courseID) {
				aCreditTaken = course;
				break;
			}
		}
		return aCreditTaken;
	}
	
	/**
	 * README: Essentially the same thing as getCreditTakenByCourseID() above
	 * but will takes in semesterID so that it finds duplicates of the same course
	 * not in the same semester.
	 * @param courseID
	 * @param semesterID
	 * @return
	 */
	public CreditTaken getCreditTakenDuplicateByCourseID(int courseID, int semesterID) {
		CreditTaken aCreditTaken = new CreditTaken();
		for (CreditTaken course : creditsTakenList) {
			if (course.getCourseID() == courseID) {
				if (course.getSemesterID() != semesterID) {
					aCreditTaken = course;
					break;
				}
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
	 * Method will loop through creditsTakenList and change to that course the corresponding semester
	 * @author Lam Duong
	 * @param creditTakenID
	 * @param profileID
	 * @param course
	 * @param semester
	 * @return successfulUpdate
	 */
	public boolean updateCourseInCreditsTaken(int creditTakenID, int profileID, Course course, Semester semester) {
		boolean successfulUpdate = false;
		boolean nothingFound = true;
		
		CreditTaken courseToBeUpdated = new CreditTaken(creditTakenID, profileID, course.getCourseID(), semester.getSemesterID());
		for (int i = 0; i < creditsTakenList.size(); i++) {
			if (creditsTakenList.get(i).getCreditTakenID() == creditTakenID) {
				CreditTaken oldCourse = creditsTakenList.get(i);
				creditsTakenList.set(i, courseToBeUpdated);
				try {
					new UpdateData().updateCreditstaken(courseToBeUpdated, 'u');
					successfulUpdate = true;
					nothingFound = false;
				} catch (IllegalStateException e) {
					successfulUpdate = false;
					System.out.println("There was a problem with updating to the database. Reverting changes.");
					creditsTakenList.set(i, oldCourse);
				}
				break;
			}
		}
		if (nothingFound) {
			System.out.println("Could not update CreditTaken because the creditTakenID input did not match with any CreditTaken in creditsTakenList");
		}
		return successfulUpdate;
	}
	
	/**
	 * @author Lam Duong
	 * @param creditTakenID
	 * @param profileID
	 * @param course
	 * @param semester
	 * @return
	 */
	public boolean removeCourseInCreditsTaken(int creditTakenID, int profileID, Course course, Semester semester) {
		boolean successfulRemoval= false;
		
		CreditTaken courseToBeRemoved = new CreditTaken(creditTakenID, profileID, course.getCourseID(), semester.getSemesterID());
		for (CreditTaken aCourse : creditsTakenList) {
			if (aCourse.getCourseID() == creditTakenID) {
				try {
					new UpdateData().updateCreditstaken(courseToBeRemoved, 'd');
					if (creditsTakenList.remove(aCourse)) {
						successfulRemoval = true;
					} else {
						System.out.println("Couldn't remove course from creditsTakenList. Course: " + aCourse.getCourseID() + " is not found in creditsTakenList!");
					}
					break;
				} catch (IllegalStateException e) {
					System.out.println("There was a problem with removing in the database.");
				}
			}
		}
		return successfulRemoval;
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
			// recordSet will hold a data table as SQL object
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
