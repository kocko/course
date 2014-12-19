package bg.learnit.course.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import bg.learnit.course.db.model.Course;

@Service("courseService")
@Scope("prototype")
public class CourseService {

    @Autowired
    private MongoOperations mongoTemplate;

    public void saveCourse(String name, Date start, Date end, Set<String> tags, byte[] picture) {
        Course course = new Course(name, start, end, tags, picture);
        mongoTemplate.insert(course);
    }

    public Course findCourse(String name) {
        Criteria criteria = Criteria.where("name").is(name);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Course.class);
    }

    public List<Course> getAllCourses() {
        return mongoTemplate.findAll(Course.class);
    }
    
}
