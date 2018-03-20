/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author moo7md
 * @author Huan Nguyen
 */
public class Course {
    private String classCode;
    private String className;
    private String classDescription;
    private int creditHours;
    private Course[] prerequisiteClassID;
    
    public Course() {
        setClass("", "", "", 0);
    }
    
    public Course (String classCode, String className, String classDescription, int creditHours) {
        setClass(classCode, className, classDescription, creditHours);
    }
    
    public void setClass(String classCode, String className, String classDescription, int creditHours) {
        this.classCode = classCode;
        this.className = className;
        this.classDescription = classDescription;
        this.creditHours = creditHours;
        this.prerequisiteClassID = null;
    }
    
    public String getClassCode() {
        return this.classCode;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getClassDescription() {
        return this.classDescription;
    }
    
    public int getCreditHours() {
        return this.creditHours;
    }
    
    public Course[] getpresequisiteClassID() {
        return this.prerequisiteClassID;
    }
}
