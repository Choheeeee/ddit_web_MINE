package kr.or.ddit.case7.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

/**
 * command object로 활용할 객체
 *
 */
@Data
public class SampleVO {

	@NotBlank //필수 파라미터
	private String strParam;
	
	@Min(1) //양수만 받으므로 single value로 1
	private int numParam;
	
	
	private char chParam;
	
	@DateTimeFormat(iso = ISO.DATE) //String으로 넘어오는 날짜데이터에 컨버터를 대신해 특정 타입 LocalDate를 바인딩해주기위한 어노테이션
	private LocalDate dateParam;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateTimeParam;
	
	//옵셔널
	private Integer optionParam;
	
	//has a 관계
	@NotNull
	@Valid //HasAInnerVO를 타고 들어가서 해당 속성의 어노테이션까지 검증을 한다는 의미
	private HasAInnerVO inner;
	
	
	//has many 관계
	@NotEmpty //List사이즈는 0보단 커야하고, List의 요소인 HasManyInnerVO는 null데이터를 포함하면 안된다는 의미
	@Valid
	private List<@NotNull HasManyInnerVO> innerList;
}
