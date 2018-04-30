import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Huan Nguyen
 *
 */

public class PlanTest {
	Login aStudent, anotherStudent;
	Profile aStudentProfile, anotherStudentProfile;
	UpdateData updateData;
	Plan aPlan;
	ConnectDB connectDB;
	
	int planID = 10;
	int catalogID = 3;
	int majorID = 1;
	int minorID = 2;
	int majorID1 = 9999;
	int majorID2 = 9999;
	
	@Before
	public void setUp() throws Exception {
		aStudent = new Login("hnguye80", "hnguye80");
		aStudent.doLogin();
		aStudentProfile = aStudent.getProfile(aStudent.getStudentID());
		updateData = new UpdateData();
		
		this.connectDB = new ConnectDB();
	}

	@After
	public void tearDown() throws Exception {
		aStudent = null;
		
		this.connectDB.disconectDB();
	}
	
	private int addNewPlan() {
		String planName = "Test Plan";
		String query = "";
		int newPlanID;
		
		
		try {
			Statement statement = connectDB.theConnection.createStatement();
			query += "SET FOREIGN_KEY_CHECKS=0; ";
                            statement.executeUpdate(query);
			query = "INSERT INTO collegespdb.tblplan(`planName`,`catalogID`,`majorID`,`minorID`,`majorID2`,`minorID2`,`profileID`) ";
			query += "VALUES (" + "\"" + planName + "\"," + catalogID + ", " + majorID1 + ", " + minorID + ", "
					+ majorID2 + ", " + "9999, " + aStudentProfile.getStudentID() + "); ";
			statement.executeUpdate(query);
			// recordSet will hold a data table and create an SQL object
            query = "SELECT LAST_INSERT_ID() as planID";
			ResultSet recordSet = statement.executeQuery(query);
			recordSet.next();
			newPlanID = recordSet.getInt("planID");
			
						
		} catch (SQLException e) {
			// We commented out the code line below so that we could return false to throw a
			// message to the user
			System.out.println(e.getLocalizedMessage());
			throw new IllegalStateException("[ERROR] there is an error with the SQL query!", e);
		} 
		
		return newPlanID;
	}
	
	private int getLastID() {
		String query = "";
		int lastPlanID;
		
		
		try {
			Statement statement = connectDB.theConnection.createStatement();			
            query = "SELECT MAX(planID) as planID FROM tblplan;";
			ResultSet recordSet = statement.executeQuery(query);
			recordSet.next();
			lastPlanID = recordSet.getInt("planID");
			
						
		} catch (SQLException e) {
			// We commented out the code line below so that we could return false to throw a
			// message to the user
			System.out.println(e.getLocalizedMessage());
			throw new IllegalStateException("[ERROR] there is an error with the SQL query!", e);
		} 
		
		return lastPlanID;
	}

	@Test
	public void testAddPlan() throws SQLException {		
		String query = "";
		int newPlanID, thePlanID;
		Statement statement = connectDB.theConnection.createStatement();
		
		
		newPlanID = addNewPlan();
		
		// get the plan name back
		query = "SELECT planID FROM tblplan WHERE planID = " + newPlanID;
		ResultSet recordSet = statement.executeQuery(query);
		recordSet.next();
		thePlanID = recordSet.getInt("planID");
		assertTrue("Fail to add new plan", thePlanID == newPlanID);
	}
	
	@Test
	public void testDeletePlan() throws SQLException {		
		String query = "";
		int newPlanID, thePlanID;
		
		
		thePlanID = -1;
		
		newPlanID = addNewPlan();
		
		// delete the plan
		query = "DELETE FROM tblplan WHERE planID = " + newPlanID;
		Statement statement = connectDB.theConnection.createStatement();
		statement.executeUpdate(query);
		
		// get the plan name back
		query = "SELECT planID FROM tblplan WHERE planID = " + newPlanID;
		ResultSet recordSet = statement.executeQuery(query);
		while (recordSet.next()) {
			thePlanID = recordSet.getInt("planID");
		}
		
		assertTrue("Fail to add new plan", thePlanID != newPlanID);
	}
	
	@Test
	public void testAddDeleteMultiplePlan() throws SQLException {		
		String query = "";
		int newPlanID, thePlanID, lastPlanID;
		Statement statement = connectDB.theConnection.createStatement();
		
		thePlanID = -1;
		
		lastPlanID = getLastID();
		for (int i = 0; i < 20; i++) {		
			newPlanID = addNewPlan();
			// delete the plan
			query = "DELETE FROM tblplan WHERE planID = " + newPlanID;
			statement.executeUpdate(query);
		}
		thePlanID = getLastID();
		
		
		assertTrue("Fail to add new plan and remove plan", thePlanID != lastPlanID);
	}

}
