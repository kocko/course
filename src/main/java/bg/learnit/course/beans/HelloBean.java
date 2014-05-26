package bg.learnit.course.beans;

import javax.faces.bean.ManagedBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import bg.learnit.course.service.EmailService;

@ManagedBean(name = "helloBean")
public class HelloBean {

	public String getGreeting() {
		return "Hello";
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-config.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

//		User user = new User("kocko", "password");
//
//		// save
//		mongoOperation.save(user);
//		// now user object got the created id.
//		System.out.println("1. user : " + user);
		
		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("kocko"));
	 
		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		System.out.println("2. find - savedUser : " + savedUser);
		
		EmailService service = (EmailService) ctx.getBean("emailService");
		
		service.sendMail("user@example.com", "Test", "test");
	}
}
