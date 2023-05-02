package emp.dao.model;

import java.util.Date;
import javax.persistence.*;

public class Train {
    @Id
    @Column(name = "train_id")
    private Integer trainId;

    private String course;

    private String teacher;

    private String trainer;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private String status;

    private String summary;

    /**
     * @return train_id
     */
    public Integer getTrainId() {
        return trainId;
    }

    /**
     * @param trainId
     */
    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    /**
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return teacher
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * @param teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * @return trainer
     */
    public String getTrainer() {
        return trainer;
    }

    /**
     * @param trainer
     */
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}