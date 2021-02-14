package admin;

import courses.Courses;

public interface AdminActivities {
    void viewCourses();
    void addCourse();
    void cancelCourse(Courses course);
    void removeCourse(Courses course);
    void updateCourse(Courses course);
    void displayReport(String courseId);
}
