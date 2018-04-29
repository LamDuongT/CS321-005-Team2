import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Lam Duong
 */
public class SemestersTest
{
	/**
	 * TESTING SEMESTERS.JAVA CLASS
	 */
	Semesters semesters = new Semesters();
	public static void main(String args[])
	{
	  org.junit.runner.JUnitCore.main("SemestersTest");
	}
	
	@Test public void semestersConstructor_1()
	{
		Semesters m = new Semesters();
		assertFalse(m.isEmpty());
	}
	
	@Test public void semestersConstructor_2()
	{
		Semesters m = new Semesters(1);
		assertFalse(m.isEmpty());
	}
	
	@Test public void semestersPrint() {
		for (Semester semester : semesters.getSemesterList()) {
			assertNotNull("Semester should not be null", semester);
			System.out.println(semester);
		}
	}
	
	@Test public void getSemesterByIDTest() 
	{
		Semester s1 = semesters.getSemesterByID(1);
		Semester s2 = semesters.getSemesterByID(2);
		Semester s3 = semesters.getSemesterByID(3);
		Semester s4 = semesters.getSemesterByID(4);
		Semester s5 = semesters.getSemesterByID(9999); // empty semester within database
		Semester s6 = semesters.getSemesterByID(103); // should not be able to find this, so return an empty semester with ID 9999
		assertEquals(s1.getSemesterID(), 1);
		assertEquals(s2.getSemesterID(), 2);
		assertEquals(s3.getSemesterID(), 3);
		assertEquals(s4.getSemesterID(), 4);
		assertEquals(s5.getSemesterID(), 9999);
		assertTrue(s5.isEmpty());
		assertEquals(s6.getSemesterID(), 9999);
		assertTrue(s6.isEmpty());
	}

	@Test public void getSemestersListTest() 
	{
		Semesters semesters = new Semesters();
		assertNotNull(semesters.getSemesterList());
		assertFalse(semesters.isEmpty());
	}
	
	/**
	 * TESTING SEMESTER.JAVA CLASS
	 */
	
	@Test public void semesterConstructor_1() 
	{
		Semester semester = new Semester();
		assertFalse(semester.isLocked());
		assertEquals(semester.getCurrentCredits(), 0);
		assertEquals(semester.toString(), " 9999      |                |	                         |  0         |  18        ");
	}
	
	@Test public void semesterConstructor_2() 
	{
		Semester semester = new Semester(2, "Fall 2016", "Fall 2016", 0, 18, new ArrayList<>());
		assertEquals(semester.toString(), " 2         | Fall 2016      |	Fall 2016                |  0         |  18        ");
		semester = new Semester(7, "Summer 2018", "Summer 2018", 0, 15, new ArrayList<>());
		assertEquals(semester.toString(), " 7         | Summer 2018    |	Summer 2018              |  0         |  15        ");
	}
	
	/**
	 * Adding a few courses into a regular semester with no anomalies
	 * Meaning all prerequisites are met and no  
	 */
	@Test public void semesterAddCourse_1() {
		Courses coursesList = new Courses(3);
		Semester sem = semesters.getSemesterByID(2);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		Course c4 = coursesList.getCourseByID(42);
		Course c5 = coursesList.getCourseByID(43);
		
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertTrue(sem.addCourse(c3));
		assertTrue(sem.addCourse(c4));
		assertTrue(sem.addCourse(c5));
		assertFalse(sem.isEmpty());
	}
	
	/**
	 * Test case to see if semesters can surpass credit limit
	 */
	@Test public void semesterAddCourse_2() {
		Courses coursesList = new Courses(3);
		Semester sem = semesters.getSemesterByID(1);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		Course c4 = coursesList.getCourseByID(42);
		Course c5 = coursesList.getCourseByID(43);
		
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertTrue(sem.addCourse(c3));
		assertTrue(sem.addCourse(c4));
		assertFalse(sem.addCourse(c5));
		assertFalse(sem.isEmpty());
	}
	
	/**
	 * Test case to see if semesters can add the same course more than once to semester
	 * Test case will pass if classes are not repeated
	 */
	@Test public void semesterAddCourse_3() {
		Courses coursesList = new Courses(3);
		Semester sem = semesters.getSemesterByID(3);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertFalse(sem.addCourse(c1));
		assertFalse(sem.addCourse(c1));
		assertFalse(sem.addCourse(c2));
		assertFalse(sem.addCourse(c1));
		assertTrue(sem.addCourse(c3));
		assertFalse(sem.addCourse(c1));
		assertFalse(sem.addCourse(c3));
		for (Course c : sem.getCourses()) {
			System.out.print(c.getCourseName() + ", ");
		}
	}
	
	/**
	 * Simply add a course and remove a course and check if they should be empty or not
	 */
	@Test public void semesterRemoveCourse_1() {
		Courses coursesList = new Courses(3);
		Semester sem = semesters.getSemesterByID(2);
		assertTrue(sem.isEmpty());
		Course c1 = coursesList.getCourseByID(1);
		assertTrue(sem.addCourse(c1));
		System.out.println("THE COURSE BEING ADDED IS: " + sem.getCourses().get(0).getCourseID());
		assertTrue(sem.removeCourse(c1));
	}
	
	/**
	 * Adding 5 courses and removing them sequentially
	 */
	@Test public void semesterRemoveCourse_2() {
		Courses coursesList = new Courses(4);
		Semester sem = semesters.getSemesterByID(2);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		Course c4 = coursesList.getCourseByID(42);
		Course c5 = coursesList.getCourseByID(43);
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertTrue(sem.addCourse(c3));
		assertTrue(sem.addCourse(c4));
		assertTrue(sem.addCourse(c5));
		assertTrue(sem.removeCourse(c1));
		assertTrue(sem.removeCourse(c2));
		assertTrue(sem.removeCourse(c3));
		assertTrue(sem.removeCourse(c4));
		assertTrue(sem.removeCourse(c5));
	}
	
	/**
	 *  Adding and removing sporadically
	 */
	@Test public void semesterRemoveCourse_3() {
		Courses coursesList = new Courses(4);
		Semester sem = semesters.getSemesterByID(2);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		Course c4 = coursesList.getCourseByID(42);
		assertFalse(sem.removeCourse(c1));
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertTrue(sem.addCourse(c3));
		assertTrue(sem.removeCourse(c3));
		assertTrue(sem.removeCourse(c2));
		assertTrue(sem.removeCourse(c1));
		assertFalse(sem.removeCourse(c1));
		assertFalse(sem.removeCourse(c1));
		assertFalse(sem.removeCourse(c2));
		assertFalse(sem.removeCourse(c4));
	}
	
	@Test public void containsTest() {
		Courses coursesList = new Courses(4);
		Semester sem = semesters.getSemesterByID(2);
		assertTrue(sem.isEmpty());
		
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(70);
		
		assertTrue(sem.addCourse(c1));
		assertTrue(sem.addCourse(c2));
		assertTrue(sem.addCourse(c3));
		assertTrue(sem.contains(c1.getCourseID()));
		assertTrue(sem.contains(c2.getCourseID()));
		assertTrue(sem.contains(c2.getCourseID()));

		assertTrue(sem.removeCourse(c3));
		assertTrue(sem.removeCourse(c2));
		assertTrue(sem.removeCourse(c1));
		assertFalse(sem.contains(c1.getCourseID()));
		assertFalse(sem.contains(c2.getCourseID()));
		assertFalse(sem.contains(c2.getCourseID()));
	}
}