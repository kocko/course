package bg.learnit.course.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
	
	private boolean loggedIn;
	
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

	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public UsersService getUsersService() {
		return usersService;
	}
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public String login() {
		User user = usersService.findUser(email, password);
		if (user != null) {
			loggedIn = Boolean.TRUE;
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid login credentials!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return "/pages/home/index.jsf?faces-redirect=true";
	}
	
	public String logout() {
		loggedIn = Boolean.FALSE;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().invalidate();
		return "/pages/login.jsf?faces-redirect=true";
	}
}
