package emp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import emp.controller.vo.SalaryVo;
import emp.dao.mapper.AttendanceMapper;
import emp.dao.mapper.EmployeeMapper;
import emp.dao.mapper.MettingMapper;
import emp.dao.mapper.SalaryMapper;
import emp.dao.mapper.TrainMapper;
import emp.dao.model.Attendance;
import emp.dao.model.Employee;
import emp.dao.model.Metting;
import emp.dao.model.Salary;
import emp.dao.model.Train;
import emp.utils.DateUtils;
import utils.page.PageView;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private AttendanceMapper attMapper;
	@Autowired
	private MettingMapper mettingMapper;
	@Autowired
	private TrainMapper trainMapper;
	@Autowired
	private SalaryMapper salaryMapper;

	@RequestMapping("login")
	public String loginPage() {
		return "emp/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currEmployee");
		return "emp/login";
	}
	
	/**
	 * 员工登录
	 * @param empno
	 * @param pwd
	 * @param session
	 * @return
	 */
	@RequestMapping("login-form")
	public ModelAndView loginForm(String empno, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:home");
		Employee record = new Employee(empno, pwd);
		record.setRole(1);//角色1表示普通员工，2为主管
		List<Employee> emp = empMapper.select(record );
		if(emp == null || emp.size() == 0) {
			mav.addObject("msg", "用户名或密码错误");
			mav.setViewName("emp/login");
		}else {
			session.setAttribute("currEmployee", emp.get(0));
		}
		return mav;
	}
	
	@RequestMapping("home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mav = new ModelAndView("emp/home");
		Employee emp = (Employee) session.getAttribute("currEmployee");
		String day = DateUtils.getTodayStr();
		Attendance att = attMapper.findEmpAttToday(day , emp.getEmpId());
		if(att != null) {
			if(att.getCheckin() != null) {
				mav.addObject("checkin", DateUtils.parseTime(att.getCheckin()));
			}
			if(att.getCheckout() != null) {
				mav.addObject("checkout", DateUtils.parseTime(att.getCheckout()));
			}
		}
		mav.addObject("today", DateUtils.getTodayStr());
		mav.addObject("now", DateUtils.getCnDateTimeStr());
		return mav;
	}
	
	/**
	 * 进入考勤打卡页面
	 * @return
	 */
	@RequestMapping("toaddatt")
	public String toAddAtt() {
		return "emp/emp-add";
	}
	
	/**
	 * 上班打卡
	 * @param emp
	 * @return
	 */
	@RequestMapping("checkin")
	public String checkin(String today, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("currEmployee");
		Attendance att = attMapper.findEmpAttToday(today , emp.getEmpId());
		boolean insert = false;
		if(att == null) {
			att = new Attendance();
			insert = true;
		}
		att.setAttDay(today);
		att.setEmpId(emp.getEmpId());
		att.setCheckin(new Date());
		String status = "正常";
		if(DateUtils.checkLate(att.getCheckin())) {
			status = "迟到";
		}
		att.setStatus(status);
		if(insert) {
			attMapper.insert(att);
		}else {
			attMapper.updateByPrimaryKeySelective(att);
		}
		return "redirect:attmgr";
	}
	
	/**
	 * 下班打卡
	 * @param emp
	 * @return
	 */
	@RequestMapping("checkout")
	public String checkout(String today, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("currEmployee");
		Attendance att = attMapper.findEmpAttToday(today , emp.getEmpId());
		boolean insert = false;
		if(att == null) {
			att = new Attendance();
			insert = true;
		}
		att.setAttDay(today);
		att.setEmpId(emp.getEmpId());
		att.setCheckout(new Date());
		String status = "";
		if(StringUtils.isNotBlank(att.getStatus())) {
			status += att.getStatus();
		}
		if(DateUtils.checkLeave(att.getCheckout())) {
			if(StringUtils.isNotBlank(status)) {
				status += "|";
			}
			status += "早退";
		}
		att.setStatus(status);
		if(insert) {
			attMapper.insert(att);
		}else {
			attMapper.updateByPrimaryKeySelective(att);
		}
		return "redirect:attmgr";
	}
	
	/**
	 * 考勤信息列表
	 * @param page
	 * @return
	 */
	@RequestMapping("attmgr")
	public ModelAndView attList(PageView<Attendance> page, HttpSession session) {
		ModelAndView mav = new ModelAndView("emp/att-list");
		Employee emp = (Employee) session.getAttribute("currEmployee");
		Attendance att = new Attendance();
		att.setEmpId(emp.getEmpId());
		List<Attendance> list = attMapper.selectByRowBounds(att, new RowBounds(page.getFirstResult(), page.getMaxresult()));
		// 排序 
		Collections.sort(list, new Comparator<Attendance>() {
			@Override
			public int compare(Attendance o1, Attendance o2) {
				return o1.getAttId() - o2.getAttId();
			}
		});
		page.setRecords(list);
		page.setTotalrecord(attMapper.selectCount(new Attendance()));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 会议列表
	 * @param page
	 * @return
	 */
	@RequestMapping("meetingmgr")
	public ModelAndView mettingList(PageView<Metting> page) {
		ModelAndView mav = new ModelAndView("emp/metting-list");
		List<Metting> list = mettingMapper.selectByRowBounds(new Metting(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(mettingMapper.selectCount(new Metting()));
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("tochpwd")
	public ModelAndView tochpwd(HttpSession session) {
		ModelAndView mav = new ModelAndView("emp/pwd");
		Employee emp = (Employee) session.getAttribute("currEmployee");
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping("profile")
	public ModelAndView profile(HttpSession session) {
		ModelAndView mav = new ModelAndView("emp/profile");
		Employee emp = (Employee) session.getAttribute("currEmployee");
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping("updatepwd")
	public ModelAndView updatepwd(HttpSession session, String pwd) {
		ModelAndView mav = new ModelAndView("redirect:attmgr");
		Employee emp = (Employee) session.getAttribute("currEmployee");
		emp.setPwd(pwd);
		empMapper.updateByPrimaryKeySelective(emp);
		return mav;
	}

	@RequestMapping("traindetail")
	public ModelAndView toTrainDetail(Integer aid) {
		ModelAndView mav = new ModelAndView("emp/train-detail");
		Train att = trainMapper.selectByPrimaryKey(aid);
		mav.addObject("train", att);
		return mav;
	}
	
	/**
	 * 培训列表
	 * @param page
	 * @return
	 */
	@RequestMapping("trainmgr")
	public ModelAndView trainList(PageView<Train> page) {
		ModelAndView mav = new ModelAndView("emp/train-list");
		List<Train> list = trainMapper.selectByRowBounds(new Train(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(trainMapper.selectCount(new Train()));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 查看我的工资列表 
	 * @param session
	 * @param page
	 * @param yearMonth
	 * @return
	 */
	@RequestMapping("salarymgr")
	public ModelAndView salaryList(HttpSession session, PageView<SalaryVo> page, String yearMonth) {
		ModelAndView mav = new ModelAndView("emp/salary-list");
		Salary record = new Salary();
		if(!StringUtils.isBlank(yearMonth)) {
			record.setSalaryDate(yearMonth);
		}
		Employee emp = (Employee) session.getAttribute("currEmployee");
		record.setEmpId(emp.getEmpId());
		List<Salary> list = salaryMapper.selectByRowBounds(record, new RowBounds(page.getFirstResult(), page.getMaxresult()));
		List<SalaryVo> vos = new ArrayList<>();
		for (Salary sal : list) {
			SalaryVo vo = new SalaryVo();
			vo.setEmp(empMapper.selectByPrimaryKey(sal.getEmpId()));
			vo.setSalary(sal);
			vos.add(vo);
		}
		page.setRecords(vos);
		page.setTotalrecord(salaryMapper.selectCount(record));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 查看工资单详情
	 * @param session
	 * @param sid
	 * @return
	 */
	@RequestMapping("salarydetail")
	public ModelAndView salaryDetail(HttpSession session, int sid) {
		ModelAndView mav = new ModelAndView("emp/salary-detail");
		Salary salary = salaryMapper.selectByPrimaryKey(sid);
		Employee emp = empMapper.selectByPrimaryKey(salary.getEmpId());
		mav.addObject("salary", salary);
		mav.addObject("emp", emp);
		return mav;
	}
}
