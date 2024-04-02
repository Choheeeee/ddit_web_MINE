package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * .properties 파일이나, Database_properties 뷰를 통해 관리되고 있는,
 * property 한건의 정보를 캡슐화할 VO(=Java Bean) - Java Bean의 5가지 규약을 따라야 VO라고 할 수 있음. 그중 하나가 Serializable
 *
 */
public class PropertyVO implements Serializable {	//데이터 입출력을 위해 직렬화와 역직렬화를 위해 Serializable 구현해놓기
	private String propertyName;
	private String propertyValue;
	private String description;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "PropertyVO [propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", description="
				+ description + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
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
		PropertyVO other = (PropertyVO) obj;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}
	
	
}
