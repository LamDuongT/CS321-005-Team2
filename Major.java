/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Major {
	private int majorID;
	private String majorName;
	private String majorDesc;
	private int catalogID;

	/**
	 * Initialize default values for all attributes
	 */
	public Major() {
		setValue(-1, "", "", -1);
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Major(int majorID, String majorName, String majorDesc, int catalogID) {
		setValue(majorID, majorName, majorDesc, catalogID);
	}

	// set value method for constructors methods
	public void setValue(int majorID, String majorName, String majorDesc, int catalogID) {
		this.majorID = majorID;
		this.majorName = majorName;
		this.majorDesc = majorDesc;
		this.catalogID = catalogID;
	}

	// setter for majorID
	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}

	// setter for majorName
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	// setter for majorDesc
	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}

	// setter for catalogID
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	// getter for majorID
	public int getMajorID() {
		return this.majorID;
	}

	// getter for majorName
	public String getMajorName() {
		return this.majorName;
	}

	// getter for majorDesc
	public String getMajorDesc() {
		return this.majorDesc;
	}

	// getter for catalogID
	public int getCatalogID() {
		return this.catalogID;
	}

	/**
	 * Override toString method for testing purposes
	 */
	public String toString() {
		return "majorID = " + this.majorID + " | majorName = " + this.majorName + " | catalogDesc = " + this.majorDesc
				+ " | catalogID = " + this.catalogID;
	}
}
