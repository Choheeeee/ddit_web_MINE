package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 하나의 MBTI유형에 대한 정보를 캡슐화한 객체 (= VO, JavaBean, DataTransferObject, Model, Info) => 여러 속성이나 필드를 한 묶음으로 이동시킬때 이용.
 * 
 * JavaBean 규약
 * 	1. 값을 저장할 수 있는 프로퍼티 제공
 * 	2. 프로퍼티에 대한 캡슐화 구조가 필요
 * 	3. 캡슐화된 프로퍼티에 대해 인터페이스가 지원돼야함 (getter/setter) : get[set]프로퍼티네임의 첫글자를 대문자로한 프로퍼티명(=capitalized property name)
 * 	4. 프로퍼티의 상태를 확인 할 수 있는 인터페이스를 지원해야함 - toString()
 * 	5. 객체의 상태를 비교할 수 있는 인터페이스가 지원돼야함 - equals(), compareTo() 등
 * 	6. 직렬화 지원 - Serializable
 */
public class MbtiVO implements Serializable{

	
	
	public MbtiVO() {
		super();
	}
	
	

	public MbtiVO(String type, String title, String logicalPath) {
		super();
		this.type = type;
		this.title = title;
		this.logicalPath = logicalPath;
	}



	private String type;
	private String title;
	private String logicalPath; //유형별로 나눈 파일들의 경로를 나타내는 논리경로 = Web resource (웹리소스와 클래스패스리소스의 공통점은 물리주소를 사용할 수 없다.)
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogicalPath() {
		return logicalPath;
	}
	public void setLogicalPath(String logicalPath) {
		this.logicalPath = logicalPath;
	}
	
	@Override
	public String toString() {
		return "MbtiVO [type=" + type + ", title=" + title + ", logicalPath=" + logicalPath + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MbtiVO other = (MbtiVO) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
