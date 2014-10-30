package bg.learnit.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import bg.learnit.course.db.model.User;
import bg.learnit.course.util.SecurityUtils;

@Service("usersService")
public class UsersService {

	@Autowired
	private MongoOperations mongoTemplate;
	
	public void saveUser(String username, String password) {
		User user = new User(username, password);
		mongoTemplate.insert(user);
	}
	
	public User findUser(String email, String password) {
		String encryptedPassword = SecurityUtils.encryptToMD5(password);
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email).and("password").is(encryptedPassword));
		User result = mongoTemplate.findOne(query, User.class);
		return result;
	}
	
	public void updateUser(User user) {
		mongoTemplate.save(user);
	}
	
	public List<User> getAllUsers() {
		return mongoTemplate.findAll(User.class);
	}
}
