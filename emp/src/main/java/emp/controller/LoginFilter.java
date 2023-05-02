package emp.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

//@Order规定多个Filter的执行顺序,按照从小到大执行()中的值
@Order(1)
//@WebFilter过滤对应的请求路径
@WebFilter(urlPatterns = {"/*"},filterName = "loginFilter")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if(uri.contains("admin")) {
			chain.doFilter(request, response);
		}else if (uri.contains("manager")){
			String[] blocks = new String[]{"empmgr", "home", "checkin", "checkout"};
			for (String key : blocks) {
				if(uri.contains(key)) {
					Object obj = req.getSession().getAttribute("currManager");
					if(obj == null) {
						HttpServletResponse resp = (HttpServletResponse) response;
						System.out.println("未登录拦截");
						resp.sendRedirect("/emp/manager/login");
						return;
					}
				}
			}
			chain.doFilter(request, response);
		}else {
			String[] blocks = new String[]{"/emp/attmgr", "/emp/checkin", "/emp/checkout", "/emp/home", "/emp/attmgr", "/emp/tochpwd", "/emp/profile"};
			Object obj = req.getSession().getAttribute("currEmployee");
			for (String key : blocks) {
				if(uri.contains(key)) {
					if(obj == null) {
						HttpServletResponse resp = (HttpServletResponse) response;
						System.out.println("未登录拦截");
						resp.sendRedirect("/emp/emp/login");
						return;
					}
				}
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
