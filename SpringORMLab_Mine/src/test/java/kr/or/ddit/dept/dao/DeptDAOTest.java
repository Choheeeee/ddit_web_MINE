package kr.or.ddit.dept.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.DeptVO;
import lombok.extern.slf4j.Slf4j;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")

@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
@Slf4j
class DeptDAOTest {

	@Inject
	DeptDAO dao;
	
	@Test
	void deptListTest() {
		PaginationInfo paging = new PaginationInfo(4,2);
		paging.setCurrentPage(1);
		
		int cnt = dao.selectTotalRecord(paging);
		paging.setTotalRecord(cnt);
		
		List<DeptVO> list = dao.selectDeptList(paging);
		
		
		log.info("결과 : {}", cnt);
		log.info("결과 : {}", list);
	}

}
