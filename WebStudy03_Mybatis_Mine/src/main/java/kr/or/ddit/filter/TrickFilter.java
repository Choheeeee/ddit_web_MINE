package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TrickFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	private static class DummyHttpServletRequestWarpper extends HttpServletRequestWrapper{

		public DummyHttpServletRequestWarpper(HttpServletRequest request) {
			super(request);
		}
		
		@Override
		public String getParameter(String name) {
			if("what".equals(name)) {
				return "P101000001";
			}else {
				
				return super.getParameter(name);
			}
		}
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		chain.doFilter(new DummyHttpServletRequestWarpper(req), response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
