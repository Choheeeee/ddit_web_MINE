package kr.or.ddit.case2.conf;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.case2.material.Bibitan;
import kr.or.ddit.case2.material.Gun;
import kr.or.ddit.case2.material.Hunter;
import kr.or.ddit.case2.material.ShotGun;

@ComponentScan("kr.or.ddit.case2")
@Configuration
public class Case2JavaConfiguration {

	
	@Scope("prototype")
	@Bean
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
	
}
