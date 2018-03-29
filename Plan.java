/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collegesp;
/**
 * @author Lam Duong
 */
public class Plan {
	private final int PLAN_ID;
	private String planName;
	private Majors majors;
	private Minors minors; 
	private Semesters semesters;
	private catalogID;
	
	
	/**
	 * ACCESSOR METHODS:
	 */
	public int getPlanID() {
		return this.PLAN_ID;
	}
	public String getPlanName() {
		return this.planName;
	}
	public Majors getMajors() {
		return this.majors;
	}
	public Minors getMinors() {
		return this.minors;
	}
	public int getCatalogID() {
		return this.catalogID;
	}
	
	/**
	 * MUTATOR METHODS:
	 */
	
	// Changing major using the 
	public void setMajor(majorPosition, majorID) {
		switch (majorPosition) {
		case 0:
			// You can only change first major but cannot delete or add
			Major m = new Major(majorID);
			this.majors.getMajorList[0] = 
		case 1:
		}
		
	}
	public void setMinor()
	public void setCatalog(int catalogID){
	}
	public void setPlanName() {
	}
	public void generateSmartPlan() {
	}
	public void retrievePlan(int planID) {
	}
	public void updatePlan(int planID) {
	}
	
}
