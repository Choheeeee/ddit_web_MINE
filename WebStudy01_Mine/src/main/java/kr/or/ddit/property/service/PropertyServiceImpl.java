package kr.or.ddit.property.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kr.or.ddit.property.dao.InMemoryPropertyDAOImpl;
import kr.or.ddit.property.dao.PropertyDAO;
import kr.or.ddit.property.dao.PropertyDAOImpl;
import kr.or.ddit.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
	
//	private PropertyDAO dao = new InMemoryPropertyDAOImpl("/kr/or/ddit/message/message-common_en.properties");
	private PropertyDAO dao = new PropertyDAOImpl();
	
	@Override
	public boolean createProperty(PropertyVO newProp) {
		int rowcnt = dao.insertProperty(newProp);
		return rowcnt > 0;
	}

	@Override
	public PropertyVO retrieveProperty(String propertyName) {
		
		return dao.selectProperty(propertyName);
	}

	@Override
	public List<PropertyVO> retrieveProperties() {
		return dao.selectProperties();
	}

	@Override
	public Set<String> retrieveKeySet() {
		return retrieveProperties().stream()
					.map(PropertyVO::getPropertyName)
					.collect(Collectors.toSet());
	}

	@Override
	public boolean updateProperty(PropertyVO modifyProp) {
		return dao.updateProperty(modifyProp) > 0;
	}

	@Override //서비스애선 int의 rowcnt보다 boolean으로 내보내는게 "여/부"를 나타내는데 훨씬 명확함
	public boolean deleteProperty(String propertyName) {
		return dao.deleteProperty(propertyName) > 0;
	}

}
