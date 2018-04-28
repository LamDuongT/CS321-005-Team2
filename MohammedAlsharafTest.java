
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author moo7md
 */
public class MohammedAlsharafTest {

    public static void main(String[] args) {
        /*
        This file is created to test some code in UpdateData().updateSemester(...)
         */
        //Fake Plan...
        testPlan();
        /*
        This test is created to test get
         */
        testSemesters();
        /*
        This part test the profile level aspact of the project
        It will create a new profile, and try to fetch it from the database
        Also it will add some plans+credittaken to it and test these to see
        if it work properly
         */
        Profile profile = testProfile();
        /*
        This test tests the MainWindow frame. It receives the latest profile 
        object created and generate the MainWindow
        */
        if(profile!=null)testMainWindow(profile);
    }

    public static void testPlan() {
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
            System.out.println("+--------------------+\nTest 1: faild\n+--------------------+");
        }
    }

    public static void testSemesters() {
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
    public static Profile testProfile(){
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
        }finally{
            connectDB.disconectDB();
        }
        return profile;
    }

    private static void testMainWindow(Profile profile) {
        try{
            MainWindow window = new MainWindow(profile);
            window.setVisible(true);
            System.out.println("+--------------------+\nTest 3: succeeded\n+--------------------+");
        }catch(RuntimeException e){
            System.out.println("+--------------------+\nTest 3: faild\n+--------------------+");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
