package kr.or.ddit.payment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.payment.controller.ReceiveCommandController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Playground {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/payment/conf/payment-autodi.xml");
		
		context.registerShutdownHook(); //앱의 종료시점을 예측하기는 어려우므로, 나중에 종류될 수 있도록 종료스레드 등록
		ReceiveCommandController controller = context.getBean(ReceiveCommandController.class);
		String empCode = "b001";
		String payStub = controller.receiveCommand(empCode);
		log.info("급여 명세서 : {}", payStub);
	}
}
