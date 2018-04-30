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
	public static void main(String args[])
	{
	  org.junit.runner.JUnitCore.main("CourseTest", "CoursesTest", "SemestersTest", "CreditsTakenTests");
	}
}
