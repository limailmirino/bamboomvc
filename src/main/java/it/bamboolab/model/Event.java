	package it.bamboolab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="events")
public class Event {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
	
	@Column(name = "title")
    private String title;
	
	@Column(name = "description")
	private String description;
    
	@Column(name = "location")
	private String location;
	
	@Column(name = "start")
    private String start;
	
	@Column(name = "end")
    private String end;
	
	@Column(name = "picture")
    private String picture;

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPictureSmall() {
        return picture;
    }

    public void setPictureSmall(String picture) {
        this.picture = picture;
    }
}
