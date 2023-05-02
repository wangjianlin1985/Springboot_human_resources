package emp.dao.model;

import javax.persistence.*;

public class Salary {
    @Id
    @Column(name = "salary_id")
    private Integer salaryId;

    @Column(name = "salary_date")
    private String salaryDate;

    @Column(name = "base_amt")
    private Double baseAmt;

    @Column(name = "meal_amt")
    private Double mealAmt;

    @Column(name = "house_amt")
    private Double houseAmt;

    @Column(name = "full_att")
    private Double fullAtt;

    private Double tax;

    private Double subsidy;

    private Double fine;

    @Column(name = "fine_detail")
    private String fineDetail;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "act_amount")
    private Double actAmount;

    /**
     * @return salary_id
     */
    public Integer getSalaryId() {
        return salaryId;
    }

    /**
     * @param salaryId
     */
    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    /**
     * @return salary_date
     */
    public String getSalaryDate() {
        return salaryDate;
    }

    /**
     * @param salaryDate
     */
    public void setSalaryDate(String salaryDate) {
        this.salaryDate = salaryDate;
    }

    /**
     * @return base_amt
     */
    public Double getBaseAmt() {
        return baseAmt;
    }

    /**
     * @param baseAmt
     */
    public void setBaseAmt(Double baseAmt) {
        this.baseAmt = baseAmt;
    }

    /**
     * @return meal_amt
     */
    public Double getMealAmt() {
        return mealAmt;
    }

    /**
     * @param mealAmt
     */
    public void setMealAmt(Double mealAmt) {
        this.mealAmt = mealAmt;
    }

    /**
     * @return house_amt
     */
    public Double getHouseAmt() {
        return houseAmt;
    }

    /**
     * @param houseAmt
     */
    public void setHouseAmt(Double houseAmt) {
        this.houseAmt = houseAmt;
    }

    /**
     * @return full_att
     */
    public Double getFullAtt() {
        return fullAtt;
    }

    /**
     * @param fullAtt
     */
    public void setFullAtt(Double fullAtt) {
        this.fullAtt = fullAtt;
    }

    /**
     * @return tax
     */
    public Double getTax() {
        return tax;
    }

    /**
     * @param tax
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }

    /**
     * @return subsidy
     */
    public Double getSubsidy() {
        return subsidy;
    }

    /**
     * @param subsidy
     */
    public void setSubsidy(Double subsidy) {
        this.subsidy = subsidy;
    }

    /**
     * @return fine
     */
    public Double getFine() {
        return fine;
    }

    /**
     * @param fine
     */
    public void setFine(Double fine) {
        this.fine = fine;
    }

    /**
     * @return fine_detail
     */
    public String getFineDetail() {
        return fineDetail;
    }

    /**
     * @param fineDetail
     */
    public void setFineDetail(String fineDetail) {
        this.fineDetail = fineDetail;
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
     * @return act_amount
     */
    public Double getActAmount() {
        return actAmount;
    }

    /**
     * @param actAmount
     */
    public void setActAmount(Double actAmount) {
        this.actAmount = actAmount;
    }
}