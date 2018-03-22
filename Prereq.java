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
	private String courseName;
	private String prereqCourseName;

	/**@param: none
	 * Initialize default values for all attributes
	 * */
	public Prereq() {
		setValue(-1, -1, -1, "", "");
	}

	public Prereq(int prereqID, int courseID, int prereqCourseID, String courseName, String prereqCourseName) {
		setValue(prereqID, courseID, prereqCourseID, courseName, prereqCourseName);
	}

	public void setValue(int prereqID, int courseID, int prereqCourseID, String courseName, String prereqCourseName) {
		this.prereqID = prereqID;
		this.courseID = courseID;
		this.prereqCourseID = prereqCourseID;
		this.courseName = courseName;
		this.prereqCourseName = prereqCourseName;
	}

	public void setPrereqID(int prereqID) {
		this.prereqID = prereqID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public void setPrereqCourseID(int prereqCourseID) {
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
	
	// getter for courseName
	public String getCourseName () {
		return this.courseName;
	}
	
	// getter for courseName
	public String getPrereqCourseName () {
		return this.prereqCourseName;
	}
	/**@param: none
	 * Override the toString method for testing purpose
	 * */
	public String toString() {
		return "CourseID = " + this.courseID + " | courseName = " + this.courseName + " | prereqCoureID = "
				+ this.prereqCourseID + " | prereqCoureName = " + this.prereqCourseName;
	}
}
