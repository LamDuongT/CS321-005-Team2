import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradRequirement {
	private ArrayList<CoursesSet> major1Req;
	private ArrayList<CoursesSet> major2Req;
	private ArrayList<CoursesSet> minorReq;
	private int major1;
	private int major2;
	private int minor;
	//The ids of different major/minor, 0 if no major selected
	public GradRequirement(int major1, int major2, int minor) {
		this.major1= major1;
		this.major2=0;
		this.minor=0;
		if(major2!=0) 
			this.major2=major2;
		if(minor!=0)
			this.minor=minor;
		getGradReqData();
	}
	//query's the database for major requirement information then consturcts CoursesSets for the requirements that are needed to fulfill
	private void getGradReqData() {
		ConnectDB connectdb = new ConnectDB();
		int _gradreqcourseID;
		int _majorID;
		int _minorID;
		String _courseName;
		String _gradreqDesc;
		CoursesSet reqContainer;
		try {
			String queryString;
			queryString = "SELECT courseName, Desc";
			queryString += "FROM tblreqcourse ";
			queryString +="WHERE majorID = " + major1;
			System.out.println(queryString);
			// Initialize a sql statement
			Statement statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			ResultSet recordSet = statement.executeQuery(queryString);
			//retrieving information for first major and sending it to CoursesSet
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				reqContainer = new CoursesSet(_courseName, _gradreqDesc);
				this.major1Req.add(reqContainer);
			}
			statement.close();
			
			queryString = "SELECT courseName, Desc";
			queryString += "FROM tblreqcourse ";
			queryString +="WHERE majorID = " + major2;
			System.out.println(queryString);
			// Initialize a sql statement
			statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			recordSet = statement.executeQuery(queryString);
			//Major 2 retrieval
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				reqContainer = new CoursesSet(_courseName, _gradreqDesc);
				this.major2Req.add(reqContainer);
			}
			statement.close();
			
			queryString = "SELECT courseName, Desc";
			queryString += "FROM tblreqcourse ";
			queryString +="WHERE majorID = " + minor;
			System.out.println(queryString);
			// Initialize a sql statement
			statement = connectdb.theConnection.createStatement();
			// recordSet will hold a data table as sql object
			// to see how the data table look like, copy the queryString contents and
			// execute in mysql Workbench
			recordSet = statement.executeQuery(queryString);
			//retreving minor data
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				reqContainer = new CoursesSet(_courseName, _gradreqDesc);
				this.minorReq.add(reqContainer);
			}
			statement.close();
		}catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
}


