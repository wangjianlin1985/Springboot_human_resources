package emp.controller.vo;

import emp.dao.model.Attendance;
import emp.dao.model.Employee;

public class EmpAttendance {

	private Attendance att;
	private Employee emp;
	
	public Attendance getAtt() {
		return att;
	}
	public void setAtt(Attendance att) {
		this.att = att;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
}
