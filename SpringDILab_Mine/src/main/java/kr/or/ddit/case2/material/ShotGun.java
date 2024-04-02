package kr.or.ddit.case2.material;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("shotGun")
@Scope("prototype")
@DependsOn("bibitan")
public class ShotGun implements Gun {
	
	public ShotGun() {
		log.info("{}객체 생성됐음.", this.getClass().getSimpleName());
	}

	@Override
	public void attack() {
		log.info("{}를 사용해서 -1000 데미지를 입혔음.",this.getClass().getSimpleName());

	}

}
