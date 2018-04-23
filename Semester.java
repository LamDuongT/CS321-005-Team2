
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
	 * 
	 * @param semesterID
	 * @param semesterName
	 * @param semesterDesc
	 * @param creditMin
	 * @param creditMax
	 * @param list
	 * @exception IllegalArgumentException
	 * 				Throw exception if creditMinx or creditMax out of bound
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

	/**
	 * @author Lam Duong
	 * @param course
	 * @return successfulAdd
	 */
	public boolean addCourse(Course course) {
		boolean successfulAdd = false;
		int creditsAfterAdding = course.getCreditHours() + this.currentCredits;
		
		// Check if by adding this course, it would surpass semester credit limit
		if ((creditsAfterAdding) > this.creditMax) {
			if (!this.courses.contains(course)) {
				try {
					new UpdateData().updateSemester(this, 'u');
					courses.add(course);
					successfulAdd = true;
				} catch (IllegalStateException e){
					System.out.println("ERROR: There was an error with the SQL query. Try again.");
				}
			} else {
				System.out.println("Could not add Course:" + course.getCourseID() + " to Semester:" + this.semesterID
						+ "\n The Course is already in the semester. Cannot add the same Course twice into the same semester.");
			}
		} else {
			System.out.println("Could not add Course:" + course.getCourseID() + " to Semester:" + this.semesterID + "\n"
					+ "Adding this Course would surpass the maximum credit limit of this Semester!"
					+ "\nCurrent Semester limit: " + this.getCreditMax() + "\nCredits after adding the course: " + creditsAfterAdding);
		}
		if (isEmpty) {
			isEmpty = false;
		}
		this.currentCredits = creditsAfterAdding;
		return successfulAdd;
	}

	/**
	 * @author Lam Duong
	 * @param course
	 * @return successfulRemoval
	 */
	public boolean removeCourse(Course course) {
		boolean successfulRemoval = false;
		boolean notFound = true;
		int creditsAfterRemoval = course.getCreditHours() - this.currentCredits;
		for (Course aCourse : courses) {
			if (aCourse.getCourseID() == course.getCourseID()) {
				try {
					new UpdateData().updateSemester(this, 'u');
					courses.remove(course);
					successfulRemoval = true;
					notFound = false;
				} catch (IllegalStateException e) {
					successfulRemoval = false;
					System.out.println("There was a problem regarding updating to the database. Try again.");
				}
			}
		}
		if (notFound == true) {
			System.out.println("Could not remove Course:" + course.getCourseID() + " from Semester:" + this.semesterID + "\n"
					+ "This Course is not in this Semester! NOTE: Check to see if IDs match.");
		}
		if (courses.size() == 0) {
			isEmpty = true;
		}
		this.currentCredits = creditsAfterRemoval;
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
		return this.isEmpty;
	}

	public boolean contains(int courseID) {
		boolean hasCourse = false;
		for (Course aCourse : courses) {
			if (courseID == aCourse.getCourseID()) {
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
