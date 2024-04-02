package kr.or.ddit.property.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PropertyVO;

//@Repository
public class PropertyDAOImpl_template implements PropertyDAO {
	
	private SqlSession sqlSession;
	@Inject
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertProperty(PropertyVO newProp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PropertyVO> selectProperties() {
		return sqlSession.selectList("kr.or.ddit.property.dao.PropertyDAO.selectProperties");
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
