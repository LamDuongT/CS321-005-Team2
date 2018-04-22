import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Lam Duong
 * @author Mohammed Alsharaf
 * @author Robert Tagliaferri
 */
public class Plan {
	private final int PLAN_ID;
	private String planName;
	private Majors majorsData;
	private Minors minorsData;
	private Major[] majors;
	private Minor[] minors;
	private Semesters semesters; // All semesters within Database
	private List<Semester> planSemesters;
	private Courses courses;
	private int catalogID;
	private int profileID;
	private GradRequirement requirements;
	private CreditsTaken planCredits; // retrieved when Plan is constructed
	private CreditsTaken profileCoursesTaken; // retrieved when Profile is constructed

	// Constructor for empty plan
	public Plan() {
		this.PLAN_ID = -1;
		this.setValues(-1, -1, "", -1, -1, -1, -1);
	}

	// Standard constructor
	public Plan(int planID, int profileID, int catalogID, String planName, int majorID, int minorID, int major2ID,
			int minor2ID, CreditsTaken profileCoursesTaken, Semesters listOfSemesters) {
		this.PLAN_ID = planID;
		this.profileCoursesTaken = profileCoursesTaken;
		semesters = listOfSemesters;
		this.setValues(profileID, catalogID, planName, majorID, minorID, major2ID, minor2ID);
	}

	// Setting values for constructor
	public void setValues(int profileID, int catalogID, String planName, int majorID, int minorID, int major2ID,
			int minor2ID) {
		this.planName = planName;
		this.catalogID = catalogID;
		this.profileID = profileID;
		this.planCredits = new CreditsTaken();

		// These methods will fetch from database
		this.majorsData = new Majors(catalogID); // fetch majors from DB within a catalog
		this.minorsData = new Minors(catalogID);// fetch minors from DB within a catalog
		this.courses = new Courses(catalogID); // fetch courses from DB within a catalog
		this.planCredits = this.getPlanCreditsTaken(); // fetch CreditsTaken of a Plan from DB
		this.planSemesters = this.getSemestersList(); // fetch Semesters of a Plan from DB
		this.requirements = new GradRequirement(majorID, major2ID, minorID, courses, profileCoursesTaken, planCredits);
		// Instantiation of empty minors and majors
		this.majors = new Major[] { new Major(), new Major() };
		this.minors = new Minor[] { new Minor(), new Minor() };

		this.majors[0] = majorsData.getMajorByID(majorID);
		this.majors[1] = majorsData.getMajorByID(major2ID);
		this.minors[0] = minorsData.getMinorByID(minorID);
		this.minors[1] = minorsData.getMinorByID(minor2ID);
	}

	/**
	 * ACCESSOR METHODS:
	 */

	public int getPlanID() {
		return this.PLAN_ID;
	}

	public String getPlanName() {
		return this.planName;
	}

	public Major[] getMajors() {
		return this.majors;
	}

	public Minor[] getMinors() {
		return this.minors;
	}

	public Majors getMajorsData() {
		return this.majorsData;
	}

	public Minors getMinorsData() {
		return this.minorsData;
	}

	public int getCatalogID() {
		return this.catalogID;
	}

	public int getProfileID() {
		return this.profileID;
	}

	public Courses getCoursesList() {
		return this.courses;
	}

	public List<Semester> getPlanSemesters() {
		return this.planSemesters;
	}
	
