use collegespdb;
SELECT plan.planID, plan.catalogID, plan.majorID, plan.minorID, plan.majorID2, plan.minorID2, 
profile.studentID, profile.profileName, course.courseID, course.courseName, credit.semesterID,
se.semesterName, se.creditMax, se.creditMin
FROM tblplan plan INNER JOIN tblcreditstaken credit ON plan.profileID = credit.studentID
     INNER JOIN tblcourse course on course.courseID = credit.courseID
     INNer JOIN tblprofile profile on plan.profileID = profile.studentID
     INNER JOIN tblsemester se ON se.semesterDesc = credit.semesterID 
WHERE plan.planID = 1