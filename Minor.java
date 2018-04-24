/**
 * 
 */

/**
 * @author Huan Nguyen
 * @author Robert Tagliaferri
 *
 */
public class Minor {
	private int minorID;
	private String minorName;
	private String minorDesc;
	private int catalogID;

	/**
	 * Initialize default values for all attributes
	 */
	public Minor() {
		setValue(-1, "", "",-1);
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Minor(int minorID, String minorName, String minorDesc, int catalogID) {
		setValue(minorID, minorName, minorDesc, catalogID);
	}

	// the set value method used for constructors
	public void setValue(int minorID, String minorName, String minorDesc, int catalogID) {
		this.minorID = minorID;
		this.minorName = minorName;
		this.minorDesc = minorDesc;
		this.catalogID = catalogID;
	}

	// setter for minorID
	public void setMinorID(int minorID) {
		this.minorID = minorID;
	}

	// setter for minorName
	public void setMinorName(String minorName) {
		this.minorName = minorName;
	}

	// setter for minorDesc
	public void setMinorDesc(String minorDesc) {
		this.minorDesc = minorDesc;
	}

	// setter for catalogID
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	// getter for minorID
	public int getMinorID() {
		return this.minorID;
	}

	// getter for minorName
	public String getMinorName() {
		return this.minorName;
	}

	// getter for minorDesc
	public String getMinorDesc() {
		return this.minorDesc;
	}

	// getter for catalogID
	public int getCatalogID() {
		return this.catalogID;
	}

	/**
	 * Override toString for testing purposes
	 */
	public String toString() {
		return "majorID = " + this.minorID + " | majorName = " + this.minorName + " | catalogDesc = " + this.minorDesc
				+ " | catalogID = " + this.catalogID;
	}
}
