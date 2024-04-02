package kr.or.ddit.property.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class PropertyDAOImpl_templateTest {

	@Inject
	@Named("propertyDAO")
	PropertyDAO dao;
	
	@Test
	void testSelectProperties() {
		List<PropertyVO> list = dao.selectProperties();
		log.info("조회 결과 : {}", list);
	}

}











