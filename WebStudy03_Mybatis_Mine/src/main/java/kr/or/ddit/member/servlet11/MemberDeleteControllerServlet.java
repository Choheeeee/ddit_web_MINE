package kr.or.ddit.member.servlet11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

// POJO(PlainOldJavaObject)

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember =(MemberVO) session.getAttribute("authMember");
		String password = req.getParameter("password");
		String memId = authMember.getMemId();
		MemberVO inputData = new MemberVO(memId, password);
		ServiceResult result = service.removeMember(inputData);
		String logicalViewName = null;
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			message = "비밀 번호 오류";
			logicalViewName = "redirect:/mypage";
			session.setAttribute("message", message);
			break;
		case OK:
			session.invalidate();
			logicalViewName = "redirect:/";
			break;
		default:
			message = "서버 오류, 쫌따 다시 탈퇴하셈.";
			logicalViewName = "redirect:/mypage";
			session.setAttribute("message", message);
			break;
		}
		
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
	}
}























