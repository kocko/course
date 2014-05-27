package bg.learnit.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import bg.learnit.course.beans.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private MongoOperations mongoTemplate;
	
	public void saveUser(String username, String password) {
		User user = new User(username, password);
		mongoTemplate.save(user);
	}
}
