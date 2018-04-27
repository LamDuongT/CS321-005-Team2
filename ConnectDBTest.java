
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.graphstream.graph.NullAttributeException;
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
            while (recordSet.next()) {
            	resultSize++;
            }   
			System.out.println("resultSize = " + resultSize);
			statement.close();			

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		}
		assertTrue("The query must return result!", resultSize > 0);
	}
	
	// test reconnect
	@Test(timeout = 1000)
	public void testReConnectDB() {
		this.connectdb.disconectDB();
		this.connectdb.reconnect();
		
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
            while (recordSet.next()) {
            	resultSize++;
            }   
			System.out.println("resultSize = " + resultSize);
			statement.close();			

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		}
		assertTrue("The query must return result!", resultSize > 0);
	}

	// test the connect is null after disconnect
	@Test(timeout = 1000)
	public void testConnectionIsNullAfterDisconect() {
		try {
			this.connectdb.disconectDB();
		}catch (NullPointerException e) {
			throw new NullPointerException();
		}
		assertTrue("The connection must be NULL after disconnecting from DATABASE!", this.connectdb.theConnection == null);
	}

	// test connection status
	@Test(timeout = 1000)
	public void testConnectionStatus() {
		assertTrue("The connection should be established", this.connectdb.isConnected() == true);
	}
}
