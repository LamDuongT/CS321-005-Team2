/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class GradreqCourse {
	private int gradreqcourseID;
	private int majorID;
	private int minorID;
	private int courseID;
	private String courseName;

	/**
	 * Initialize default values for all attributes
	 */
	public GradreqCourse() {
		setValue(-1, -1, -1, -1, "");
	}

	/**
	 * Initialize all attributes with given information
	 */
	public GradreqCourse(int gradreqcourseID, int majorID, int minorID, int courseID, String courseName) {
		setValue(gradreqcourseID, majorID, minorID, courseID, courseName);
	}

	/**
	 * method setValue is used for constructors
	 */
	private void setValue(int gradreqcourseID, int majorID, int minorID, int courseID, String courseName) {
		this.gradreqcourseID = gradreqcourseID;
		this.majorID = majorID;
		this.minorID = minorID;
		this.courseID = courseID;
		this.courseName = courseName;
	}

	// setter for gradreqcourseID
	public void setGradreqcourseID(int gradreqcourseID) {
		this.gradreqcourseID = gradreqcourseID;
	}

	// setter for majorID
	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}

	// setter for minorID
	public void setMinorID(int minorID) {
		this.minorID = minorID;
	}

	// setter for courseID
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	// setter for courseName
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	// getter for gradreqcourseID
	public int getGradreqcourseID() {
		return this.gradreqcourseID;
	}

	// getter for majorID
	public int getMajorID() {
		return this.majorID;
	}

	// getter for minorID
	public int getMinorID() {
		return this.minorID;
	}

	// getter for courseID
	public int getCourseID() {
		return this.courseID;
	}

	// getter for courseName
	public String getCourseName() {
		return this.courseName;
	}
	
	/**Override toString method for testing purpose
	 * */
	public String toString() {
		String returnString = "";		
		returnString = String.format("\t%-8d| %-10d| %-10d|  %-10d| %-15s", this.gradreqcourseID, this.majorID, this.minorID, this.courseID, this.courseName);	
		return returnString;
	}
}
