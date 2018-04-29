import java.io.IOException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class LamTestClass
{
	Courses coursesList;
	
	
	/**
	 * Lam will mostly test
	 * @author Lam Duong 
	 * @param args
	 */
	public static void main(String args[])
	{
	  org.junit.runner.JUnitCore.main("LamTestClass");
	}
	
	@Test public void coursesConstructor() {
		coursesList  = new Courses(3);
	}
	
	@Test public void courseConstructor() {
		Course c1 = coursesList.getCourseByID(1);
	}
	
	@Test public void coursesSetConstructor_1() {
	 CoursesSet c = new CoursesSet("CS Core", "%|11|11|CS110,CS112,CS211,CS262,CS306,CS310,CS321,CS330,CS367,CS471,CS483", coursesList);
	}
}
