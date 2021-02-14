package courses;

import java.util.ArrayList;
import java.util.List;

public class Courses {
    private String module;
    private int semester;
    private String course_id;
    private String course_name;
    private String level;
    private String status;
    private String optional_module;
    private List<String> teacher;

    public static String OPEN="Open";
    public static String CANCEL="Cancel";


    public Courses() {
        this.course_id="";
        this.course_name="";
        this.level="";
        this.status="Open";
        this.optional_module="No";
        this.teacher=new ArrayList();
    }


    public List<String> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String Optional_module() {
        return this.optional_module;
    }
    public void setOptional_module(){

    }

    public void setOptional_module(String optional_module) {
            this.optional_module="Yes";
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
