import java.util.LinkedList;
import java.util.List;

/**
 * @author Lam Duong 
 * @author Mohammed Alsharf
 * FOREWARNING: ChooseCourse methods are tailored for Plan
 * class. Using ChooseCourse class OUTSIDE of Plan class is dangerous.
 * ALSO, do not feed in EMPTY coursesList into arguments of this class.
 * This class allows for the user selection of "wild card" courses. This
 * class will be a part of GradRequirement class. ChooseCourse class
 * shall hold a list of courses to be chosen from within a catalog year.
 */

public class ChooseCourse {
	// Fields that will be instantiated by constructor parameters
	private int chooseCourseID;
	private int majorID;
	private int minorID;
	private String chooseCourseName;
	private String chooseCourseDescription;

	// Fields that will be parsed by parseString() method
	private List<Course> coursesToBeChosen; // courses that user could choose from
	private List<Course> coursesChosen; // courses that the user has already chosen from
	private int amountOfChoices; // amount of classes that user will need to choose
	private int amountOfCourses; // amount of classes that are available for choosing
	private int amountOfChosen; // amount of classes that user has already chosen.
	// amountOfChosen CANNOT EXCEED amountOfChoices


	/**
	 * Constructor for ChooseCourse. Make sure there's a courseList that is not
	 * empty is being fed to the arguments.
	 * @author Lam Duong
	 * @author Mohammed Alsharf
	 * @param chooseCourseID
	 * @param majorID
	 * @param minorID
	 * @param name
	 * @param description
	 * @param coursesList
	 * @param creditsTaken
	 */
	public ChooseCourse(int chooseCourseID, int majorID, int minorID, String name, String description,
			Courses coursesList, CreditsTaken creditsTaken) {
		this.chooseCourseID = chooseCourseID;
		this.majorID = majorID;
		// minorID can only be added if it does not equal to majorID
		if (majorID == minorID) {
			throw new RuntimeException("Either majorID or minorID needs to be -1,"
					+ " cannot have both be -1 or both be not -1. Fix by changing database entries.");
		} else {
			this.minorID = minorID;
		}
		this.chooseCourseName = name;
		this.chooseCourseDescription = description;
		this.amountOfChosen = 0;

		// Create data out of String from database
		this.parseString(this.chooseCourseDescription, coursesList);

		// Initially, the coursesChosen shall be empty.
		// coursesChosen shall have a full array of empty Course objects
		this.coursesChosen = new LinkedList<Course>();

		this.reAdjustChooseCourse(creditsTaken);
	}

	/**
	 * ACCESSOR METHODS
	 */
	public int getChooseCourseID() {
		return this.chooseCourseID;
	}

	public int getMajorID() {
		return this.majorID;
	}

	public int getMinorID() {
		return this.minorID;
	}

	public String getName() {
		return this.chooseCourseName;
	}

	public String getDescription() {
		return this.chooseCourseDescription;
	}

	public int getAmountOfChoices() {
		return this.amountOfChoices;
	}

	public int getAmountOfClasses() {
		return this.amountOfCourses;
	}
	
	public int getAmountOfChosen() {
		return amountOfChosen;
	}

	public List<Course> getCoursesChosen() {
		return this.coursesChosen;
	}

	public List<Course> getCoursesToBeChosen() {
		return this.coursesToBeChosen;
	}
	
	/**
	 * Return a string to display the status. These numbers should be equal to each other.
	 * @return s
	 */
	public String checkStatus() {
		String s = new String();
		s += "Status Check:\n coursesChosen.size(): " + coursesChosen.size() + "\n" +
				"amountOfChosen: " + amountOfChosen + "\n" +
				"coursesToBeChosen.size(): " + coursesToBeChosen.size() + "\n" +
				"amountOfCourses:" + amountOfCourses + "\n" +
				"amountOfChoices:" + amountOfChoices;
		return s;
	}

	/**
	 * Check if the all the values align.
	 * This is to check if 
	 * @return working
	 */
	public boolean stillWorking() {
		boolean working = true;
		if (coursesChosen.size() != amountOfChosen) {
			if (coursesToBeChosen.size() != amountOfCourses) {
				if (amountOfChoices > amountOfChosen) {
					working = false;
				}
			}
		}
		return working;
	}


