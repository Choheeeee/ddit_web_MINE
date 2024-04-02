package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.CalculatorVO;

@WebServlet("/07/case4/calculator.do") // 요청받기
public class CalculatorServlet_Case4 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int status = validate(req); // 파라미터값 검증 (모든 파라미터를 담고 있는 req 넘겨주기. 콜바이레퍼런스, 주소를 참조하고 있는 객체 - 호출자에게 여러타입의 객체를 전달해야 할때)

		if (status == HttpServletResponse.SC_OK) { // 요청 분석
			String view = "/WEB-INF/views/07/calculateView.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			resp.sendError(status);
		}
	}

	private int validate(HttpServletRequest req) {

		int status = HttpServletResponse.SC_OK;

		try (InputStream is = req.getInputStream();

		) {
			//역직렬화, 언마샬링
			ObjectMapper objectMapper = new ObjectMapper();
			CalculatorVO calVO = objectMapper.readValue(is, CalculatorVO.class);

			req.setAttribute("calculator", calVO);
		} catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}

		return status;
	}

}
