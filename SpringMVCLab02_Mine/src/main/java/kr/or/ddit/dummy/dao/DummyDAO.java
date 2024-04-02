package kr.or.ddit.dummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CartVO;

@Mapper //이 어노테이션 + xml의 namespace에 등록해줘야 비로소 mapper가 됨. (매퍼마다 xml의 namespace에서 1대1로 등록이 돼야함!!!)
public interface DummyDAO {
	
	public List<CartVO> selectCartList();
}
