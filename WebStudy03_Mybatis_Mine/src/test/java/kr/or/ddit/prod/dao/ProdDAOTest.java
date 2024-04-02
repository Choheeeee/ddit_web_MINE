package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest {
	ProdDAO dao = new ProdDAOImpl();

	@Test
	void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProd() {
		 ProdVO prod = dao.selectProd("P101000001");
		 assertNotNull(prod);
		 assertNotNull(prod.getLprod());
		 log.info("prod : {}", prod);
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}










