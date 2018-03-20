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
	
	public Major() {
		setValue(-1, "", "", -1);
	}
	
	public Major (int majorID, String majorName, String majorDesc, int catalogID) {
		setValue(majorID, majorName, majorDesc, catalogID);
	}
	
	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}
	
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}
	
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}
	
	public void setValue(int majorID, String majorName, String majorDesc, int catalogID) {
		this.majorID = majorID;
		this.majorName = majorName;
		this.majorDesc = majorDesc;
		this.catalogID = catalogID;
	}
	
	public int getMajorID() {
		return this.majorID;
	}
	
	public String getMajorName() {
		return this.majorName;
	}
	
	public String getMajorDesc() {
		return this.majorDesc;
	}
	
	public int getCatalogID() {
		return this.catalogID;
	}
	
	public String toString () {
		return "majorID = " + this.majorID + " | majorName = " + this.majorName + " | catalogDesc = " + this.majorDesc + " | catalogID = " + this.catalogID;
	}
}
