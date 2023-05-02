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
import emp.dao.mapper.ApplicantsMapper;
import emp.dao.mapper.AttendanceMapper;
import emp.dao.mapper.EmployeeMapper;
import emp.dao.mapper.MettingMapper;
import emp.dao.mapper.TrainMapper;
import emp.dao.model.Applicants;
import emp.dao.model.Attendance;
import emp.dao.model.Employee;
import emp.dao.model.Metting;
import emp.dao.model.Train;
import emp.utils.DateUtils;
import utils.page.PageView;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private AttendanceMapper attMapper;
	@Autowired
	private MettingMapper mettingMapper;
	@Autowired
	private ApplicantsMapper appMapper;
	@Autowired
	private TrainMapper trainMapper;
	
	@RequestMapping("login")
	public String loginPage() {
		return "manager/login";
	}
	
	@RequestMapping("login-form")
	public ModelAndView loginForm(String username, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:home");
		Employee record = new Employee(username, pwd);
		record.setRole(2);
		List<Employee> emp = empMapper.select(record);
		if(emp == null || emp.size() == 0) {
			mav.addObject("msg", "用户名或密码错误");
			mav.setViewName("manager/login");
		}else {
			session.setAttribute("currManager", emp.get(0));
		}
		return mav;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currManager");
		return "redirect:login";
	}
	
	@RequestMapping("home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mav = new ModelAndView("manager/home");
		Employee emp = (Employee) session.getAttribute("currManager");
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
	 * 上班打卡
	 * @param emp
	 * @return
	 */
	@RequestMapping("checkin")
	public String checkin(String today, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("currManager");
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
		Employee emp = (Employee) session.getAttribute("currManager");
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
	
	/* *********************员工信息管理**************************/
	@RequestMapping("toaddemp")
	public ModelAndView toAddEmp(HttpSession session) {
		ModelAndView mav = new ModelAndView("manager/emp-add");
		Employee mgr = (Employee) session.getAttribute("currManager");//当前登录的主管
		mav.addObject("mgr", mgr);
		return mav;
	}
	
	@RequestMapping("saveEmp")
	public String saveEmp(Employee emp) {
		empMapper.insert(emp);
		return "redirect:empmgr";
	}
	
	@RequestMapping("empmgr")
	public ModelAndView empList(PageView<Employee> page, HttpSession session) {
		ModelAndView mav = new ModelAndView("manager/emp-list");
		Employee emp = new Employee();
		emp.setRole(1);//主管只能管理普通员工
		Employee mgr = (Employee) session.getAttribute("currManager");//当前登录的主管
		emp.setDept(mgr.getDept());//主管只能查询自己部门的员工
		List<Employee> list = empMapper.selectByRowBounds(emp, new RowBounds(page.getFirstResult(), page.getMaxresult())); //empService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(empMapper.selectCount(new Employee()));
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("toupdateemp")
	public ModelAndView toUserModify(Integer uid) {
		ModelAndView mav = new ModelAndView("manager/emp-update");
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
		ModelAndView mav = new ModelAndView("manager/emp-update2");
		Employee emp = empMapper.selectByPrimaryKey(uid);
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping("updateemp2")
	public String updateUser2(Integer empId, Integer discount) {
		Employee emp = empMapper.selectByPrimaryKey(empId);
		emp.setDiscount(discount);
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
	
	/**
	 * 考勤信息列表
	 * @param page
	 * @return
	 */
	@RequestMapping("attmgr")
	public ModelAndView attList(PageView<EmpAttendance> page, HttpSession session) {
		ModelAndView mav = new ModelAndView("manager/att-list");
		Employee mgr = (Employee) session.getAttribute("currManager");//当前登录的主管
		List<Attendance> list = attMapper.findDeptAttendancePage(mgr.getDept(), page.getFirstResult(), page.getMaxresult());
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
		ModelAndView mav = new ModelAndView("manager/attmgr-chart");
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
		return "manager/metting-add";
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
		ModelAndView mav = new ModelAndView("manager/metting-list");
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
		ModelAndView mav = new ModelAndView("manager/metting-update");
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
	
	/* *********************人才招聘管理**************************/
	@RequestMapping("toaddjob")
	public String toAddApplicants() {
		return "manager/job-add";
	}
	
	/**
	 * 保存招聘信息
	 * @param emp
	 * @return
	 */
	@RequestMapping("savejob")
	public String saveApplicants(Applicants att) {
		appMapper.insert(att);
		return "redirect:jobmgr";
	}
	
	/**
	 * 招聘列表
	 * @param page
	 * @return
	 */
	@RequestMapping("jobmgr")
	public ModelAndView jobList(PageView<Applicants> page) {
		ModelAndView mav = new ModelAndView("manager/job-list");
		List<Applicants> list = appMapper.selectByRowBounds(new Applicants(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(appMapper.selectCount(new Applicants()));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 进入更新招聘页面
	 * @param uid
	 * @return
	 */
	@RequestMapping("toupdatejob")
	public ModelAndView toApplicantsModify(Integer aid) {
		ModelAndView mav = new ModelAndView("manager/job-update");
		Applicants att = appMapper.selectByPrimaryKey(aid);
		mav.addObject("job", att);
		return mav;
	}
	
	/**
	 * 招聘详情
	 * @param aid
	 * @return
	 */
	@RequestMapping("jobdetail")
	public ModelAndView toApplicantsDetail(Integer aid) {
		ModelAndView mav = new ModelAndView("manager/job-detail");
		Applicants att = appMapper.selectByPrimaryKey(aid);
		mav.addObject("job", att);
		return mav;
	}
	
	/**
	 * 招聘信息详情
	 * @param app
	 * @return
	 */
	@RequestMapping("updatejob")
	public String updateAtt(Applicants app) {
		appMapper.updateByPrimaryKey(app);
		return "redirect:jobmgr";
	}
	
	/**
	 * 删除招聘信息
	 * @param aid
	 * @return
	 */
	@RequestMapping("deletejob")
	public String deleteApplicants(Integer aid) {
		appMapper.deleteByPrimaryKey(aid);
		return "redirect:jobmgr";
	}
	
	/**
	 * 添加到人才库
	 * @param aid
	 * @return
	 */
	@RequestMapping("addtoselected")
	public String addToSelected(Integer aid) {
		Applicants att = appMapper.selectByPrimaryKey(aid);
		att.setIsSelected(1);
		appMapper.updateByPrimaryKeySelective(att);
		return "redirect:jobmgr";
	}
	
	/**
	 * 从人才库移除
	 * @param aid
	 * @return
	 */
	@RequestMapping("deletejobsel")
	public String deleteJobsel(Integer aid) {
		Applicants att = appMapper.selectByPrimaryKey(aid);
		att.setIsSelected(0);
		appMapper.updateByPrimaryKeySelective(att);
		return "redirect:rckmgr";
	}
	
	/**
	 * 查看人才库列表 
	 * @param page
	 * @return
	 */
	@RequestMapping("rckmgr")
	public ModelAndView selectedJobList(PageView<Applicants> page) {
		ModelAndView mav = new ModelAndView("manager/job-list2");
		Applicants record = new Applicants();
		record.setIsSelected(1);
		List<Applicants> list = appMapper.selectByRowBounds(record, new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(appMapper.selectCount(new Applicants()));
		mav.addObject("page", page);
		return mav;
	}
	/* *********************人才招聘管理END**************************/
	
	/* *********************培训管理**************************/
	@RequestMapping("toaddtrain")
	public String toAddTrain() {
		return "manager/train-add";
	}
	
	/**
	 * 保存培训信息
	 * @param emp
	 * @return
	 */
	@RequestMapping("savetrain")
	public String saveTrain(Train att) {
		trainMapper.insert(att);
		return "redirect:trainmgr";
	}
	
	/**
	 * 培训列表
	 * @param page
	 * @return
	 */
	@RequestMapping("trainmgr")
	public ModelAndView trainList(PageView<Train> page) {
		ModelAndView mav = new ModelAndView("manager/train-list");
		List<Train> list = trainMapper.selectByRowBounds(new Train(), new RowBounds(page.getFirstResult(), page.getMaxresult())); //attService.findUsers(page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(trainMapper.selectCount(new Train()));
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 进入更新培训页面
	 * @param uid
	 * @return
	 */
	@RequestMapping("toupdatetrain")
	public ModelAndView toTrainModify(Integer aid) {
		ModelAndView mav = new ModelAndView("manager/train-update");
		Train att = trainMapper.selectByPrimaryKey(aid);
		mav.addObject("train", att);
		return mav;
	}
	
	@RequestMapping("traindetail")
	public ModelAndView toTrainDetail(Integer aid) {
		ModelAndView mav = new ModelAndView("manager/train-detail");
		Train att = trainMapper.selectByPrimaryKey(aid);
		mav.addObject("train", att);
		return mav;
	}
	
	@RequestMapping("updatetrain")
	public String updateAtt(Train app) {
		trainMapper.updateByPrimaryKeySelective(app);
		return "redirect:trainmgr";
	}
	
	@RequestMapping("deletetrain")
	public String deleteTrain(Integer aid) {
		trainMapper.deleteByPrimaryKey(aid);
		return "redirect:trainmgr";
	}
	
	/* *********************培训管理END**************************/
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
