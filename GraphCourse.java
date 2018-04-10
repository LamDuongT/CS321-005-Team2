
/**
 * 
 */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;



/**
 * @author Huan Nguyen
 *
 */
public class GraphCourse {

	Graph sampleGraph;
	Graph theGraph;

	public GraphCourse() throws IOException {
		this.sampleGraph = generateSampleGraph();
	}

	public GraphCourse(int catalogID) throws IOException {
		this.theGraph = generateGraph(catalogID, -1, -1);
	}

	public GraphCourse(int catalogID, int majorID) throws IOException {
		this.theGraph = generateGraph(catalogID, majorID, -1);
	}

	public GraphCourse(int catalogID, int majorID, int minorID) throws IOException {
		this.theGraph = generateGraph(catalogID, majorID, minorID);
	}

	public Graph getSampleGraph() {
		return this.sampleGraph;
	}

	public Graph getGraph() {
		return this.theGraph;
	}

	private Graph generateGraph(int catalogID, int majorID, int minorID) throws IOException {
		Graph graph = null;
		// only generate the graph for specific catalogID
		if (catalogID != -1) {
			// if the major or minor is not empty, we will continue
			// otherwise, return an empty graph
			if (majorID != -1 || minorID != -1) {
				//**********************************Graph Setting********************************************
				String graphName = "The Graph";
				String majorName = "";
				String minorName = "";

				graph = new SingleGraph(graphName);
				graph.addAttribute("ui.antialias"); 
				graph.addAttribute("ui.quality"); 
				graph.setAttribute("ui.stylesheet", "url(src/graph.style.css);"); // get some style
				//**********************************Graph Setting********************************************

				// get the list of graduation for majorID and/or minorID
				int _majorID = -1;
				int _minorID = -1;

				if (majorID != -1 && minorID != -1) {
					_majorID = majorID;
					_minorID = minorID;
					majorName = getMajorMinorName(_majorID, 'm');
					minorName = getMajorMinorName(_minorID, 'n');

				} else if (majorID != -1) {
					_majorID = majorID;
					_minorID = -1;
					majorName = getMajorMinorName(_majorID, 'm');

				} else if (minorID != -1) {
					_majorID = -1;
					_minorID = minorID;

					minorName = getMajorMinorName(_minorID, 'n');
				}
				// get the list of graduation requirement
				List<Course> coursesList = new Courses(catalogID,_majorID, _minorID).getCoursesList();
				// the list of prereqs courses
				List<Prereq> prereqsList;

				Node node;
				int nodeIndex = 1;

				// if the major is not empty
				if (majorID != -1) {
					node = graph.addNode("major");
					node.addAttribute("ui.label", majorName);
				}

				// if the minor is not empty
				if (minorID != -1) {
					node = graph.addNode("minor");
					node.addAttribute("ui.label", minorName);
				}

				int index, parentIndex, childrenIndex;
				Course aCourse;
				String parentNodeName = "";

				String childNodeName = "";

				String currentNodeName = "";
				String currentNodeDisplay = "";

				int parentCourseID = -1;

				LinkedList<String> listChildrenCourseName = new LinkedList<String>();

				//****************************SLOW SOLUTION*********************************
				// 1. create all the nodes for each course in the courseList
				// 2. create edges for parent and children
				
				// loop through all course in the courseList
				for (index = 0; index < coursesList.size(); index ++ ) {
					aCourse = coursesList.get(index);
					if (aCourse != null) {
						currentNodeName = aCourse.getCourseName();
						currentNodeDisplay = currentNodeName;
						
						// format the node CS110 as CS 110
						String alphabet = currentNodeDisplay.replaceAll("[^-?a-zA-Z]+", ""); 
						String number = currentNodeDisplay.replaceAll("[^-?0-9]+", "");
						
						currentNodeDisplay = alphabet + "" + number;
						
						// add the new node based on the course name
						if (graph.getNode(currentNodeName) == null) {
							node = graph.addNode(currentNodeName);
							node.addAttribute("ui.label", currentNodeDisplay);
						}
						
					}// end if aCourse != null
				}// end for loop
				
				// loop through all course in the courseList
				for (index = 0; index < coursesList.size(); index ++ ) {
					aCourse = coursesList.get(index);					
					if (aCourse != null) {
						currentNodeName = aCourse.getCourseName();
						currentNodeDisplay = currentNodeName;
						
						//System.out.println(aCourse.toString() + "\n courseID::::" + aCourse.getCourseID());
						
						// get the list of prereq courses for the current course
						prereqsList= new Prereqs(aCourse.getCourseID()).getPrereqList();
						//System.out.println(" ==> Prereq currentCourseID = " + aCourse.getCourseID() + "==>" + prereqsList.toString());
						
						
						// if the prereqsList for the current course is not empty
						// meaning that this course will not be added directly to the major or minor
						// but it will be added by the parents class

						
						// only add courses that are parents or do not have prereq course
						if (prereqsList.size() == 0) {
//							System.out.println("\nmajorID:::" + aCourse.getMajorID() + "majorID Parameter =" + majorID + " indexOf" + aCourse.getMajorID().toString().indexOf(String.valueOf(majorID)));
							// if the preresList is 0, the current node will be the parentNode
							parentNodeName = currentNodeName;

							// add the course to the tree according to its major or minor
							// if the course belongs to major, add it to the graph
							if (aCourse.getMajorID().toString().indexOf(String.valueOf(majorID)) != -1 && majorID != -1) {					
								graph.addEdge("'major_" + parentNodeName + "'", "major", parentNodeName, true);
								nodeIndex++;
							}
							// if the course belongs to minor, add it to the graph
							if (aCourse.getMinorID().toString().indexOf(String.valueOf(minorID)) != -1 && minorID != -1) {					
								graph.addEdge("'minor_" + parentNodeName + "'", "minor", parentNodeName, true);
								nodeIndex++;
							}
							// get the list of children for this parentCourseID
							listChildrenCourseName = getChildrenCourseName(parentCourseID);
							// add the child to its parent
							for(childrenIndex = 0; childrenIndex < listChildrenCourseName.size(); childrenIndex++) {

								// then, get the child course for the current course
								childNodeName = listChildrenCourseName.get(childrenIndex).toString();

								System.out.println("Node::::" + graph.getNode(childNodeName));

								// link the child node to its parent
								if (graph.getEdge("'" + parentNodeName + "_" + childNodeName  + "'") == null) {
									graph.addEdge("'" + parentNodeName + "_" + childNodeName  + "'", parentNodeName, childNodeName, true);
									nodeIndex++;
								}

							} // end for childIndex

						} // end if the prereqsList.size == 0
						// when the prereqsList.size is not 0
						
						// if the prereqsList.size > 0, meaning that this current node will have children node
						else {
							// if the prereqsList size is not 0, the currentNodeName will become the childNodeName
							childNodeName = currentNodeName;

							// add the parents to its children
							for(parentIndex = 0; parentIndex < prereqsList.size(); parentIndex++) {

								// then, get the child course for the current course
								parentNodeName = prereqsList.get(parentIndex).getPrereqCourseName();

								System.out.println("Node::::" + graph.getNode(parentNodeName));

								// link the child node to its parent
								if (graph.getEdge("'" + parentNodeName + "_" + childNodeName  + "'") == null) {
									graph.addEdge("'" + parentNodeName + "_" + childNodeName  + "'", parentNodeName, childNodeName, true);
									nodeIndex++;
								}

							} // end for childIndex
						} // end prereqsList.size() != 0
						
					}// end if aCourse != null
				}// end for loop
				
			}// end if majorID | minorID != -1
		}// end if catalogID != -1
		return graph;
	}

