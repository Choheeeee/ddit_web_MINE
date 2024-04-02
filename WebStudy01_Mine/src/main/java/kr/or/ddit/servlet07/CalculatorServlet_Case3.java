package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MediaType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculatorVO;

//serialize, deserialize, unmarshalling의 차이

/**
 * 응답을 JSON으로 전송하기 위해, Marshalling과 Serialize가 필요함 Model인 calVO를 Serializable객체로
 * 만들어 줘야함
 *
 */
@WebServlet("/07/case3/calculator.do") // 요청받기
public class CalculatorServlet_Case3 extends HttpServlet {

	@Override // req에 들어있는 데이터들이 nativeData임
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int status = validate(req); 
		String accept = req.getHeader("Accept");

		if (status == HttpServletResponse.SC_OK) {
			String view = null;
			if (accept.contains("json")) {	// 마샬링, 직렬화
				view = "/jsonView.do";
			} else {
				view = "/WEB-INF/views/07/calculateView.jsp";
			}
			req.getRequestDispatcher(view).forward(req, resp);

		} else {
			resp.sendError(status);
		}
	}

	private int validate(HttpServletRequest req) {
		String left = req.getParameter("left");
		String right = req.getParameter("right");
		String operator = req.getParameter("operator");
		int status = HttpServletResponse.SC_OK;

		try {
			double leftOp = Double.parseDouble(left);
			double rightOp = Double.parseDouble(right);
			OperatorType operatorType = OperatorType.valueOf(operator); // 이 값을 넣으면 리턴타입은 OperatorType

			CalculatorVO calVO = new CalculatorVO(leftOp, rightOp, operatorType);// 모델생성
			req.setAttribute("calculator", calVO);
		} catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}

		return status;
	}

}
