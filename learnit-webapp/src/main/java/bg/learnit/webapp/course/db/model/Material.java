package bg.learnit.webapp.course.db.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Material {

    private String title;
    
    private byte[] slides;
    
    private byte[] video;
    
    private Date date;
    
    public Material(String title, byte[] slides, byte[] video, Date date) {
        this.title = title;
        this.slides = slides;
        this.video = video;
        this.date = date;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public byte[] getSlides() {
        return slides;
    }
    
    public void setSlides(byte[] slides) {
        this.slides = slides;
    }
    
    public byte[] getVideo() {
        return video;
    }
    
    public void setVideo(byte[] video) {
        this.video = video;
    }
    
    public Date getDate() {
        return date;
    }
    
}
