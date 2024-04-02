package kr.or.ddit.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PrintNumberJob {
	
	//매 1초마다 이 값이 1씩 증가해야함
	private int number = 1; //멀티쓰레드 상황에서도, 이 변수에 값이 누적 기록돼야 하는데 그럴려면 PrintNumberJob 이 빈은 싱글턴으로 관리돼야함. Spring은 빈을 디폴트로 싱글턴으로 관리함. 빈등록을 위해 @Component
	
//	@Scheduled(fixedDelay = 1000, initialDelay = 10000) //매 1초마다 지연실행 <-> fixedRate 매 1초마다 반복실행 / 일주일마다라는 표현을 해야하는데, 왼쪽의 표현을 쓰느니 크론표현식을 써서 더 세밀한 설정을 할 수 있다. CronExpression
//	@Scheduled(cron = "0 0 3 * * MON") Spring의 스케쥴러는 사용하지 않고, Quartz를 이용하겠다.
	public void printNumberPerSecond() {
		log.info("number : {}, thread name : {}",number++, Thread.currentThread().getName());
	}
}
