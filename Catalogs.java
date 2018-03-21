import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Huan Nguyen
 */
public class Catalogs {
	private List<Catalog> catalogList = new LinkedList<Catalog>();
	
	private Catalog aCatalog;
	
	public Catalogs() {
		getCatalogData();
	}

	/* Get catalog by ID from the list of catalogs
	 * */
	public Catalog getCatalogByID(int catalogID) {
		aCatalog = null;
		boolean isFound = false;
		
		for (int index = 0; index < this.catalogList.size(); index ++) {
			aCatalog = this.catalogList.get(index);
			if (aCatalog.getCatalogID() == catalogID) {
				isFound = true;
				break;
			}
		}
		if (!isFound)
			aCatalog = new Catalog();
		
		return aCatalog;
	}

	/* Get catalog by Name from the list of catalogs
	 * */
	public Catalog getCatalogByName(String catalogName) {
		aCatalog = null;
		boolean isFound = false;
		
		for (int index = 0; index < this.catalogList.size(); index ++) {
			aCatalog = this.catalogList.get(index);
			if (aCatalog.getCatalogName().toLowerCase().indexOf(catalogName) != -1) {
				isFound = true;
				break;
			}
		}
		if (!isFound)
			aCatalog = new Catalog();
		
		return aCatalog;
	}
	
	/* Get the list of major for the catalog ID
	 * */
	
	
	/* fetch all data from the table catalog and add to the list
	 * */
	private void getCatalogData () {
		ConnectDB connectdb = new ConnectDB();	
		
        try {
        	int catalogID;
        	String catalogName;
        	String catalogDesc;
        	
        	String queryString = "SELECT catalogID, catalogName, catalogDesc FROM `tblcatalog` ";        	
        	queryString += "ORDER BY catalogID ASC";
        	
        	 System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            while (recordSet.next()) {
                catalogID = recordSet.getInt("catalogID");
                catalogName = recordSet.getString("catalogName");
                catalogDesc = recordSet.getString("catalogDesc");
                
                aCatalog = new Catalog(catalogID, catalogName, catalogDesc);
                this.catalogList.add(aCatalog);                
            }            
            statement.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        }
        finally {
        	connectdb.disconectDB();
		}		
	}
	
	public String toString () {
		String returnString = "";
		for (int index = 0; index < this.catalogList.size(); index ++) {
			aCatalog = this.catalogList.get(index);
			returnString += aCatalog.toString() + "\n";
		}
		
		return returnString;
	}
}
