package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember =  (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());
		String logicalViewName = "member/memberEdit";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2. 17개의 파라미터를 받고 -> Command Object 로 캡슐화(MemberVO)
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(member, parameterMap);
		
		MemberVO authMember =  (MemberVO) req.getSession().getAttribute("authMember");
		member.setMemId(authMember.getMemId());
		
		// 2-1. 검증 작업 : 통과, 불통
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, UpdateGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
	//		3. 수정 로직 호출
				ServiceResult result = service.modifyMember(member);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case INVALIDPASSWORD:
					logicalViewName = "member/memberEdit";
					message = "인증 실패";
					break;
				case OK:
					logicalViewName = "redirect:/mypage";
					break;
					
				default:
					logicalViewName = "member/memberEdit";
					message = "서버 오류";
					break;
				}
				
				req.setAttribute("message", message);
		}else {
			logicalViewName = "member/memberEdit";
		}
		
		
//		5. 해당 뷰로 이동.
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
		
	}
}
