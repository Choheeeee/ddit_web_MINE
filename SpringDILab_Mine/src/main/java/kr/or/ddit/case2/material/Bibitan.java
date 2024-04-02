package kr.or.ddit.case2.material;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Scope("prototype")
public class Bibitan implements Gun {
	
	public Bibitan() {
		log.info("{}객체 생성됐음.", this.getClass().getSimpleName());
	}
	
	@Override
	public void attack() {
		log.info("{}를 사용해서 -10 데미지를 입혔음.",this.getClass().getSimpleName());
	}
	
}
