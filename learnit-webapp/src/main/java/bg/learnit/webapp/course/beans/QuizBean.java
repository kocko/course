package bg.learnit.webapp.course.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import bg.learnit.webapp.course.db.model.Course;
import bg.learnit.webapp.course.service.CourseService;

@RequestScoped
@ManagedBean(name = "quizBean")
public class QuizBean {

    @ManagedProperty(name = "courseService", value = "#{courseService}")
    private CourseService courseService;

    private String course;

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }
}
