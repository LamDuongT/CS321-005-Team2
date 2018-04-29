import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author Lam Duong
 *
 */
public class CourseTest
{
	Courses coursesList = new Courses(3);
	public static void main(String args[])
	{
	  org.junit.runner.JUnitCore.main("CourseTest");
	}

	@Test public void courseConstructors()
	{
		Course c1 = new Course(14, "CS110", "Intro to Computer Science", 3, 3, "1|2", "9999");
		Course c2 = new Course(15, "CS112", "Intro to Programming", 3, 3, "1|2", "9999");
		Course c3 = new Course(16, "CS211", "Intro to Object-oriented Programming", 3, 3, "1|2", "9999");
		Course c4 = new Course(17, "CS310", "Data Structure", 3, 3, "1|2", "9999");
		Course c5 = new Course();
		Course c6 = new Course (213123, "asdkjasdlkj", "aksldjlksadj", 3123123, 23123123, "sadkjaskdlj", "skadjlkasdj");
		System.out.println("C1: " + c1.toString() + "\n");
		System.out.println("C2: " + c2.toString() + "\n");
		System.out.println("C3: " + c3.toString() + "\n");
		System.out.println("C4: " + c4.toString() + "\n");
		System.out.println("C5: " + c5.toString() + "\n");
		System.out.println("C6: " + c6.toString() + "\n");
		assertEquals(c1.toString(), " 14      | CS110      | 3           |  3         | 1|2       | 9999      |                          | Intro to Computer Science");
		assertEquals(c2.toString(), " 15      | CS112      | 3           |  3         | 1|2       | 9999      |                          | Intro to Programming     ");
		assertEquals(c3.toString(), " 16      | CS211      | 3           |  3         | 1|2       | 9999      |                          | Intro to Object-oriented Programming");
		assertEquals(c4.toString(), " 17      | CS310      | 3           |  3         | 1|2       | 9999      |                          | Data Structure           ");
		assertEquals(c5.toString(), "");
		assertEquals(c6.toString(), " 213123  | asdkjasdlkj| 3123123     |  23123123  | sadkjaskdlj| skadjlkasdj|                          | aksldjlksadj             ");
	}
	
	@Test public void checkDatabaseRetrieval() {
		Course course = new Course(6, "CS310", "Data Structures", 3, 3, "1|2", "1");
		Course courseFromDatabase = coursesList.getCourseByID(6);
		
		assertEquals(course.getCourseID(), courseFromDatabase.getCourseID());
		assertEquals(course.getCourseDesc(), courseFromDatabase.getCourseDesc());
		assertEquals(course.getCourseName(), courseFromDatabase.getCourseName());
		assertEquals(course.getMajorID(), courseFromDatabase.getMajorID());
		assertEquals(course.getMinorID(), courseFromDatabase.getMinorID());
		assertEquals(course.getCreditHours(), courseFromDatabase.getCreditHours());
		assertEquals(course.toString(), courseFromDatabase.toString());
	}
	
	@Test public void isEmptyTest() {
		Course course = new Course(6, "CS310", "Data Structures", 3, 3, "1|2", "1");
		Course courseFromDatabase = coursesList.getCourseByID(6);
		Course emptyCourse = new Course();
		
		assertFalse(course.isEmpty());
		assertFalse(courseFromDatabase.isEmpty());
		assertTrue(emptyCourse.isEmpty());
	}
	
	@Test public void hasTakenTest() {
		Course c = coursesList.getCourseByID(145);
		assertFalse(c.isTaken());
	}
	
	
}