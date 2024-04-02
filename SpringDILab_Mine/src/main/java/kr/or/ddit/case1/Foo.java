package kr.or.ddit.case1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Data
public class Foo {
	
	//필수 전략
	private Bar bar;
	public Foo(Bar bar){
		this.bar = bar;
	}
	
	//옵셔널
//	@Autowired
	private Baz baz;
	
	
//	@Resource(name = "baz")
	@Inject
	@Named("baz")
	@Required
	public void setBaz(Baz baz) {
		this.baz = baz;
	}
	
	@PostConstruct //객채생성 하고, 주입까지 완료된 후에 실행될 콜백메서드
	public void asdf() {
		log.info("주입된 결과 => {},{}", bar, baz);
	}
}
