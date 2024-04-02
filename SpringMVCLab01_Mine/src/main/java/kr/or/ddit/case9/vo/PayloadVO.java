package kr.or.ddit.case9.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PayloadVO implements Serializable {

	private String prop1;
	private int prop2;
	private boolean prop3;
}
