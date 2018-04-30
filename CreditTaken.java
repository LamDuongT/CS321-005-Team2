/**
 * @author Huan Nguyen
 * @author Lam Duong
 *
 */
public class CreditTaken {
	private int creditTakenID;
	private int studentID;
	private int courseID;
	private int semesterID;

	/**
	 * Initialize default values for all attributes
	 */
	public CreditTaken() {
		setValue(-1, -1, -1, -1);
	}
	
	/**
	 * Initialize all attributes with given information
	 */
	public CreditTaken(int creditTakenID, int studentID, int courseID, int semesterID) {
		setValue(creditTakenID, studentID, courseID, semesterID);
	}

	/**
	 * Method setValue used for constructors methods
	 */
	private void setValue(int creditTakenID, int studentID, int courseID, int semesterID) {
		this.creditTakenID = creditTakenID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.semesterID = semesterID;
	}

	/**
	 *  MUTATOR METHODS
	 */
	
	// setter for creditstakenID
	public void setCreditTakenID(int creditTakenID) {
		this.creditTakenID = creditTakenID;
	}

	// setter for studentID
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	// setter for courseID
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	// setter for semesterID
	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}

	/**
	 *  ACCESSOR METHODS
	 */
	
	
	// getter for creditTakenID
	public int getCreditTakenID() {
		return this.creditTakenID;
	}

	// getter for studentID
	public int getStudentID() {
		return this.studentID;
	}

	// getter for courseID
	public int getCourseID() {
		return this.courseID;
	}

	// getter for semesterID
	public int getSemesterID() {
		return this.semesterID;
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "";
		returnString = String.format("\t%-8d|\t%-4d|\t%-7d|\t%-2d", this.creditTakenID, this.studentID, this.courseID, this.semesterID);

		return returnString;
	}
}
