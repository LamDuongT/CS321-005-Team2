
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Huan Nguyen
 * @author Lam Duong
 *
 */
public class Semester {
	// the range of credit taken for a semester
	private final int DEFAULT_CREDIT_MIN = 0;
	private final int DEFAULT_CREDIT_MAX = 18;

	private int semesterID;
	private String semesterName;
	private String semesterDesc;
	private int currentCredits;
	private int creditMin;
	private int creditMax;
	private ArrayList<Course> courses;
	private boolean locked;
	private boolean isEmpty;

	/**
	 * Initialize default values for all attributes
	 */
	public Semester() {
		setValue(-1, "", "", 0, 18, new ArrayList<>());
		this.locked = false;
		this.currentCredits = 0;
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Semester(int semesterID, String semesterName, String semesterDesc, int creditMin, int creditMax,
			ArrayList<Course> list) {
		setValue(semesterID, semesterName, semesterDesc, creditMin, creditMax, list);
		this.locked = false;
	}

	/**
	 * The method to set value for constructors
	 * 
	 * @exception IllegalArgumentException
	 *                throw exception if creditMinx or creditMax out of bound
	 */
	private void setValue(int semesterID, String semesterName, String semesterDesc, int creditMin, int creditMax,
			ArrayList<Course> list) {
		// check creditMin and creditMax range
		if (creditMin < DEFAULT_CREDIT_MIN) {
			throw new IllegalArgumentException("[ERROR] creditMin can not < " + DEFAULT_CREDIT_MIN);
		}

		if (creditMax > DEFAULT_CREDIT_MAX) {
			throw new IllegalArgumentException("[ERROR] creditMax can not > " + DEFAULT_CREDIT_MAX);
		}

		this.courses = list;
		for (int i = 0; i < courses.size(); i++) {
			this.currentCredits += courses.get(i).getCreditHours();
		}
		this.semesterID = semesterID;
		this.semesterName = semesterName;
		this.semesterDesc = semesterDesc;
		this.creditMin = creditMin;
		this.creditMax = creditMax;

	}

	// adds a course to the courses list
	public boolean addCourse(Course course) {
		boolean successfulAdd = false;
		if ((course.getCreditHours()+this.currentCredits) > this.creditMax) {
			courses.add(course);
			successfulAdd = true;
		}
		if (successfulAdd == false) {
			System.out.println("Could not add Course:" + course.getCourseID() + " to Semester:" + this.semesterID + "\n"
					+ "Adding this Course would surpass the maximum creditLimit of this Semester!");
		}
		return successfulAdd;
	}

	public boolean removeCourse(Course course) {
		boolean successfulRemoval = false;
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourseID() == course.getCourseID()) {
				courses.remove(course);
				successfulRemoval = true;
			}
		}
		if (successfulRemoval == false) {
			System.out.println("Could not remove Course:" + course.getCourseID() + " from Semester:" + this.semesterID + "\n"
					+ "This Course is not in this Semester! DEVS: Check to see if IDs match.");
		}
		return successfulRemoval;
	}

	// setter for courses
	public void setCourses(ArrayList<Course> list) {
		this.courses = list;
	}

	// setter for semesterID
	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}

	// setter for semesterName
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	// setter for semesterDesc
	public void setSemesterDesc(String semesterDesc) {
		this.semesterDesc = semesterDesc;
	}

	/**
	 * setter for creditMin
	 * 
	 * @exception IllegalArgumentException
	 *                throw exception if creditMin is out of bound
	 */
	public void setCreditMin(int creditMin) {
		// check creditMin range
		if (creditMin < DEFAULT_CREDIT_MIN) {
			throw new IllegalArgumentException("[ERROR] creditMin can not < 0");
		}
		this.creditMin = creditMin;
	}

	/**
	 * setter for creditMax
	 * 
	 * @exception IllegalArgumentException
	 *                throw exception if creditMax is out of bound
	 */
	public void setCreditMax(int creditMax) {
		// check creditMin range
		if (creditMax < DEFAULT_CREDIT_MAX) {
			throw new IllegalArgumentException("[ERROR] creditMin can not < 0");
		}
		this.creditMax = creditMax;
	}

	// getter for semesterID
	public int getSemesterID() {
		return this.semesterID;
	}

	// getter for semesterName
	public String getSemesterName() {
		return this.semesterName;
	}

	// getter for semesterDesc
	public String getSemesterDesc() {
		return this.semesterDesc;
	}

	// getter for creditMin
	public int getCreditMin() {
		return this.creditMin;
	}

	// getter for creditMax
	public int getCreditMax() {
		return this.creditMax;
	}

	public int getCurrentCredits() {
		return this.currentCredits;
	}

	// getter for lock status
	public boolean isLocked() {
		return this.locked;
	}

	// change credit max
	public void changeCreditMax(int m) {
		this.creditMax = m;
	}

	// change credit min
	public void changeCreditMin(int m) {
		this.creditMin = m;
	}

	// inverts lock status
	public void toggleLock() {
		this.locked = !this.locked;
	}

	public boolean isEmpty() {
		if (courses.size() == 0) {
			this.isEmpty = true;
		} else {
			this.isEmpty = false;
		}
		return this.isEmpty;
	}

	public boolean contains(Course course) {
		boolean hasCourse = false;
		for (int i = 0; i < courses.size(); i++) {
			if (course.getCourseID() == courses.get(i).getCourseID()) {
				hasCourse = true;
			}
		}
		return hasCourse;
	}

	/**
	 * Override toString method for testing purpose
	 */
	public String toString() {
		String returnString = "";
		returnString = String.format(" %-10d| %-15s|\t%-25s|  %-10d|  %-10d", this.semesterID, this.semesterName,
				this.semesterDesc, this.creditMin, this.creditMax);

		return returnString;
	}
}
