import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * @author Lam Duong
 */

public class Plans {
	private LinkedList<Plan> plansList = new LinkedList<Plan();
	private Plan aPlan;
	
	public Plans() {
		getPlansData();
	}
	
	/**
	 * Fetch all of the plans data from the table Plan and add to plansList
	 */
	public getPlansData() {
		ConnectDB connectdb = new ConnectDB();
		try {
			int _planID;
			String _planName;
			int _major1ID;
			int _minor1ID;
			int _major2ID;
			int _minor2ID;
			int _catalogID;
			
			String queryString = "SELECT "
		}
	}
}