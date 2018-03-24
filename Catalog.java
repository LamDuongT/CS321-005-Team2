
/**
 *
 * @author Huan Nguyen
 */
public class Catalog {
	private int catalogID;
	private String catalogName;
	private String catalogDesc;

	/**
	 * Initialize default values for all attributes
	 */
	public Catalog() {
		this.catalogID = -1;
		this.catalogName = "";
		this.catalogDesc = "";
	}

	/**
	 * Initialize all attributes with given information
	 */
	public Catalog(int catalogID, String catalogName, String catalogDesc) {
		this.catalogID = catalogID;
		this.catalogName = catalogName;
		this.catalogDesc = catalogDesc;
	}

	// setter for catalogID
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	// setter for catalogName
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	// setter for catalogDesc
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	// getter for catalogID
	public int getCatalogID() {
		return this.catalogID;
	}

	// getter for catalogName
	public String getCatalogName() {
		return this.catalogName;
	}

	// getter for catalogDesc
	public String getCatalogDesc() {
		return this.catalogDesc;
	}

	/**
	 * Override toString method for testing purposes
	 */
	public String toString() {
		return "catalogID = " + this.catalogID + " | catalogName = " + this.catalogName + " | catalogDesc = "
				+ this.catalogDesc;
	}
}
