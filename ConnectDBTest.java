
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectDBTest {
	ConnectDB connectdb;
	
	@Before
	public void setUp() throws Exception {
        this.connectdb = new ConnectDB();        
	}

	@After
	public void tearDown() throws Exception {
		this.connectdb.disconectDB();
	}

	@Test(timeout = 1000)
	public void testConnectDB() {
		int resultSize = 0;
		try {
			// query string
			String queryString = "SELECT majorID, majorName, majorDesc, catalogID FROM `tblmajor` ";
			System.out.println(queryString);

			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);
			// loop through each record in the data table
			
			resultSize = recordSet.getFetchSize();
			System.out.println("resultSize = " + resultSize);
			statement.close();			

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		}
		assertTrue("The query must return result!", resultSize > 0);
	}

	// Note: more to come
	// test not connect but get data
	// test not disconnect database
	// test connection status

}