	/**
	 * Checking coursesChosen, coursesToBeChosen, and coursesTaken to
	 * update chooseCourse when there are changes made to the Plan
	 * or if the chooseCourse has just been instantiated.
	 * NOTE: Make sure to run this at the end of CRG
	 * WARNING: Excessive commenting ahead
	 * @author Lam Duong
	 * @param creditsTaken
	 */
	@SuppressWarnings("unlikely-arg-type") 
	public void reAdjustChooseCourse(CreditsTaken creditsTaken) {
		
		List<CreditTaken> coursesTaken = creditsTaken.getCreditsTakenList();
		
		// coursesFulfilled is the union of coursesToBeChosen and coursesTaken
		// In short, it has the items that are only in both lists.
		List<Course> coursesFulfilled = new LinkedList<Course>();
		
		// Create a list that will sync positions with coursesFulfilled and coursesToBeChosen
		List<Integer> coursesToBeChosenPositions = new LinkedList<Integer>();

		for (int i = 0; i < coursesTaken.size(); i++) {
			// Loop through courses that the user wants to take in choose course
			for (int j = 0; j < coursesToBeChosen.size(); j++) {
				if (coursesTaken.get(i).getCourseID() == coursesToBeChosen.get(j).getCourseID()) {
					coursesFulfilled.add(coursesToBeChosen.get(j));
					coursesToBeChosenPositions.add(j);
				}
			}
		}
		// Remove any courseChosen if they are not in coursesFulfilled
		for (int i = 0; i < coursesChosen.size(); i++) {
			if (!coursesFulfilled.contains(coursesChosen.get(i))) {
				coursesChosen.remove(i);
				amountOfChosen--;
			}
		}
		
		// Add any courseFulfilled to coursesChosen if they don't exist in coursesChosen
		for (int i = 0; i < coursesFulfilled.size(); i++) {
			// You can still pick more classes
			if (amountOfChosen < amountOfChoices) {
				// If coursesChosen has the courseFulfilled
				if (!coursesChosen.contains(coursesFulfilled.get(i))) {
					// Then add to list of coursesChosen
					coursesChosen.add(coursesToBeChosen.get(coursesToBeChosenPositions.get(i)));
					// And remove to the list of coursesToBeChosen
					coursesToBeChosen.remove(coursesToBeChosenPositions.get(i));
					amountOfChosen++;
				}
			}
		}
	}

	/**
	 * This method will take a String query from the database to parse into the
	 * method. The query looks like something like this:
	 * "%|3|20|CLASS101,CLASS102,CLASS200" NOTE: THIS METHOD IS PART OF THE
	 * CONSTRUCTOR
	 * 
	 * @author Lam Duong
	 * @author Mohammed Alsharf
	 * @param query
	 * @param coursesList
	 */
	private void parseString(String query, Courses coursesList) {

		// split string to start deciphering
		String[] parts = query.split("\\|");

		// We start at parts[1] because parts[0] is just "%"
		this.amountOfChoices = Integer.parseInt(parts[1]);
		this.amountOfCourses = Integer.parseInt(parts[2]);
		this.coursesToBeChosen = new LinkedList<Course>();

		String[] classes = parts[3].split("\\,");

		// NOTE: classes.length in loop below should be equal to amountOfCourses
		// POTENTIAL BUG if they do not equate!! They should be equal though!
		// IF THERES A BUG: It might also be because coursesList is empty
		// MAKE SURE coursesList is not empty!!!
		for (int i = 0; i < classes.length; i++) {
			Course course = coursesList.getCourseByName(classes[i]);
			coursesToBeChosen.add(course);
		}

	}
	
	public String toString() {
		String s = new String();
		s += "ChooseCourse Object:\n" +
			"-------------------------------------\n" + 
			"DATABASE FIELDS: \n" + 
			"chooseCourseID: " + this.chooseCourseDescription + "\n" +
			"majorID: " + this.majorID + "\n" +
			"minorID: " + this.minorID + "\n" +
			"name:" + this.chooseCourseName + "\n" +
			"description: " + this.chooseCourseDescription + "\n" +
			"-------------------------------------\n" + 
			"PARSED FIELDS: \n" +
			"coursesToBeChosen: " + this.coursesToBeChosen + "\n" +
			"coursesChosen: " + this.coursesChosen + "\n" + 
			"amountOfChoices: " + this.amountOfChoices + "\n" +
			"amountOfCourses: " + this.amountOfCourses +"\n" +
			"amountOfChosen: " + this.amountOfChosen;
		return s;
	}
}
