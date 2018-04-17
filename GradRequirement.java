import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Comparator;
import java.util.*;


public class GradRequirement {
	private ArrayList<CoursesSet> major1Req;
	private ArrayList<CoursesSet> major2Req;
	private ArrayList<CoursesSet> minorReq;
	private int major1;
	private int major2;
	private int minor;
	private ArrayList<CoursesSet> tempHolder;
	
	//The ids of different major/minor, 0 if no major selected
	public GradRequirement(int major1, int major2, int minor, Courses coursesList, CreditsTaken profileLevel, CreditsTaken planLevel ) {
		this.major1= major1;
		this.major2=0;
		this.minor=0;
		if(major2!=9999) 
			this.major2=major2;
		if(minor!=9999)
			this.minor=minor;
		getGradReqData(coursesList);
		Collections.sort(major1Req);
		Collections.sort(major2Req);
		Collections.sort(minorReq);
		for(CreditTaken a: planLevel.getCreditsTakenList()) {
			addCourse(a.getCourseID());
		}
		for(CreditTaken a: profileLevel.getCreditsTakenList()) {
			addCourse(a.getCourseID());
		}
	}
	public void addCourse(int courseID) {
		for(CoursesSet a:major1Req) {
			if(a.checkReq(courseID)) {
				if(a.checkScheduled(courseID)) {
					tempHolder.add(a);
				}
			}
		}
		
		int difference=999;
		int i=0;
		int s=0;
		for(CoursesSet a: tempHolder) {
			if(Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen())<difference) {
				difference=Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen());
				s=i;
			}
			i++;
		}
		tempHolder.get(s).scheduleCourse(courseID);
		Collections.sort(major1Req);
		tempHolder.clear();
		
		if(major2!=0) {
			for(CoursesSet a:major2Req) {
				if(a.checkReq(courseID)) {
					if(a.checkScheduled(courseID)) {
						tempHolder.add(a);
					}
				}
			}
			difference=9999;
			for(CoursesSet a: tempHolder) {
				if(Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen())<difference) {
					difference=Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen());
					s=i;
				}
				i++;
			}
			tempHolder.get(s).scheduleCourse(courseID);

			Collections.sort(major2Req);
			tempHolder.clear();
		}
		if(minor!=0) {
			for(CoursesSet a:minorReq) {
				if(a.checkReq(courseID)) {
					if(a.checkScheduled(courseID)) {
						tempHolder.add(a);
					}
				}
			}
			difference=9999;
			for(CoursesSet a: tempHolder) {
				if(Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen())<difference) {
					difference=Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen());
					s=i;
				}
				i++;
			}
			tempHolder.get(s).scheduleCourse(courseID);
			Collections.sort(minorReq);
			tempHolder.clear();
		}
	}
	public void removeCourse(int courseID) {
		for(CoursesSet a:major1Req) {
			if(a.checkScheduled(courseID)){
				a.unScheduleCourse(courseID);
			}
		}
		Collections.sort(major2Req);
		if(major2!=0) {
			for(CoursesSet a:major2Req) {
				if(a.checkScheduled(courseID)){
					a.unScheduleCourse(courseID);
				}
			}
			Collections.sort(major2Req);
		}
		if (minor != 0) {
			for (CoursesSet a : minorReq) {
				if (a.checkScheduled(courseID)) {
					a.unScheduleCourse(courseID);
				}
			}
			Collections.sort(minorReq);
		}
	
	}
	//query's the database for major requirement information then constructs CoursesSets for the requirements that are needed to fulfill
	private void getGradReqData(Courses coursesList) {
		ConnectDB connectdb = new ConnectDB();
		int _majorID;
		int _minorID;
		String _courseName;
		String _gradreqDesc;
		CoursesSet reqContainer;
		try {
			String queryString;
			queryString = "SELECT courseName, Desc ";
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
				reqContainer = new CoursesSet(_courseName, _gradreqDesc,coursesList);
				this.major1Req.add(reqContainer);
			}
			statement.close();
			if (major2 != 0) {
				queryString = "SELECT courseName, Desc ";
				queryString += "FROM tblreqcourse ";
				queryString += "WHERE majorID = " + major2;
				System.out.println(queryString);
				// Initialize a sql statement
				statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				recordSet = statement.executeQuery(queryString);
				// Major 2 retrieval
				while (recordSet.next()) {
					_courseName = recordSet.getString("courseName");
					_gradreqDesc = recordSet.getString("gradreqDesc");
					_minorID = recordSet.getInt("minorID");
					_majorID = recordSet.getInt("majorID");
					if (_minorID == 9999 && _majorID == 9999)
						throw new RuntimeException("the coders suck at programming... sorry");
					if (_minorID < 9999 && _majorID < 9999)
						throw new RuntimeException("the coders suck at programming... sorry");
					reqContainer = new CoursesSet(_courseName, _gradreqDesc, coursesList);
					this.major2Req.add(reqContainer);
				}
				statement.close();
			}
			if (minor != 0) {
				queryString = "SELECT courseName, Desc ";
				queryString += "FROM tblreqcourse ";
				queryString += "WHERE majorID = " + minor;
				System.out.println(queryString);
				// Initialize a sql statement
				statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				recordSet = statement.executeQuery(queryString);
				// retreving minor data
				while (recordSet.next()) {
					_courseName = recordSet.getString("courseName");
					_gradreqDesc = recordSet.getString("gradreqDesc");
					_minorID = recordSet.getInt("minorID");
					_majorID = recordSet.getInt("majorID");
					if (_minorID == 9999 && _majorID == 9999)
						throw new RuntimeException("Database Error:the coders suck at programming... sorry");
					if (_minorID < 9999 && _majorID < 9999)
						throw new RuntimeException("the coders suck at programming... sorry");
					reqContainer = new CoursesSet(_courseName, _gradreqDesc, coursesList);
					this.minorReq.add(reqContainer);
				}
				statement.close();
			}
		}catch (SQLException e) {
			throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
		} finally {
			connectdb.disconectDB();
		}
	}
	//gets requirement data by ID
	public ArrayList<CoursesSet> reqByID(int ID){
		if(ID==major1)
			return major1Req;
		if(ID==major2)
			return major2Req;
		if(ID==minor)
			return minorReq;
		throw new RuntimeException("Error: requirments ID mismatch");
	}
	public void CheckAddedClass(Course course) {
		if(major1!=0) {
			
		}else {
			throw new RuntimeException("Error: primary major information removed.");
		}
	}
	private void SortList(int num) {
		if(num==1){
			Collections.sort(major1Req);
		}else if(num==2) {
			Collections.sort(major2Req);
		}else if(num==3) {
			Collections.sort(minorReq);
		}
	}
	
}


