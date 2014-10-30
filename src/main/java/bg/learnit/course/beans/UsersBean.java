package bg.learnit.course.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import bg.learnit.course.db.model.User;
import bg.learnit.course.service.UsersService;

@RequestScoped
@ManagedBean(name = "usersBean")
public class UsersBean {
	
	@ManagedProperty(name = "usersService", value="#{usersService}")
	private UsersService usersService;
	
	private List<User> allUsers;
	
	public UsersService getUsersService() {
		return usersService;
	}
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public List<User> getAllUsers() {
		if (allUsers == null) {
			allUsers = usersService.getAllUsers();
		}
		return allUsers;
	}

}
