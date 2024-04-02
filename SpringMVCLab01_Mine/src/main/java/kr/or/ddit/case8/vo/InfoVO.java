package kr.or.ddit.case8.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class InfoVO implements Serializable {

	@JsonIgnore //마샬링 제외
	private transient String info1; //transient 직렬화 제외
	private String info2;
}
