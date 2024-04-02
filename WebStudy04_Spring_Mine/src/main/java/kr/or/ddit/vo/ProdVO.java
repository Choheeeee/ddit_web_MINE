package kr.or.ddit.vo;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품관리 Domain Layer 
 *
 */
@Data
@EqualsAndHashCode(of = "prodId")
public class ProdVO {
	private int rnum;
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String prodId;
	@NotBlank
	@Size(max=20)
	private String prodName;
	@NotBlank(groups = InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups = InsertGroup.class)
	private String prodBuyer;
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	@ToString.Exclude
	private String prodDetail;
	
	
	//VO는 commandObject와 Domain역할을 동시에 함. 그래서 구분이 필요함. naming Rule : Img는 String타입이고 db와 소통할때, Image는 client와 소통할때 즉, 클라이언트가 업로드하는 파일을 받을때 사용함(multipart타입)
	@NotBlank(groups = InsertGroup.class)	
	private String prodImg;//DB 메타데이터용
	
	@NotNull(groups = InsertGroup.class) //등록할땐 반드시 상품의 이미지를 등록해야한단 의미.
	private MultipartFile prodImage; //MultipartFile 형태로 클라이언트로부터 전송된 실제 파일 = 파일시스템에 저장될 실제 파일객체
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage == null || prodImage.isEmpty()) {
			return;	//비어있는 파일을 걸러냄
		}
		prodImg = UUID.randomUUID().toString();	//공격자가 의도를 갖고 파일을 업로드할 시, 우리는 공격자가 올린 원본의 이름이 아닌 랜덤의 고유한 이름을 만들어서 DB에 저장하기 위함.
												//이 UUID는 파일명으로 사용되어 파일명의 중복을 피하고, 보안상의 이유로 원본 파일명을 사용하지 않기 위함
												//생성된 UUID는 prodImg 변수에 저장됩니다
		this.prodImage = prodImage;
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private LocalDate prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private Map<String, Object> lprod; // 1:1 관계 바인딩 -> Has A 관계, association 엘리먼트로 바인딩.
		// 1:N 관계 바인딩 -> Has Many 관계, collection 엘리먼트로 바인딩
	private BuyerVO buyer; // PROD Has A BUYER
	
}



