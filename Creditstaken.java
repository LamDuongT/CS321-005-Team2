/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Creditstaken {
	private int creditstakenID;
	private int studentID;
	private int courseID;
	private String courseName;
	private int semesterID;
	private boolean isChangable;

	/**
	 * Initialize default values for all attributes
	 */
	public Creditstaken() {
		setValue(-1, -1, -1, "", -1, false);
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Creditstaken(int creditstakenID, int studentID, int courseID, String courseName, int semesterID,
			boolean isChangable) {
		setValue(creditstakenID, studentID, courseID, courseName, semesterID, isChangable);
	}

	/**
	 * Method setValue used for constructors methods
	 */
	private void setValue(int creditstakenID, int studentID, int courseID, String courseName, int semesterID,
			boolean isChangable) {
		this.creditstakenID = creditstakenID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.courseName = courseName;
		this.semesterID = semesterID;
		this.isChangable = isChangable;
	}

	// setter for creditstakenID
	public void setCreditstakenID(int creditstakenID) {
		this.creditstakenID = creditstakenID;
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

	// setter for isChangable
	public void setIsChangable(boolean isChangable) {
		this.isChangable = isChangable;
	}

	// getter for creditstakenID
	public int getCreditstakenID() {
		return this.creditstakenID;
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

	// getter for isChangable
	public boolean getIsChangable() {
		return this.isChangable;
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "";
		returnString = String.format("\t%-8d|\t%-4d|\t%-8d| %-11s|\t%-10d| %-8b", this.creditstakenID, this.studentID, this.courseID, this.courseName, this.semesterID, this.isChangable);

		return returnString;
	}
}
