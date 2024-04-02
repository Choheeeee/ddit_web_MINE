package kr.or.ddit.property.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.CustomPersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.PropertyVO;


//sql로 DB에 명령 보내기
//Oracle DB타입으로 받아온 데이터를 JAVA타입으로 매핑하는 역할까지 함. => SQL Mapper = Data Mapper = My batis 
public class PropertyDAOImpl implements PropertyDAO {

	@Override
	public int insertProperty(PropertyVO newProp) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TB_PROPERTIES    ");
		sql.append(" (PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION)                     ");
		sql.append(" VALUES    ");
		sql.append(" (?, ?, ?)       ");

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, newProp.getPropertyName()); // ab' OR '1'='1
			pstmt.setString(2, newProp.getPropertyValue());
			pstmt.setString(3, newProp.getDescription());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

	@Override
	public List<PropertyVO> selectProperties() {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION, PROP_DATE, PROP_TIMESTAMP ");
		sql.append(" FROM TB_PROPERTIES ");

		List<PropertyVO> propList = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement();) {
			
			//ResultSet도 이름보면 알다시피 Set자료구조여서 인덱스가 없음. 그래서 포인터를 통해 커서를 이동시키며 반복문을 순회하고 요소에 접근해야함
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				PropertyVO propVO = new PropertyVO();
				propList.add(propVO);
				propVO.setPropertyName(rs.getString("PROPERTY_NAME"));
				propVO.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				propVO.setDescription(rs.getString("DESCRIPTION"));
				Date propDate = rs.getDate("PROP_DATE");
				propVO.setPropDate(propDate.toLocalDate());
				Timestamp propTimeStamp = rs.getTimestamp("PROP_TIMESTAMP");
				propVO.setPropTimestamp(propTimeStamp.toLocalDateTime());
			}
			return propList;
		} catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

	@Override
	public PropertyVO selectProperty(String propertyName) {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION, PROP_DATE, PROP_TIMESTAMP ");
		sql.append(" FROM TB_PROPERTIES ");
		sql.append(String.format(" WHERE PROPERTY_NAME = '%s' ", propertyName));

		PropertyVO propVO = null;
		try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement();) {
			
			//ResultSet도 이름보면 알다시피 Set자료구조여서 인덱스가 없음. 그래서 커서를 이동시키며 반복문을 순회해 요소에 접근해야함
			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				propVO = new PropertyVO();
				propVO.setPropertyName(rs.getString("PROPERTY_NAME"));
				propVO.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				propVO.setDescription(rs.getString("DESCRIPTION"));
				Date propDate = rs.getDate("PROP_DATE");
				propVO.setPropDate(propDate.toLocalDate());
				Timestamp propTimeStamp = rs.getTimestamp("PROP_TIMESTAMP");
				propVO.setPropTimestamp(propTimeStamp.toLocalDateTime());
			}
			return propVO;
		} catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

	@Override
	public int updateProperty(PropertyVO modifyProp) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE TB_PROPERTIES    ");
		sql.append(" SET                     ");
		sql.append(" PROPERTY_VALUE = ? ,    ");
		sql.append(" DESCRIPTION = ?        ");
		sql.append(" WHERE PROPERTY_NAME= ?  ");

		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) { //쿼리 실행이 아닌 쿼리객체를 생성할때 sql을 미리 컴파일함 => 미리 컴파일 된 정적쿼리
			pstmt.setString(1, modifyProp.getPropertyValue());
			pstmt.setString(2, modifyProp.getDescription());
			pstmt.setString(3, modifyProp.getPropertyName()); // ab' OR '1'='1
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

	@Override
	public int deleteProperty(String propertyName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM TB_PROPERTIES ");
		sql.append(" WHERE PROPERTY_NAME = ? ");
		
		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, propertyName);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

}












