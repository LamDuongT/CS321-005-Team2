import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Mohammed Alsharf
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
    
    public Course() {
        setValue(-1, "", "", -1, -1, -1, -1);
        this.prereqList = null;
    }
    
    public Course (int courseID, String courseName, String courseDesc, int creditHours, int catalogID, int majorID, int minorID) {
        setValue(courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID);
        
        this.prereqList = new Prereqs(courseID);
    }
    
	// Fields population method for constructor method
    public void setValue(int courseID, String courseName, String courseDesc, int creditHours, int catalogID, int majorID, int minorID) {
    	this.courseID = courseID;
    	this.courseName = courseName;
    	this.courseDesc = courseDesc;
    	this.creditHours = creditHours;
    	this.catalogID = catalogID;
    	this.majorID = majorID;
    	this.minorID = minorID;
    }
	
	// Mutator methods
	public void setCourseID(int courseID) {
    	this.courseID = courseID;
    }
    public void setCourseName(String courseName) {
    	this.courseName = courseName;
    }
    public void setCourseDesc(String courseDesc) {
    	this.courseDesc = courseDesc;
    }
	public void setCreditHours(int creditHours) throws Exception {
		if ((creditHours <=0) || (creditHours >10)) {
			throw new Exception ("Amount of credit hours entered is invalid!");
		}
		this.creditHours = creditHours;
	}
    public void setCatalogID (int catalogID) {
    	this.catalogID = catalogID;
    }
    public void setMajorID (int majorID) {
    	this.majorID = majorID;
    }
    public void setMinorID (int minorID) {
    	this.minorID = minorID;
    }

	// Accessor methods
    public int getCourseID() {
        return this.courseID;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public String getCourseDesc() {
        return this.courseDesc;
    }
    public int getCreditHours() {
        return this.creditHours;
    }
    public int getCatalogID() {
    	return this.catalogID;
    }
    public int getMajorID() {
    	return this.majorID;
    }
    public int getMinorID() {
    	return this.minorID;
    }
    public Prereqs getPrereqList () {
    	return this.prereqList;
    }
    
    public String toString() {
    	String returnString = "courseID = " + this.courseID + "\n";
    	returnString += "courseName = " + this.courseName + "\n";
    	returnString += "courseDesc = " + this.courseDesc + "\n";
    	returnString += "CreditHours = " + this.creditHours + "\n";
    	returnString += "CatalogID = " + this.catalogID + "\n";
    	returnString += "Major = " + this.majorID + "\n";
    	returnString += "Minor = " + this.minorID + "\n";
    	returnString += "Prereqs List::\n" + this.prereqList.toString() + "\n";
    	
    	return returnString;
    }
}
