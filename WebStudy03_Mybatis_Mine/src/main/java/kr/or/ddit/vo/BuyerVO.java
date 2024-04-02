package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO {
	private int rnum;
	private String buyerTelext2;
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	private String buyerMail2;
	private String buyerCharger2;
	
	// Has A 관계 - 1:1 -> association
	private Map<String, Object> lprod;
	
	// Has Many 관계 - 1:N -> collection
	private List<ProdVO> prodList; // 제조사와의 거래품목
}




















