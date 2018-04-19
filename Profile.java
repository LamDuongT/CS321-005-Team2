/*import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.LinkedList;
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
	
	public boolean addPlan(int planID) {
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

}
