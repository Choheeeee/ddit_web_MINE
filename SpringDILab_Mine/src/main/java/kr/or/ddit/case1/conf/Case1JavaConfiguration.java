package kr.or.ddit.case1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.case1.Bar;
import kr.or.ddit.case1.Baz;
import kr.or.ddit.case1.Foo;

@Configuration
@ComponentScan("kr.or.ddit.case1")
@Lazy
public class Case1JavaConfiguration {

//	@Bean
//	@Scope("prototype")
//	public Baz baz() {
//		return new Baz();
//	}
//	
//	@Bean
//	public Bar bar() {
//		return new Bar();
//	}
//	
//	@Bean
//	public Foo foo(Bar bar) {
//		return new Foo(bar);
//	}
}
