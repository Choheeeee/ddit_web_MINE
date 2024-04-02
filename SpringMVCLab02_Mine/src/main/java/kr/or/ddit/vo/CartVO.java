package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CartVO {

	@NotBlank
	private String cartMember;
	@NotBlank
	private String cartNo;
	@NotBlank
	private String cartProd;
	@NotNull
	private Integer cartQty;
	
	private MultipartFile image;
	
}
