package kr.or.ddit.payment.dao;

import org.springframework.stereotype.Component;

public class EmployeeDAOFactory {
	public static EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImpl_Oracle();
	}
}
