package main;

import admin.Admin;
import student.Student;
import teacher.Teacher;

import java.util.Scanner;

public class MainProgram extends Current{
    private Current activeClass;

    public MainProgram(){
        activeClass=this;
        activeClass.operate();
    }

    @Override
    public void output() {
        int selection = 1000;
        System.out.println("College Management System ");
        System.out.println("1: Student Section");
        System.out.println("2: Admin Section");
        System.out.println("3: Teacher Section");
        selection = new Scanner(System.in).nextInt();
        switch (selection) {
            case 1:
                activeClass = new Student();
                break;
            case 2:
                activeClass = new Admin();
                break;
            case 3:
                activeClass = new Teacher();
                break;
            default:
                System.out.println("Invalid Selection!! Please select correct option");

        }
        activeClass.output();
    }

    @Override
    public void operate() {
        activeClass.output();
    }
    public static void main(String[] args) {
        MainProgram application=new MainProgram();
    }
}
