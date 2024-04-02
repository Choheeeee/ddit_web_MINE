package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Application Lifecycle Listener implementation class asdf
 *
 */
/**
 * 이벤트 처리 단계
 * 1. 이벤트 타겟 결정 : let $btn = $("button");
 * 2. 이벤트 종류 결정 : $btn.on("click"
 * 3. 이벤트 핸들러(리스너) 구현 : let handler = (event) => { event.target...};
 * 4. 이벤트 타겟에 핸들러를 부착 : $btn.on("click", handler);
 *
 */
@Slf4j
public class CustomServletContextHttpSessionListener implements ServletContextListener, HttpSessionListener {

    /**
     * Default constructor. 
     */
    public CustomServletContextHttpSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	int userCount = (int) application.getAttribute("userCount");
    	userCount++;
    	application.setAttribute("userCount", userCount);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     * 서버가 종료될때
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * ServletContextEvent객체가 발생한 event정보를 담고있는 aka event객체
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	int userCount = 0;
    	sce.getServletContext().setAttribute("userCount", userCount);
    	log.info("{} 어플리케이션 시작", sce.getServletContext().getContextPath());
    }
	
}
