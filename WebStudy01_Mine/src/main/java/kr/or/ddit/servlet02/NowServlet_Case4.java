package kr.or.ddit.servlet02;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//model2 구조 : 모델 컨트롤러와 뷰가 뷴리되는 구조

//클라이언트의 요청이 처리되는 단계
//1.요청을 접수
//2.요청한 데이터 생성
//3.데이터를 컨텐츠로 만들고, 응답으로 전송 (인계 객체(req의 dispatcher)가 필요함)
//1~3번까지의 모든 단계를 1개의 서블릿이나 JSP에 의해 처리한 구조 => Model1

//1~2단계의 처리 객체와 3단계의 처리 객체가 분리된 구조



@WebServlet("/now_case4.do") //Model2의 1단계 = 요청 접수만 하는 서블릿
public class NowServlet_Case4 extends HttpServlet {
     
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Model2의 2단계 = 데이터 생성
		req.setAttribute("today", LocalDate.now());	//key-value : map의 형태
		req.setAttribute("now", LocalDateTime.now());
		
		req.getRequestDispatcher("/01/nowView.c41").forward(req, resp);	//req를 넘겨주는데, rep에 이미 map으로 저장된 데이터가 들어있음.
	}

}
