
/**
 *
 * @author Huan Nguyen
 */
public class Catalog {
	private int catalogID;
	private String catalogName;
	private String catalogDesc;
	
	public Catalog() {
		this.catalogID = -1;
		this.catalogName = "";
		this.catalogDesc = "";
	}
	
	public Catalog(int catalogID, String catalogName, String catalogDesc) {
		this.catalogID = catalogID;
		this.catalogName = catalogName;
		this.catalogDesc = catalogDesc;
	}
	
	public void setCatalogID (int catalogID) {
		this.catalogID = catalogID;
	}
	
	public void setCatalogName (String catalogName) {
		this.catalogName = catalogName;
	}
	
	public void setCatalogDesc (String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}
	
	public int getCatalogID () {
		return this.catalogID;
	}
	
	public String getCatalogName () {
		return this.catalogName;		
	}
	
	public String getCatalogDesc () {
		return this.catalogDesc;		
	}
	
	public String toString () {
		return "catalogID = " + this.catalogID + " | catalogName = " + this.catalogName + " | catalogDesc = " + this.catalogDesc;
	}
}
