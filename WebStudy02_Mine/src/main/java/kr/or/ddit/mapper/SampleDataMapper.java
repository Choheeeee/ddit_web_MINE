package kr.or.ddit.mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.vo.MemberVO;

public class SampleDataMapper extends SampleTemplateMapper{

	private String snakeToCamel(String snake) {
		return WordUtils.capitalizeFully("A"+snake, '_')	//Amem_Id
				.replaceAll("_", "") //AmemId
				.substring(1);
	}
	
	@Override
	protected <E> E resultSetToModel(ResultSet rs, Class<E> resultType) throws SQLException {
//		MemberVO saved = new MemberVO();
//		saved.setMemId(rs.getString("MEM_ID"));
//		saved.setMemPass(rs.getString("MEM_PASS"));
//		saved.setMemName(rs.getString("MEM_NAME"));
//		saved.setMemMail(rs.getString("MEM_MAIL"));
//		saved.setMemHp(rs.getString("MEM_HP"));
//		return (E) saved;
		try {
			Object result = resultType.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);	//MEM_ID
				String propertyName = snakeToCamel(columnName);	//memId
				try {
					PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
					Method setter = pd.getWriteMethod();
					Class<?> propertyType = pd.getPropertyType();
					Object propertyValue = null;
					if(propertyType.equals(boolean.class)) {
						propertyValue = rs.getBoolean(columnName);
					}else if (propertyType.equals(Integer.class)) {
						propertyValue = rs.getInt(columnName);
					}else if (propertyType.equals(LocalDateTime.class)) {
						Timestamp tmp = rs.getTimestamp(columnName);
						propertyValue = tmp == null? null : tmp.toLocalDateTime();
					}else if (propertyType.equals(LocalDate.class)) {
						Date tmp = rs.getDate(columnName);
						propertyValue = tmp == null? null : tmp.toLocalDate();
					}else if (propertyType.equals(String.class)) {
						propertyValue = rs.getString(columnName);
					}else {
						throw new SQLException("타입 변환이 불가능한 타입임");
					}
					setter.invoke(result, propertyValue);
				} catch (IntrospectionException | IllegalArgumentException | InvocationTargetException e) {
					throw new SQLException(e);
				}
			}
			return (E) result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SQLException(e);
		}
	}

}
