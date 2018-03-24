import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Huan Nguyen
 */
public class Login {
	private int studentID;
	private String netID;
	private String username;
	private String passowrd;
	// the attribute that capture whether user logged in the system
	private boolean isLoggedIn = false;
	// the connection to database
	// this variable will be used for login as well as get profile
	private ConnectDB connectdb;

	/**
	 * Initialize username and password the studentID and netID will be assigned
	 * after user logged in the system NOTE: the login class will not have empty
	 * constructor. username and password are always required therefore, setter
	 * method for username and password will not necessary
	 */
	public Login(String username, String password) {
		this.username = username;
		this.passowrd = password;
	}

	// getter for studentID
	public int getStudentID() {
		return this.studentID;
	}

	// getter for netID
	public String getnetID() {
		return this.netID;
	}

	// getter for isLoggedIn
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}

	/**
	 * Do the login using username and password
	 */
	public void doLogin() {
		connectdb = new ConnectDB();

		try {
			// the querry string
			String queryString = "SELECT studentID, netID FROM tblprofile ";
			queryString += "WHERE username ='" + this.username + "' ";
			queryString += "AND password = '" + this.passowrd + "' ";
			queryString += "LIMIT 1";

			System.out.println(queryString);

			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);

			// even though we have a loop here
			// the code really execute exactly once due to the LIMIT 1 statement in the
			// queryString
			while (recordSet.next()) {
				// assign the studentID and netID attributes
				this.studentID = recordSet.getInt("studentID");
				this.netID = recordSet.getString("netID");

				System.out.println("StudentID = " + studentID + "	netID = " + netID);
				// assign isLoggedIn to true
				isLoggedIn = true;

			}
			// close the sql statement
			statement.close();

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			// close the connection
			// NOTE: the close connection method need to be called in finally block to
			// ensure the connection is closed
			connectdb.disconectDB();
		}
	}

	/**
	 * @param studentID
	 *            the studentID
	 * @return a profile object get user profile based on the studentID NOTE: the
	 *         user profile only can retrieved if user already login
	 */
	public Profile getProfile(int studentID) {
		Profile aProfile = new Profile();
		// check whether user login or not. if not, print error message
		if (this.isLoggedIn == false) {
			System.out.println("[ERROR] You are not logged in yet!");
			// otherwise, get profile for the studentID
		} else {
			try {
				// because after the login method executed, the connection is disconnected
				// therefore, we need to reconnect to the database
				connectdb.reconnect();

				// the query string
				String queryString = "SELECT studentID, netID, studentName, studentEmail, username, password, profileName FROM tblprofile ";
				queryString += "WHERE studentID = " + this.studentID + " ";
				queryString += "LIMIT 1";

				System.out.println(queryString);

				// Initialize a sql statement
				Statement statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				ResultSet recordSet = statement.executeQuery(queryString);

				// loop through each record in the data table
				while (recordSet.next()) {
					// assign local variables for a profile object
					int _studentID = recordSet.getInt("studentID");
					String _netID = recordSet.getString("netID");
					String _studentName = recordSet.getString("studentName");
					String _studentEmail = recordSet.getString("studentEmail");
					String _username = recordSet.getString("username");
					String _password = recordSet.getString("password");
					String _profileName = recordSet.getString("profileName");

					// create aProfile object with all local information
					aProfile.setValue(_studentID, _netID, _studentName, _studentEmail, _username, _password,
							_profileName);

					System.out.println(aProfile.toString());

				}
				statement.close();

			} catch (SQLException e) {
				throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
			} finally {
				// close the connection
				// NOTE: the close connection method need to be called in finally block to
				// ensure the connection is closed
				connectdb.disconectDB();
			}

		}
		// then, return the profile
		return aProfile;
	}
	
}
