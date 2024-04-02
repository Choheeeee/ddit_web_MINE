package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="attNo")
public class AttatchVO {
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private Integer attFilesize;
	private String attFancysize;
	private Integer attDownload;
}
