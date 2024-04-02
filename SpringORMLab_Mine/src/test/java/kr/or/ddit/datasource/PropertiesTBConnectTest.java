package kr.or.ddit.datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class PropertiesTBConnectTest {

	@Inject
	SqlSession sqlSessionTemplate;
	
	@Test
	void test() {
		List<PropertyVO> list = 
					sqlSessionTemplate.selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
		log.info("조회 결과 : {}", list);
	}

}











