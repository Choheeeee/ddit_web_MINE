package kr.or.ddit.dummy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.vo.CartVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml") //test case는 클래스패스 사용하지 않으므로 file:
class DummyDAOTest {

	
	@Inject
	DummyDAO mapper;
	
	@Test
	void testSelectCartList() {
		log.info("cartList : {}", mapper.selectCartList());
	}

}
