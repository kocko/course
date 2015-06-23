package bg.learnit.webapp.course.db.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import bg.learnit.annotation.MetaModel;

@Document(collection = "users")
@MetaModel
public class User {

    @Id
    private String email;

    private String password;

    private String fullname;

    private String place;

    private byte[] picture;

    private Set<String> courses;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Set<String> getCourses() {
        if (courses == null) {
            courses = new HashSet<String>();
        }
        return courses;
    }
}