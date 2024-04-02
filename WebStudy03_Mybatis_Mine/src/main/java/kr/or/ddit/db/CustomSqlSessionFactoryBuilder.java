package kr.or.ddit.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String configFile = "kr/or/ddit/mybatis/Configuration.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(configFile);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}











