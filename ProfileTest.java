
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** @author Huan Nguyen
 * */

public class ProfileTest {
	Login aStudent, anotherStudent;
	Profile aStudentProfile, anotherStudentProfile;
	String username;
	String password;

	@Before
	public void setUp() throws Exception {
		aStudent = new Login("hnguye80", "hnguye80");
		aStudent.doLogin();
		aStudentProfile = aStudent.getProfile(aStudent.getStudentID());
	}

	@After
	public void tearDown() throws Exception {
		aStudent = null;
	}

	@Test(timeout = 1000)
	public void testGetUsername() {
		assertTrue("Username does not match", aStudentProfile.getUsername().equals("hnguye80"));
	}

	@Test(timeout = 1000)
	public void testGetPassword() {
		assertTrue("Password does not match", aStudentProfile.getPassword().equals("hnguye80"));
	}

	@Test(timeout = 1000)
	public void testTwoProfilesUsername() {
		anotherStudent = new Login("johnny74", "johnny74");
		anotherStudent.doLogin();
		anotherStudentProfile = aStudent.getProfile(anotherStudent.getStudentID());

		assertTrue("Two Students have same username and password",
				aStudentProfile.getUsername().toLowerCase().equals(aStudentProfile.getUsername().toLowerCase()));
	}

	@Test(timeout = 1000)
	public void testTwoProfilesStudentID() {
		anotherStudent = new Login("johnny74", "johnny74");
		anotherStudent.doLogin();
		anotherStudentProfile = aStudent.getProfile(anotherStudent.getStudentID());

		assertTrue("Two Students have same username and password",
				aStudentProfile.getStudentID() == aStudentProfile.getStudentID() );
	}
	
	// Note: more to come
	// test limit numbers for plans
	// test add plan
	// test delete plan
	// test plan contain same either major, minor, major2, minor2
	
}
