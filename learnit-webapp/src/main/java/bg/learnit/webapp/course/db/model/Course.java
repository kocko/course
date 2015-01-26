package bg.learnit.webapp.course.db.model;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {

    @Id
    private String name;

    private Date start;

    private Date end;

    private Set<String> tags;

    private byte[] picture;
    
//    private Set<Material> materials;

    public Course(String name, Date start, Date end, Set<String> tags, byte[] picture) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.tags = tags;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
//    public Set<Material> getMaterials() {
//        return materials;
//    }
//    
//    public void addWeekMaterials(String title, byte[] slides, byte[] video) {
//        if (materials == null) {
//            materials = new TreeSet<>(new Comparator<Material>() {
//                @Override
//                public int compare(Material first, Material second) {
//                    return first.getDate().compareTo(second.getDate());
//                }
//            });
//        }
//        materials.add(new Material(title, slides, video, new Date()));
//    }
}
