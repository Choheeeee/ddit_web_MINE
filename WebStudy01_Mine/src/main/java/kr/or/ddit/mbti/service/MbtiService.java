package kr.or.ddit.mbti.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kr.or.ddit.vo.MbtiVO;


/**
 * raw data를 가공해서 information을 생성함
 *
 */
public interface MbtiService {
	/**
	 * 전체 MBTI 유형을 Entry(key:Type, value:Title)집합으로 조회
	 * @return
	 */
	public Set<Entry<String, String>> retrieveEntrySet();
	/**
	 * 전체 MBTI 유형을 Map(key:Type, value:Title)으로 collecting함.
	 * @return
	 */
	public Map<String, String> retrieveEntryMap();
	/**
	 * 한건의 MBTI유형 조회
	 * @param type 조회할 MBTI유형
	 * @return 해당 MBTI유형이 없으면, {@link  MbtiNotFoundException} 발생
	 */
	public MbtiVO retrieveMbti(String type);
}
