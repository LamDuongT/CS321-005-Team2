import java.io.IOException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LamTestClass {

	public static void main(String[] args) {
		
		// TESTING FOR PROFILE AND COURSESSET
		
		Login login = new Login ("lduong6", "FuckPasswords");
		
		login.doLogin();
		Profile p = login.getProfile(1);
		
		
	}
}
