import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huan Nguyen
 * @author Lam Duong
 */
public class Courses {
	// the list of Course objects
	private List<Course> courseList = new LinkedList<>();
	private Course aCourse;

	/**
	 * Initialize the list of Course objects from database
	 */
	public Courses() {
		getCourseData(-1, -1, -1);
	}

	/**
	 * Initialize the list of Course for a catalog year from database
	 * @param catalogID
	 *            the catalog year
	 */
	public Courses(int catalogID) {
		getCourseData(catalogID, -1, -1);
	}
	
	/**
	 * Initialize the list of Course for a catalog year from database
	 * 
	 * @param catalogID
	 *            the catalog year
	 * @param majorID
	 *            the majorID
	 */
	public Courses(int catalogID, int majorID) {
		getCourseData(catalogID, majorID, -1);
	}

	/**
	 * Initialize the list of Course for a catalog year from database
	 * 
	 * @param catalogID
	 *            the catalog year
	 * @param majorID
	 *            the majorID
	 * @param minorID
	 *            the minorID
	 */
	public Courses(int catalogID, int majorID, int minorID) {
		getCourseData(catalogID, majorID, minorID);
	}
	
	/**
	 * This method will return a course by ID within list of Courses.
	 * However, if there is no match, it shall return an empty Course Object
	 * @author Lam Duong
	 * @param courseID
	 * @return Course Object
	 */
	public Course getCourseByID(int courseID) {
		Course aCourse = new Course();
		for (int index = 0; index < this.courseList.size(); index++) {
			aCourse = this.courseList.get(index);
			if (aCourse.getCourseID() == courseID) {
				return aCourse;
			}
		}
		return new Course();
	}
	
	/**
	 * This method will return a course by name within list of Courses.
	 * However, if there is no match, it shall return an empty Course Object
	 * @author Lam Duong
	 * @param courseName
	 * @return Course Object
	 */
	public Course getCourseByName(String courseName) {
		Course aCourse = new Course();
		for (int index = 0; index < this.courseList.size(); index++) {
			aCourse = this.courseList.get(index);
			if (aCourse.getCourseName().toLowerCase().contains(courseName.toLowerCase())) {
				return aCourse;
			}
		}
		return new Course();
	}
	
	
	public List<Course> getCoursesList() {
		return this.courseList;
	}
	
	
	/**
	 * @author Huan nguyen
	 * This method will return a list of course based on the search string
	 * */
	
	public List<Course> search (String searchQuery) {
		List<Course> courseList = new LinkedList<>();
		
		// search course when the searchQuery is not empty
		if (!searchQuery.equals("")) {
			Course aCourse = new Course();
			
			for (int index = 0; index < this.courseList.size(); index++) {
				aCourse = this.courseList.get(index);
				if (aCourse.getCourseName().toLowerCase().indexOf(searchQuery.toLowerCase()) != -1) {
					courseList.add(aCourse);
				}
			}
		}		
		
		return courseList;
	}

	/**
	 * fetch all data from the table course and add to the list
	 * 
	 * @return
	 */
	private void getCourseData(int catalogID, int majorID, int minorID) {
		
		ConnectDB connectdb = new ConnectDB();

		try {
			// local variable for course object
			int courseID;
			String courseName;
			String courseDesc;
			int creditHours;
			int _catalogID;
			String _majorID;
			String _minorID;
			
			String whereCondition = "";
			// if the catalogID is not -1
			if (catalogID != -1) {
				whereCondition = "WHERE catalogID = '" + catalogID + "'";
			} else {
				whereCondition = "WHERE 1=1 ";
			}

			// if the catalogID is different from 1, meaning that we need to fetch data for courses by the catalogID
			// also, if majorOrMinorID != -1, meaning that we need to constrain the major or minor as well
			if (majorID != -1 && minorID != -1) {				
				whereCondition += " AND majorID like '%" + majorID + "%' OR minorID like '%" + minorID + "%'";				
			}
			else if (majorID != -1 && minorID == -1) {
				whereCondition += " AND majorID like '%" + majorID + "%'";
			}
			else if (majorID == -1 && minorID != -1) {
				whereCondition += " AND minorID like '%" + minorID + "%'";
			}
			
			
			// the query string
			String queryString = "SELECT courseID, courseName, courseDesc, creditHours, catalogID, majorID, minorID FROM `tblcourse` ";
			queryString += whereCondition;
			queryString += "ORDER BY courseID ASC";

			 System.out.println(queryString);

			// Initialize a SQL statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mySQL Workbench
			ResultSet recordSet = statement.executeQuery(queryString);

			// loop through each record in the data table
			while (recordSet.next()) {
				// assign value for local variable accordingly to the query field
				courseID = recordSet.getInt("courseID");
				courseName = recordSet.getString("courseName");
				courseDesc = recordSet.getString("courseDesc");
				creditHours = recordSet.getInt("creditHours");
				_catalogID = recordSet.getInt("catalogID");
				_majorID = recordSet.getString("majorID");
				_minorID = recordSet.getString("minorID");

				// instantiate aCourse object with all local variable
				// the prereqList will be instantiated in the constructor of Course class
				aCourse = new Course(courseID, courseName, courseDesc, creditHours, _catalogID, _majorID, _minorID);
				// add the aCourse to the courseList for later uses
				this.courseList.add(aCourse);
			}
			// close the sql statement
			statement.close();

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			// close the connection
			// NOTE: the close connection method need to be called in finally block to
			// ensure the connection is closed
			connectdb.disconectDB();
		}
	}

	/**
	 * Override the toString method for testing purpose
	 */
	public String toString() {
		String returnString = "courseID | courseName     | creditHours | catalogID  | majorID   | minorID   | Prereq List              | courseDesc\n";
		// loop through the list and print out all elements
		for (int index = 0; index < this.courseList.size(); index++) {
			aCourse = this.courseList.get(index);
			returnString += aCourse.toString() + "\n";
		}
		return returnString;
	}
}
