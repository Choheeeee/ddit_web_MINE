package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DummyFilter2 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{}필터 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//request filtering
		HttpServletRequest req = (HttpServletRequest) request;
		log.info("{} 요청 필터링, filter2", req.getRequestURI());
		chain.doFilter(request, response);
		//response filtering
		HttpServletResponse resp = (HttpServletResponse) response;
		log.info("응답 사이즈 확인, filter2 : {}", resp.getContentType());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
