import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Lam Duong 
 * This class allows for the user selection of "wild card"
 * classes. This class will be a part of GradRequirement class.
 * ChooseCourse class shall hold a list of courses to be chosen from
 * within a catalog year.
 * Choose course is also a child class of Plan.
 */

public class ChooseCourse extends Plan {
	// Fields that will be instantiated by constructor parameters
	private int chooseCourseID;
	private int majoriD; // either majorID or minorID; Cannot have both; -1 for empty
	private int minorID; // either minorID or majorID; Cannot have both; -1 for empty
	private String chooseCourseName;
	private String chooseCourseDescription;

	// Fields that will be parsed by parseString() method
	private Course[] coursesToBeChosen; // courses that user could choose from
	private Course[] coursesChosen;
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
	public ChooseCourse(int chooseCourseID, int majorID, int minorID, String name, String description, Courses coursesList) {
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

		this.parseString(this.chooseCourseDescription, coursesList);
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

	/**
	 * MUTATOR METHODS
	 */
	
	/**
	 * Choose a course from the list of choose courses
	 * @param course
	 */
	public void chooseTheCourse(Course course) {
		
	}
	
	/**
	 * @param course
	 */
	public void removeChooseCourse(Course course)
		 
	}
	
	/**
	 * 
	 * @param course
	 */
	public void checkChosenInPlan(Course course) {
		
	}

	/**
	 * This method will take a String query from the database to parse into the
	 * method. The query looks like something like this:
	 * "%|3|20|CLASS101,CLASS102,CLASS200"
	 * 
	 * @author Lam Duong
	 * @param query
	 * @return void
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
