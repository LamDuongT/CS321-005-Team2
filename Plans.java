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
	private CreditsTaken profileCreditsTaken;

	/**
	 * Retrieve all the plans
	 */
	public Plans() {
		getPlansData(-1);
	}

	/**
	 * Retrieve the plans of a certain student
	 * 
	 * @param StudentID
	 */
	public Plans(int studentID, CreditsTaken profileCreditsTaken) {
		this.profileCreditsTaken = profileCreditsTaken;
		this.getPlansData(studentID);
		this.addPlanCreditsTaken();
	}

	/**
	 * Search through the plansList and return a plan matching a planID Method may
	 * return "null" if no matches are found during the search
	 * 
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

	public List<Plan> getPlans() {
		return this.plansList;
	}
	
	public void addPlanCreditsTaken() {
		ConnectDB connectDB = new ConnectDB();
		CreditsTaken planCreditsTaken = new CreditsTaken();
		String queryPrefix = "SELECT plan.planID, plan.catalogID, plan.majorID, plan.minorID, plan.majorID2, plan.minorID2,"
				+ "profile.studentID, profile.profileName, course.courseID, course.courseName, credit.semesterID,"
				+ "se.semesterName, se.creditMax, se.creditMin"
				+ "FROM tblplan plan INNER JOIN tblcreditstaken credit ON plan.profileID = credit.studentID"
				+ "INNER JOIN tblcourse course on course.courseID = credit.courseID"
				+ "INNer JOIN tblprofile profile on plan.profileID = profile.studentID"
				+ "INNER JOIN tblsemester se ON se.semesterDesc = credit.semesterID" 
				+ "WHERE plan.planID = ";
		String query = new String();
		try (Statement statement = connectDB.theConnection.createStatement()) {
			for (int i = 0; i < plansList.size(); i++) {
				query = queryPrefix + plansList.get(i).getPlanID();
				ResultSet recordSet = statement.executeQuery(query);
				while(recordSet.next()) {
					int _creditsTakenID = recordSet.getInt("creditsTakenID");
					int _studentID = recordSet.getInt("studentID");
					int _courseID = recordSet.getInt("courseID");
					int _semesterID = recordSet.getInt("semesterID");
					
					CreditTaken planCreditTaken = new CreditTaken(_creditsTakenID, _studentID, _courseID, _semesterID);
					planCreditsTaken.getCreditsTakenList().add(planCreditTaken);
				}
			}
		} catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the SQL query!", e);
		} finally {
            connectDB.disconectDB();
        }
		
	}

	/**
	 * Fetch all of the plans data from the table Plan and add to plansList
	 */
	public void getPlansData(int studentID) {
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
			// !!!WE NEED THIS METHOD TO PULL PLANS THAT ARE RELATED TO THE CURRENT
			// PROFILE!!!
			// Instantiate query strings with all fields for Plan
			String queryString = "SELECT profileID, planID, catalogID, planName, majorID, minorID, majorID2, minorID2 FROM tblplan ";
			queryString += "WHERE profileID = " + studentID;
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

				aPlan = new Plan(_profileID, _planID, _catalogID, _planName, _major1ID, _minor1ID, _major2ID,
						_minor2ID, this.profileCreditsTaken);
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