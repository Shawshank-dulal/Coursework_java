package courses;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseManagement {
    CSVReader file;
    String filename;

    public CourseManagement(){
        filename="./src/courses/courses.csv";
    }

    public List<Courses> getCourseList() throws IOException {
        filePointer();
        List<Courses> courseList=new ArrayList<>();
        String[] nextLine= file.readNext();
        while ((nextLine= file.readNext()) != null){


            if(nextLine.length>1) {
                Courses course = new Courses();
                course.setCourse_id(nextLine[0]);
                course.setCourse_name(nextLine[1]);
                course.setLevel(nextLine[2]);
                course.setStatus(nextLine[3]);
                course.setOptional_module(nextLine[4]);
                course.setTeacher(Arrays.asList(nextLine[5].split(":").clone()));
                courseList.add(course);
            }

        }
        return  courseList;
    }
    public List<String> findModuleStudents(String module_Id){

        List moduleStudents = new ArrayList();
    ////check here
        return moduleStudents;
    }

    public Courses findCourseById(String courseId) throws IOException {

        List<Courses> courseList=getCourseList();
        for (Courses c:courseList) {
            System.out.println(courseId+":"+c.getCourse_id());
            if(courseId.equals(c.getCourse_id()))
                return c;
        }
        return null;

    }

    public void removeCOurse(Courses course) throws IOException {
        filePointer();
        List<Courses> courseList=getCourseList();

        for (int i=0;i<courseList.size();i++) {
            if(courseList.get(i).getCourse_id().equals(course.getCourse_id())){
                courseList.remove(courseList.get(i));
            }
        }

        saveFile(courseList);

    }
    public void addCourse(Courses course) throws IOException {
        filePointer();
        List<Courses> courseList=getCourseList();
        courseList.add(course);
        saveFile(courseList);
    }

    public void updateCourse(Courses course) throws IOException {
        filePointer();
        List<Courses> courseList=getCourseList();

        for (int i=0;i<courseList.size();i++) {
            if(courseList.get(i).getCourse_id().equals(course.getCourse_id())){
                courseList.set(i,course);
            }
        }

        saveFile(courseList);
    }


    public void courseCancel(Courses course) throws IOException {
        filePointer();
        List<Courses> courseList=getCourseList();
        for (int i=0;i<courseList.size();i++) {
            if(courseList.get(i).getCourse_id().equals(course.getCourse_id())){
                courseList.get(i).setStatus(Courses.CANCEL);
            }
        }
        saveFile(courseList);
    }
    public List<Courses> findOptionalCourses() throws IOException {  //changed
        List<Courses> coursesList=getCourseList();
        List<Courses> optionalList=new ArrayList<>();
        for (Courses course:coursesList ) {
            if (course.Optional_module().equals("Yes")) {
                optionalList.add(course);
            }

        }
        return optionalList;
    }

    public void filePointer(){
        try {
            file =new CSVReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File Not FOund");
        }
    }

    public void saveFile(List<Courses> courseList){
        filePointer();
        try {
            CSVWriter writer=new CSVWriter(new FileWriter(new File(filename)));
            writer.writeNext(new String[]{"ModuleID","courseID", "Course", "Level","Semester", "PublishedStatus", "optional_module","Teachers"});//column name
            for (Courses course:courseList) {   //herehere
                String teachers="";
                for(int i=0;i<course.getTeacher().size();i++){
                    teachers=teachers+course.getTeacher().get(i) +",";
                }
                writer.writeNext(new String[]{
                        course.getModule(),
                        course.getCourse_id(),
                        course.getCourse_name(),
                        course.getLevel(),
                        String.valueOf(course.getSemester()),
                        course.getStatus(),
                        course.Optional_module(),
                        teachers,
                });

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
