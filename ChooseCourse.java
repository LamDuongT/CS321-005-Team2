import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Lam Duong 
 * FOREWARNING: ChooseCourse methods are tailored for Plan class.
 *              Using ChooseCourse class OUTSIDE of Plan class is dangerous.
 *              ALSO, do not feed in EMPTY coursesList into arguments
 *              of this class.
 * This class allows for the user selection of "wild card"
 * courses. This class will be a part of GradRequirement class.
 * ChooseCourse class shall hold a list of courses to be chosen from
 * within a catalog year.
 * 
 */

public class ChooseCourse {
	// Fields that will be instantiated by constructor parameters
	private int chooseCourseID;
	private int majoriD; 
	private int minorID;
	private String chooseCourseName;
	private String chooseCourseDescription;

	// Fields that will be parsed by parseString() method
	private Course[] coursesToBeChosen; // courses that user could choose from
	private Course[] coursesChosen; // courses that the user has already chosen from
	private int amountOfChoices; // amount of classes that you'll need to pick
	private int amountOfClasses; // amount of classes that are available for picking 

	/**
	 * Constructor for choose course. Make sure there's a courseList that is not empty
	 * is being fed to the arguments.
	 * @param chooseCourseID
	 * @param majorID
	 * @param minorID
	 * @param name
	 * @param description
	 * @param coursesList
	 */
	public ChooseCourse(int chooseCourseID, int majorID, int minorID, String name, String description,
			Courses coursesList, Creditstakens creditsTaken) {
		this.chooseCourseID = chooseCourseID;
		this.majoriD = majorID;
		if (majorID == minorID) {
			throw new RuntimeException("Either majorID or minorID needs to be -1,"
			+ " cannot have both be -1 or both be not -1. Fix by changing database entries.");
		} else {
			this.minorID = minorID;
		}
		this.chooseCourseName = name;
		this.chooseCourseDescription = description;

		// Create data out of String from database
		this.parseString(this.chooseCourseDescription, coursesList);
		
		// Initially, the coursesChosen shall be empty
		this.coursesChosen = new Course[this.amountOfChoices];
		
		this.checkForChosen(creditsTaken);
	}

	/**
	 * ACCESSOR METHODS
	 */
	public int getChooseCourseID() {
		return this.chooseCourseID;
	}

	public int getMajorID() {
		return this.majoriD;
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
		return this.amountOfClasses;
	}
	
	public Course[] getCoursesChosen() {
		return this.coursesChosen;
	}
	
	public Course[] getCoursesToBeChosen() {
		return this.coursesToBeChosen;
	}
	
	/**
	 * 
	 * @param coursesList
	 */
	public void checkForChosen(Creditstakens creditsTaken) {
		for (int i = 0; i < creditsTaken.get
	}

	
	/**
	 * This method will take a String query from the database to parse into the
	 * method. The query looks like something like this:
	 * "%|3|20|CLASS101,CLASS102,CLASS200"
	 * NOTE: THIS CLASS IS PART OF THE CONSTRUCTOR
	 * @author Lam Duong
	 * @param query
	 * @param coursesList
	 */
	private void parseString(String query, Courses coursesList) {
		
		// split string to start deciphering
		String[] parts = query.split("|");
		
		// We start at parts[1] because parts[0] is just "%"
		this.amountOfChoices = Integer.parseInt(parts[1]);
		this.amountOfClasses = Integer.parseInt(parts[2]);
		this.coursesToBeChosen = new Course[this.amountOfClasses];
		
		String[] classes = parts[3].split(",");
		
		// NOTE: classes.length in loop below should be equal to amountOfClasses
		// POTENTIAL BUG if they do not equate!! They should be equal though!
		// IF THERES A BUG: It might also be because coursesList is empty
		// MAKE SURE coursesList is not empty!!!
		for (int i = 0; i < classes.length; i++) {
			Course course = coursesList.getCourseByName(classes[i]);
			coursesToBeChosen[i] = course;
		}
		
	}
}
