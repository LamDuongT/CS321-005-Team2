import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Comparator;
import java.util.*;

/*
 * @author Robert Tagliaferri
 */

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
		//
		this.major1= major1;
		this.major2=0;
		this.minor=0;
		tempHolder=new ArrayList<>();
                major1Req = new ArrayList<>();
                major2Req = new ArrayList<>();
                minorReq  = new ArrayList<>();
		if(major2!=9999) 
			this.major2=major2;
		if(minor!=9999)
			this.minor=minor;
		getGradReqData(coursesList);
		Collections.sort(major1Req);
		if(major2==0)
			Collections.sort(major2Req);
		if(minor==0)
			Collections.sort(minorReq);
		for(CreditTaken a: planLevel.getCreditsTakenList()) {
			addCourse(a.getCourseID());
		}
		for(CreditTaken a: profileLevel.getCreditsTakenList()) {
			addCourse(a.getCourseID());
		}
	}
	//returns the %completion of a major/minor
	public float checkTotalCompletion() {
		int coursesNeeded=0;
		int coursesFulfilled=0;
		for(CoursesSet a: major1Req) {
			coursesNeeded+=a.getAmountOfChoices();
			if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
				coursesFulfilled+=a.getAmountOfChosen();
			}else {
				coursesFulfilled+=a.getAmountOfChoices();
			}
		}
		if(major2!=0) {
			for(CoursesSet a: major2Req) {
				
				coursesNeeded+=a.getAmountOfChoices();
				if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
					coursesFulfilled+=a.getAmountOfChosen();
				}else {
					coursesFulfilled+=a.getAmountOfChoices();
				}
		
			}
		}
		if(minor!=0) {
			for(CoursesSet a: minorReq) {
				
				coursesNeeded+=a.getAmountOfChoices();
				if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
					coursesFulfilled+=a.getAmountOfChosen();
				}else {
					coursesFulfilled+=a.getAmountOfChoices();
				}
		
			}
		}
		return ((float)coursesFulfilled)/coursesNeeded;		

	}
	public float checkCompletion(int reqSel) {
		//number needed for a major/minor
		int coursesNeeded=0;
		//number fulfilled for a major/minor
		int coursesFulfilled=0;
		//same method done 3 times based on the indexed variable
		if(reqSel==1) {
			//goes through each CoursesSet and adds the reamining courses and needed courses int
			for(CoursesSet a: major1Req) {
				coursesNeeded+=a.getAmountOfChoices();
				//make sure you are not counting a requirement that has fufilled its requirements
				if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
					coursesFulfilled+=a.getAmountOfChosen();
				}else {
					//this prevents weird stuff like getting 120% completion
					coursesFulfilled+=a.getAmountOfChoices();
				}
					
				
			}
		}
		if(reqSel==2) {
			for(CoursesSet a: major2Req) {
				coursesNeeded+=a.getAmountOfChoices();
				if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
					coursesFulfilled+=a.getAmountOfChosen();
				}else {
					coursesFulfilled+=a.getAmountOfChoices();
				}
					
				
			}
		}
		if(reqSel==3) {
			for(CoursesSet a: minorReq) {
				coursesNeeded+=a.getAmountOfChoices();
				if(!(a.getAmountOfChoices()-a.getAmountOfChosen()<0)) {
					coursesFulfilled+=a.getAmountOfChosen();
				}else {
					coursesFulfilled+=a.getAmountOfChoices();
				}				
			}
		}
		return ((float)coursesFulfilled)/coursesNeeded;
	}
	
	public void addCourse(int courseID) {
		for(CoursesSet a:major1Req) {
			if(a.checkReq(courseID)) {
				if(!a.checkScheduled(courseID)) {
					tempHolder.add(a);
				}
			}
		}
		
		int difference=999;
		//iterator
		int i=0;
		//captures a specific i in ze loops
		int s=0;
		for(CoursesSet a: tempHolder) {
			if(Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen())<difference) {
				difference=Math.abs(a.getAmountOfChoices()-a.getAmountOfChosen());
				s=i;
			}
			i++;
		}
		if(!tempHolder.isEmpty())
			tempHolder.get(s).scheduleCourse(courseID);
		Collections.sort(major1Req);
		tempHolder.clear();
		
		if(major2!=0) {
			for(CoursesSet a:major2Req) {
				if(a.checkReq(courseID)) {
					if(!a.checkScheduled(courseID)) {
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
			if(!tempHolder.isEmpty())
				tempHolder.get(s).scheduleCourse(courseID);

			Collections.sort(major2Req);
			tempHolder.clear();
		}
		if(minor!=0) {
			for(CoursesSet a:minorReq) {
				if(a.checkReq(courseID)) {
					if(!a.checkScheduled(courseID)) {
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
			if(!tempHolder.isEmpty())
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
		String queryString;
		try {
			queryString = "SELECT courseName, gradreqDesc FROM tblgradreqcourse WHERE majorID = "+ major1;
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
//				_minorID= recordSet.getInt("minorID");
//				_majorID= recordSet.getInt("majorID");
//				if(_minorID == 9999 && _majorID == 9999)
//					throw new RuntimeException("the coders suck at programming... sorry");
//				if(_minorID<9999 && _majorID<9999)
//					throw new RuntimeException("the coders suck at programming... sorry");
				reqContainer = new CoursesSet(_courseName, _gradreqDesc,coursesList);
				this.major1Req.add(reqContainer);
			}
			statement.close();
			if (major2 != 0) {
				queryString = "SELECT courseName, gradreqDesc ";
				queryString += "FROM tblgradreqcourse ";
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
//					_minorID = recordSet.getInt("minorID");
//					_majorID = recordSet.getInt("majorID");
//					if (_minorID == 9999 && _majorID == 9999)
//						throw new RuntimeException("the coders suck at programming... sorry");
//					if (_minorID < 9999 && _majorID < 9999)
//						throw new RuntimeException("the coders suck at programming... sorry");
					reqContainer = new CoursesSet(_courseName, _gradreqDesc, coursesList);
					this.major2Req.add(reqContainer);
				}
				statement.close();
			}
			if (minor != 0) {
				queryString = "SELECT courseName, gradreqDesc ";
				queryString += "FROM tblgradreqcourse ";
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
//					_minorID = recordSet.getInt("minorID");
//					_majorID = recordSet.getInt("majorID");
//					if (_minorID == 9999 && _majorID == 9999)
//						throw new RuntimeException("Database Error:the coders suck at programming... sorry");
//					if (_minorID < 9999 && _majorID < 9999)
//						throw new RuntimeException("the coders suck at programming... sorry");
					reqContainer = new CoursesSet(_courseName, _gradreqDesc, coursesList);
					this.minorReq.add(reqContainer);
				}
				statement.close();
			}
		}catch (SQLException e) {
                    System.out.println("\n\n\n\n\n"+e.getLocalizedMessage()+"\n\n\n\n\n\n");
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
	//
	public void SortList(int num) {
		if(num==1){
			Collections.sort(major1Req);
		}else if(num==2) {
			Collections.sort(major2Req);
		}else if(num==3) {
			Collections.sort(minorReq);
		}
	}
	//
	public void SortLists() {
		Collections.sort(major1Req);
		Collections.sort(major2Req);
		Collections.sort(minorReq);
	}
	
}


