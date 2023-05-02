package emp.dao.model;

import java.util.Date;
import javax.persistence.*;

public class Metting {
    @Id
    @Column(name = "metting_id")
    private Integer mettingId;

    private String title;

    private String location;

    @Column(name = "metting_time")
    private Date mettingTime;

    private String content;

    /**
     * @return metting_id
     */
    public Integer getMettingId() {
        return mettingId;
    }

    /**
     * @param mettingId
     */
    public void setMettingId(Integer mettingId) {
        this.mettingId = mettingId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return metting_time
     */
    public Date getMettingTime() {
        return mettingTime;
    }

    /**
     * @param mettingTime
     */
    public void setMettingTime(Date mettingTime) {
        this.mettingTime = mettingTime;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}