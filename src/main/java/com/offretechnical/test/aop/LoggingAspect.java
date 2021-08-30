package com.offretechnical.test.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.offretechnical.test.models.User;
import com.offretechnical.test.models.dtos.UserDto;

/**
 * Aop Logging input , output and Time Taken of services
 * 
 * @author bn
 *
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	/**
	 * Time Taken by Service
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("execution(* com.offretechnical.test.controllers..*(..)))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		LOGGER.info("Time Taken by {} is {}", joinPoint, timeTaken);
	}

	/**
	 * Service 's , return Value
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@SuppressWarnings("unchecked")
	@AfterReturning(value = "execution(* com.offretechnical.test.controllers.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		User user = ((ResponseEntity<User>) result).getBody();
		LOGGER.info("{} returned with value {}", joinPoint, user != null ? user.toString() : "");
	}

	/**
	 * Service 's , Input Data 
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.offretechnical.test.controllers.*.*(..))")
	public void before(JoinPoint joinPoint) {
		UserDto user = (UserDto) joinPoint.getArgs()[0];
		LOGGER.info("{} Input execution for {}", joinPoint, user != null ? user.toString() : "");
	}
}