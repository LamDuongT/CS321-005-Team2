import java.io.IOException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LamTestClass {

	public static void main(String[] args) {
		
		Profile p = new Profile();
		
		Login login = new Login ("lduong6", "passwords");
		
		login.doLogin();
		p = login.getProfile(3);
		
		
	}
}
