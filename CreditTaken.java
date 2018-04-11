/**
 * @author Huan Nguyen
 * @author Lam Duong
 *
 */
public class CreditTaken {
	private int creditTakenID;
	private int studentID;
	private int courseID;
	private String courseName;
	private int semesterID;
	private int planID;
	private boolean isChangable;

	/**
	 * Initialize default values for all attributes
	 */
	public CreditTaken() {
		setValue(-1, -1, -1, "", -1, false, -1);
	}

	/**
	 * Initialize all attributes with given information
	 */
	public CreditTaken(int creditTakenID, int studentID, int courseID, String courseName, int semesterID,
			boolean isChangable, int planID) {
		setValue(creditTakenID, studentID, courseID, courseName, semesterID, isChangable, planID);
	}

	/**
	 * Method setValue used for constructors methods
	 */
	private void setValue(int creditTakenID, int studentID, int courseID, String courseName, int semesterID,
			boolean isChangable, int planID) {
		this.creditTakenID = creditTakenID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.courseName = courseName;
		this.semesterID = semesterID;
		this.isChangable = isChangable;
		this.planID = planID;
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
	
	// setter for planID
	// If plan ID is -1, that means that it's a credit that was taken previously
	public void setPlanID(int planID) {
		this.planID = planID;
	}

	// setter for isChangable
	public void setIsChangable(boolean isChangable) {
		this.isChangable = isChangable;
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

	// getter for isChangable
	public boolean getIsChangable() {
		return this.isChangable;
	}
	
	//getter for planID
	public int getPlanID() {
		return this.planID;
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "";
		returnString = String.format("\t%-8d|\t%-4d|\t%-8d| %-11s|\t%-10d| %-8b", this.creditTakenID, this.studentID, this.courseID, this.courseName, this.semesterID, this.isChangable);

		return returnString;
	}
}
