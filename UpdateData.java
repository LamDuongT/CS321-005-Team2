import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Huan Nguyen This class will contain all methods to manipulate data
 *         including Insert new record Update existing record Delete existing
 *         record
 *
 */
public class UpdateData {
	private ConnectDB connectdb;
	
	// Initialize the connection
	public UpdateData() {
		this.connectdb = new ConnectDB();
		this.connectdb.disconectDB();
	}

	/**
	 * @param theProfile
	 *            the profile to update
	 * @param theAction
	 *            the flag for insert new profile or update an existing profile
	 *            action will have 3 values: 'i' for insert, 'u' for update and 'd'
	 *            for delete
	 * @exception IllegalArgumentException
	 *                when theProfile is null
	 * @return none
	 */
	public void updateProfile(Profile theProfile, char theAction) {
		// throw exception when theProfile is null
		if (theProfile.equals(null))
			throw new IllegalArgumentException("[ERROR] theProfile can not be null!");

		try {
			// the connection is disconnected therefore, we need to reconnect to the database
			connectdb.reconnect();

			// the query string
			String queryString = "";

			switch (theAction) {

			// for insert new profile
			case 'i':
				// form the query for insert
				queryString = "INSERT INTO collegespdb.tblprofile (netID, studentName, studentEmail, username, password, profileName) ";
				queryString += "VALUES (\"" + theProfile.getNetID() + "\", \"" + theProfile.getStudentName() + "\", \""
						+ theProfile.getStudentEmail() + "\", \"" + theProfile.getUsername() + "\", \""
						+ theProfile.getPassword() + "\", \"" + theProfile.getProfileName() + "\");";
				break;

			// for update existing profile
			case 'u':
				// form the query string for update
				queryString = "UPDATE collegespdb.tblprofile SET ";
				queryString += "netID=\"" + theProfile.getNetID() + "\",";
				queryString += "studentName=\"" + theProfile.getStudentName() + "\", ";
				queryString += "studentEmail=\"" + theProfile.getStudentEmail() + "\", ";
				queryString += "username=\"" + theProfile.getUsername() + "\", ";
				queryString += "password=\"" + theProfile.getPassword() + "\", ";
				queryString += "profileName=\"" + theProfile.getProfileName() + "\" ";
				queryString += "WHERE studentID=\"" + theProfile.getStudentID() + "\";";
				break;

			// for delete existing profile
			case 'd':
				queryString = "DELETE FROM tblprofile WHERE studentID = " + theProfile.getStudentID();
				break;
			}

			System.out.println(queryString);
			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// execute the query
			// for INSERT and UPDATE query, there will be no return result
			// therefore, we do not need a ResultSet to hold the return value
			statement.executeUpdate(queryString);

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
	
	/**
	 * @param theCreditstaken
	 *            the theCreditstaken to update
	 * @param theAction
	 *            the flag for insert new profile or update an existing profile
	 *            action will have 3 values: 'i' for insert, 'u' for update and 'd'
	 *            for delete
	 * @exception IllegalArgumentException
	 *                when theProfile is null
	 * @return none
	 */
	public void updateCreditstaken(Creditstaken theCreditstaken, char theAction) {
		// throw exception when theProfile is null
		if (theCreditstaken.equals(null))
			throw new IllegalArgumentException("[ERROR] theCreditstaken can not be null!");

		try {
			// the connection is disconnected therefore, we need to reconnect to the database
			connectdb.reconnect();

			// the query string
			String queryString = "";

			switch (theAction) {

			// for insert new profile
			case 'i':
				// form the query for insert
				queryString = "INSERT INTO collegespdb.tblcreditstaken (studentID, courseID, semesterID, isChangable) ";
				queryString += "VALUES (\"" + theCreditstaken.getStudentID() + "\", \"" + theCreditstaken.getCourseID() + "\", \""
						+ theCreditstaken.getSemesterID() + "\", " + theCreditstaken.getIsChangable() + ");";
				break;

			// for update existing profile
			case 'u':
				// form the query string for update
				queryString = "UPDATE collegespdb.tblcreditstaken SET  ";
				queryString += "studentID =\"" + theCreditstaken.getStudentID() + "\",";
				queryString += "courseID =\"" + theCreditstaken.getCourseID() + "\",";
				queryString += "semesterID =\"" + theCreditstaken.getSemesterID() + "\", ";
				queryString += "isChangable = " + theCreditstaken.getIsChangable() + " ";
				queryString += "WHERE creditstakenID = " + theCreditstaken.getCreditstakenID();
				break;

			// for delete existing profile
			case 'd':
				queryString = "DELETE FROM tblcreditstaken WHERE creditstakenID = " + theCreditstaken.getCreditstakenID();
				break;
			}

			System.out.println(queryString);
			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// execute the query
			// for INSERT and UPDATE query, there will be no return result
			// therefore, we do not need a ResultSet to hold the return value
			statement.executeUpdate(queryString);

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}

}
