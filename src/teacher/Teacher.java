package teacher;

import courses.Courses;
import main.Current;
import main.MainProgram;
import student.EnrollStudent;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Teacher extends Current implements TeacherActivities{

    private Current activeClass;
    private TeacherManagement instructorController;

    public Teacher(){
        instructorController=new TeacherManagement();
        activeClass=this;
    }

    @Override
    public void output() {
        System.out.println("Instructor Section");
        System.out.println("1:See All Modules");
        System.out.println("2:See Enrolled Students");
        System.out.println("3:Enter Grade");
        System.out.println("99:Go Back");

        switch(new Scanner(System.in).nextInt()){
            case 1:
                displayModule();
                break;
            case 2:
                displayEnroll();
                break;
            case 3:
                addGrade();
                break;
            case 99:
                activeClass=new MainProgram();
                break;
            default:
                System.out.println("Enter correct Choice");
                break;
        }
        activeClass.operate();
    }

    @Override
    public void operate() {
        activeClass.output();
    }

    @Override
    public void addGrade() {
        MarksManagement marksManager=new MarksManagement();
        System.out.println("Enter Student Id");
        String studentId=new Scanner(System.in).nextLine();
        System.out.println("Enter Course Id");
        String courseId=new Scanner(System.in).nextLine();
        System.out.println("Enter Grade");
        String grade=new Scanner(System.in).nextLine();

        try {
            marksManager.setMarks(studentId,courseId,grade);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void displayEnroll() {
        System.out.println("Display Enroll");
        System.out.println("Enter Module Id");

        try {
            List<EnrollStudent> enrolls=instructorController.getEnroll(new Scanner(System.in).nextLine());

            System.out.println("student_id\t\t"+"student_name\t\t"+"course_id\t\t" + "Optional Module\t\t"+"grade\t\t");

            for(EnrollStudent e:enrolls){
                System.out.println(e.getStudent_id()+"\t\t"+e.getStudent_name()+"\t\t"+e.getCourse_id()+"\t\t"+e.getOptional_module()+"\t\t"+e.getGrade());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void displayModule() {

        System.out.println("Display Modules");

        System.out.println("Enter Teacher Name");

        try {
            List<Courses> courseList=instructorController.getCourse(new Scanner(System.in).nextLine());
            for(Courses c:courseList){
                System.out.println("Course ID:"+c.getCourse_id());
                System.out.println("Course Name:"+c.getCourse_name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
