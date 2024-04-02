package kr.or.ddit.mbti.dao;

import java.util.List;

import kr.or.ddit.vo.MbtiVO;

public interface MbtiDAO {
	
	/**
	 * 전체 MBTI 유형 조회
	 * @return .size()==0일 수 있음. => mybatis selectList()는 쿼리 실행결과가 null이어도 null을 반환하지 않음
	 */
	public List<MbtiVO>selectMbtiList();
	
	/**
	 * 하나의 MBTI 유형 조회
	 * @param type 조회할 MBTI 유형
	 * @return 해당 MBTI가 존재하지 않으면, null반환.
	 */
	public MbtiVO selectMbti(String type);
}
