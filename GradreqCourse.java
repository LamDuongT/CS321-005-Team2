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
	private String courseName;
	private String gradreqDesc;
	
	/**
	 * Initialize default values for all attributes
	 */
	public GradreqCourse() {
		setValue(-1, -1, -1, "", "");
	}

	/**
	 * Initialize all attributes with given information
	 */
	public GradreqCourse(int gradreqcourseID, int majorID, int minorID, String courseName, String gradreqDesc) {
		setValue(gradreqcourseID, majorID, minorID, courseName, gradreqDesc);
	}

	/**
	 * method setValue is used for constructors
	 */
	private void setValue(int gradreqcourseID, int majorID, int minorID, String courseName, String gradreqDesc) {
		this.gradreqcourseID = gradreqcourseID;
		this.majorID = majorID;
		this.minorID = minorID;
		this.courseName = courseName;
		this.gradreqDesc = gradreqDesc;
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

	// setter for courseName
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	// setter for description
	public void setCourseID(String gradreqDesc) {
		this.gradreqDesc = gradreqDesc;
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

	// getter for courseName
	public String getCourseName() {
		return this.courseName;
	}
	
	// getter for courseID
	public String getDesc() {
		return this.gradreqDesc;
	}
	
	/**Override toString method for testing purpose
	 * */
	public String toString() {
		String returnString = "";		
		returnString = String.format("\t%-8d| %-10d| %-10d| %-15s| %s ", this.gradreqcourseID, this.majorID, this.minorID, this.courseName, this.gradreqDesc);	
		return returnString;
	}
}
