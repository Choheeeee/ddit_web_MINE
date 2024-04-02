package kr.or.ddit.resource;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component("srcVO")
@Data

public class VariousVO {
	@Value("34")
	private int number;
	@Value("true")
	private boolean boolData;
	@Value("TEXT")
	private String text;
	
	@Inject
	@Named("localDateTime")
	private LocalDateTime now;
	
	
	@Value("file:D:/01.medias/images/손석구3.jpg")
	private Resource fsRes;
	@Value("classpath:kr/or/ddit/db/dbInfo.properties")
	private Resource cpRes;
	@Value("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
	private Resource webRes;
}
