package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.LoginGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원 관리용 Domain Layer
 *
 */
@Data
@EqualsAndHashCode(of = "memId")
@NoArgsConstructor
public class MemberVO implements Serializable { //Serializable JSON으로 마샬링되고 직렬화될때 필요한 키워드
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	private int rnum;
	@NotBlank(groups = {LoginGroup.class, InsertGroup.class, DeleteGroup.class})
	private String memId;
	@NotBlank(groups = {LoginGroup.class, Default.class})
	@Size(min = 4, max = 12, groups = {LoginGroup.class, Default.class})
	private String memPass;
	@NotBlank(groups = InsertGroup.class)
	private String memName;
	@Size(min = 6, max = 6)
	@JsonIgnore
	@ToString.Exclude
	private transient String memRegno1;
	@JsonIgnore
	@ToString.Exclude
	private transient String memRegno2;
	@DateTimeFormat(iso = ISO.DATE_TIME) //클라이언트로부터 요청으로 json데이터를 받을때 동작하고, 응답을 json으로 내보낼때의 마샬링시엔 동작하지 않음. 그래서 아래에서 @JsonFormat도 같이 써야함.
//	@JsonFormat(shape = Shape.STRING)	//이 2개의 어노테이션이 필요해서 좀 귀찮고, 둘중 하나를 까먹을 수도 있음. 그래서 내부에서 작동하고 있는 ObjectMapper에 이 설정을 미리 해주는데, servlet-context.xml에서 TimeStamp를 disable해준다.
	private LocalDateTime memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	
	@TelNumber(regex = "\\d{2,3}\\)\\d{3,4}-\\d{4}")
	private String memHometel;
	
	@TelNumber
	private String memComtel;
	
	@NotBlank
	@TelNumber
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@DateTimeFormat(iso = ISO.DATE) //json데이터를 받을때 동작하고, 응답을 json으로 내보낼땐 동작하지 않음. 그래서 아래에서 @JsonFormat을 써야함.
//	@JsonFormat(shape = Shape.STRING)
	private LocalDate memMemorialday;
	private Integer memMileage;
	private boolean memDelete;
	
	@JsonIgnore	//마샬링 제외
	private transient List<CartVO> cartList; // Has Many (transient = 직렬화 제외)
	// MEMBER(1) : CART[PROD(1)](N)
	
	private String memRole;
	
	//이미지를 주고 받을땐, 2개의 프로퍼티가 필요함.
	private byte[] memImg;	//DB의 BLOB타입을 바인딩 하기위해 byte[]
	private MultipartFile memImage;	//client가 업로드하는 파일을 받기위함.
	
	public void setMemImage(MultipartFile memImage) {
		if(memImage == null || memImage.isEmpty()) {
			return;
		}//여기를 통과했다면, 이미지를 업로드 했다는 의미.
		this.memImage = memImage;
		try {
			this.memImg = memImage.getBytes();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public String getBase64Img() {
		if(memImg == null) { //DB에 저장된 이미지가 없단소리. 인코딩할 필요 x
			return null;
		}else {
			return Base64.getEncoder().encodeToString(memImg); //바이트배열로 돼있는걸 인코딩하면, 문자열이 됨
		}
	}
}







