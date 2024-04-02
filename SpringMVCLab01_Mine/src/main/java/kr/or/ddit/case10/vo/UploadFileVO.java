package kr.or.ddit.case10.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class UploadFileVO {
	private String uploader;
	
	@NotNull
	private Integer count;
	private MultipartFile[] uploadFile; //배열이니까 복수개의 파일들
	
	//commandObject에 넣기 위해 setter를 이용해 매핑함
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = Arrays.stream(uploadFile)
				.filter(single -> !single.isEmpty()) //비어있지 않은 파일만 모으기위해 필터를하고 MultipartFile타입의 배열로 생성함.
				.toArray(MultipartFile[]::new);
		
//		List<MultipartFile> temp = new ArrayList<>();
//		
//		//비어있는 파일은 걸러내기 위해 setter커스터마이즈
//		for(MultipartFile single : uploadFile) {
//			if(single.isEmpty()) continue;	//비어있으면 그냥 넘기고, 비어있지않은 업로드된것들은 모아놔야함.
//			temp.add(single);
//		}
//		MultipartFile[] tempArray = new MultipartFile[temp.size()];
//		this.uploadFile = temp.toArray(tempArray);
	}
}
