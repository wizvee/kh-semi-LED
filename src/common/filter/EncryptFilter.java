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

/**
 * Servlet Filter implementation class EncryptFilter
 */
@WebFilter(servletNames = { "registerServlet" })
public class EncryptFilter implements Filter {

	public EncryptFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		EncryptPasswordWrapper encPw = new EncryptPasswordWrapper((HttpServletRequest) request);
		chain.doFilter(encPw, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
