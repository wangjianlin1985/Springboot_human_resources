package emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("emp.dao")
public class EmployeeApplication {

	/**
	 * 程序启动入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
}
