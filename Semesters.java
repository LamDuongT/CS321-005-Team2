import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public Semesters() {
		getSemesterData();
	}
	
	/**
	 * Get Semester by ID from the list of Semesters
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
		if (!isFound)
			aSemester = new Semester();

		return aSemester;
	}

	/**
	 * Get Semester by Name from the list of Semesters
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
		if (!isFound)
			aSemester = new Semester();

		return aSemester;
	}

	/**
	 * fetch all data from the table Semester and add to the list
	 */
	private void getSemesterData() {
		ConnectDB connectdb = new ConnectDB();

		try {
			int semesterID;
			String catalogName;
			String catalogDesc;
			int creditMin;
			int creditMax;

			String queryString = "SELECT semesterID, semesterName, semesterDesc, creditMin, creditMax FROM tblsemester ";
			queryString += "ORDER BY semesterID ASC";

			System.out.println(queryString);

                    // recordSet will hold a data table as sql object
                    // to see how the data table look like, copy the queryString contents and
                    // execute in mysql Workbench
                    try ( // Initialize a sql statement
                            Statement statement = connectdb.theConnection.createStatement()) {
                        // recordSet will hold a data table as sql object
                        // to see how the data table look like, copy the queryString contents and
                        // execute in mysql Workbench
                        ResultSet recordSet = statement.executeQuery(queryString);
                        
                        while (recordSet.next()) {
                            semesterID = recordSet.getInt("semesterID");
                            catalogName = recordSet.getString("semesterName");
                            catalogDesc = recordSet.getString("semesterDesc");
                            creditMin = recordSet.getInt("creditMin");
                            creditMax = recordSet.getInt("creditMax");
                            
                            aSemester = new Semester(semesterID, catalogName, catalogDesc, creditMin, creditMax);
                            this.semesterList.add(aSemester);
                        }
                    }

		} catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
        public void addSemester(Semester sm){
            
        }

	/**
	 * Override toString method for testing purpose
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
