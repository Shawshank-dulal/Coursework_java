package teacher;
import courses.CourseManagement;
import courses.Courses;
import student.EnrollManagement;
import student.EnrollStudent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class TeacherManagement {

        private CourseManagement courseController;
        private EnrollManagement enrollController;

        public TeacherManagement(){
            this.courseController = new CourseManagement();
            this.enrollController=new EnrollManagement();
        }

        public List<Courses> getCourse(String teacher) throws IOException {
            List<Courses> courseList=new ArrayList<Courses>();
            for(Courses course:courseController.getCourseList()){
                if(course.getTeacher().equals(teacher)){
                    courseList.add(course);
                }
            }
            return  courseList;
        }


        public List<EnrollStudent> getEnroll(String modelId) throws IOException {
            List<EnrollStudent> enrolls=new ArrayList<EnrollStudent>();

            for(EnrollStudent e:enrollController.getAllEnrolls()){
                if(e.getCourse_id().equals(modelId)){
                    enrolls.add(e);
                }
            }

            return enrolls;
        }



    }

