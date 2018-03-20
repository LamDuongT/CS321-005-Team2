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
	
	