/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Huan Nguyen
 */
public class Student {
    private String studentID;
    private String password;
    private String studentName;
    private String studentEmail;
    
    public Student() {
        setStudent("", "", "", "");
    }
    
    public Student(String studentID, String password, String studentName, String studentEmail) {
        setStudent(studentID, password, studentName, studentEmail);
    }
    
    public void setStudent (String studentID, String password, String studentName, String studentEmail) {
        this.studentID = studentID;
        this.password = password;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }
    
    public String getStudentID() {
        return this.studentID;        
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getStudentName() {
        return this.studentName;
    }
    
    public String getStudentEmail() {
        return this.studentEmail;
    }
}
