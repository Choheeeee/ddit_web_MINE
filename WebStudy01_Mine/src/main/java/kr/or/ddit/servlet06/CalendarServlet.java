package kr.or.ddit.servlet06;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.FormatStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalendarVO;


/**
 * Model2 + MVC
 * 
 * Servlet (Model + Controller = request 처리자)
 * 		1. 요청(주문)을 받기 - (@WebServlet을 설정하거나, web.xml파일에 servlet-mapping태그 설정)
 * 		2. 해당 요청(주문)을 분석하고 바른 요청(주문)인지 검증 - (request line, header, body)
 * 		3. 바른 요청(주문)이면 Model(데이터, 요리)생성 => 컨트롤러는 Information을 담당
 * 		4. Model을 전달 및 공유  - setAttribute(name, value)
 * 		5. view layer 선택 
 * 		6. 제어의 이동(controller -> view) - forward()이용
 * JSP (View = response 처리자)
 * 		1. Model 확보 - getAttribute(name)
 * 		2. model을 content로 가공
 */
@WebServlet("/case3/calendar.do")
public class CalendarServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/06/case3/calendarForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Locale locale = Optional.ofNullable(req.getParameter("locale")).filter(lp -> !lp.trim().isEmpty())
				.map(lp -> Locale.forLanguageTag(lp)).orElse(Locale.getDefault());

		ZoneId zone = ZoneId.systemDefault(); // 여기서 말하는 system은 JVM

		ZonedDateTime current = ZonedDateTime.now(zone);
		FormatStyle fullStyle = FormatStyle.FULL;

		int targetYear = Optional.ofNullable(req.getParameter("year")).map(yp -> new Integer(yp))
				.orElse(YearMonth.from(current).getYear());
		YearMonth thisMonth = Optional.ofNullable(req.getParameter("month"))
				.map(mp -> YearMonth.of(targetYear, Integer.parseInt(mp))).orElse(YearMonth.from(current));

		YearMonth beforeMonth = thisMonth.minusMonths(1);
		YearMonth nextMonth = thisMonth.plusMonths(1);

		WeekFields weekFields = WeekFields.of(locale);
		DayOfWeek firstDOW = weekFields.getFirstDayOfWeek();
		LocalDate firstDate = thisMonth.atDay(1); // 11월1일
		int firstDateDOW = firstDate.get(weekFields.dayOfWeek()); // 요일을 정수로 표현
		int offset = firstDateDOW - 1; // 걸쳐져있는 전달의 날짜가 몇개인지
		
		CalendarVO calVO = new CalendarVO();
		calVO.setBeforeMonth(beforeMonth);
		calVO.setCurrent(current);
		calVO.setFirstDate(firstDate);
		calVO.setFirstDOW(firstDOW);
		calVO.setFormatStyle(fullStyle);
		calVO.setLocale(locale);
		calVO.setNextMonth(nextMonth);
		calVO.setOffset(offset);
		calVO.setThisMonth(thisMonth);
		
		req.setAttribute("calendar", calVO);
		String view = "/WEB-INF/views/06/case3/calendar.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
		
	}

}
