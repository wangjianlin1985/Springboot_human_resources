package emp.dao.mapper;

import org.apache.ibatis.annotations.Param;

import emp.dao.model.Admin;
import emp.utils.MyMapper;

public interface AdminMapper extends MyMapper<Admin> {
	
	Admin selectLogin(@Param("username") String username, @Param("pwd") String pwd);
}