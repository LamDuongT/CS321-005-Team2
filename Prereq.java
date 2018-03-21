/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Prereq {
	private int prereqID;
	private int courseID;
	private int prereqCourseID;
<<<<<<< HEAD
	
	
	public Prereq() {
		setValue(-1, -1, -1);		
	}
	
	public Prereq(int prereqID, int courseID, int prereqCourseID) {
		setValue(prereqID, courseID, prereqCourseID);		
	}
	
	
	public void setValue(int prereqID, int courseID, int prereqCourseID) {
=======
	private String courseName;
	private String prereqCourseName;
	
	
	public Prereq() {
		setValue(-1, -1, -1, "", "");		
	}
	
	public Prereq(int prereqID, int courseID, int prereqCourseID, String courseName, String prereqCourseName) {
		setValue(prereqID, courseID, prereqCourseID, courseName, prereqCourseName);		
	}
	
	
	public void setValue(int prereqID, int courseID, int prereqCourseID, String courseName, String prereqCourseName) {
>>>>>>> e9d8f16be2bda217ff41e4cdb570141f154b535c
		this.prereqID = prereqID;
		this.courseID = courseID;
		this.prereqCourseID = prereqCourseID;
	}
	
	public void setPrereqID (int prereqID) {
		this.prereqID = prereqID;
	}
	
	public void setCourseID (int courseID) {
		this.courseID = courseID;
	}
	
	public void setPrereqCourseID (int prereqCourseID) {
		this.prereqCourseID = prereqCourseID;
	}
	
	public int getPrereqID() {
		return this.prereqID;
	}
	
	public int getCourseID() {
		return this.courseID;
	}
	
	public int getPrereqCourseID() {
		return this.prereqCourseID;
	}
}
