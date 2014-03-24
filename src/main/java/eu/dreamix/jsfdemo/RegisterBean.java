package eu.dreamix.jsfdemo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * A request-scoped managed bean that holds the logic for registering a new
 * user.
 * 
 * @author kocko
 * 
 */
@RequestScoped
@ManagedBean(name = "registerBean")
public class RegisterBean {

	private String email;

	private String password;

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

	public String register() {
		return "/pages/register/success.xhtml?faces-redirect=true";
	}
}
