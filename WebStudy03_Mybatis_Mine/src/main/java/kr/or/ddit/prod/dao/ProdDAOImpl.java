package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	private SqlSessionFactory sqlSessionFactory = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			int rowcnt = mapperProxy.insertProd(prod);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public int selectTotalRecord(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectTotalRecord(paging);
			
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectProdList(paging);
			
		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectProd(prodId);
			
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				int rowcnt = mapperProxy.updateProd(prod);
				sqlSession.commit();
				return rowcnt;
			}
	}

}
