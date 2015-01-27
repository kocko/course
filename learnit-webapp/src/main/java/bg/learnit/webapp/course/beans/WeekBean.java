package bg.learnit.webapp.course.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import bg.learnit.webapp.course.db.model.Course;
import bg.learnit.webapp.course.service.CourseService;
import bg.learnit.webapp.course.util.JSFUtils;

@RequestScoped
@ManagedBean(name = "weekBean")
public class WeekBean {
    
    @ManagedProperty(name = "courseService", value = "#{courseService}")
    private CourseService courseService;

    private String title;
    
    private Part slides;
    
    private Part video;
    
    public CourseService getCourseService() {
        return courseService;
    }
    
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setSlides(Part slides) {
        this.slides = slides;
    }
    
    public Part getSlides() {
        return slides;
    }
    
    public Part getVideo() {
        return video;
    }
    
    public void setVideo(Part video) {
        this.video = video;
    }
    
    private Course getCurrentCourse() {
        LoginBean loginBean = JSFUtils.getBean("loginBean", LoginBean.class);
        return loginBean.getSelectedCourse();
    }
    
    public String save() throws IOException {
        Course course = getCurrentCourse();
        byte[] slidesAsBytes = null;
        if (slides != null) {
            slidesAsBytes = IOUtils.toByteArray(slides.getInputStream());
        }
        byte[] videoAsBytes = null;
        if (video != null) {
            videoAsBytes = IOUtils.toByteArray(video.getInputStream());
        }

        courseService.saveWeekMaterials(course, title, slidesAsBytes, videoAsBytes);
        return "/pages/home/course/materials";
    }
}
