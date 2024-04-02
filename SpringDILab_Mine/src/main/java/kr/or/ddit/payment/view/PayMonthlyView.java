package kr.or.ddit.payment.view;

import org.springframework.stereotype.Component;

import kr.or.ddit.payment.MvcView;

@MvcView
public class PayMonthlyView {

	public String generateContent(StringBuffer model){
		StringBuffer content = new StringBuffer();
		content.append("<table>");
		content.append(model);
		content.append("</table>");
		return content.toString();
	}
}
