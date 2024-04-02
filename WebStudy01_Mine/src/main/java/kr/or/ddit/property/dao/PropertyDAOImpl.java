package kr.or.ddit.property.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.PropertyVO;

//sql로 DB에 명령 보내기
//Oracle DB타입으로 받아온 데이터를 JAVA타입으로 매핑하는 역할까지 함. => SQL Mapper = Data Mapper = My batis 
public class PropertyDAOImpl implements PropertyDAO {

	@Override
	public int insertProperty(PropertyVO newProp) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TB_PROPERTIES(property_name, property_value, description) ");
		sql.append(" VALUES( ?, ?, ?) ");
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
				pstmt.setString(1, newProp.getPropertyName());
				pstmt.setString(2, newProp.getPropertyValue());
				pstmt.setString(3, newProp.getDescription());
				
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PropertyVO> selectProperties() {
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select property_name, property_value, description ");
		sql.append(" from TB_PROPERTIES ");
		
		List<PropertyVO> propList = new ArrayList<>();
		
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			//ResultSet도 이름보면 알다시피 Set자료구조여서 인덱스가 없음. 그래서 포인터를 통해 커서를 이동시키며 반복문을 순회하고 요소에 접근해야함
			ResultSet rs = stmt.executeQuery(sql.toString()); 
			while(rs.next()){
				PropertyVO propVO = new PropertyVO();
				propList.add(propVO);
				propVO.setPropertyName( rs.getString("property_name") );
				propVO.setPropertyValue( rs.getString("property_value") );
				propVO.setDescription( rs.getString("description") );
			}
			return propList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PropertyVO selectProperty(String propertyName) {
		
		
		StringBuffer sql = new StringBuffer();
		
		PropertyVO propVO = null;
		
		sql.append(" select property_name, property_value, description ");
		sql.append(" from TB_PROPERTIES ");
		sql.append(String.format(" where property_name = '%s' ", propertyName));
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			//ResultSet도 이름보면 알다시피 Set자료구조여서 인덱스가 없음. 그래서 커서를 이동시키며 반복문을 순회해 요소에 접근해야함
			ResultSet rs = stmt.executeQuery(sql.toString()); 
			if(rs.next()){
				propVO = new PropertyVO();
				propVO.setPropertyName( rs.getString("property_name") );
				propVO.setPropertyValue( rs.getString("property_value") );
				propVO.setDescription( rs.getString("description") );
			}
			return propVO;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE TB_PROPERTIES      ");
		sql.append(" SET                       ");
		sql.append(" PROPERTY_VALUE = ? ,"      );
		sql.append(" DESCRIPTION = ? ");
		sql.append(" WHERE PROPERTY_NAME = ? ");
		
		try (Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());) { //쿼리 실행이 아닌 쿼리객체를 생성할때 sql을 미리 컴파일함 => 미리 컴파일 된 정적쿼리
			pstmt.setNString(1, modifyProp.getPropertyValue());
			pstmt.setNString(2, modifyProp.getDescription());
			pstmt.setNString(3, modifyProp.getPropertyName());
			return pstmt.executeUpdate(); //쿼리 실행
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteProperty(String propertyName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM TB_PROPERTIES ");
		sql.append(" WHERE PROPERTY_NAME = ? ");
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, propertyName);	
			return  pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
