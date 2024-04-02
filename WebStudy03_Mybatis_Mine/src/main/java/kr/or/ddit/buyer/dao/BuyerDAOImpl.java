package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDAOImpl implements BuyerDAO {
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public BuyerVO selectBuyer(String buyerId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			return mapperProxy.selectBuyer(buyerId);
			
		}
	}
	@Override
	public int selectTotalRecord(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			return mapperProxy.selectTotalRecord(paging);
			
		}
	}
	@Override
	public List<BuyerVO> selectBuyerList(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			return mapperProxy.selectBuyerList(paging);
			
		}		
	}
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			int rowcnt = mapperProxy.insertBuyer(buyer);
			sqlSession.commit();
			return rowcnt;
		}
	}
	
	@Override
	public int updateBuyer(BuyerVO buyer) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			int rowcnt = mapperProxy.updateBuyer(buyer);
			sqlSession.commit();
			return rowcnt;
		}
	}

}
