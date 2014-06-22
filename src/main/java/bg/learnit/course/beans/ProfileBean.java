package bg.learnit.course.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
@ManagedBean(name = "profileBean")
public class ProfileBean {
	
	@ManagedProperty(name = "loginBean", value="#{loginBean}")
	private LoginBean loginBean;
	
	private Part picture;
	
	public Part getPicture() {
		return picture;
	}
	
	public void setPicture(Part picture) {
		this.picture = picture;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public StreamedContent getPictureStream() {
		FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	User loggedInUser = loginBean.getLoggedInUser();
        	System.out.println(loggedInUser.getEmail());
            return new DefaultStreamedContent(new ByteArrayInputStream(loggedInUser.getPicture()));
        }
	}
	
	public String updateProfile() throws IOException {
		User loggedInUser = loginBean.getLoggedInUser();
		if (picture != null) {
			byte[] pictureAsBytes = IOUtils.toByteArray(picture.getInputStream());
			loggedInUser.setPicture(pictureAsBytes);
		}
		loginBean.getUsersService().updateUser(loggedInUser);
		return "/pages/home/index";
	}
}
