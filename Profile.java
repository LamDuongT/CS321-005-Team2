<<<<<<< HEAD
public class Profile
{
	private final int UNIQUE_ID;
	private final String COLLEGE;
	private String studentID;
	private String studentName;
	private String studentEmail;
	private String username;
	private String password;
	private Plans[] plans;
	private LinkedList<Course> creditsTaken;
	
	
	// Profile is built from the 
	public Profile(int UNIQUE_ID)
	{
		this.UNIQUE_ID = UNIQUE_ID;
		Profile.retrieveProfile(UNIQUE_ID);
		// TODO: Construct the rest of the profile from database
		
	}
	
	// Getter method for UniqueID
	// Profile Object must be constructed first before this method can be called
	public int getUniqueID()
	{
		return this.UNIQUE_ID;
	}
	
	public String getStudentID()
	{
		return this.studentID;
	}
	
	public String getStudentName()
	{
		return this.studentName;
	}
	
	public String getStudentEmail()
	{
		return this.studentEmail;
	}
	
	public String getStudentCollege()
	{
		return this.COLLEGE;
	}
	
	public Plan getPlan(int planPosition)
	{
		return this.plans[planPosition];
	}
	
	public Plan[] getPlans()
	{
		return this.plans;
	}
	
	public void clonePlan(int planPosition)
	{
		if (this.plans.length <= 0) || (this.plans.length >= 10)
		{
			throw new Exception("Invalid plans size!");
		}
		
		
	
	
	// This method will create PARTIAL plans < Jargon only, not an actual class
	// This means that the Plan objects shall only have 2 fields, the planID and planName
	public static void retrieveProfile(studentID)
	{
		
		// TODO: Retrieve fields information from DB
		
		// Plan p1 = new Plan("Plan Name", "Plan ID", null, null, null, null);
	}
	
	
=======
/**
 * 
 */

/**
 * @author Huan Nguyen
 *
 */
public class Profile {
	private int studentID;
	private String netID;
	private String studentName;
	private String studentEmail;
	private String username;
	private String password;
	private String profileName;
	
	public Profile() {
		setValue(-1, "", "", "", "", "", "");
	}
	public Profile (int studentID, String netID, String studentName, String studentEmail, String username, String password, String profileName) {
		setValue(studentID, netID, studentName, studentEmail, username, password, profileName);
	}

	public void setValue(int studentID, String netID, String studentName, String studentEmail, String username, String password, String profileName) {
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
	
	public void setStudentName (String studentName) {
		this.studentName = studentName;
	}
	
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public void setProfileName (String profileName) {
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
>>>>>>> e9d8f16be2bda217ff41e4cdb570141f154b535c
