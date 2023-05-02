package emp.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import emp.controller.vo.EmpAttendance;
import emp.controller.vo.SalaryVo;
import emp.dao.mapper.AdminMapper;
import emp.dao.mapper.AttendanceMapper;
import emp.dao.mapper.EmployeeMapper;
import emp.dao.mapper.MettingMapper;
import emp.dao.mapper.SalaryMapper;
import emp.dao.mapper.TrainMapper;
import emp.dao.model.Admin;
import emp.dao.model.Attendance;
import emp.dao.model.Employee;
import emp.dao.model.Metting;
import emp.dao.model.Salary;
import emp.dao.model.Train;
import emp.utils.DateUtils;
import utils.page.PageView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private AttendanceMapper attMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private MettingMapper mettingMapper;
	@Autowired
	private SalaryMapper salaryMapper;
	@Autowired
	private TrainMapper trainMapper;
	
	
	@RequestMapping("login")
	public String loginPage() {
		return "admin/login";
	}
	
	@RequestMapping("login-form")
	public ModelAndView loginForm(String username, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:home");
		Admin admin = adminMapper.selectLogin(username, pwd);
		if(admin == null) {
			mav.addObject("msg", "用户名或密码错误");
			mav.setViewName("admin/login");
		}else {
			session.setAttribute("currAdmin", admin);
		}
		return mav;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currAdmin");
		return "redirect:login";
	}
	
	@RequestMapping("home")
	public String home() {
		return "admin/home";
	}
	
	/* *********************员工信息管理**************************/
	@RequestMapping("toaddemp")
	public String toAddEmp() {
		return "admin/emp-add";
	}
	
	@RequestMapping("saveEmp")
	public String saveEmp(Employee emp) {
		empMapper.insert(emp);
		return "redirect:empmgr";
	}
	
	@RequestMapping("empmgr")
	public ModelAndView empList(PageView<Employee> page) {
		ModelAndView mav = new ModelAndView("admin/emp-list");
		List<Employee> list = empMapper.selectByRowBounds(new Employee(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //empService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(empMapper.selectCount(new Employee()));
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("toupdateemp")
	public ModelAndView toUserModify(Integer uid) {
		ModelAndView mav = new ModelAndView("admin/emp-update");
		Employee emp = empMapper.selectByPrimaryKey(uid);
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping("updateemp")
	public String updateUser(Employee emp) {
		empMapper.updateByPrimaryKey(emp);
		return "redirect:empmgr";
	}

	@RequestMapping("toupdatesal")
	public ModelAndView toupdatesal(Integer uid) {
		ModelAndView mav = new ModelAndView("admin/emp-update2");
		Employee emp = empMapper.selectByPrimaryKey(uid);
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping("updateemp2")
	public String updateUser2(Integer empId, Integer discount) {
		Employee emp = empMapper.selectByPrimaryKey(empId);
		emp.setDiscount(discount);
		empMapper.updateByPrimaryKeySelective(emp);
		return "redirect:empmgr";
	}
	
	/**
	 * 注销员工
	 * @param uid
	 * @return
	 */
	@RequestMapping("deleteemp")
	public String deleteUser(Integer uid) {
		Employee emp = empMapper.selectByPrimaryKey(uid);
		emp.setStatus(-1);
		empMapper.updateByPrimaryKeySelective(emp);
		return "redirect:empmgr";
	}
	/* *********************员工信息管理END**************************/
	
	/* *********************考勤信息管理**************************/
	@RequestMapping("toaddatt")
	public String toAddAtt() {
		return "admin/att-add";
	}
	
	/**
	 * 考勤信息列表
	 * @param page
	 * @return
	 */
	@RequestMapping("attmgr")
	public ModelAndView attList(PageView<EmpAttendance> page, HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/att-list");
		List<Attendance> list = attMapper.findAttendancePage(page.getFirstResult(), page.getMaxresult());
		List<EmpAttendance> vos = new ArrayList<>();
		for (Attendance att : list) {
			EmpAttendance vo = new EmpAttendance();
			vo.setEmp(empMapper.selectByPrimaryKey(att.getEmpId()));
			vo.setAtt(att);
			vos.add(vo);
		}
		page.setRecords(vos);
		page.setTotalrecord(attMapper.selectCount(new Attendance()));
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("attmgr-chart")
	public ModelAndView attrChart() {
		ModelAndView mav = new ModelAndView("admin/attmgr-chart");
		List<Attendance> list = attMapper.findAttendanceByDate(DateUtils.getMonthFirstDay(), DateUtils.getMonthLastDay());
		Map<String, List<Attendance>> map = new HashMap<>();
		List<String> keys = new ArrayList<>();
		for (Attendance att : list) {
			if(map.containsKey(att.getAttDay())) {
				List<Attendance> item = map.get(att.getAttDay());
				item.add(att);
			}else {
				keys.add(att.getAttDay());
				List<Attendance> atts = new ArrayList<Attendance>();
				atts.add(att);
				map.put(att.getAttDay(), atts);
			}
		}
		JSONArray dateJson = new JSONArray();
		JSONArray rate = new JSONArray();
		Employee emp = new Employee();
		emp.setStatus(1);
		int count = empMapper.selectCount(emp);
		for(String key:keys) {
			List<Attendance> currList = map.get(key);
			dateJson.add(key);
			int normal = 0, late = 0, early = 0;
			for (Attendance dayAtt : currList) {
				if(dayAtt.getStatus().contains("正常")) {
					normal++;
				}else {
					if(dayAtt.getStatus().contains("迟到"))
						late++;
					if(dayAtt.getStatus().contains("早退"))
						early++;
				}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			String s = df.format((float)normal / count * 100);
			rate.add(s);
		}
		System.out.println(dateJson.toJSONString());
		System.out.println(rate.toJSONString());
		mav.addObject("day", dateJson.toJSONString());
		mav.addObject("rate", rate.toJSONString());
		return mav;
	}
	
	/* *********************考勤信息管理END**************************/
	
	/* *********************会议通知管理**************************/
	@RequestMapping("toaddmetting")
	public String toAddMetting() {
		return "admin/metting-add";
	}
	
	/**
	 * 保存会议信息
	 * @param emp
	 * @return
	 */
	@RequestMapping("saveMetting")
	public String saveMetting(Metting att) {
		mettingMapper.insert(att);
		return "redirect:meetingmgr";
	}
	
	/**
	 * 会议列表
	 * @param page
	 * @return
	 */
	@RequestMapping("meetingmgr")
	public ModelAndView mettingList(PageView<Metting> page) {
		ModelAndView mav = new ModelAndView("admin/metting-list");
		List<Metting> list = mettingMapper.selectByRowBounds(new Metting(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(mettingMapper.selectCount(new Metting()));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 进入更新会议页面
	 * @param uid
	 * @return
	 */
	@RequestMapping("toupdatemetting")
	public ModelAndView toMettingModify(Integer uid) {
		ModelAndView mav = new ModelAndView("admin/metting-update");
		Metting att = mettingMapper.selectByPrimaryKey(uid);
		mav.addObject("meeting", att);
		return mav;
	}
	
	@RequestMapping("updatemetting")
	public String updateAtt(Metting att) {
		mettingMapper.updateByPrimaryKey(att);
		return "redirect:meetingmgr";
	}
	
	@RequestMapping("deletemetting")
	public String deleteMetting(Integer uid) {
		mettingMapper.deleteByPrimaryKey(uid);
		return "redirect:meetingmgr";
	}
	/* *********************会议通知管理END**************************/
	
	/* *********************工资管理**************************/
	@RequestMapping("tosalaryadd")
	public ModelAndView toAddSalary() {
		ModelAndView mav = new ModelAndView("admin/salary-add");
		mav.addObject("emps", empMapper.selectAll());
		return mav;
	}
	
	/**
	 * 发放工资，计算实发工资
	 * @param sal
	 * @return
	 */
	@RequestMapping("saveSalary")
	public String saveSalary(Salary sal) {
		double total = 0;
		total += sal.getBaseAmt();
		total += sal.getFullAtt();
		total += sal.getHouseAmt();
		total += sal.getMealAmt();
		total += sal.getSubsidy();
		total -= sal.getTax();
		total -= sal.getFine();
		sal.setActAmount(total);
		salaryMapper.insertSelective(sal);
		return "redirect:salarymgr";
	}
	
	/**
	 * 工资列表
	 * @param page
	 * @param yearMonth
	 * @return
	 */
	@RequestMapping("salarymgr")
	public ModelAndView salaryList(PageView<SalaryVo> page, String yearMonth) {
		ModelAndView mav = new ModelAndView("admin/salary-list");
		Salary record = new Salary();
		if(!StringUtils.isBlank(yearMonth)) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//			yearMonth = sdf.format(new Date());
			record.setSalaryDate(yearMonth);
		}
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
	 * 工资单详情
	 * @param sid
	 * @return
	 */
	@RequestMapping("salarydetail")
	public ModelAndView salaryDetail(int sid) {
		ModelAndView mav = new ModelAndView("admin/salary-detail");
		Salary salary = salaryMapper.selectByPrimaryKey(sid);
		Employee emp = empMapper.selectByPrimaryKey(salary.getEmpId());
		mav.addObject("salary", salary);
		mav.addObject("emp", emp);
		return mav;
	}
	/* *********************工资管理END**************************/
	
	@RequestMapping("traindetail")
	public ModelAndView toTrainDetail(Integer aid) {
		ModelAndView mav = new ModelAndView("admin/train-detail");
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
		ModelAndView mav = new ModelAndView("admin/train-list");
		List<Train> list = trainMapper.selectByRowBounds(new Train(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(trainMapper.selectCount(new Train()));
		mav.addObject("page", page);
		return mav;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
