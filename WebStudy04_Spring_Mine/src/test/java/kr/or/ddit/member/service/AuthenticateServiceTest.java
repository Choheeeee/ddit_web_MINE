package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

class AuthenticateServiceTest extends AbstractRootContextTest{
	
	@Inject
	AuthenticateService service;
	MemberVO inputData;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setup");
		inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("1111");
	}

	@Test
	void testAuthenticate() {
		System.out.println("test case1");
		
		assertDoesNotThrow(()->{
			service.authenticate(inputData);
		});
		
		inputData.setMemPass("asdfasdf");
		assertThrows(AuthenticateException.class, ()->{
			service.authenticate(inputData);
		});
	}
	

}





















