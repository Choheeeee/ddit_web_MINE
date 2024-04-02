package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리용 Persistence Layer
 *
 */
public interface MemberDAO {

	/**
	 * 인증 처리를 위해 회원 정보 조회
	 * @param inputData 사용자가 입력한 정보를 가진 VO
	 * @return persistene한 형태로 저장되어 있던 정보를 가진 VO, 없으면 null반환
	 */
	public MemberVO selectMemberForAuth(MemberVO  inputData);
}
