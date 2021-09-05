package com.offretechnical.test.aop;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.offretechnical.test.models.User;

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
	 * Service 's , return Value
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "execution(* com.offretechnical.test.controllers.UserController.createUser(..))", returning = "result")
	public void afterReturningCreateUser(JoinPoint joinPoint, ResponseEntity<User> result) {
		User user = result.getBody();
		LOGGER.info("{} returned with value {}", joinPoint, user != null ? user.toString() : "Empty . ");
	}

	/**
	 * Service 's , return Value
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "execution(* com.offretechnical.test.controllers.UserController.getAllUsers(..))", returning = "result")
	public void afterReturningGetAll(JoinPoint joinPoint, ResponseEntity<List<User>> result) {
		List<User> users = result.getBody();
		LOGGER.info("{} returned with value {}", joinPoint, users != null ? users.toString() : "Empty . ");
	}

	/**
	 * Service 's , Input Data
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.offretechnical.test.controllers.UserController.createUser(..))")
	public void before(JoinPoint joinPoint) {
		User user = (User) joinPoint.getArgs()[0];
		LOGGER.info("{} Input execution for {}", joinPoint, user != null ? user.toString() : "Empty . ");
	}

	/**
	 * method Time Logger
	 * 
	 * @param proceedingJoinPoint
	 * @throws Throwable
	 */
	@Around("@annotation(com.offretechnical.test.aop.annotation.LogExecutionTime)")
	public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		StopWatch stopWatch = new StopWatch(
				methodSignature.getDeclaringType().getSimpleName() + "->" + methodSignature.getName());
		stopWatch.start(methodSignature.getName());
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		LOGGER.info("StopWatch : {}, running time : {} ns",
				methodSignature.getDeclaringType().getSimpleName() + "->" + methodSignature.getName(),
				stopWatch.getTotalTimeNanos());
		return result;
	}

}