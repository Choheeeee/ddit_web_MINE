package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageStr = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchCondition simpleCondition = new SearchCondition(searchType, searchWord);
		req.setAttribute("condition", simpleCondition);
		
		int currentPage = 1; 
		if(StringUtils.isNumeric(pageStr)) {
			currentPage = Integer.parseInt(pageStr);
		}
		PaginationInfo paging = new PaginationInfo(3,3);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		List<MemberVO> memberList = service.retrieveMemberList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		req.setAttribute("memberList", memberList);
		req.setAttribute("pagingHTML", pagingHTML);
		
		String logicalViewName = "member/memberList";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}



















