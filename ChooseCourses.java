import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ChooseCourses
{
	private LinkedList<ChooseCourse> chooseCourses;
	private ChooseCourse aChooseCourse;
	private int majorID;
	private int minorID;
	
	public ChooseCourses()
	{
		
	}
	
	public void getChooseData(int )
	{
		ConnectDB connectDB = new ConnectDB();
		try
		{
			int _requirementID;
			int _majorID;
			int _minorID;
			String _name;
			String _description;
			
			String queryString = "SELECT requirementID, majorID, minorID, name, description FROM 'tblgradrequirement' "
								+ "ORDER BY requirementID ASC";
			System.out.println(queryString);
			
			Statement statement = connectdb.theConnection.createStatement();
			
			ResultSet recordSet = statement.executeQuery(queryString);
			
			while (recordSet.next()) {
				// assign value for local variable accordingly to the query field
				_requirementID = recordSet.getInt("requirementID");
				_majorID = recordSet.getInt("majorID");
				_minorID = recordSet.getInt("minorID");
				_name = recordSet.getString("name");
				_description = recordSet.getString("description")
				// instantiate a Major object
				aMajor = new Major(_majorID, _majorName, _majorDesc, _catalogID);
				this.majorList.add(aMajor);
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

}
