package kr.or.ddit.case6.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class Case6DummyVO {	//commandObject로써의 역할을 함 (Map과 다르게 이렇게 도메인모델 즉, VO를 만들어서 쓰면 이름이 고정되고 타입이 고정돼버림)
	private String[] param1;
	private int paramA;
	private boolean param3;
	@DateTimeFormat(iso = ISO.DATE) //populateUtils의 convertor역할을 이 어노테이션이 그대로 해줌
	private LocalDate date;
}
