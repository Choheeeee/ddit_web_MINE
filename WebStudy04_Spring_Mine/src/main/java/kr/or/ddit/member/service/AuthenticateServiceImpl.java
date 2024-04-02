package kr.or.ddit.member.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
	private final MemberDAO dao;
	
	@Override
	public MemberVO authenticate(MemberVO inputData) {
		boolean auth = false;
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			auth = savedPass.equals(inputPass);
		}
		if(!auth) throw new AuthenticateException(inputData.getMemId());
		return saved;
	}

}












