
public class HuanTestClass {

	public static void main(String[] args) {
		//******************************************TEST LOGIN******************************************
//      connectdb.reconnect();
      Login aStudent = new Login("hnguye80", "hnguye80");
      aStudent.doLogin();
      aStudent.getProfile(aStudent.getStudentID());
      
      Catalogs catalogs = new Catalogs();
      
      Catalog catalog2017 = catalogs.getCatalogByName("catalog 2017");      
      System.out.println("Catalog 2017 \n" + catalog2017.toString());   
      
      Majors majors = new Majors(catalog2017.getCatalogID());
      System.out.println("List of Major for Catalog 2017 \n" + majors.toString());
      
      Courses courseList = new Courses();
      System.out.println("***********List of Course***************\n" + courseList.toString());
	}

}