	/*
	 private Graph generateGraph(int majorID, int minorID) throws IOException {
		Graph graph = null;
		// if the major or minor is not empty, we will continue
		// otherwise, return an empty graph
		if (majorID != -1 || minorID != -1) {
			String graphName = "The Graph";
			String majorName = "";
			String minorName = "";

			graph = new SingleGraph(graphName);
			graph.addAttribute("ui.antialias"); 
			graph.addAttribute("ui.quality"); 
			graph.setAttribute("ui.stylesheet", "url(src/graph.style.css);"); // get some style


			// get the list of graduation for majorID and/or minorID
			int _majorID = -1;
			int _minorID = -1;

			if (majorID != -1 && minorID != -1) {
				_majorID = majorID;
				_minorID = minorID;
				majorName = getMajorMinorName(_majorID, 'm');
				minorName = getMajorMinorName(_minorID, 'n');

			} else if (majorID != -1) {
				_majorID = majorID;
				_minorID = -1;
				majorName = getMajorMinorName(_majorID, 'm');

			} else if (minorID != -1) {
				_majorID = -1;
				_minorID = minorID;

				minorName = getMajorMinorName(_minorID, 'n');
			}
			// get the list of graduation requirement
			List<GradreqCourse> gradreqcoursesList = new GradreqCourses(_majorID, _minorID).getGradreqCourseList();
			// the list of prereqs courses
			List<Prereq> prereqsList;

			Node node;
			int nodeIndex = 1;

			// if the major is not empty
			if (majorID != -1) {
				node = graph.addNode("major");
				node.addAttribute("ui.label", majorName);
			}

			// if the minor is not empty
			if (minorID != -1) {
				node = graph.addNode("minor");
				node.addAttribute("ui.label", minorName);
			}

			GradreqCourse aReqCourse;
			String parentNodeName = "";
			String parentNodeDisplay = "";

			String childNodeName = "";
			String childNodeDisplay = "";

			int parentCourseID = -1;
			LinkedList<String> listChildrenCourseName = new LinkedList<String>();

			// loop through all course in the grad list
			for (int index = 0; index<gradreqcoursesList.size(); index ++ ) {
				aReqCourse = gradreqcoursesList.get(index);
				System.out.println("\nGradreq Courses = " + aReqCourse.toString());

				// if the courseName is not empty
				if (!aReqCourse.getCourseName().equals("")) {

					// get the courseID for the current node
					parentCourseID = getCourseID(aReqCourse.getCourseName());

					// first, we create a node for the current course
					parentNodeName = aReqCourse.getCourseName();
					parentNodeDisplay = parentNodeName + "-" + parentCourseID;

					// add the new node based on the course name
					if (graph.getNode(parentNodeName) == null) {
						node = graph.addNode(parentNodeName);
						node.addAttribute("ui.label", parentNodeDisplay);
					}


					// then, we get the list of prereq courses for the current course
					prereqsList= new Prereqs(parentCourseID).getPrereqList();
					System.out.println(" ==> Prereq parentCourseID = " + parentCourseID + "==>" + prereqsList.toString());

					// if the prereqsList for the current course is not empty
					// meaning that this course will not be added directly to the major or minor
					// but it will be added by the parents class

					// only add courses that are parents or do not have prereq course
					if (prereqsList.size() == 0) {				
						// add the course to the tree according to its major or minor
						// if the course belongs to major, add it to the graph
						if (aReqCourse.getMajorID() == majorID && majorID != -1) {					
							graph.addEdge("'" + nodeIndex + "'", "major", parentNodeName, true);
							nodeIndex++;
						}
						// if the course belongs to minor, add it to the graph
						if (aReqCourse.getMinorID() == minorID && minorID != -1) {					
							graph.addEdge("'" + nodeIndex + "'", "minor", parentNodeName, true);
							nodeIndex++;
						}
						// get the list of children for this parentCourseID
						listChildrenCourseName = getChildrenCourseName(parentCourseID);

						for(int childIndex = 0; childIndex < listChildrenCourseName.size(); childIndex++) {

							// then, get the child course for the current course
							childNodeName = listChildrenCourseName.get(childIndex).toString();
							childNodeDisplay = childNodeName;

							System.out.println("Node::::" + graph.getNode(childNodeName));

							// if the child node is not created, then create it
							// add the new node based on the course name
							if (graph.getNode(childNodeName) == null) {
								node = graph.addNode(childNodeName);
								node.addAttribute("ui.label", childNodeDisplay);
							}

							// link the child node to its parent
							graph.addEdge("'" + nodeIndex + "'", parentNodeName, childNodeName, true);
							nodeIndex++;

						} // end for childIndex

					}

				} // end if courseName is not empty
				// when courseName is empty, the description will hold the list of course need 
				// to take together.
				else {

				} // end if courseName is empty

			}

		}
		return graph;
	}
	 */



