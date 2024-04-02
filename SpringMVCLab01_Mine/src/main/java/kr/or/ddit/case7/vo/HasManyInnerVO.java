package kr.or.ddit.case7.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class HasManyInnerVO {

	@NotBlank
	private String innerParam3;
	
	@NotBlank
	private String innerParam4;
}
