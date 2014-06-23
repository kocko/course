package bg.learnit.course.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import bg.learnit.course.db.model.User;
import bg.learnit.course.service.UsersService;

/**
 * 
 * @author kocko
 *
 */

@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

	private String email;

	private String password;
	
	private User loggedInUser;
	
	@ManagedProperty(name = "usersService", value = "#{usersService}")
	private UsersService usersService;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
	
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	public UsersService getUsersService() {
		return usersService;
	}
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public boolean isLoggedIn() {
		return loggedInUser != null;
	}
	
	public String login() {
		loggedInUser = usersService.findUser(email, password);
		if (loggedInUser == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid login credentials!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		password = null;
		return "/pages/home/index.jsf?faces-redirect=true";
	}
	
	public String logout() {
		loggedInUser = null;
		email = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();;
		return "/pages/login.jsf?faces-redirect=true";
	}
}
