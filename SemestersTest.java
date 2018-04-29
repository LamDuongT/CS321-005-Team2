import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Lam Duong
 */
public class SemestersTest
{
	Semesters semesters = new Semesters();
	public static void main(String args[])
	{
	  org.junit.runner.JUnitCore.main("SemestersTest");
	}
	
	@Test public void semestersConstructor_1()
	{
		Semesters m = new Semesters();
	}
	
	@Test public void semestersConstructor_2()
	{
		Semesters m = new Semesters(1);
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
	
	@Test public void getSemestersListTest() 
	{
		Semesters semesters = new Semesters();
		assertNotNull(semesters.getSemesterList());
		assertFalse(semesters.isEmpty());
	}
}