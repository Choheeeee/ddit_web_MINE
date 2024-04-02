package kr.or.ddit.payment.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class SalaryDAOImpl implements SalaryDAO {

	private static Map<String, Long> salTB;
	static {
		salTB = new LinkedHashMap<>();
		salTB.put("a001", new Long(10000000));
		salTB.put("b001", new Long(100000000));
	}
	@Override
	public long selectSalary(String empCode) {
		return salTB.get(empCode);
	}
}
