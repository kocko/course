package bg.learnit.webapp.course.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import bg.learnit.mail.service.EmailService;
import bg.learnit.webapp.course.service.UsersService;
import bg.learnit.webapp.course.util.SecurityUtils;

/**
 * A request-scoped managed bean that holds the logic for registering a new
 * user.
 * 
 */
@RequestScoped
@ManagedBean(name = "registerBean")
public class RegisterBean {

    private String email;

    private String password;

    @ManagedProperty(name = "emailService", value = "#{emailService}")
    private EmailService emailService;

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

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String register() {
        String encryptedPassword = SecurityUtils.encryptToMD5(password);
        if (encryptedPassword != null) {
            usersService.saveUser(email, encryptedPassword);
            emailService.sendMail(email, "Welcome to LearnIT!", "Welcome to LearnIT!");
        } else {
            // TODO
        }
        return "success";
    }
}