	private int getCourseID(String courseName) {
		int courseID = -1;

		if (!courseName.equals("")) {
			ConnectDB connectdb = new ConnectDB();

			try {
				// query string
				String queryString = "";
				queryString += "SELECT tblcourse.courseID ";
				queryString += "FROM tblcourse ";
				queryString += "WHERE tblcourse.courseName = '" + courseName + "' ";
				queryString += "LIMIT 1";
				System.out.println(queryString);

				// Initialize a sql statement
				Statement statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				ResultSet recordSet = statement.executeQuery(queryString);
				// loop through each record in the data table
				while (recordSet.next()) {
					courseID = recordSet.getInt("courseID");

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
		return courseID;
	}

	private LinkedList<String> getChildrenCourseName(int prereqCourseID) {
		LinkedList<String> listChildrenCourseName = new LinkedList<String>();

		String courseChilName = "";

		if (prereqCourseID != -1) {
			ConnectDB connectdb = new ConnectDB();

			try {
				// query string
				String queryString = "";
				queryString += "SELECT tblcourse.courseName ";
				queryString += "FROM collegespdb.tblprereq INNER JOIN tblcourse ON tblprereq.courseID = tblcourse.courseID ";
				queryString += "WHERE tblprereq.prereqCourseID = " + prereqCourseID + " ";
				System.out.println(queryString);

				// Initialize a sql statement
				Statement statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				ResultSet recordSet = statement.executeQuery(queryString);
				// loop through each record in the data table
				while (recordSet.next()) {
					courseChilName = recordSet.getString("courseName");
					listChildrenCourseName.add(courseChilName);

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
		return listChildrenCourseName;
	}

	private String getMajorMinorName(int theID, char majorOrMinor) {
		String majorOrMinorName = "";

		if (theID != -1) {
			ConnectDB connectdb = new ConnectDB();
			String fieldName = "";

			try {
				// query string
				String queryString = "";
				if (majorOrMinor == 'm') {
					// query string
					queryString = "SELECT majorName FROM `tblmajor` WHERE majorID = " + theID;
					fieldName = "majorName";
				} else if (majorOrMinor == 'n') {
					// query string
					queryString = "SELECT minorName FROM `tblminor` WHERE minorID = " + theID;
					fieldName = "minorName";
				}

				// if the catalogID is not -1, then we get the list of major for that catalogID

				System.out.println(queryString);

				// Initialize a sql statement
				Statement statement = connectdb.theConnection.createStatement();
				// recordSet will hold a data table as sql object
				// to see how the data table look like, copy the queryString contents and
				// execute in mysql Workbench
				ResultSet recordSet = statement.executeQuery(queryString);
				// loop through each record in the data table
				while (recordSet.next()) {
					majorOrMinorName = recordSet.getString(fieldName);

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
		return majorOrMinorName;
	}

	private Graph generateSampleGraph() throws IOException {
		Graph graph = new SingleGraph("Sample Graph");

		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.quality");
		graph.setAttribute("ui.stylesheet", "url(graph.style.css);");

		Node node;
		int nodeIndex = 1;

		node = graph.addNode("CS");
		node.addAttribute("ui.label", "Computer Science");
		node = graph.addNode("CS105");
		node.addAttribute("ui.label", "CS 105");
		node = graph.addNode("CS211");
		node.addAttribute("ui.label", "CS 211");
		node = graph.addNode("CS311");
		node.addAttribute("ui.label", "CS 311");
		node = graph.addNode("CS411");
		node.addAttribute("ui.label", "CS 411");

		node = graph.addNode("CS205");
		node.addAttribute("ui.label", "CS 205");
		node = graph.addNode("CS225");
		node.addAttribute("ui.label", "CS 225");

		node = graph.addNode("MATH113");
		node.addAttribute("ui.label", "MATH 113");
		node = graph.addNode("MATH114");
		node.addAttribute("ui.label", "MATH 114");

		node = graph.addNode("MATH213");
		node.addAttribute("ui.label", "MATH 213");
		node = graph.addNode("MATH214");
		node.addAttribute("ui.label", "MATH 214");

		node = graph.addNode("MATH203");
		node.addAttribute("ui.label", "MATH 203");
		node = graph.addNode("MATH125");
		node.addAttribute("ui.label", "MATH 125");

		node = graph.addNode("ENGR107");
		node.addAttribute("ui.label", "ENGR 107");

		node = graph.addNode("ECE101");
		node.addAttribute("ui.label", "ECE 101");
		node = graph.addNode("ECE201");
		node.addAttribute("ui.label", "ECE 201");
		node = graph.addNode("ECE220");
		node.addAttribute("ui.label", "ECE 220");

		graph.addEdge("'" + nodeIndex + "'", "CS", "CS105", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS", "CS205", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS", "MATH113", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS", "MATH125", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS", "ECE101", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS", "ENGR107", true);
		nodeIndex++;

		graph.addEdge("'" + nodeIndex + "'", "CS105", "CS211", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS105", "CS311", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS105", "CS411", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "CS205", "CS225", true);
		nodeIndex++;

		graph.addEdge("'" + nodeIndex + "'", "MATH113", "MATH114", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "MATH114", "MATH213", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "MATH114", "MATH214", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "MATH114", "MATH203", true);
		nodeIndex++;

		graph.addEdge("'" + nodeIndex + "'", "ECE101", "ECE201", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "MATH114", "ECE201", true);
		nodeIndex++;
		graph.addEdge("'" + nodeIndex + "'", "ECE201", "ECE220", true);
		nodeIndex++;

		return graph;
	}
}
