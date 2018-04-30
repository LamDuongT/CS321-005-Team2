import java.io.IOException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class CoursesTest {
	/**
	 * @author Lam Duong
	 * @param args
	 */
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("CoursesTest");
	}

	/**
	 * TEST FOR COURSE EMPTY CONSTRUCTOR
	 */
	@Test
	public void courseEmptyConstructor() {
		Course c = new Course();
	}

	/**
	 * TESTS FOR COURSES CLASS
	 */

	@Test
	public void coursesConstructor() {
		Courses coursesList = new Courses(3);
	}

	@Test
	public void getCourseByIDTest() {
		Courses coursesList = new Courses(3);
		Course c1 = coursesList.getCourseByID(1);
		Course c2 = coursesList.getCourseByID(2);
		Course c3 = coursesList.getCourseByID(3);
	}

	@Test
	public void getCourseByNameTest() {
		Courses coursesList = new Courses(3);
		// Courses that do exist in the database
		Course c1 = coursesList.getCourseByName("CS110");
		Course c2 = coursesList.getCourseByName("CS112");
		Course c3 = coursesList.getCourseByName("CS330");
		// Made up courses that do not exist in the database
		Course c4 = coursesList.getCourseByName("BIOL431");
		Course c5 = coursesList.getCourseByName("CHEM418");
		Course c6 = coursesList.getCourseByName("GEOL312");
		assertFalse("Course 1 should not be empty!", c1.isEmpty());
		assertFalse("Course 2 should not be empty!", c2.isEmpty());
		assertFalse("Course 3 should not be empty!", c3.isEmpty());
		assertTrue("Course 4 should be empty!", c4.isEmpty());
		assertTrue("Course 5 should be empty!", c5.isEmpty());
		assertTrue("Course 6 should be empty!", c6.isEmpty());
	}

	@Test
	public void getCoursesListTest() {
		Courses coursesList = new Courses(3);
		assertNotNull(coursesList.getCoursesList());
	}

	@Test
	public void toStringTest() {
		Courses coursesList = new Courses(3);
		System.out.println(coursesList.toString());
	}

	/**
	 * TEST FOR COURSESSET CLAS
	 */

	@Test
	public void coursesSetConstructor() {
		Courses coursesList = new Courses(3);
		String coursesSetDesc = "%|11|11|CS110,CS112,CS211,CS262,CS306,CS310,CS321,CS330,CS367,CS471,CS483";
		CoursesSet c = new CoursesSet("CS Core", coursesSetDesc, coursesList);

		String[] parts = coursesSetDesc.split("\\|");
		String[] classes = parts[3].split("\\,");
		int pos = 0;

		for (Course course : c.getCoursesToBeChosen()) {
			assertTrue(course.getCourseName().equals(classes[pos]));
			pos++;
		}
		System.out.println(c.toString());
	}

	@Test
	public void coursesSetConstructor_2() {
		Courses coursesList = new Courses(3);
		String coursesSetDesc = "%|1|3|CS455,CS468,CS475";
		CoursesSet c = new CoursesSet("Senior CS 1", coursesSetDesc, coursesList);

		String[] parts = coursesSetDesc.split("\\|");
		String[] classes = parts[3].split("\\,");
		int pos = 0;

		for (Course course : c.getCoursesToBeChosen()) {
			assertTrue(course.getCourseName().equals(classes[pos]));
			pos++;
		}
		System.out.println(c.toString());
	}

	@Test
	public void parseStringTest_1() {

	}
}
