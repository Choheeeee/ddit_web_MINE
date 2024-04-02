package kr.or.ddit.prod.dao;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.others.dao.OthersDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OthersDAOTest extends AbstractRootContextTest{
	@Inject
	OthersDAO dao;

	@Test
	void testSelectLprodList() {
		dao.selectLprodList().stream()
			.forEach(m->log.info("lprod : {}", m));
	}

	@Test
	void testSelectBuyerList() {
		dao.selectBuyerList().stream()
			.forEach(m->log.info("buyer : {}", m));
	}

}
