import java.util.ArrayList;
import java.util.List;

public class ReqContainer {
	private int numClasses;
	private int minNeeded;
	private ArrayList<String> courses;
	private int numFulfilled=0;
	private ArrayList<String> unscheduledCourses;
	private ArrayList<String> scheduledCourses;
	
	public ReqContainer(String input){
		String[] temp = input.split("\\s");
		if(temp[0]!="C") {
			numClasses=1;
			minNeeded=1;
			courses.add(input);
			unscheduledCourses=courses;
		}else {
			numClasses= temp.length-2;
			minNeeded=Integer.parseInt(temp[1]);
			for(int i=2;i<temp.length-3;i++) {
				courses.add(temp[i]);
			}
			unscheduledCourses=courses;
		}
		
	}
	public ArrayList<String> getclassList(){
		return courses;
	}
	public ArrayList<String> getUnscheduledCourses(){
		return unscheduledCourses;
	}
	public ArrayList<String> getScheduledCourses(){
		return scheduledCourses;
	}
	public int getNumClasses() {
		return numClasses;
	}
	//checks if a class name fulfills this requirement
	public boolean checkInReq(String className){
		return courses.contains(className);
	}
	public boolean checkUncheduledCourses(String className) {
		return unscheduledCourses.contains(className);
	}
	public boolean checkScheduledCourse(String className) {
		return scheduledCourses.contains(className);
	}
	public void scheduleCourse(String className) {
		unscheduledCourses.remove(className);
		scheduledCourses.add(className);
		numFulfilled++;
	}
	public int additionalCoursesNeeded() {
		return minNeeded-numFulfilled;
	}
	public void unscheduleCourse(String className) {
		scheduledCourses.remove(className);
		unscheduledCourses.add(className);
		numFulfilled--;
	}
}
