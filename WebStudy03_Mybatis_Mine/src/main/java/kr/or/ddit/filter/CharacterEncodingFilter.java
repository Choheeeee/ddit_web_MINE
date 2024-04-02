package kr.or.ddit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharacterEncodingFilter implements Filter {

	private String encoding;
	private boolean forced;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		String forcedParam = fConfig.getInitParameter("forced");
		if(StringUtils.isNoneBlank(forcedParam))
			forced = Boolean.parseBoolean(forcedParam);
		log.info("{}필터 초기화", this.getClass().getName());
	}

	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		if(forced) {
			
		}
		response.setCharacterEncoding(encoding);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
