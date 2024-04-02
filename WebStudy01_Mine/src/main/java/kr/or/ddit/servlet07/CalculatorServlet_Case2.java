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


/*
 	serialize, deserialize, unmarshalling의 차이
 */
/**
 * 응답을 JSON으로 전송하기 위해, Marshalling과 Serialize가 필요함
 * Model인 calVO를 Serializable객체로 만들어 줘야함
 *
 */
@WebServlet("/07/case2/calculator.do") //요청받기
public class CalculatorServlet_Case2 extends HttpServlet {
	
	@Override	//req에 들어있는 데이터들이 nativeData임
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int status = validate(req); //파라미터값 검증 (모든 파라미터를 담고 있는 req 넘겨주기. 콜바이레퍼런스, 주소를 참조하고 있는 객체 - 호출자에게 여러타입의 객체를 전달해야 할때)
		
		if(status == HttpServletResponse.SC_OK) { //요청 분석
			//마샬링, 직렬화
			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
			CalculatorVO calVO = (CalculatorVO) req.getAttribute("calculator");
			try(
					PrintWriter writer = resp.getWriter();
			){
				new ObjectMapper().writeValue(writer, calVO);
			}
		}else {
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
			OperatorType operatorType = OperatorType.valueOf(operator); //이 값을 넣으면 리턴타입은 OperatorType
			
			CalculatorVO calVO = new CalculatorVO(leftOp, rightOp, operatorType);//모델생성
			req.setAttribute("calculator", calVO);
		}catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		return status;
	}

}
