package bg.learnit.course.beans;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import bg.learnit.course.db.model.Course;
import bg.learnit.course.db.model.User;
import bg.learnit.course.service.UsersService;
import bg.learnit.course.util.JSFUtils;

/**
 * 
 * @author kocko
 *
 */

@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean {
    
    private static final Logger logger = Logger.getLogger(LoginBean.class);

    private String email;

    private String password;

    private User loggedInUser;

    private Course selectedCourse;

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

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
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
        if (logger.isDebugEnabled()) {
            logger.debug("Trying to log in with email = " + email);
        }
        loggedInUser = usersService.findUser(email, password);
        if (loggedInUser == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("login.error"), bundle.getString("login.invalidcredentials"));
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        password = null;
        return "/pages/home/index.jsf?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/pages/login.jsf?faces-redirect=true";
    }

    // TODO: Fix this, as it will have to be action, not actionListener (due to
    // the navigation)
    public String enrollToCourse(String courseName) throws IOException {
        LoginBean loginBean = JSFUtils.getBean("loginBean", LoginBean.class);
        User currentUser = loginBean.getLoggedInUser();
        boolean success = currentUser.getCourses().add(courseName);
        if (!success) {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("enroll.alreadyEnrolled"), bundle.getString("enroll.alreadyEnrolled"));
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        loginBean.getUsersService().updateUser(currentUser);
        // FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        return "pages/index.jsf?faces-redirect=true";
    }

}
