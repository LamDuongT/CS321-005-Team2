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
	public void setMajor(majorPosition) {
		Major m = this.majors.getMajorByNum(majorPosition);
		// Make sure that there is at least one major in the Majors object
		if (this.majors.getMajors.isEmpty()){
			throw new Exception("ERROR: The plan has no major.");
		}
		// Major has to be either 1 or 2 to indicate primary or second major
		if (majorsPosition != 1 || 2) {
			throw new Exception("ERROR: Only two majors are allowed. Major position input needs to be 1 or 2.");
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
