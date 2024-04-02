package kr.or.ddit.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.CustomPersistenceException;
import kr.or.ddit.db.ConnectionFactory;

//Persistence Layer인 DAO는 동시에 2개의 역할을 함
//자바객체를 DB객체로 바꿔서 쿼리를 실행하게 해주는 SQL Mapper
//받아온 DB객체를 자바객체로 바꿔꿔주는 DB Mapper
public abstract class SampleTemplateMapper {
	
	
	//template()
	public final <E> E selectOne(String sql, Class<E> resultType, String[] parameters) {
		try(
			Connection conn = getConnection();
			PreparedStatement pstmt = createStatement(conn, sql);
		){
			for(int i=0; i<parameters.length; i++) {
				pstmt.setString(i+1, parameters[i]);
			}
			
			ResultSet rs = pstmt.executeQuery();
			E result = null;
			if(rs.next()) {
				result = resultSetToModel(rs, resultType);
			}
			return result;
		}catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}
	
	
	//template()
	public final <E> List<E> selectList(String sql, Class<E> resultType) { //final = 오버라이딩 불가, 최종메서드, 외부에서 변경 불가
		List<E> list = new ArrayList<>();
		try(
			Connection conn = getConnection();
			PreparedStatement pstmt = createStatement(conn, sql);
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add( resultSetToModel(rs, resultType) );
			}
			return list;
		}catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}
	
	//stepTow() 추상메서드
	protected abstract <E> E resultSetToModel(ResultSet rs, Class<E> resultType) throws SQLException;
	
	
	//stepOne()
	private Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnection();
	}
	
	//stepThree()
	private PreparedStatement createStatement(Connection conn, String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
