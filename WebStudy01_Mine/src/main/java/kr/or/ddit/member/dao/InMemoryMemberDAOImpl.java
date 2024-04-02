package kr.or.ddit.member.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public class InMemoryMemberDAOImpl implements MemberDAO {
	private Map<String, MemberVO> memberMap;
	
	
	public InMemoryMemberDAOImpl() {
		memberMap = new LinkedHashMap<>();
		MemberVO a001 = new MemberVO();
		memberMap.put("a001", a001);
		a001.setMemId("a001");
		a001.setMemPass("asdfasdf");
		MemberVO b001 = new MemberVO();
		memberMap.put("b001", b001);
		b001.setMemId("b001");
		b001.setMemPass("1004");
	}


	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		return memberMap.get(inputData.getMemId());
	}

}
