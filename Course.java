import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammed Alsharf IS REALLY COOL
 * @author Huan Nguyen
 * @author Lam Duong
 */
public class Course {
	private int courseID;
	private String courseName;
	private String courseDesc;
	private int creditHours;
	private int catalogID;
	private int majorID;
	private int minorID;
	private Prereqs prereqList;

	/**
	 * Initialize default values for all attributes
	 */
	public Course() {
		setValue(-1, "", "", -1, -1, -1, -1);
		this.prereqList = null;
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Course(int courseID, String courseName, String courseDesc, int creditHours, int catalogID, int majorID,
			int minorID) {
		setValue(courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID);

		this.prereqList = new Prereqs(courseID);
	}

	// Fields population method for constructor method
	public void setValue(int courseID, String courseName, String courseDesc, int creditHours, int catalogID,
			int majorID, int minorID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.creditHours = creditHours;
		this.catalogID = catalogID;
		this.majorID = majorID;
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

	// setter for courseDesc
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	// setter for creditHours
	public void setCreditHours(int creditHours) throws Exception {
		if ((creditHours <= 0) || (creditHours > 10)) {
			throw new Exception("Amount of credit hours entered is invalid!");
		}
		this.creditHours = creditHours;
	}

	// setter for catalogID
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	// setter for majorID
	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}

	// setter for minorID
	public void setMinorID(int minorID) {
		this.minorID = minorID;
	}

	// getter for courseID
	public int getCourseID() {
		return this.courseID;
	}

	// getter for courseName
	public String getCourseName() {
		return this.courseName;
	}

	// getter for courseDesc
	public String getCourseDesc() {
		return this.courseDesc;
	}

	// getter for creditHours
	public int getCreditHours() {
		return this.creditHours;
	}

	// getter for catalogID
	public int getCatalogID() {
		return this.catalogID;
	}

	// getter for majorID
	public int getMajorID() {
		return this.majorID;
	}

	// getter for minorID
	public int getMinorID() {
		return this.minorID;
	}

	// getter for prereqList
	public Prereqs getPrereqList() {
		return this.prereqList;
	}

	/**
	 * Override toString method for testing purposes
	 */
	public String toString() {	
		String returnString = "";		
		returnString = String.format(" %-8d| %-15s| %-12d|  %-10d| %-10d| %-10d| %-25s| %-25s", this.courseID, this.courseName, this.creditHours, this.catalogID, this.majorID, this.minorID, this.prereqList.getPrereqsCourseNames(), this.courseDesc);	
		return returnString;
	}
}
