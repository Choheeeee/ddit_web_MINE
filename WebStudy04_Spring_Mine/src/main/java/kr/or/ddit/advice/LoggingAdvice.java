package kr.or.ddit.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingAdvice {
	public Object loggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable { //탁겟과 어드바이스가 런타임에 위빙되는데, 그 위빙되는 지점을 JoinPoint라 함.
		log.info("================================================>>>");
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		long start = System.currentTimeMillis();
		Object result  = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("==============={}.{} 메소드 실행 : 소요시간 : {}ms===========================>>", targetName, methodName, (end-start));
		return result;
				
		
	}
}
