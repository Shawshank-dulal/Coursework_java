package student;

public class EnrollStudent {
    private String student_id;
    private String student_name;
    private List<String> course_id;//here
    private String grade;
    private String optional_module;

    public EnrollStudent(){
        this.student_id="";
        this.student_name="";
        this.optional_module="";
        this.grade="";
        this.course_id="";
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOptional_module() {
        if(this.optional_module==true){
            return "Yes";
        }
        else {
            return "No";
        }
    }

    public void setOptional_module(String optional_module) {
        if(optional_module.equals("Yes")){
        }
        else {
            this.optional_module=false;
        }
    }
}
