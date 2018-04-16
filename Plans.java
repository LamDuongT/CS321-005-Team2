import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lam Duong
 */

public class Plans {
	private LinkedList<Plan> plansList = new LinkedList<Plan>();
	private Plan aPlan;
        private Profile profile;
	public Plans() {
		getPlansData();
	}
	public Plans(Profile prof){
            getPlansData();
            profile = prof;
        }
	/**
	 * Search through the plansList and return a plan matching a planID
	 * Method may return "null" if no matches are found during the search
	 * @param planID
	 * @return Plan
	 */
	public Plan getPlanByID(int planID) {
		aPlan = null;
		for (int i = 0; i < plansList.size(); i++) {
			aPlan = plansList.get(i);
			if (planID == aPlan.getPlanID()) {
				break;
			}
		}
		return aPlan;
	}
	public List<Plan> getPlans(){
            return this.plansList;
        }
	/**
	 * Fetch all of the plans data from the table Plan and add to plansList
	 */
	public void getPlansData() {
		ConnectDB connectDB = new ConnectDB();
		try {
			int _planID;
			int _catalogID;
			String _planName;
			int _major1ID;
			int _minor1ID;
			int _major2ID;
			int _minor2ID;
			int _profileID;
			//!!!WE NEED THIS METHOD TO PULL PLANS THAT ARE RELATED TO THE CURRENT PROFILE!!!
			// Instantiate query strings with all fields for Plan
			String queryString = "SELECT planID, catalogID, planName, majorID, minorID, majorID2, minorID2 FROM 'tablplan' ";
			queryString += "ORDER BY planID ASC";
			System.out.println(queryString);
			
			// Initialize an SQL statement
			Statement statement = connectDB.theConnection.createStatement();
			
			// recordSet will hold a data table and create an SQL object
			ResultSet recordSet = statement.executeQuery(queryString);
			
			// Loop through recordSet and add every plan within database to the list of Plans
			while (recordSet.next()) {
				_planID = recordSet.getInt("planID");
				_catalogID = recordSet.getInt("catalogID");
				_planName = recordSet.getString("planName");
				_major1ID = recordSet.getInt("majorID");
				_minor1ID = recordSet.getInt("minorID");
				_major2ID = recordSet.getInt("major2ID");
				_minor2ID = recordSet.getInt("minor2ID");
				_profileID = recordSet.getInt("profileID");
				
				aPlan = new Plan(_profileID, _planID, _catalogID, _planName, _major1ID, _minor1ID, _major2ID, _minor2ID);
				plansList.add(aPlan);
			}
			statement.close();
		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			// close the connection
			// NOTE: the close connection method need to be called in finally block to
			// ensure the connection is closed
			connectDB.disconectDB();
		}
	}
}