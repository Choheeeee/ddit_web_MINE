package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "deptNo")
public class DeptVO implements Serializable {

	private String deptNo;
	private String dName;
	private String loc;
	
}
