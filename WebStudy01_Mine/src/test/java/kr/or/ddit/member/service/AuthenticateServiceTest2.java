package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class AuthenticateServiceTest2 {

	AuthenticateService service = new AuthenticateServiceImpl();
	MemberVO inputData;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setup");
		inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemId("asdfasdf");
	}

	@Test
	void testAuthenticate() {
		System.out.println("test case1");
		boolean auth = service.authenticate(inputData);
		assertEquals(true, auth);
	}

}
