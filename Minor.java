/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Minor {
	private int minorID;
	private String minorName;
	private String minorDesc;
	private int catalogID;
	
	public Minor() {
		setValue(-1, "", "", -1);
	}
	
	public Minor (int minorID, String minorName, String minorDesc, int catalogID) {
		setValue(minorID, minorName, minorDesc, catalogID);
	}
	
	public void setMinorID(int minorID) {
		this.minorID = minorID;
	}
	
	public void setMinorName(String minorName) {
		this.minorName = minorName;
	}
	
	public void setMinorDesc(String minorDesc) {
		this.minorDesc = minorDesc;
	}
	
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}
	
	public void setValue(int minorID, String minorName, String minorDesc, int catalogID) {
		this.minorID = minorID;
		this.minorName = minorName;
		this.minorDesc = minorDesc;
		this.catalogID = catalogID;
	}
	
	public int getMinorID() {
		return this.minorID;
	}
	
	public String getMinorName() {
		return this.minorName;
	}
	
	public String getMinorDesc() {
		return this.minorDesc;
	}
	
	public int getCatalogID() {
		return this.catalogID;
	}
	
	public String toString () {
		return "majorID = " + this.minorID + " | majorName = " + this.minorName + " | catalogDesc = " + this.minorDesc + " | catalogID = " + this.catalogID;
	}
}
