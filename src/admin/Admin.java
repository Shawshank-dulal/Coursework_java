package admin;

import courses.CourseManagement;
import courses.Courses;
import main.Current;
import main.MainProgram;
import student.EnrollManagement;
import student.EnrollStudent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Admin extends Current implements AdminActivities{
    private Current currentClass;
    private AdminActivities adminActivity;
    private CourseManagement courseManager;
    private EnrollManagement enrollManager;

    public Admin(){
        adminActivity =this;
        currentClass =this;
        courseManager=new CourseManagement();
        enrollManager =new EnrollManagement();
    }

    @Override
    public void output() {
        int choice = -1;
        System.out.println("Admin Section");
        System.out.println("1:Add Course");
        System.out.println("2:View Courses");
        System.out.println("3:Edit Course");
        System.out.println("4:Generate Report");
        System.out.println("5:Register Student");
        System.out.println("99:Go To Main Page");

        switch (new Scanner(System.in).nextInt()) {
            case 1:
                adminActivity.addCourse();
            case 2:
                adminActivity.viewCourses();
            case 3:
                System.out.println("Please Enter Course ID to Proceed");
                String courseId = new Scanner(System.in).nextLine();
                System.out.println("What Action do You want to perform?");
                System.out.println("1:Cancel Course");
                System.out.println("2:Update Course");
                System.out.println("3:Delete Course");
                System.out.println("99: Back to Menu");
                switch (new Scanner(System.in).nextInt()) {
                    case 1:
                        try {
                            cancelCourse(courseManager.findCourseById(courseId));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    case 2:
                        System.out.println("Enter course id to delete");
                        String cId = new Scanner(System.in).nextLine();
                        try {
                            updateCourse(courseManager.findCourseById(cId));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    case 3:
                        System.out.println("Enter course id to delete");
                        String coId = new Scanner(System.in).nextLine();
                        try {
                            removeCourse(courseManager.findCourseById(coId));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    case 99:
                        currentClass = new MainProgram();
                        currentClass.operate();
                }
            case 4:
                System.out.println("Enter Student Id");
                String studentId = "";
                displayReport(new Scanner(System.in).nextLine());
            case 999:
                currentClass = new MainProgram();
                currentClass.operate();
            default:
                System.out.println("Please Select correct Option");

        }

    }
        @Override
   public void operate() {
        currentClass.output();
    }

    @Override
    public void viewCourses() {
        System.out.println("List of Course");
        try {
            List<Courses> courseList=courseManager.getCourseList();
            System.out.println("Course Id \t Course_name \t Level \t\tPublished_status \t\t teacher \t Optional Module");
            for (Courses c: courseList) {
                System.out.println(c.getCourse_id()+
                        "\t\t"+c.getCourse_name()+
                        "\t\t"+c.getLevel()+
                        "\t\t"+c.getStatus()+
                        "\t\t"+c.getTeacher()+
                        "\t\t"+"\t\t"+
                        c.Optional_module());
                System.out.println("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCourse() {
        System.out.println("Add New Course");

        Courses course=new Courses();

        System.out.println("Enter Course ID");
        course.setCourse_id(new Scanner(System.in).nextLine());

        System.out.println("Enter Course Name");
        course.setCourse_name(new Scanner(System.in).nextLine());

        System.out.println("Enter Level");
        course.setLevel(new Scanner(System.in).nextLine());

        System.out.println("Enter Status:Done");
        course.setStatus(Courses.OPEN);

        System.out.println("Set Teacher,separate with ,");
        course.setTeacher(Arrays.asList(new Scanner(System.in).nextLine().split(",")));

        System.out.println("If this module is optional, type Yes");//changed here
        if(new Scanner(System.in).nextLine()=="Yes"){
            course.setOptional_module("Yes");
        }

        try {
            courseManager.addCourse(course);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void cancelCourse(Courses course) {
        if(course==null){
            System.out.println("No Such Course");
            return;
        }
        System.out.println("Cancel Course:"+course.getCourse_name());
        try {
             courseManager.courseCancel(course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCourse(Courses course) {
        if(course==null){
            System.out.println("No Such Course");
            return;
        }
        System.out.printf("Deleting Course:"+course.getCourse_name());
        try {
            courseManager.removeCOurse(course);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCourse(Courses course) {
        System.out.println("Update For Course:"+course.getCourse_id()+"\n\n");
        System.out.println("1:Update Name-Current="+course.getCourse_name());
        System.out.println("2:Update Level-Current="+course.getLevel());
        System.out.println("3:Update Status-Current="+course.getStatus());
        System.out.println("4:Update Teacher-Current="+course.getTeacher().toString());
        System.out.println("5:Update Optional Modules-Current="+course.Optional_module().toString());

        switch (new Scanner(System.in).nextInt()){
            case 1:
                System.out.println("Enter Course Name");
                course.setCourse_name(new Scanner(System.in).nextLine());
                break;
            case 2:
                System.out.println("Enter Level");
                course.setLevel(new Scanner(System.in).nextLine());
                break;
            case 3:
                System.out.println("Enter Status:Done");
                course.setStatus(Courses.OPEN);
                break;
            case 4:
                System.out.println("Set Teachers,separate with , Set no if none");
                course.setTeacher(Arrays.asList(new Scanner(System.in).nextLine().split(",")));
            case 5:
                System.out.println("If this module is optional, type Yes");//changed here
                if(new Scanner(System.in).nextLine()=="Yes"){
                    course.setOptional_module("Yes");
                }
            default:
                System.out.println("Enter Correct Choice");
        }
        try {
            courseManager.updateCourse(course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayReport(String id) {
        try {
            List<EnrollStudent> enrolls= enrollManager.getAllEnrolls();
            for(EnrollStudent enroll:enrolls){
                if(enroll.getStudent_id().equals(id)){
                    System.out.println("Student ID:" + enroll.getStudent_id());
                    System.out.println("Student Name:"+enroll.getStudent_name());

                    List<Courses> course=courseManager.getCourseList();
                    for(Courses c:course){
                        if(c.getCourse_id().equals(enroll.getCourse_id())){
                            System.out.println( c.getCourse_id()+":"+c.getCourse_name() );
                            System.out.println("Grade:"+enroll.getGrade());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
