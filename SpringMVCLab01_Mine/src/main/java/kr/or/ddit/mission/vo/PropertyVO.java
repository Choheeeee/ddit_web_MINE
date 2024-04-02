package kr.or.ddit.mission.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

/**
 * .properties 파일이나, Database_properties 뷰를 통해 관리되고 있는, 
 * property 한건의 정보를 캡슐화할 VO(Java Bean)
 *
 */
//@Getter
//@Setter
//@ToString
@EqualsAndHashCode(of = "propertyName")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyVO implements Serializable{
	
	public PropertyVO(String propertyName) {
		super();
		this.propertyName = propertyName;
	}
	
	@NotBlank
	private String propertyName;
	
	@NotBlank
	private String propertyValue;
	private String description;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Exclude
	private LocalDate propDate;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Exclude
	private LocalDateTime propTimestamp;
}
