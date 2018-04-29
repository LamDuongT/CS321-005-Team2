
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mohammed Alsharaf
 * This file tests some components of the system to check if they are working 
 * properly
 */
public class MohammedAlsharafTest {

    public static void main(String[] args) {
        /*
        This file is created to test some code in UpdateData().updateSemester(...)
         */
        //Fake Plan... test 1
        testPlan();
        /*
        This test 2 is created to test get
         */
        testSemesters();
        /*
        This part test the profile level aspact of the project
        It will create a new profile, and try to fetch it from the database
        Also it will add some plans+credittaken to it and test these to see
        if it work properly
        TEST 3
         */
        Profile profile = testProfile();
        /*
        This test tests the MainWindow frame. It receives the latest profile 
        object created and generate the MainWindow
        TEST 4
         */
        if (profile != null) {
            testMainWindow(profile);
        }
        /*
        Now this test tests the Majors.java to see if it retrieves all majors from a specific catalog 
         */
        //TEST 5
        testMajors();
        /*
        Now this test tests the Minors.java to see if it retreieves all minors from a specific catalog
        */
        //TEST 6
        testMinors();
        /*
        Now this test checks if a the program will properly show the semester table for a given semeter in plan
        */
        testSemesterTable(profile);
    }

    private static void testPlan() {
        //        Plan(int planID, int profileID, int catalogID, String planName, int majorID, int minorID, int major2ID,
//			int minor2ID, CreditsTaken profileCoursesTaken, Semesters listOfSemesters)
        Plan plan = new Plan(9998, 9998, 9998, "PlanTest", 1, 9999, 9999, 9999, new CreditsTaken(), new Semesters());
        //Fake Semesters
        Semester sm = new Semester(1, "Fall 2018", "Bla Bla", 9, 18, new ArrayList<>());
        //This line of code tests the updateSemester(...)
        try {
            new UpdateData().updateSemester(sm, 'i');
            System.out.println("+--------------------+\nTest 1: succeeded\n+--------------------+");
        } catch (RuntimeException e) {
            System.out.println("+--------------------+\n[Error] something wrong happned\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
            System.out.println("NOTE: SOMETIMES THE TEST WILL FIAL BECUASE THE PLAN IS ALREADY"
                    + " IN THE DATABASE, IF THIS WAS THE CASE IGNORE THIS FAILER");
            System.out.println("+--------------------+\nTest 1: faild\n+--------------------+");
            
        }
    }

    private static void testSemesters() {
        try {
            List<Semester> list = (List<Semester>) new Semesters().getSemesterList();
            list.forEach((smtemp) -> {
                System.out.println(smtemp.getSemesterName());
            });
            System.out.println("+--------------------+\nTest 2: succeeded\n+--------------------+");
        } catch (RuntimeException e) {
            System.out.println("+--------------------+\n[Error] something wrong happned\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
            System.out.println("+--------------------+\nTest 2: faild\n+--------------------+");
        }
    }

    private static Profile testProfile() {
        //This step is used to add the new profile into the database
        boolean test = true;//boolean value for tracking if the test faild or not
        int studentID = 9999;//defualt null value in our database
        Profile profile = null;
        String netID = "12112", studentName = "test", studentEmail = "email@test.com",
                username = "test", password = "0000", profileName = "prof";
        //This step construct the connection
        ConnectDB connectDB = new ConnectDB();
        connectDB.reconnect();
        try {
            Statement statement = connectDB.theConnection.createStatement();
            //this query insert a new profile to database
            String query = "INSERT INTO collegespdb.tblprofile(netID,studentName,studentEmail,username,password,profileName)";
            query += " VALUES(\"" + netID + "\",\"" + studentName + "\",\"" + studentEmail + "\",\"" + username + "\",\"" + password + "\",\"" + profileName + "\")";
            statement.executeUpdate(query);//query executed 
            //Now this one gets the last profileID for the last insertion
            query = "SELECT LAST_INSERT_ID() as profileID";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                studentID = result.getInt("profileID");
            }
            if (studentID == 9999) {
                test = false;//test faild
            } else {
                test = true;
                profile = new Profile(studentID, netID, studentName, studentEmail, username, password, profileName);
                System.out.println(profile.toString());
                System.out.println("+--------------------+\nTest 3: succeeded\n+--------------------+");
            }
        } catch (SQLException ex) {
            System.out.println("+--------------------+\nTest 3: faild\n+--------------------+");
            System.out.println(ex.getLocalizedMessage());
        } finally {
            connectDB.disconectDB();
        }
        return profile;
    }

    private static void testMainWindow(Profile profile) {
        try {
            MainWindow window = new MainWindow(profile);
            window.setVisible(true);
            System.out.println("+--------------------+\nTest 4: succeeded\n+--------------------+");
        } catch (RuntimeException e) {
            System.out.println("+--------------------+\nTest 4: faild\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void testMajors() {
        //Get catalog 2017
        try {
            Catalog cat = new Catalogs().getCatalogByName("Catalog 2017");
            Majors majors = new Majors(cat.getCatalogID());
            if(majors.getMajorByName("Computer Science").getMajorID()==-1){
                throw new RuntimeException("Major was not found in database");
            }
            System.out.println("+--------------------+\nTest 5: succeeded\n+--------------------+");
            System.out.println(majors.toString());
        } catch (RuntimeException e) {
            System.out.println("+--------------------+\nTest 5: faild\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
        }
    }
    private static void testMinors(){
        //Get catalog 2017
        try {
            Catalog cat = new Catalogs().getCatalogByName("Catalog 2017");
            Minors minors = new Minors(cat.getCatalogID());
            if(minors.getMinorByName("IT").getMinorID()==-1){
                throw new RuntimeException("Minor was not found in database");
            }
            System.out.println("+--------------------+\nTest 6: succeeded\n+--------------------+");
            System.out.println(minors.toString());
        } catch (RuntimeException e) {
            System.out.println("+--------------------+\nTest 6: faild\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void testSemesterTable(Profile profile) {
        //get all the plans in a profile
        //test each one and see if they all work fine
        //test indcator
        boolean test = true;
        LinkedList<Plan> plans = (LinkedList<Plan>) profile.getPlans().getPlans();
        for(Plan plan : plans){
            //now for each plan tests each semester inside it
            for(Semester sem : plan.getSemestersList()){
                //now construct each table if any excption was thorwn catch and display the message
                try{
                new SemsterTable(sem,plan,plan.getCoursesList()).setVisible(true);
                }catch(RuntimeException e){
                    test=false;
                    System.out.println("+--------------------+\nTest 7: faild\n+--------------------+");
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
        if(test){
            System.out.println("+--------------------+\nTest 7: succeeded\n+--------------------+");
        }
    }
}
