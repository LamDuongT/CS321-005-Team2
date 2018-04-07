import java.io.IOException;

public class HuanTestClass {

	public static void main(String[] args) {
		// ******************************************TEST
		// LOGIN******************************************
		// connectdb.reconnect();
		
		 Login aStudent = new Login("hnguye80", "hnguye80"); aStudent.doLogin();
		 aStudent.getProfile(aStudent.getStudentID());
		 
		 /*
		 Profile aNewStudent = new Profile(-1, "andrew", "Andrew Nguyen",
		 "andrew@andrew.com", "andrew123", "andrew123", "Andrew's Profile123");
		 UpdateData updateData = new UpdateData();
		 
		 updateData.updateProfile(aNewStudent, 'u');
		 
		 Creditstaken aCredittaken = new Creditstaken(-1, 1, 68, "CS321", 3, false);
		 updateData.updateCreditstaken(aCredittaken, 'u');
		 */
		 
		 Creditstakens creditstakenList = new Creditstakens(aStudent.getStudentID());
		 System.out.println("The list of Creditstaken \n" +
		 creditstakenList.toString());
		 
		 Semesters semesterList = new Semesters();
		 System.out.println("The list of Semesters \n" + semesterList.toString());
		 
		 Courses coursesList = new Courses();
		 System.out.println("The list of Courses \n" + coursesList.toString());
		 
/*		 Courses coursesListForCatalog = new Courses(3, -1, ' ');
		 System.out.println("The list of Courses for catalog \n" +
		 coursesListForCatalog.toString());*/
		 
		/* Courses coursesListForCatalogAndMajor = new Courses(3, 3, 'm');
		 System.out.println("The list of Courses for catalog and Major \n" +
		 coursesListForCatalogAndMajor.toString());*/
		 
		 GradreqCourses gradreqcoursesList = new GradreqCourses(1);
		 System.out.println("The list of Gradreq Courses \n" +
		 gradreqcoursesList.toString());
		 
		GraphCourse aGraph = null;
		try {
			aGraph = new GraphCourse(1, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aGraph.getGraph().display();
		
		

/*		 Catalogs catalogs = new Catalogs();
		 
		 Catalog catalog2017 = catalogs.getCatalogByName("catalog 2017");
		 System.out.println("Catalog 2017 \n" + catalog2017.toString());
		 
		 Majors majors = new Majors(catalog2017.getCatalogID());
		 System.out.println("List of Major for Catalog 2017 \n" + majors.toString());
		 System.out.println("The Computer Science \n" + majors.getMajorByName("CS").toString());
 
		 Courses courseList = new Courses();
		 System.out.println("***********List of Course***************\n" +
		 courseList.toString());*/
		
	}

}
