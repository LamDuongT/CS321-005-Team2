
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * This class will contain all methods that will be manipulating data such as
 * inserting, updating, and deleting of Profile, Plan, and Semester
 *
 * @author Huan Nguyen
 * @author Lam Duong
 * @author Mohammed Alsharf
 */
public class UpdateData {

    private ConnectDB connectdb;

    // Initialize the connection
    public UpdateData() {
        this.connectdb = new ConnectDB();
        this.connectdb.disconectDB();
    }

    /**
     * @param theProfile the profile to update
     * @param theAction the flag for insert new profile or update an existing
     * profile action will have 3 values: 'i' for insert, 'u' for update and 'd'
     * for delete
     * @exception IllegalArgumentException when theProfile is null
     */
    public void updateProfile(Profile theProfile, char theAction) {
        // throw exception when theProfile is null
        if (theProfile == null) {
            throw new IllegalArgumentException("[ERROR] theProfile can not be null!");
        }

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
     * @author Huan Nguyen
     * @author Lam Duong
     * @param theCreditTaken the theCreditTaken to update
     * @param theAction the flag for insert new profile or update an existing
     * profile action will have 3 values: 'i' for insert, 'u' for update and 'd'
     * for delete
     * @exception IllegalArgumentException when theProfile is null
     */
    public void updateCreditstaken(CreditTaken theCreditTaken, char theAction) {
        // throw exception when theProfile is null
        if (theCreditTaken == null) {
            throw new IllegalArgumentException("[ERROR] theCreditstaken can not be null!");
        }

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
                    queryString += "VALUES (" + theCreditTaken.getStudentID() + ", " + theCreditTaken.getCourseID()
                            + ", " + theCreditTaken.getSemesterID() + ");";
                    break;

                // for update existing profile
                case 'u':
                    // form the query string for update
                    queryString = "UPDATE collegespdb.tblcreditstaken SET  ";
                    queryString += "studentID =" + theCreditTaken.getStudentID() + ",";
                    queryString += "courseID =" + theCreditTaken.getCourseID() + ",";
                    queryString += "semesterID =" + theCreditTaken.getSemesterID() + ", ";
                    queryString += "WHERE creditstakenID = " + theCreditTaken.getCreditTakenID();
                    break;

                // for delete existing profile
                case 'd':
                    queryString = "DELETE FROM tblcreditstaken WHERE creditstakenID = "
                            + theCreditTaken.getCreditTakenID();
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
     * @param thePlan The Plan to be updated
     * @param theAction The flag for inserting new Plan or update an existing
     * Plan action will have 3 values: 'i' for insert, 'u' for update, and 'd'
     * for delete
     * @exception IllegalArgumentException when thePlan is null
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
                    queryString = "INSERT INTO `collegespdb`.`tblplan` (`catalogID`, `majorID`, `minorID`, `majorID2`, `minorID2`, `profileID`) ";
                    queryString += "VALUES (" + thePlan.getCatalogID() + ", " + thePlan.getMajors()[0].getMajorID()
                            + ", " + thePlan.getMinors()[0].getMinorID() + ", " + thePlan.getMajors()[1].getMajorID()
                            + ", " + thePlan.getMinors()[1].getMinorID() + ", " + thePlan.getProfileID() + ");";
                    break;

                // Case for updating an existing plan
                case 'u':
                    // form the query string for update
                    queryString = "UPDATE collegespdb.tblplan SET  ";
                    queryString += "catalogID =\"" + thePlan.getCatalogID() + "\",";
                    queryString += "major1ID =\"" + thePlan.getMajors()[0].getMajorID() + "\",";
                    queryString += "minor1ID =\"" + thePlan.getMinors()[0].getMinorID() + "\",";
                    queryString += "major2ID =\"" + thePlan.getMajors()[1].getMajorID() + "\",";
                    queryString += "minor2ID =\"" + thePlan.getMinors()[1].getMinorID() + "\",";
                    // No need for query string to update profileID because profileID will always remain the same?
                    queryString += "WHERE planID = " + thePlan.getPlanID();
                    break;

                // Case for deleting an existing plan
                case 'd':
                    queryString = "DELETE FROM tblplan WHERE planID = " + thePlan.getPlanID();
                    break;
            }

            System.out.println(queryString);
            // Initialize a SQL statement
            Statement statement = connectdb.theConnection.createStatement();
            // execute the query
            // for INSERT and UPDATE query, there will be no return result
            // therefore, we do not need a ResultSet to hold the return value
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
            statement.executeUpdate(queryString);

        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        } finally {
            connectdb.disconectDB();
        }
    }

    /**
     * Method for updating the semesters in database insert, update and delete
     *
     * @param PlanID
     * @param sm
     * @param action
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void updateSemester(int PlanID, Semester sm, char action) {
        if (sm == null) {
            throw new IllegalArgumentException("[ERROR] Semester can not be null!");
        }
        //this list holds all the semesters in database
        List<Semester> semesters = new Semesters().getSemesterList();
        //this is a boolean to see if a duplicate is found
        boolean found = false;
        //check if there is a duplicate
        for (Semester semester : semesters) {
            if (semester.getSemesterName().equals(sm.getSemesterName())) {
                found = true;
            }
        }
        //if no duplicate found, then proceed 
        if (!found) {
            connectdb.reconnect();// reconnection with database
            String query = "";//the query that to be used
            switch (action) {
                case 'i':
                    query = query.concat("INSERT INTO collegespdb.tblsemester(semesterName,semesterDesc,creditMin,creditMax)")
                            .concat("VALUES(\"").concat(sm.getSemesterName()).concat("\",\"").concat(sm.getSemesterDesc())
                            .concat("\",").concat("" + sm.getCreditMin()).concat(",").concat("" + sm.getCreditMax())
                            .concat(");");
                    break;
                case 'u':
                    query = query.concat("UPDATE  collegespdb.tblsemester SET")
                            .concat("semesterName = \"").concat(sm.getSemesterName()).concat("\",")
                            .concat("semesterDecs = \"").concat(sm.getSemesterDesc()).concat("\",")
                            .concat("creditMin = ").concat("" + sm.getCreditMin()).concat(",")
                            .concat("semesterID = ").concat("" + sm.getCreditMax()).concat(",")
                            .concat("WHERE semesterID = ").concat("" + sm.getSemesterID());
                    break;
                case 'd':
                    query = query.concat("DELETE FROM collegespdb.tblsemester WHERE semesterID = ").concat("" + sm.getSemesterID());
                    break;
            }
            try {
                Statement statement = connectdb.theConnection.createStatement();
                statement.executeUpdate(query);//insert the semester into database
            } catch (SQLException ex) {
                throw new IllegalStateException("[ERROR] there is an error with the sql querry!", ex);
            } finally {
                connectdb.disconectDB();
            }
        } else {
            throw new RuntimeException("[Error] " + sm.getSemesterName() + " is a duplicate");
        }
    }
}
