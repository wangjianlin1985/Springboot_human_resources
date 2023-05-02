package emp.controller.vo;

import emp.dao.model.Employee;
import emp.dao.model.Salary;

public class SalaryVo {

	private Salary salary;
	private Employee emp;
	
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
}
