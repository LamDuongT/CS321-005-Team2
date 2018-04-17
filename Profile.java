/*import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
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
	private Plans plans;

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
	 * Method to insert creditsTaken into each plan within plans
	 * @param plans
	 */
	public void addPlansCreditsTaken(Plans plans) {
		ConnectDB connectDB = new ConnectDB();
		CreditsTaken plansCreditsTaken = null;
		String queryString = "";
		for (int i = 0; i < plans.getPlans().size(); i++) {
			queryString = "SELECT  plan.planID, plan.catalogID, plan.majorID, "
					+ "plan.minorID, plan.majorID2, plan.minorID2, profile.studentID, "
					+ "profile.profileName ,course.courseID, course.courseName, credit.semesterID"
					+ "FROM tblplan plan INNER JOIN tblcreditstaken credit "
					+ "ON plan.profileID = credit.studentID "
					+ "INNER JOIN tblcourse course on course.courseID = credit.courseID "
				    + "INNER JOIN tblprofile profile on plan.profileID = profile.studentID "
					+ "WHERE planID = " + plans.getPlans().get(i).getPlanID();
			try {
				
			}
		}
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
