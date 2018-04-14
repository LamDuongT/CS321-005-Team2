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
	
	public GradRequirement(int major1, int major2, int minor) {
		this.major1= major1;
		this.major2=null;
		this.minor=null;
		if(major2!=null) 
			this.major2=major2;
		if(minor!=null)
			this.minor=minor;
		getGradReqData();
	}
	
	private void getGradReqData() {
		ConnectDB connectdb = new ConnectDB();
		int _gradreqcourseID;
		int _majorID;
		int _minorID;
		String _courseName;
		String _gradreqDesc;
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
			
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				CoursesSet ReqContainer = new CoursesSet(courseName, gradreqDesc);
				this.major1Req.add(ReqContainer);
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
			
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				CoursesSet ReqContainer = new CoursesSet(courseName, gradreqDesc);
				this.major2Req.add(ReqContainer);
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
			
			while (recordSet.next()){
				_courseName = recordSet.getString("courseName");
				_gradreqDesc = recordSet.getString("gradreqDesc");
				_minorID= recordSet.getInt("minorID");
				_majorID= recordSet.getInt("majorID");
				if(_minorID == 9999 && _majorID == 9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				if(_minorID<9999 && _majorID<9999)
					throw new RuntimeException("the coders suck at programming... sorry");
				CoursesSet ReqContainer = new CoursesSet(courseName, gradreqDesc);
				this.minorReq.add(ReqContainer);
			}
			statement.close();
		}catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
}


