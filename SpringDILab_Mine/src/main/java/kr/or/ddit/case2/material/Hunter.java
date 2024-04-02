package kr.or.ddit.case2.material;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Lazy(true)
public class Hunter extends Thread{
	
	public void init3() {
		log.info("실행되는 시점을 반드시 확인할 것!----init, 주입된 객체 : {}", gun.getClass().getSimpleName());
	}
	
	public void destroy3() {
		
		log.info("실행되는 시점을 반드시 확인할 것!----destroy");
	}

	private Gun gun;
	
	
	public Hunter() {
		log.info("{}객체 기본 생성자로 생성 .", this.getClass().getSimpleName());
	}

//	@Inject
//	@Named("bibitan")
	public Hunter(Gun gun) {
		super();
		this.gun = gun;
		log.info("{}객체 생성 및 생성자로 {} 주입 완료.", this.getClass().getSimpleName(), gun.getClass().getSimpleName());
	}
	
	@Resource(name = "shotGun")
	public void setGun(Gun gun) {
		this.gun = gun;
		log.info("setter로 {} 주입 완료.", this.getClass().getSimpleName(), gun.getClass().getSimpleName());

	}
	
	public void hunting() {
		gun.attack();
		
	}
	
	
}
