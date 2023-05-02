package emp.dao.model;

import java.util.Date;
import javax.persistence.*;

public class Attendance {
    @Id
    @Column(name = "att_id")
    private Integer attId;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "att_day")
    private String attDay;

    private Date checkin;

    private Date checkout;

    private String status;

    /**
     * @return att_id
     */
    public Integer getAttId() {
        return attId;
    }

    /**
     * @param attId
     */
    public void setAttId(Integer attId) {
        this.attId = attId;
    }

    /**
     * @return emp_id
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * @param empId
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * @return att_day
     */
    public String getAttDay() {
        return attDay;
    }

    /**
     * @param attDay
     */
    public void setAttDay(String attDay) {
        this.attDay = attDay;
    }

    /**
     * @return checkin
     */
    public Date getCheckin() {
        return checkin;
    }

    /**
     * @param checkin
     */
    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    /**
     * @return checkout
     */
    public Date getCheckout() {
        return checkout;
    }

    /**
     * @param checkout
     */
    public void setCheckout(Date checkout) {
        this.checkout = checkout;
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
}