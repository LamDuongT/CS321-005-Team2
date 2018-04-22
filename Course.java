import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammed Alsharf - 10%
 * @author Huan Nguyen - 40%
 * @author Lam Duong - 10%
 * @author Robert Tagliaferri -40%
 */
public class Course {
	private final int MINIMAL_CREDIT_HOURS = 0;
	private final int MAXIMUM_CREDIT_HOURS = 10;
	
	private int courseID;
	private String courseName;
	private String courseDesc;
	private int creditHours;
	private int catalogID;
	private String majorID;
	private String minorID;
	private Prereqs prereqList;
	private boolean isEmpty;
    private boolean taken; // Just for GUI
    
	/**
	 * Initialize default values for all attributes
	 */
	public Course() {
		setValue(-1, "", "", -1, -1, "-1", "-1");
		this.prereqList = null;
		this.isEmpty = true;
                taken =false;
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Course(int courseID, String courseName, String courseDesc, int creditHours, int catalogID, String majorID,
			String minorID) {
		
		setValue(courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID);
		this.prereqList = new Prereqs(courseID);
		this.isEmpty = false;
                taken = false;
	}

	// Fields population method for constructor method
	public void setValue(int courseID, String courseName, String courseDesc, int creditHours, int catalogID,
			String majorID, String minorID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.creditHours = creditHours;
		this.catalogID = catalogID;
		this.majorID = majorID;
		this.minorID = minorID;
	}
	//this method is used in NewProfile
	public void setTaken(boolean bool){
            taken = bool;
        }
	// NOTE: WE DO NOT NEED SETTER METHODS FOR THE COURSE CLASS
	// ANY CHANGES TO THE CLASS SHOULD BE DONE IN DATABASE

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
		if ((creditHours <= MINIMAL_CREDIT_HOURS) || (creditHours > MAXIMUM_CREDIT_HOURS)) {
			throw new Exception("Amount of credit hours entered is invalid!");
		}
		this.creditHours = creditHours;
	}

	// setter for catalogID
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	// setter for majorID
	public void setMajorID(String majorID) {
		this.majorID = majorID;
	}

	// setter for minorID
	public void setMinorID(String minorID) {
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
	public String getMajorID() {
		return this.majorID;
	}

	// getter for minorID
	public String getMinorID() {
		return this.minorID;
	}

	// getter for prereqList
	public Prereqs getPrereqList() {
		return this.prereqList;
	}
	
	public boolean isEmpty() {
		return this.isEmpty;
	}
    //used for NewProfile
    public boolean isTaken(){
        return taken;
    }

	/**
	 * Override toString method for testing purposes
	 */
	public String toString() {	
		String returnString = "";		
		returnString = String.format(" %-8d| %-15s| %-12d|  %-10d| %-10s| %-10s| %-25s| %-25s", this.courseID, this.courseName, this.creditHours, this.catalogID, this.majorID, this.minorID, this.prereqList.getPrereqsCourseNames(), this.courseDesc);	
		return returnString;
	}
}
