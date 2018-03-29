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
	private Majors majorsData;
	private Minors minorsData;
	private Major[] majors;
	private Minor[] minors;
	private Semesters semesters;
	private int catalogID;

	public Plan(int planID, String planName, int catalogID, int majorID) {
		this.PLAN_ID = planID;
		this.planName = planName;
		this.catalogID = catalogID;

		this.semesters = new Semesters();
		this.majorsData = new Majors(catalogID);
		this.minorsData = new Minors(catalogID);

		// Instantiation of empty minors and majors
		this.majors = new Major[] { new Major(), new Major() };
		this.minors = new MMinor[] { new Minor(), new Minor() };

		// PLAN CANNOT BE INSTANTIATED WITHOUT AT LEAST ONE MAJOR
		this.majors[0] = majorsData.getByID(majorID);
	}

	/**
	 * ACCESSOR METHODS:
	 */
	public int getPlanID() {
		return this.PLAN_ID;
	}

	public String getPlanName() {
		return this.planName;
	}

	public Major[] getMajors() {
		return this.majors;
	}

	public Minors[] getMinors() {
		return this.minors;
	}

	public Majors getMajorsData() {
		return this.majorsData;
	}

	public Minors getMinorsData() {
		return this.minorsData;
	}

	public int getCatalogID() {
		return this.catalogID;
	}

	/**
	 * MUTATOR METHODS:
	 */
	
	// Changing major using the 
	public void setMajor(int majorPosition, int majorID) {
		// Add and Change
		// If not removing (majorID not being -1)
		if (majorID >= 0) {
			Major m = this.majorsData.getMajorByID(majorID);
			// If primary major is not the same as the major being added
			if ((majors[0].getMajorID() != m.getMajorID()) && 
			(majors[1].getMajorID() != m.getMajorID())){
				// If major being added is not the same name as a current minor
				if ((!m.getMajorName().equals(minor[0].getMinorName())) &&
				(!m.getMajorName().equals(minor[1].getMinorName()))) {
					majors[majorPosition] = m;
					new UpdateData
				}
				else {
					throw new RuntimeException("ERROR: Major-minor conflict: Cannot add a major that is the same name as a minor!");
				}
			}
			else {
				throw new RuntimeException("ERROR: Major-major conflict: Cannot add the same major again!");
			}
		}
		// Remove
		else {
			if (majorPosition == 1) {
				majors[1] = null;
			}
			else {
				throw new RuntimeException("ERROR: Cannot remove the primary major.")
			}
		}
	}

	public void setMinor(int minorPosition, int minorID) {
		// Add and Change
		// If not removing (minorID not being -1)
		if (minorID >= 0) {
			Minor m = this.minorsData.getMinorByID(minorID);
			// If primary minor is not the same as the minor being added
			if ((minors[0].getMinorID() != m.getMinorID()) && 
			(minors[1].getMinorID() != m.getMinorID())){
				// If minor being added is not the same name as a current minor
				if ((!m.getMinorName().equals(major[0].getMajorName())) &&
				(!m.getMinorName().equals(major[1].getMajorName()))) {
					minors[minorPosition] = m;
				}
				else {
					throw new RuntimeException("ERROR: Minor-minor conflict: Cannot add a minor that is the same name as a minor!");
				}
			}
			else {
				throw new RuntimeException("ERROR: Minor-major conflict: Cannot add the same minor again!");
			}
		}
		// Remove
		else {
			minors[1] = null;
		}
	}

	public void setCatalog(int catalogID) {
		this.catalogID = catalogID;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void generateSmartPlan() {
		// TODO: GENERATE SORTING ALGORITHM
	}

	public void retrievePlan(int planID) {
	}

	public void updatePlan(int planID) {
	}

}
