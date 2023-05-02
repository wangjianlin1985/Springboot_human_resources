package emp.dao.model;

import javax.persistence.*;

public class Applicants {
    @Id
    @Column(name = "applicant_id")
    private Integer applicantId;

    @Column(name = "real_name")
    private String realName;

    private String gender;

    private String age;

    private String position;

    private String school;

    private String education;

    private String major;

    @Column(name = "is_selected")
    private Integer isSelected;

    @Column(name = "job_exp")
    private String jobExp;

    /**
     * @return applicant_id
     */
    public Integer getApplicantId() {
        return applicantId;
    }

    /**
     * @param applicantId
     */
    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return is_selected
     */
    public Integer getIsSelected() {
        return isSelected;
    }

    /**
     * @param isSelected
     */
    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @return job_exp
     */
    public String getJobExp() {
        return jobExp;
    }

    /**
     * @param jobExp
     */
    public void setJobExp(String jobExp) {
        this.jobExp = jobExp;
    }
}