SELECT plan.planID, plan.catalogID, plan.majorID, plan.minorID, plan.majorID2, plan.minorID2, 
		profile.studentID, profile.profileName, 
        course.courseID, course.courseName, credit.semesterID
FROM tblplan plan INNER JOIN tblcreditstaken credit ON plan.profileID = credit.studentID
	 INNER JOIN tblcourse course on course.courseID = credit.courseID
     INNER JOIN tblprofile profile on plan.profileID = profile.studentID
WHERE planID = 2
