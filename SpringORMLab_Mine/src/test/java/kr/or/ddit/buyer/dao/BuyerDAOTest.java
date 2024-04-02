package kr.or.ddit.buyer.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.common.paging.PaginationInfo;

@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class BuyerDAOTest {
	@Inject
	BuyerDAO dao;
	

	@Test
	void test() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		dao.selectBuyerList(paging);
	}

}








