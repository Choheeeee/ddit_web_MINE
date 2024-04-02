package kr.or.ddit.payment;

import org.springframework.context.annotation.ComponentScan;
import static org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import kr.or.ddit.payment.controller.ReceiveCommandController;

@ComponentScan(value="kr.or.ddit.payment", excludeFilters = {
		@Filter(classes = Controller.class),
		@Filter(classes = MvcView.class)
} )
@Configuration
public class PaymentParentJavaConfiguration {


}
