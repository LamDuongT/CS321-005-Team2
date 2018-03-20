<<<<<<< HEAD
import java.sql.*;

public class Course
{
	private int courseID;
	private Prerequisites prereqs;
	private String courseName;
	private String description;
	private int creditHour;
	private int catalogID;
	private int MajorID;
	private int MinorID;
	
	public Course(
	
=======
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
    private List<Prereqs> prereqList;
    
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
	public void setCreditHours(int creditHours) {
		if (creditHours <0) || (credit hours > 10) {
			throw new Exception("Invalid amount of credit hours!");
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
}
>>>>>>> d1a65ee5c61a38cc9f6eff02317090b4bcf0daaa
