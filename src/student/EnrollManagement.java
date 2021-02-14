package student;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import courses.CourseManagement;
import courses.Courses;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollManagement extends EnrollStudent {

    CSVReader csvReader;
    String filename;

    CourseManagement courseManager;


    public EnrollManagement(){
        filename="/home/sashank/Downloads/coursework/src/student/students.csv";
        courseManager =new CourseManagement();
    }

    public List<Courses> getALLCourse(String level) throws IOException {
        List<Courses> allCourse= courseManager.getCourseList();
        List<Courses> levelCourse=new ArrayList<Courses>();
        for(Courses course:allCourse){
            if(course.getLevel().equals(level)){
                levelCourse.add(course);
            }
        }
        return levelCourse;
    }

    public void enrollCourse(EnrollStudent enroll) throws IOException {
        setCsvReader();
        List<EnrollStudent> enrolledStudents=getAllEnrolls();
        enrolledStudents.add(enroll);
        saveToCsv(enrolledStudents);
    }

    public List<EnrollStudent> getAllEnrolls() throws IOException {
        setCsvReader();
        List<EnrollStudent> enrolledStudents=new ArrayList<>();
        String[] nextLine= csvReader.readNext();
        while ((nextLine=csvReader.readNext()) != null){
            if(nextLine.length>1) {
                EnrollStudent enroll=new EnrollStudent();
                enroll.setStudent_id(nextLine[0]);
                enroll.setStudent_name(nextLine[1]);
                enroll.setCourse_id(nextLine[3]);
                enroll.setOptional_module(nextLine[4]);
                enroll.setGrade(nextLine[5]);
                enrolledStudents.add(enroll);
            }

        }
        return  enrolledStudents;
    }

    public String getEnrolledStatus(String studentId,String courseId) throws IOException {
            setCsvReader();
            Student stud=new Student();
            List<EnrollStudent> enrolledList=new ArrayList<>();
            enrolledList=getAllEnrolls();
            return "";
    }

    public void saveToCsv(List<EnrollStudent> enrollList){
        setCsvReader();
        try {
            CSVWriter writer=new CSVWriter(new FileWriter(new File(filename)));
            writer.writeNext(new String[]{"student_id", "student_name", "course_id","Optional Module","grade"});//column name
            for (EnrollStudent enroll:enrollList) {
                writer.writeNext(new String[]{
                        enroll.getStudent_id(),
                        enroll.getStudent_name(),
                        enroll.getCourse_id(),
                        enroll.getOptional_module(),
                        enroll.getGrade()
                });

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setCsvReader(){
        try {
            csvReader=new CSVReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
