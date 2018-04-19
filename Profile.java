import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Huan Nguyen
 * @author Lam Duong
 */
public class Profile {
	private int studentID;
	private String netID;
	private String studentName;
	private String studentEmail;
	private String username;
	private String password;
	private String profileName;
	private CreditsTaken coursesTaken;
	
	// Private fields for Plans Management
	private Plans plans;
	private int plansLimit = 10;
	private LinkedList<Plan> plansToBeAdded;
	private LinkedList<Plan> plansToBeDeleted;
	private LinkedList<Plan> plansToBeUpdated;
	

	public Profile() {
		setValue(-1, "", "", "", "", "", "");
	}

	public Profile(int studentID, String netID, String studentName, String studentEmail, String username,
			String password, String profileName) {
		setValue(studentID, netID, studentName, studentEmail, username, password, profileName);

		// semesterID being '9999' because we're taking creditsTaken
		coursesTaken = new CreditsTaken(studentID, 9999);
		this.plans = new Plans(studentID, coursesTaken);
	}
	
	/* MUTATOR METHODS: */
	
	/**
	 * Method part of the constructor
	 * @param studentID
	 * @param netID
	 * @param studentName
	 * @param studentEmail
	 * @param username
	 * @param password
	 * @param profileName
	 */
	public void setValue(int studentID, String netID, String studentName, String studentEmail, String username,
			String password, String profileName) {
		this.studentID = studentID;
		this.netID = netID;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.username = username;
		this.password = password;
		this.profileName = profileName;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public boolean addPlanToProfile(String planName, int catalogID, int majorID1, int majorID2, int minorID) {
		if (plans.getPlans().size() >= 10) {
			return false;
		}
		// Get a new planID from database
		int newPlanID = 9999;
		ConnectDB connectDB = new ConnectDB();
		String query = "select * from tblplan";
		try {
			query += "INSERT INTO 'collegespdb'.'tblplan' ('catalogID', 'majorID', 'minorID', 'majorID2', 'minorID2', 'profileID') ";
			query += "VALUES ('" + catalogID + "', '" + majorID1 + "', '" + minorID + "', '" + majorID2 + "', " + "'9999' , '" +  this.studentID + "');";
			query += "SELECT LAST_INSERT_ID() as planID";
			Statement statement = connectDB.theConnection.createStatement();

			// recordSet will hold a data table and create an SQL object
			ResultSet recordSet = statement.executeQuery(query);
			recordSet.next();
			newPlanID = recordSet.getInt("planID");
		} catch (SQLException e) {
			return false;
			// We commented out the code line below so that we could return false to throw a message to the user
			// throw new IllegalStateException("[ERROR] there is an error with the SQL query!", e);
		} finally {
			// close the connection
			// NOTE: the close connection method need to be called in finally block to
			// ensure the connection is closed
			connectDB.disconectDB();
		}
		// (int planID, int profileID, int catalogID, String planName, int majorID, int minorID, int major2ID, int minor2ID, CreditsTaken profileCoursesTaken)
		Plan planToBeAdded = new Plan(newPlanID, this.studentID, catalogID, planName, majorID1, minorID, majorID2, 9999, this.coursesTaken);
		plans.addPlan(planToBeAdded);
		return true;
	}
	
	public boolean removePlan(int planID) {
		Plan planToBeRemoved = plans.getPlanByID(planID);
		
		return true;
	}
	
	public boolean updatePlan(int planID) {
		return true;
		
	}
	
	public void saveProfile() {
		
	}
	
	/* ACCESSOR METHODS: */

	public int getStudentID() {
		return this.studentID;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public String getStudentEmail() {
		return this.studentEmail;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public String getNetID() {
		return this.netID;
	}
	
	public CreditsTaken getCoursesTaken() {
		return coursesTaken;
	}
	
	/**
	 * Override toString method for testing purpose
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		String returnString = "StudentID = " + this.studentID + "\n";
		returnString += "netId = " + this.netID + "\n";
		returnString += "studentName = " + this.studentName + "\n";
		returnString += "studentEmail = " + this.studentEmail + "\n";
		returnString += "username = " + this.username + "\n";
		returnString += "password = " + this.password + "\n";
		returnString += "profileName = " + this.profileName + "\n";
		
		return returnString;
	}
	
	/**/

}
