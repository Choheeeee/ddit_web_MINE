package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.principal.MemberVOWrapper;
import kr.or.ddit.vo.MemberVO;

/**
 * 보호자원에 대한 요청을 확인하고 인가 여부를 결정하는 필터
 * 인가 여 : 해당 자원에 대한 접근 권한을 부여받은 사용자이므로, 해당 자원으로 제어권 이동 
 * 인가 부 : 해당 자원에 대한 접근 권한이 없는 사용자이므로, 403 상태코드로 응답 전송
 * 
 */
public class AuthorizationCheckFilter implements Filter{
   
   
   
   /**
    * 필터와 필터는 종속성이 없어 모두 다른 싱글톤 객체로 작동
    * 따라서 AuthenticationCheckFilter중 init에서 properties를 map에 저장한다해도
    * AuthorizationCheckFilter중 init 메소드에서 꺼내버리면 앞에서 다 저장하기도 전에 꺼내버려 오류가 날 가능성이 있음
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	  //현재 요청하는 자원이 보호자원에 대한것인지 
      Map<String, String[]> securedResources = 
            (Map<String, String[]>) request.getServletContext().getAttribute("securedResources");
      
      HttpServletRequest req = (HttpServletRequest) request;
      String uri = (String) req.getRequestURI().substring(req.getContextPath().length());
      
      boolean pass = false;
      boolean secured = securedResources.containsKey(uri);
       if(secured) {
    	   MemberVOWrapper wrapper = (MemberVOWrapper) req.getUserPrincipal();
    	   MemberVO authMember = wrapper.getRealUser();
    	   String userRole = authMember.getMemRole();
    	   String[] authorities = securedResources.get(uri);
    	   int idx = Arrays.binarySearch(authorities, userRole);
    	   if(idx < 0) {
    		   pass = false;
    	   }else {
    		   pass = true;
    	   }
       } else {
          pass = true;
       }
      
      if(pass) {
         chain.doFilter(request, response);
      } else {
    	  HttpServletResponse resp = (HttpServletResponse) response;
    	  resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      }
   }

   @Override
   public void destroy() {
   }

}