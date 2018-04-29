
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
/**
 * @author Huan Nguyen
 *
 */
public class Semesters {

    private List<Semester> semesterList = new LinkedList<>();

    private Semester aSemester;
    private int profileID;

    public Semesters() {
        getSemesterData();
    }
    public Semesters(int id){
        profileID = id;
        getSemesterData();
    }

    /**
     * Get Semester by ID from the list of Semesters
     *
     * @param semesterID
     * @return
     */
    public Semester getSemesterByID(int semesterID) {
        aSemester = null;
        boolean isFound = false;

        for (int index = 0; index < this.semesterList.size(); index++) {
            aSemester = this.semesterList.get(index);
            if (aSemester.getSemesterID() == semesterID) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            aSemester = new Semester();
        }

        return aSemester;
    }

    /**
     * Get Semester by Name from the list of Semesters
     *
     * @param catalogName
     * @return
     */
    public Semester getCatalogByName(String catalogName) {
        aSemester = null;
        boolean isFound = false;

        for (int index = 0; index < this.semesterList.size(); index++) {
            aSemester = this.semesterList.get(index);
            if (aSemester.getSemesterName().toLowerCase().contains(catalogName)) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            aSemester = new Semester();
        }

        return aSemester;
    }

    /**
     * Gets the list of semesters that the student took
     *
     * @return the list of semesters
     */
    public List<Semester> getSemesterList() {
        return this.semesterList;
    }

    /**
     * fetch all data from the table Semester and add to the list
     */
    private void getSemesterData() {
        ConnectDB connectdb = new ConnectDB();

        try {
            int semesterID;
            String semesterName;
            String semesterDesc;
            int creditMin;
            int creditMax;

            String queryString = "SELECT semesterID, semesterName, semesterDesc, creditMin, creditMax FROM tblsemester ";
            queryString += "ORDER BY semesterID ASC";

            System.out.println(queryString);

            // recordSet will hold a data table as sql object
            // to see how the data table look like, copy the queryString contents and
            // execute in mySQL Workbench
            try ( // Initialize a SQL statement
                    Statement statement = connectdb.theConnection.createStatement()) {
                // recordSet will hold a data table as sql object
                // to see how the data table look like, copy the queryString contents and
                // execute in mySQL Workbench
                ResultSet recordSet = statement.executeQuery(queryString);

                while (recordSet.next()) {
                    semesterID = recordSet.getInt("semesterID");
                    semesterName = recordSet.getString("semesterName");
                    semesterDesc = recordSet.getString("semesterDesc");
                    creditMin = recordSet.getInt("creditMin");
                    creditMax = recordSet.getInt("creditMax");
                    
                    if (semesterID != 9999) {
                        aSemester = new Semester(semesterID, semesterName, semesterDesc, creditMin, creditMax, new ArrayList<>());
                      
                    } else {
                    	aSemester = new Semester();
                    }
                    this.semesterList.add(aSemester);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        } finally {
            connectdb.disconectDB();
        }
    }
    
    public boolean isEmpty() {
    	return this.semesterList.isEmpty();
    }
  

    /**
     * Override toString method for testing purpose
     *
     * @return
     */
    @Override
    public String toString() {
        String returnString = "semesterID | semesterName   | semesterDesc\t\t | credtiMin  | creditMax\n";
        for (int index = 0; index < this.semesterList.size(); index++) {
            aSemester = this.semesterList.get(index);
            returnString += aSemester.toString() + "\n";
        }

        return returnString;
    }
}
