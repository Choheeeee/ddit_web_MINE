package kr.or.ddit.payment.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository("oracle")
public class EmployeeDAOImpl_Oracle implements EmployeeDAO{
	private static Map<String, StringBuffer> empTB;
	static {
		empTB = new LinkedHashMap<>();
		empTB.put("a001", new StringBuffer("a001,정민지(oracle)"));
		empTB.put("b001", new StringBuffer("b001,변상원(oracle)"));
	}
	@Override
	public StringBuffer selectEmployee(String empCode) {
		return empTB.get(empCode);
	}
	
	
}
