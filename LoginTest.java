import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	Login aStudent, anotherStudent;
	
	
	@Before
	public void setUp() throws Exception {
		aStudent = new Login("hnguye80", "hnguye80");
		aStudent.doLogin();
	}

	@After
	public void tearDown() throws Exception {
		aStudent.loggedOut();
	}

	@Test
	public void testGetLoginStatus() {
		assertTrue("The student must logged in", aStudent.isLoggedIn());
	}
	
	@Test
	public void testGetLogoutStatus() {
		aStudent.loggedOut();
		assertTrue("The student must logged out", !aStudent.isLoggedIn());
	}
	
	@Test
	public void testLogginWrongCredential() {
		anotherStudent = new Login("notexistaccount", "passwordnotset");
		assertTrue("The student must logged out", !aStudent.isLoggedIn());
	}
	
	@Test
	public void testSQLInjection() {
		anotherStudent = new Login(" OR 1=1", " OR 1=1");
		// the student must has not login status 
		assertTrue("The student must logged out", !aStudent.isLoggedIn());
	}

}
