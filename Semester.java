
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Semester {
	// the range of credit taken for a semester
	private final int DEFAULT_CREDIT_MIN = 0;
	private final int DEFAULT_CREDIT_MAX = 18;

	private int semesterID;
	private String semesterName;
	private String semesterDesc;
	private int creditMin;
	private int creditMax;
	private ArrayList<Course> courses;
	private boolean lock;

	/**
	 * Initialize default values for all attributes
	 */
	public Semester() {
		setValue(-1, "", "", 0, 0, new ArrayList<>());
		this.lock = false;
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Semester(int semesterID, String semesterName, String semesterDesc, int creditMin, int creditMax) {
		setValue(semesterID, semesterName, semesterDesc, creditMin, creditMax, new ArrayList<>());
		this.lock = false;
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
			throw new IllegalArgumentException("[ERROR] creditMin can not < 0");
		}

		if (creditMax > DEFAULT_CREDIT_MAX) {
			throw new IllegalArgumentException("[ERROR] creditMax can not > 18");
		}

		this.courses = list;
		this.semesterID = semesterID;
		this.semesterName = semesterName;
		this.semesterDesc = semesterDesc;
		this.creditMin = creditMin;
		this.creditMax = creditMax;

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

	// getter for lock status
	public boolean getLock() {
		return this.lock;
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
	public void toggleLock(){
      this.lock = !this.lock;
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
