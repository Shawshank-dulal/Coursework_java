package teacher;

import com.opencsv.CSVReader;

public class Marks {
    CSVReader file;
    String filename;
    String student_Id;
    String course_Id;
    String marks;
    Marks(){

    }

    public String getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(String student_Id) {
        this.student_Id = student_Id;
    }

    public String getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(String course_Id) {
        this.course_Id = course_Id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }


}
