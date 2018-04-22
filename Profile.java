import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huan Nguyen
 * @author Lam Duong
 * @author Robert Tagliaferri
 * @author Mohammed Alsharaf
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
	private Semesters listOfSemesters; // fetch all semesters from DB

	// Private fields for Plans Management
	private Plans plans;
	private int plansLimit = 10;

	public Profile() {
		plans = new Plans();
		this.coursesTaken = new CreditsTaken();
		setValue(-1, "", "", "", "", "", "");
		listOfSemesters = new Semesters();
	}

	public Profile(int studentID, String netID, String studentName, String studentEmail, String username,
			String password, String profileName) {
		setValue(studentID, netID, studentName, studentEmail, username, password, profileName);

		// semesterID being '9999' because we're taking creditsTaken
		coursesTaken = new CreditsTaken(studentID, 9999);
		this.listOfSemesters = new Semesters();
		this.plans = new Plans(studentID, coursesTaken, listOfSemesters);
	}

	/* MUTATOR METHODS: */

	/**
	 * Method part of the constructor
	 * 
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

	/**
	 * README FIRST! This method will insert a new Plan into the Database with given
	 * attributes. It will then get the auto-incremented planID from the database in
	 * order to create a new Plan Object within the Profile Object in Java. Method
	 * will return false if user has reached the limit of 10 plans or if there is a
	 * problem with connecting to Database.
	 * 
	 * @author Lam Duong
	 * @author Mohammed Alsharaf
	 * @param planName
	 * @param catalogID
	 * @param majorID1
	 * @param majorID2
	 * @param minorID
	 * @return successfulAdd
	 */
	public boolean addPlanToProfile(String planName, int catalogID, int majorID1, int majorID2, int minorID) {
		boolean successfulAdd = true;
		if (plans.getPlans().size() >= plansLimit) {
			successfulAdd = false;
		} else {
			// Get a new planID from database
			int newPlanID = 9999;
			ConnectDB connectDB = new ConnectDB();
			String query = ""/* "select * from tblplan" */;
			try {
				Statement statement = connectDB.theConnection.createStatement();
				query += "SET FOREIGN_KEY_CHECKS=0; ";
                                statement.executeUpdate(query);
				query = "INSERT INTO collegespdb.tblplan(`planName`,`catalogID`,`majorID`,`minorID`,`majorID2`,`minorID2`,`profileID`) ";
				query += "VALUES (" + "\"" + planName + "\"," + catalogID + ", " + majorID1 + ", " + minorID + ", "
						+ majorID2 + ", " + "9999, " + this.studentID + "); ";
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
			} finally {
				// close the connection
				// NOTE: the close connection method need to be called in finally block to
				// ensure the connection is closed
				connectDB.disconectDB();
			}
			Plan planToBeAdded = new Plan(newPlanID, this.studentID, catalogID, planName, majorID1, minorID, majorID2,
					9999, this.coursesTaken, this.listOfSemesters);
			plans.addPlan(planToBeAdded);
		}
		return successfulAdd;
	}

	/**
	 * Method will call on removePlanFromList to remove Plan from Plans List. It
	 * will then update to the Database by removing the plan from the database.
	 * Returns true if the removal was successful.
	 * 
	 * @param planToBeRemoved
	 * @return
	 */
	public boolean removePlanFromProfile(Plan planToBeRemoved) {
		boolean successfulRemoval = false;
		if (plans.removePlanFromList(planToBeRemoved.getPlanID())) {
			successfulRemoval = true;
			new UpdateData().updatePlan(planToBeRemoved, 'd');
		} else {
			System.out.println("ERROR: The plan to be removed was not found in the List of Plans in Profile");
		}
		return successfulRemoval;
	}

	/**
	 * Method simply saves the profile or any changes to database.
	 * @return
	 */
	public boolean saveProfile() {
		boolean successfulSave = true;
		try {
			new UpdateData().updateProfile(this, 'u');
		} catch (IllegalStateException e) {
			successfulSave = false;
		}
		return successfulSave;
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
	
	public Semesters getAllSemesters() {
		return listOfSemesters;
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
		returnString += "Plans within Profile: \n" + plans.toString();

		return returnString;
	}
}
