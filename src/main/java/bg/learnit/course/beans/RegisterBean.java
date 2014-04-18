package bg.learnit.course;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import bg.learnit.course.util.JSFUtils;
import bg.learnit.course.util.Mailer;


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

	public void register(ActionEvent event) {
	    Mailer mailer = JSFUtils.getBean("mailer", Mailer.class);
		mailer.sendMail(email, "Welcome to ADF@FMI 2014!", "Success");
	}
}
