package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MediaType;
import kr.or.ddit.vo.CalculatorVO;

@WebServlet("/07/case5/calculator.do") // 요청받기
public class CalculatorServlet_Case5 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int status = validate(req); // 파라미터값 검증 (모든 파라미터를 담고 있는 req 넘겨주기. 콜바이레퍼런스, 주소를 참조하고 있는 객체 - 호출자에게 여러타입의 객체를
									// 전달해야 할때)

		if (status == HttpServletResponse.SC_OK) {
			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
			CalculatorVO calVO = (CalculatorVO) req.getAttribute("calculator");
			try (
					PrintWriter writer = resp.getWriter();
			){
				new ObjectMapper().writeValue(writer, calVO); // Java 객체를 JSON 형식의 문자열로 변환하여 출력 스트림에 작성
			}
		}else {
			resp.sendError(status);
		}
	}

	private int validate(HttpServletRequest req) {

		int status = HttpServletResponse.SC_OK;
		try (
				InputStream is = req.getInputStream();// JSON타입의 요청 읽기
		) {
			// 클라이언트단에서 서버단으로 온 요청의 타입이 json이므로, JAVA 객체로 매핑해줄때 쓸 API생성
			ObjectMapper objectMapper = new ObjectMapper();
			CalculatorVO calVO = objectMapper.readValue(is, CalculatorVO.class); // 읽어온 JSON형태의 요청인 is를 CalculatorVO타입으로
																					// 매핑
																					// 매핑한 calVO는 JSON타입은 아니고 JSON형태처럼
																					// key:value의 형태인 문자열데이터임

			req.setAttribute("calculator", calVO);
		} catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}

		return status;
	}

}
