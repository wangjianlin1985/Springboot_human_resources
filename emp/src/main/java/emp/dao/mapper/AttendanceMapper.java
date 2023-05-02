package emp.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import emp.dao.model.Attendance;
import emp.utils.MyMapper;

public interface AttendanceMapper extends MyMapper<Attendance> {
	
	/**
	 * 查询某个员工当天的打卡情况
	 * @param day
	 * @param empId
	 * @return
	 */
	Attendance findEmpAttToday(@Param("day") String day, @Param("empId") Integer empId);
	
	/**
	 * 分页查找所有员工的考勤记录，并根据日期倒序排序
	 * @param start
	 * @param end
	 * @return
	 */
	List<Attendance> findAttendancePage(@Param("start") int start, @Param("end") int end);
	
	List<Attendance> findDeptAttendancePage(@Param("dept") String dept, @Param("start") int start, @Param("end") int end);
	
	/**
	 * 根据日期查找考勤记录
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	List<Attendance> findAttendanceByDate(@Param("startDay") String startDay, @Param("endDay") String endDay);
}