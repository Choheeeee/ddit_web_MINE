package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.InMemoryMemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	private MemberDAO dao = new InMemoryMemberDAOImpl();
	
	@Override
	public boolean authenticate(MemberVO inputData) {
		boolean auth = false;
		MemberVO saved = dao.selectMemberForAuth(inputData);
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			auth = savedPass.equals(inputPass);
		}
		return auth;
	}

}
