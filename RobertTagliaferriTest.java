import java.util.ArrayList;
import java.util.List;

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
		try {
			
		}catch(RuntimeException e){
			
		}
	}
}
