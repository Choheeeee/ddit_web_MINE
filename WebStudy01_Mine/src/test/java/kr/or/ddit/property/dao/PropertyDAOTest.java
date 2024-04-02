package kr.or.ddit.property.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PropertyVO;


//TDD : 테스트 주도형 개발 <-> EDD(이벤트 주도형 개발)
class PropertyDAOTest {

	PropertyDAO dao = new PropertyDAOImpl();
			
	@Test
	void testInsertProperty() {
		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("dummyName");
		newProp.setPropertyValue("dummyValue");
		newProp.setDescription("dummyDescription");
		
		int rowCnt = dao.insertProperty(newProp);
		assertEquals(1, rowCnt); 
	}
	

	@Test
	void testSelectProperties() {
		List<PropertyVO> propList = dao.selectProperties();
		
		assert propList != null;
		assertNotNull(propList);
		assertNotEquals(0,propList.size());
	}

	@Test
	void testSelectProperty() {
		for(int i=1; i<=2000; i++) {
			PropertyVO vo = dao.selectProperty("NLS_TIME_TZ_FORMAT");
			assertNotNull(vo);
		}
	}

	@Test
	void testUpdateProperty() {
		PropertyVO modifyProp = new PropertyVO();
		modifyProp.setPropertyName("fail.common.msg");
		modifyProp.setPropertyValue("modified value");
		int rowCnt = dao.updateProperty(modifyProp);
		assertEquals(1, rowCnt);
		
		modifyProp.setPropertyName("asdfasdf");
		modifyProp.setPropertyValue("modified value");
		rowCnt = dao.updateProperty(modifyProp);
		assertEquals(0, rowCnt);
	}

	@Test
	void testDeleteProperty() {
		int rowcnt = dao.deleteProperty("NLS_COMP");
		assertEquals(1, rowcnt);
	}

}
