import java.util.ArrayList;

public class RobertTagliaferriTest {

	public static void main(String[] args) {
		Courses all = new Courses(3);
		//constructs for testing
		GradRequirement reqTest= new GradRequirement();
		try {
			reqTest = new GradRequirement(1,2,1,all);
		}catch(RuntimeException e) {
			System.out.println("Could not construct");
		}
		//tests if the arrayLists are being constructed
		ArrayList<CoursesSet>[] test= new ArrayList[3];
		for(int i=1;i<=3;i++) {
			try {
				test[i] = reqTest.getCoursesSetList(1);
			}catch(RuntimeException e) {
				System.out.println("AList: "+ i +" failed");
			}
		}
		reqTest.SortList(1);
		reqTest.SortList(2);
		reqTest.SortList(3);
		reqTest.SortLists();
		if(test[0].equals(reqTest.reqByID(1))){
			System.out.println("reqById1 successfull");
			if(test[1].equals(reqTest.reqByID(2))) {
				System.out.println("reqByID2 successfull");
				if(test[2].equals(reqTest.reqByID(1))) {
					System.out.println("all reqByID successfull");
				}
			}
		}
		float ComTest=reqTest.checkTotalCompletion();
		if(ComTest==0) {
			System.out.println("working");
		}	
	}
}
