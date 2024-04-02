package kr.or.ddit.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

//단위테스트(=모듈테스트, 유닛테스트)를 위한 프레임워크로 JUnit을 빌드패스에 넣음
class Java8TimeAPITest {

	@Test
	void test() {
		System.out.println("test case run");
		//epoch time : 1970.01.01. 00:00:00 이후의 밀리 세컨드로 시간을 연산하는 방식 => 시간에 오차가 발생함
		System.out.printf("java 8버전 이전 : %s\n", new Date());
		System.out.printf("java 8버전 이전 : %s\n", Calendar.getInstance());
		
		//시간에 오차 없이 출력해주는 API
		System.out.printf("java 8버전 이후 : %s\n", LocalDate.now());
		System.out.printf("java 8버전 이후 : %s\n", LocalDateTime.now());
	}

}
