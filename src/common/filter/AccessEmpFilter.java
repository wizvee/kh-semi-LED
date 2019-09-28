package common.filter;

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
import javax.servlet.http.HttpSession;

import com.semi.emp.model.vo.Employee;
import com.semi.userinfo.model.vo.UserInfo;

@WebFilter("/views/emp/*")
public class AccessEmpFilter implements Filter {

	public AccessEmpFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		userInfo.getParameters("E");
		session.setAttribute("userInfo", userInfo);

		System.out.println("emp:UserInfo갱신");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
