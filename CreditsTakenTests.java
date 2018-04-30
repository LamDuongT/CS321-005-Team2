import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

/**
 * NOTE: This class tests for both CreditTaken.java AND CreditsTaken.java
 * 
 * @author Lam Duong
 */
public class CreditsTakenTests {
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("CreditsTakenTests");
	}

	/*
	 * TESTS FOR CREDITTAKEN
	 * -----------------------------------------------------------------------------
	 */
	@Test
	public void creditTakenConstructor_1() {
		System.out.println("Now testing creditTakenConstructor_1:");
		CreditTaken ct = new CreditTaken();
		System.out.println(ct);
		assertEquals(ct.getCourseID(), -1);
		assertEquals(ct.getCreditTakenID(), -1);
		assertEquals(ct.getSemesterID(), -1);
		assertEquals(ct.getStudentID(), -1);
	}

	@Test
	public void creditTakenConstructor_2() {
		CreditTaken ct1 = new CreditTaken(2, 9, 2, 2);
		assertEquals(ct1.getCreditTakenID(), 2);
		assertEquals(ct1.getStudentID(), 9);
		assertEquals(ct1.getCourseID(), 2);
		assertEquals(ct1.getSemesterID(), 2);
		CreditTaken ct2 = new CreditTaken(2, 9, 2, 2);
		assertEquals(ct2.getCreditTakenID(), 2);
		assertEquals(ct2.getStudentID(), 9);
		assertEquals(ct2.getCourseID(), 2);
		assertEquals(ct2.getSemesterID(), 2);
		CreditTaken ct3 = new CreditTaken(3, 9, 3, 3);
		assertEquals(ct3.getCreditTakenID(), 3);
		assertEquals(ct3.getStudentID(), 9);
		assertEquals(ct3.getCourseID(), 3);
		assertEquals(ct3.getSemesterID(), 3);
		CreditTaken ct4 = new CreditTaken(11, 9, 204, 5);
		assertEquals(ct4.getCreditTakenID(), 11);
		assertEquals(ct4.getStudentID(), 9);
		assertEquals(ct4.getCourseID(), 204);
		assertEquals(ct4.getSemesterID(), 5);
		System.out.println("Now testing creditTakenConstructor_2:");
		System.out.println(ct1 + "\n" + ct2 + "\n" + ct3 + "\n" + ct4);
	}

	/*
	 * TESTS FOR CREDITSTAKEN
	 * -----------------------------------------------------------------------------
	 */
	@Test
	public void creditsTakenConstructor_1() {
		CreditsTaken cts = new CreditsTaken();
		assertTrue(cts.getCreditsTakenList().isEmpty());
		System.out.println("Testing for an empty CreditsTaken:\n" + cts);
	}

	@Test
	public void creditsTakenConstructor_2() {
		CreditsTaken cts = new CreditsTaken(9);
		assertFalse(cts.getCreditsTakenList().isEmpty());
		System.out.println("Testing for a student's CreditsTaken:\n" + cts);
	}

	@Test
	public void creditsTakenConstructor_3() {
		CreditsTaken cts = new CreditsTaken(9, 2);
		assertFalse(cts.getCreditsTakenList().isEmpty());
		System.out.println("Testing for a semester's CreditsTaken:\n" + cts);
	}

	@Test
	public void containsTest_1() {
		CreditsTaken cts = new CreditsTaken(9, 2);
		assertTrue(cts.contains(1));
		assertTrue(cts.contains(179));
		assertTrue(cts.contains(34));
		assertFalse(cts.contains(204));
		assertFalse(cts.contains(211));
	}

	@Test
	public void containsTest_2() {
		CreditsTaken cts = new CreditsTaken(9);
		assertTrue(cts.contains(1));
		assertTrue(cts.contains(2));
		assertTrue(cts.contains(3));
		assertTrue(cts.contains(6));
		assertTrue(cts.contains(211));
		assertTrue(cts.contains(204));
		assertFalse(cts.contains(180));
		assertFalse(cts.contains(9999));
	}

	/**
	 * Standard retrieval from within a CreditsTaken history.
	 */
	@Test
	public void getCreditTakenByCourseID_1() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(81);
		Course c4 = coursesList.getCourseByID(34);
		Course c5 = coursesList.getCourseByID(37);

		assertEquals(cts.getCreditTakenByCourseID(1).getCourseID(), c1.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(2).getCourseID(), c2.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(81).getCourseID(), c3.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(34).getCourseID(), c4.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(37).getCourseID(), c5.getCourseID());
	}

	/**
	 * Get CreditTaken objects that do not exist. Should return -1 for all.
	 */
	@Test
	public void getCreditTakenByCourseID_2() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);
		Course c1 = coursesList.getCourseByID(9999);
		Course c2 = coursesList.getCourseByID(532);
		Course c3 = coursesList.getCourseByID(765);
		Course c4 = coursesList.getCourseByID(3999);

		// These should just all be -1
		assertEquals(cts.getCreditTakenByCourseID(9999).getCourseID(), c1.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(532).getCourseID(), c2.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(765).getCourseID(), c3.getCourseID());
		assertEquals(cts.getCreditTakenByCourseID(3999).getCourseID(), c4.getCourseID());
	}

	@Test
	public void getCreditTakenByCreditTakenID() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);

		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(35);
		Course c3 = coursesList.getCourseByID(200);

		assertEquals(cts.getCreditTakenByID(1).getCourseID(), c1.getCourseID());
		assertEquals(cts.getCreditTakenByID(13).getCourseID(), c2.getCourseID());
		assertEquals(cts.getCreditTakenByID(19).getCourseID(), c3.getCourseID());
	}

	@Test
	public void getCreditsTakenListTest() {
		CreditsTaken cts = new CreditsTaken();
		assertTrue(cts.getCreditsTakenList().isEmpty());
		cts = new CreditsTaken(9);
		assertFalse(cts.getCreditsTakenList().isEmpty());
		cts = new CreditsTaken(9, 2);
		assertFalse(cts.getCreditsTakenList().isEmpty());
	}

	@Test
	public void addCourseToCreditsTakenTest() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);
		Semesters semesters = new Semesters();
		Semester sem = semesters.getSemesterByID(6);
		Course c1 = coursesList.getCourseByID(55);
		Course c2 = coursesList.getCourseByID(185);
		Course c3 = coursesList.getCourseByID(182);
		
		assertTrue(cts.addCourseToCreditsTaken(9, c1, sem));
		assertTrue(cts.addCourseToCreditsTaken(9, c2, sem));
		assertTrue(cts.addCourseToCreditsTaken(9, c3, sem));
		new UpdateData().updateCreditstaken(cts.getCreditTakenByCourseID(55), 'd');
		new UpdateData().updateCreditstaken(cts.getCreditTakenByCourseID(185), 'd');
		new UpdateData().updateCreditstaken(cts.getCreditTakenByCourseID(182), 'd');
	}
	
	@Test
	public void updateCourseToCreditsTakenTest() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);
		Semesters semesters = new Semesters();
		Semester sem6 = semesters.getSemesterByID(6);
		Semester sem7 = semesters.getSemesterByID(7);
		Course c1 = coursesList.getCourseByID(199);
		Course c2 = coursesList.getCourseByID(200);
		
		assertTrue(cts.updateCourseInCreditsTaken(cts.getCreditTakenIDOfCourse(c1), 9, c1, sem7));
		assertTrue(cts.updateCourseInCreditsTaken(cts.getCreditTakenIDOfCourse(c2), 9, c2, sem7));
		// Update back to the old place, should not have duplicates (duplicates handled by Plan class)
		assertTrue(cts.updateCourseInCreditsTaken(cts.getCreditTakenIDOfCourse(c1), 9, c1, sem6));
		assertTrue(cts.updateCourseInCreditsTaken(cts.getCreditTakenIDOfCourse(c2), 9, c2, sem6));
	}
	
	@Test
	public void removeCourseToCreditsTakenTest() {
		CreditsTaken cts = new CreditsTaken(9);
		Courses coursesList = new Courses(3);
		Semesters semesters = new Semesters();
		Semester sem = semesters.getSemesterByID(6);
		
		Course c1 = coursesList.getCourseByID(82);
		Course c2 = coursesList.getCourseByID(74);
		Course c3 = coursesList.getCourseByID(165);
		
		assertTrue(cts.addCourseToCreditsTaken(9, c1, sem));
		assertTrue(cts.addCourseToCreditsTaken(9, c2, sem));
		assertTrue(cts.addCourseToCreditsTaken(9, c3, sem));
		// Remove after being added with actual removal method
		assertTrue(cts.removeCourseInCreditsTaken(cts.getCreditTakenByCourseID(c1.getCourseID()).getCreditTakenID(), 9, c1, sem));
		assertTrue(cts.removeCourseInCreditsTaken(cts.getCreditTakenByCourseID(c2.getCourseID()).getCreditTakenID(), 9, c2, sem));
		assertTrue(cts.removeCourseInCreditsTaken(cts.getCreditTakenByCourseID(c3.getCourseID()).getCreditTakenID(), 9, c3, sem));
	}
}