	/**
	 * @author Mohammed Alsharaf
	 * @return
	 */
	public List<Semester> getSemestersList() {
		ConnectDB connectdb = new ConnectDB();
		List<Semester> semesterlist = new ArrayList<>();
		String query = "SELECT plan.planID, plan.catalogID, plan.majorID, plan.minorID, plan.majorID2, plan.minorID2, profile.studentID, profile.profileName, course.courseID, course.courseName, credit.semesterID\n"
				+ "FROM tblplan plan INNER JOIN tblcreditstaken credit ON plan.profileID = credit.studentID\n"
				+ "     INNER JOIN tblcourse course on course.courseID = credit.courseID\n"
				+ "     INNer JOIN tblprofile profile on plan.profileID = profile.studentID\n" + "WHERE planID = "
				+ PLAN_ID;
		try ( // Initialize a sql statement
				Statement statement = connectdb.theConnection.createStatement()) {
			ResultSet recordSet = statement.executeQuery(query);
			// this hashmap stores all of semesters' id and also with it's courses' id
			HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
			int catalogID;
			while (recordSet.next()) {
				// hold the plan id
				int plan = recordSet.getInt("planID");
				// hold the catalogID accordingly
				catalogID = recordSet.getInt("catalogID");
				// if the current plan is equal to the given plan, add that semester
				if (plan == PLAN_ID) {
					int semesterID = recordSet.getInt("semesterID");
					if (map.containsKey(semesterID)) {
						map.get(semesterID).add(recordSet.getInt("courseID"));
					}
				}
			}
			/*
			 * for each semester in map, get it's correspoding courses and add them to its
			 * course list
			 */
			map.keySet().forEach((intg) -> {
				Semester sm = semesters.getSemesterByID(intg);
				map.get(intg).forEach((cID) -> {
					sm.addCourse(courses.getCourseByID(cID));
				});
				semesterlist.add(sm);
			});
		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
		return semesterlist;
	}

	/**
	 * Method will retrieve CreditsTaken for plan from Database
	 * 
	 * @author Lam Duong
	 * @return planCreditsTaken
	 */
	public CreditsTaken getPlanCreditsTaken() {
		ConnectDB connectDB = new ConnectDB();
		CreditsTaken planCreditsTaken = new CreditsTaken();
		String query = "SELECT plan.planID, plan.catalogID, plan.majorID, plan.minorID, plan.majorID2, plan.minorID2,\n"
				+ "	profile.studentID, profile.profileName, course.courseID, course.courseName, credit.semesterID,\n"
				+ "	creditsTakenID \n"
				+ "	FROM tblplan plan INNER JOIN tblcreditstaken credit ON plan.profileID = credit.studentID\n"
				+ "	INNER JOIN tblcourse course on course.courseID = credit.courseID\n"
				+ "	INNER JOIN tblprofile profile on plan.profileID = profile.studentID WHERE plan.planID = "
				+ this.PLAN_ID;

		try (Statement statement = connectDB.theConnection.createStatement()) {
			ResultSet recordSet = statement.executeQuery(query);
			while (recordSet.next()) {
				int _creditsTakenID = recordSet.getInt("creditsTakenID");
				int _studentID = recordSet.getInt("studentID");
				int _courseID = recordSet.getInt("courseID");
				int _semesterID = recordSet.getInt("semesterID");

				CreditTaken planCreditTaken = new CreditTaken(_creditsTakenID, _studentID, _courseID, _semesterID);
				planCreditsTaken.getCreditsTakenList().add(planCreditTaken);
			}
		} catch (SQLException e) {
			System.out.println("\n\n\n\n" + e.getLocalizedMessage() + "\n\n\n\n");
			throw new IllegalStateException("[ERROR] there is an error with the SQL query!", e);
		} finally {
			connectDB.disconectDB();
		}
		return planCreditsTaken;
	}
	
	/**
	 * This method will check for a course within a semester and see if it
	 * would belong in this semester by checking its prerequisites.
	 * @author Lam Duong
	 * @param courseToBeAdded
	 * @param targetSemester
	 * @return requirementsMet
	 */
	public boolean checkPrerequisites(Course courseToBeAdded, Semester targetSemester) {
		// Check for if the prerequisite requirements have been met in previous semesters
		boolean requirementsMet = false; // if the requirements are met
		List<Prereq> prerequisites = courseToBeAdded.getPrereqList().getPrereqList();
		int amountOfPrerequisitesMet = 0;
		if (prerequisites.size() != 0) {
			List<Integer> coursesFulfilled= new LinkedList<Integer>();
			for (int i = 0; i < planSemesters.size(); i++) {
				for (int j = 0; j < prerequisites.size(); j++) {
					if (amountOfPrerequisitesMet < prerequisites.size()) {
						if (planSemesters.get(i).contains(courseToBeAdded.getCourseID())){
							amountOfPrerequisitesMet++;
							coursesFulfilled.add(courseToBeAdded.getCourseID());
						}
					} else {
						requirementsMet = true;
						break;
					}
				}
				if (requirementsMet == true) {
					break;
				}
			}
			// Now check prerequisite requirements from user's past
			if (requirementsMet == false) {
				for (int i = 0; i < planCredits.getCreditsTakenList().size(); i++) {
					for (int j = 0; j < prerequisites.size(); j++) {
						CreditTaken course = planCredits.getCreditsTakenList().get(i);
						if (course.getCourseID() == prerequisites.get(j).getCourseID()) {
							amountOfPrerequisitesMet++;
							coursesFulfilled.add(courseToBeAdded.getCourseID());
							requirementsMet = true;
							break;
						}
					}
				}
			}
			if (requirementsMet == false) {
				String s = "The course:" + courseToBeAdded.getCourseID() + " has not met its prerequisite reuirements"
						+ " to take the class in Semester:" + targetSemester.getSemesterID()
						+ "\nThe courses still need to be met are:\n";
				for (int k = 0; k < coursesFulfilled.size(); k++) {
					if (k < coursesFulfilled.size()-1) {
						s += coursesFulfilled.get(k) + ", ";
					} else {
						s += coursesFulfilled.get(k) + ".";
					}
				}
				System.out.println(s);
			}
		}
		return requirementsMet;
	}

	/**
	 * MUTATOR METHODS:
	 */

	/**
	 * This method will either add a new course to a semester or
	 * update a course by moving it to a new semester. Method will return
	 * true if everything went well. It will return false and will also
	 * print out a message if something went wrong.
	 * Should something go wrong, this method will also automatically revert
	 * any changes made to its original state.
	 * @author Lam Duong - 90%
	 * @author Robert Tagliaferri - 10%
	 * @param courseToBeAdded
	 * @param targetSemester
	 * @return successfulAdd (boolean)
	 */
	public boolean addCourseToSemester(Course courseToBeAdded, Semester targetSemester) {
		boolean successfulAdd = false; // if course was added successfully
		
		// STAGE 1: CHECK FOR REQUIREMENTS:
		if (!this.checkPrerequisites(courseToBeAdded, targetSemester)) {
			return false;
		}
		
		// STAGE 2: ADD COURSE
		
		int creditsAfterAdding = courseToBeAdded.getCreditHours() + targetSemester.getCurrentCredits();
		// If adding the course will not surpass maxCredits
		if (creditsAfterAdding <= targetSemester.getCreditMax()) {
			System.out.println("CANNOT ADD COURSE: Adding the course would exceed the preferred maximum credit limit");
		} else {
			// Try to get the creditTakenID of the courseToBeAdded
			int creditTakenID = planCredits.getCreditTakenID(courseToBeAdded);
			// If the courseToBeAdded is already within list of planCredits
			if (creditTakenID != 9999) {
				// CASE: MOVING A COURSE FROM ONE SEMESTER TO ANOTHER
				// Update CreditsTaken by updating the current plansCredit 
				if (planCredits.updateCourseInCreditsTaken(creditTakenID, this.profileID, courseToBeAdded, targetSemester) == true) {
					// The rest of this if block is about UPDATING SEMESTERS
					Semester oldSemester = semesters.getSemesterByID(planCredits.getCreditTakenByID(creditTakenID).getSemesterID());
					if (oldSemester.removeCourse(courseToBeAdded) == true) {
						successfulAdd = targetSemester.addCourse(courseToBeAdded);
						if (successfulAdd == false) {
							// Revert change since something went wrong at the final step
							oldSemester.addCourse(courseToBeAdded);	
						}
					}
				}
			} else {
				// CASE: ADDING A NEW COURSE TO A SEMESTER
				if (planCredits.addCourseToCreditsTaken(this.profileID, courseToBeAdded, targetSemester) == true) {
					// Since it's a new Course, check if it meets requirements
					requirements.addCourse(courseToBeAdded.getCourseID());
					// Add the courseToBeTaken to the targetSemester object in Java
					successfulAdd = targetSemester.addCourse(courseToBeAdded);
				}
			}
		}
		return successfulAdd;
	}
		
	/**
	 * 
	 * @param courseToBeRemoved
	 * @param targetSemester
	 * @return successfulRemoval
	 */
	public boolean removeCourseFromSemester(Course courseToBeRemoved, Semester targetSemester) {
		// if course was removed successfully
		
		boolean successfulRemoval = false;
		// Handle here with checks to see if the course is a valid remove
		// target
		// remove class to planCOursesTaken and the correct semesters
		
		// Check if 
		if (targetSemester.removeCourse(courseToBeRemoved) == true) {
			
		}
		if (successfulRemoval == true) {
			requirements.removeCourse(courseToBeRemoved.getCourseID());
		}
		return successfulRemoval;
	}

	/**
	 * @param majorPosition
	 * @param majorID
	 * @return void
	 */
	public void setMajor(int majorPosition, int majorID) {
		// Add and Change
		// If not removing (majorID not being -1)
		if (majorID >= 0) {

			Major m = this.majorsData.getMajorByID(majorID);

			// If the major being added does not have the same ID as a major within majors
			// array
			if ((majors[0].getMajorID() != m.getMajorID()) && (majors[1].getMajorID() != m.getMajorID())) {

				// If major being added does not have the same name as a current minor within
				// minors array
				if ((!m.getMajorName().equals(minors[0].getMinorName()))
						&& (!m.getMajorName().equals(minors[1].getMinorName()))) {
					majors[majorPosition] = m;
				} else {
					throw new RuntimeException(
							"ERROR: Major-minor conflict: Cannot add a major that is the same name as a minor!");
				}
			} else {
				throw new RuntimeException("ERROR: Major-major conflict: Cannot add the same major again!");
			}
		}
		// Removal process
		else {
			// Removal is only possible with the secondary major
			if (majorPosition == 1) {
				majors[1] = new Major();
			} else {
				// and not the primary major
				throw new RuntimeException("ERROR: Cannot remove the primary major.");
			}
		}

		// Update to Database once finished with modification in Java
		new UpdateData().updatePlan(this, 'u');
	}

	/**
	 * 
	 * @param minorPosition
	 * @param minorID
	 * @return void
	 */
	public void setMinor(int minorPosition, int minorID) {
		// Add and Change
		// If not removing (minorID not being -1)
		if (minorID >= 0) {

			Minor m = this.minorsData.getMinorByID(minorID);

			// If minor being added doesn't already exist within the minors array
			if ((minors[0].getMinorID() != m.getMinorID()) && (minors[1].getMinorID() != m.getMinorID())) {

				// If minor being added does not have the same name as a major within majors
				// array
				if ((!m.getMinorName().equals(majors[0].getMajorName()))
						&& (!m.getMinorName().equals(majors[1].getMajorName()))) {

					minors[minorPosition] = m;

				} else {

					throw new RuntimeException(
							"ERROR: Minor-major conflict: Cannot add a minor that is the same name as a major!");
				}
			} else {
				throw new RuntimeException("ERROR: Minor-minor conflict: Cannot add the same minor again!");
			}
		}
		// Removal of minors
		else {
			minors[minorPosition] = new Minor();
		}
		// Update to Database once finished with modification in Java
		new UpdateData().updatePlan(this, 'u');
	}

	public void setCatalog(int catalogID) {
		this.catalogID = catalogID;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void generateSmartPlan() {
		// TODO: GENERATE SORTING ALGORITHM
	}

	public String toString() {
		String s = "";
		return s;
	}

}
