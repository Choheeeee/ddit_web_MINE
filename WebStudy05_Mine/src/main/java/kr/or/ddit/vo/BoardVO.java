package kr.or.ddit.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "boNo")
public class BoardVO {
	private int rnum;
	private int boNo;
	private String boTitle;
	private String boWriter;
	private String boIp;
	private String boMail;
	private String boPass;
	private String boContent;
	private LocalDateTime boDate;
	private int boHit;
	private Integer boParent;
	
	private int level;
	private List<AttatchVO> attatchList;
}
