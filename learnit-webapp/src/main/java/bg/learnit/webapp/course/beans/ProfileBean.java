package bg.learnit.webapp.course.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import bg.learnit.webapp.course.db.model.User;

@RequestScoped
@ManagedBean(name = "profileBean")
public class ProfileBean {

    @ManagedProperty(name = "loginBean", value = "#{loginBean}")
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String updateProfile() throws IOException {
        User loggedInUser = loginBean.getLoggedInUser();
        loginBean.getUsersService().updateUser(loggedInUser);
        return "/pages/home/index";
    }
}
