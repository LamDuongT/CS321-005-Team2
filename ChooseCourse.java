import java.util.LinkedList;

/**
 * @author Lam Duong
 * This class allows for the user selection of "wild card" classes
 * 
 */

public class ChooseCourse
{
	// Fields that will be instantiated by constructor parameters
	private int chooseCourseID;
	private int majoriD;
	private int minorID;
	private String chooseCourseName;
	private String chooseCourseDescription;
	
	// Fields that will be parsed by parseString() method
	private Course[] childrenCourses;
	private int amountOfClasses;
	
	public ChooseCourse(int chooseCourseID, int majorID, int minorID, String name, String description)
	{
		this.chooseCourseID = chooseCourseID;
		this.majoriD = majorID;
		this.minorID = minorID;
		this.chooseCourseName = name;
		this.chooseCourseDescription = description;
		
		this.parseString(this.chooseCourseDescription);
	}
	
	/*
	 * ACCESSOR METHODS
	 */
	public int getChooseCourseID()
	{
		return this.chooseCourseID;
	}
	public int getMajorID()
	{
		return this.majoriD;
	}
	public int getMinorID()
	{
		return this.minorID;
	}
	public String getName()
	{
		return this.chooseCourseName;
	}
	public String getDescription()
	{
		return this.chooseCourseDescription;
	}
	
	/*
	 * MUTATOR METHODS
	 */	
	private void parseString(String query)
	{
		
	}
}
