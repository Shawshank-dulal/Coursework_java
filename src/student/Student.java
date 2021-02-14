package student;

import courses.Courses;
import main.Current;
import main.MainProgram;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Student extends Current implements StudentActivities {

        private Current currentClass;
        private EnrollManagement enrollManagement;

        public Student() {

            currentClass = this;
            enrollManagement = new EnrollManagement();
        }


    @Override
    public void output() {
            System.out.println("Student Section");
            System.out.println("1:Enroll To a Course");
            System.out.println("2:View Teachers for Modules");
            System.out.println("999:Back To Main Menu");

            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    viewTeachers();
                    break;
                case 999:
                    currentClass = new MainProgram();
                    break;
                default:
                    System.out.println("Invalid Selection!! Please Enter Correct Input");
            }
            currentClass.operate();
        }


        @Override
        public void enrollStudent() {
            EnrollStudent enroll = new EnrollStudent();
            System.out.println("Student Enrollment");
            System.out.println("1: Continue");
            System.out.println("99: Back to Student Section");
            System.out.println("999: Back to Main Menu");
            String selection=new Scanner(System.in).nextLine();
            if(selection.equals("1")){
                System.out.println("Please Enter Student-ID");
                enroll.setStudent_id(new Scanner(System.in).nextLine());
                System.out.println("Please Enter Student Name");
                enroll.setStudent_name(new Scanner(System.in).nextLine());
                System.out.println("Enter Course Id");
                enroll.setCourse_id(new Scanner(System.in).nextLine());
                System.out.println("Enter Level");
                if (new Scanner(System.in).nextLine().equals("6")) {
                    try {
                        List<Courses> courseList = enrollManagement.getALLCourse("6");
                        System.out.println("Optional Module");
                        for (Courses course : courseList) {
                            if (course.getLevel().equals("6")) {
                                System.out.println(course.Optional_module().toString());
                                System.out.println(course.getCourse_id()+":"+course.getCourse_name()+"Select Optional Subject:By Index");
                                enroll.setOptional_module(course.Optional_module()); ///changes here
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    enrollManagement.enrollCourse(enroll);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(selection.equals("99")){
                currentClass=new Student();
                currentClass.operate();

            }
            else if(selection.equals("999")){
                currentClass=new MainProgram();
                currentClass.operate();
            }
            else {
                System.out.println("Please input correct menu selection");
                enrollStudent();
            }

        }

        @Override
        public void viewTeachers() {
            System.out.println("Enter Level");
            try {
                List<Courses> courses = enrollManagement.getALLCourse(new Scanner(System.in).nextLine());
                for (Courses course : courses) {
                    System.out.println(course.getCourse_name()+":"+course.getTeacher());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void operate() {
            currentClass.output();
        }


}
