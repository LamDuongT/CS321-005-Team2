/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Lam Duong
 * @author Mohammed Alsharf
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
		this.minors = new Minor[] { new Minor(), new Minor() };

		// PLAN CANNOT BE INSTANTIATED WITHOUT AT LEAST ONE MAJOR
		this.majors[0] = majorsData.getMajorByID(majorID);
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

	public Minor[] getMinors() {
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

	/**
	 * 
	 * @param majorPosition
	 * @param majorID
	 * @return void
	 */
	public void setMajor(int majorPosition, int majorID) {
		// Add and Change
		// If not removing (majorID not being -1)
		if (majorID >= 0) {
			
			Major m = this.majorsData.getMajorByID(majorID);
			
			// If the major being added does not have the same ID as a major within majors array
			if ((majors[0].getMajorID() != m.getMajorID()) && (majors[1].getMajorID() != m.getMajorID())) {
				
				// If major being added does not have the same name as a current minor within minors array
				if ((!m.getMajorName().equals(minors[0].getMinorName())) && (!m.getMajorName().equals(minors[1].getMinorName()))) {
					
					majors[majorPosition] = m;
					
				} else {
					throw new RuntimeException(
							"ERROR: Major-minor conflict: Cannot add a major that is the same name as a minor!");
				}
			} else {
				throw new RuntimeException("ERROR: Major-major conflict: Cannot add the same major again!");
			}
		}
		// Removal processs
		else {
			// Removal is only possible with the secondary major
			if (majorPosition == 1) {
				majors[1] = new Major();
			} else {
				// and not the primary major
				throw new RuntimeException("ERROR: Cannot remove the primary major.");
			}
		}

		// Update to Databse once finished with modification in Java
		new UpdateData().updatePlan(this, 'u');
	}
	
	/**
	 * 
	 * @param minorPosition
	 * @param minorID
	 * @return void
	 */
	public void setMinor(int minorPosition, int minorID) {
		// Add and Change
		// If not removing (minorID not being -1)
		if (minorID >= 0) {
			
			Minor m = this.minorsData.getMinorByID(minorID);
			
			// If minor being added doesn't already exist within the minors array
			if ((minors[0].getMinorID() != m.getMinorID()) && (minors[1].getMinorID() != m.getMinorID())) {
				
				// If minor being added does not have the same name as a major within majors array
				if ((!m.getMinorName().equals(majors[0].getMajorName())) && (!m.getMinorName().equals(majors[1].getMajorName()))) {
					
					minors[minorPosition] = m;
					
				} else {
					
					throw new RuntimeException(
							"ERROR: Minor-major conflict: Cannot add a minor that is the same name as a major!");
				}
			} else {
				throw new RuntimeException("ERROR: Minor-minor conflict: Cannot add the same minor again!");
			}
		}
		// Removal of minors
		else {
			minors[minorPosition] = new Minor();
		}
		// Update to Databse once finished with modification in Java
		new UpdateData().updatePlan(this, 'u');
	}
	
	/**
	 * @author Mohammed Alsharf
	 * @param sm
	 * @param action
	 */
	public void setSemester(Semester sm, char action) {
		switch (action) {
		case 'i':// insert
			break;
		case 'u':// update
			break;
		case 'd':// delete
			break;
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
}
