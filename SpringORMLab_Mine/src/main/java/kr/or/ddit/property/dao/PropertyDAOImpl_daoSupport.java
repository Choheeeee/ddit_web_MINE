package kr.or.ddit.property.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.or.ddit.vo.PropertyVO;

public class PropertyDAOImpl_daoSupport extends SqlSessionDaoSupport implements PropertyDAO {

	@Override
	public int insertProperty(PropertyVO newProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PropertyVO> selectProperties() {
//		return getSqlSessionTemplate().selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
		PropertyDAO mapperProxy = getSqlSessionTemplate().getMapper(PropertyDAO.class);
		return mapperProxy.selectProperties();
	}

	@Override
	public PropertyVO selectProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProperty(String propertyName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
