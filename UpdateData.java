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
	 */
	public void updateProfile(Profile theProfile, char theAction) {
		// throw exception when theProfile is null
		if (theProfile == null)
			throw new IllegalArgumentException("[ERROR] theProfile can not be null!");

		try {
			// the connection is disconnected therefore, we need to reconnect to the
			// database
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
	 */
	public void updateCreditstaken(Creditstaken theCreditstaken, char theAction) {
		// throw exception when theProfile is null
		if (theCreditstaken == null)
			throw new IllegalArgumentException("[ERROR] theCreditstaken can not be null!");

		try {
			// the connection is disconnected therefore, we need to reconnect to the
			// database
			connectdb.reconnect();

			// the query string
			String queryString = "";

			switch (theAction) {

			// for insert new profile
			case 'i':
				// form the query for insert
				queryString = "INSERT INTO collegespdb.tblcreditstaken (studentID, courseID, semesterID, isChangable) ";
				queryString += "VALUES (\"" + theCreditstaken.getStudentID() + "\", \"" + theCreditstaken.getCourseID()
						+ "\", \"" + theCreditstaken.getSemesterID() + "\", " + theCreditstaken.getIsChangable() + ");";
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
				queryString = "DELETE FROM tblcreditstaken WHERE creditstakenID = "
						+ theCreditstaken.getCreditstakenID();
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
	 * @author Lam Duong
	 * @param thePlan
	 *            The Plan to be updated
	 * @param theAction
	 *            The flag for inserting new Plan or update an existing Plan action
	 *            will have 3 values: 'i' for insert, 'u' for update, and 'd' for
	 *            delete
	 * @exception IllegalArgumentException
	 *                when thePlan is null
	 */
	public void updatePlan(Plan thePlan, char theAction) {
		// throw exception when thePlan is null
		if (thePlan == null) {
			throw new IllegalArgumentException("[ERROR] thePlan can not be null!");
		}

		try {
			// the connection is disconnected therefore, we need to reconnect to the
			// database
			connectdb.reconnect();

			// the query string
			String queryString = "";
			switch (theAction) {

			// Case for inserting a new plan
			case 'i':
				// form the query for insertion
				queryString = "INSERT INTO collegespdb.tblplan (catalogID, major1ID, minor1ID, major2ID, minor2ID) ";
				queryString += "VALUES (\"" + thePlan.getCatalogID() + "\", \"" + thePlan.getMajors()[0].getMajorID()
						+ "\", " + thePlan.getMinors()[0].getMinorID() + "\", " + thePlan.getMajors()[1].getMajorID()
						+ "\", " + thePlan.getMinors()[1].getMinorID() + ");";
				break;

			// Case for updating an existing plan
			case 'u':
				// form the query string for update
				queryString = "UPDATE collegespdb.tblplan SET  ";
				queryString += "catalogID =\"" + thePlan.getCatalogID() + "\",";
				queryString += "major1ID =\"" + thePlan.getMajors()[0].getMajorID() + "\",";
				queryString += "minor1ID =\"" + thePlan.getMinors()[0].getMinorID() + "\", ";
                queryString += "major2ID =\"" + thePlan.getMajors()[1].getMajorID() + "\",";
				queryString += "minor2ID =\"" + thePlan.getMinors()[1].getMinorID() + "\", ";
				queryString += "WHERE planID = " + thePlan.getPlanID();
				break;

			// Case for deleting an existing plan
			case 'd':
				queryString = "DELETE FROM tblPlan WHERE planID = " + thePlan.getPlanID();
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
        //updates the semesters in data base
        //insert, update and delete
        @SuppressWarnings("ResultOfMethodCallIgnored")
        public void updateSemester(int PlanID, Semester sm, char action){
            if(sm == null){
                throw new IllegalArgumentException("[ERROR] Semester can not be null!");
            }
            connectdb.reconnect();
            String query = "";
            switch(action){
                case 'i':
                    query.concat("INSERT INOT collegespdb.tblsemester(semesterID,semesterName,semesterDesc,creditMin,creditMax)")
                         .concat("VALUES(\"").concat(sm.getSemesterName()).concat("\",\"").concat(sm.getSemesterDesc())
                         .concat("\",\"").concat(""+sm.getCreditMin()).concat("\",\"").concat(""+sm.getCreditMax())
                         .concat("\")");
                    break;
                case 'u':
                    query.concat("UPDATE  collegespdb.tblsemester SET")
                         .concat("semesterName = \"").concat(sm.getSemesterName()).concat("\",")
                         .concat("semesterDecs = \"").concat(sm.getSemesterDesc()).concat("\",")
                         .concat("creditMin = \"").concat(""+sm.getCreditMin()).concat("\",")
                         .concat("semesterID = \"").concat(""+sm.getCreditMax()).concat("\",")
                         .concat("WHERE semesterID = ").concat(""+sm.getSemesterID());
                    break;
                case 'd':
                    query.concat("DELETE FROM collegespdb.tblsemester WHERE semesterID = ").concat(""+sm.getSemesterID());
                    break;
            }
        }
}
