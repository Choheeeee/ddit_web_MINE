package kr.or.ddit.payment.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.payment.dao.EmployeeDAOImpl_Oracle;
import kr.or.ddit.payment.dao.SalaryDAOImpl;
import kr.or.ddit.payment.service.PaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.payment.view.PayMonthlyView;
import lombok.NoArgsConstructor;


@Controller
@NoArgsConstructor
public class ReceiveCommandController {
	private PaymentService service = new PaymentServiceImpl();
	private PayMonthlyView view = new PayMonthlyView();
	
	
	@Inject
	public ReceiveCommandController(PaymentService service, PayMonthlyView view) {
		super();
		this.service = service;
		this.view = view;
	}



	public String receiveCommand(String empCode) {
		StringBuffer model = service.payMonthly(empCode);
		return view.generateContent(model);
	}
}
