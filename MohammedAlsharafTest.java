
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author moo7md
 */
public class MohammedAlsharafTest {

    public static void main(String[] args) {
        /*
        This file is created to test some code in UpdateData().updateSemester(...)
         */
        //Fake Plan...
        Plan plan = new Plan(1, 1, 1, "Plan1", 1, -1, -1, -1);
        //Fake Semesters
        Semester sm = new Semester(1, "Fall 2018", "Bla Bla", 9, 18);
        //This line of code tests the updateSemester(...)
        try {
            new UpdateData().updateSemester(plan.getPlanID(), sm, 'i');
            System.out.println("Test 1: succeeded");
        } catch (RuntimeException e) {
            System.out.println("[Error] something wrong happned");
            System.out.println(e.getLocalizedMessage());
            System.out.println("Test 1: faild");
        }
        /*
        This test is created to test get
         */
        try {
            List<Semester> list = (List<Semester>) new Semesters().getSemesterList();
            list.forEach((smtemp) -> {
                System.out.println(smtemp.getSemesterName());
            });
            System.out.println("Test 2: succeeded");
        } catch (RuntimeException e) {
            System.out.println("[Error] something wrong happned");
            System.out.println(e.getLocalizedMessage());
            System.out.println("Test 2: faild");
        }
    }
}
