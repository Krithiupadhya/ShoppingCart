package com.mindtree.shoppingcart.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MethodAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.mindtree.shoppingcart..*.*(..))")
	public void allMethodsInPackage() {}
	
	
	//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	@Before("execution(* com.mindtree.shoppingcart..*(..))")
	public void beforeMethodCall(JoinPoint joinPoint){
		//Advice
		logger.trace( joinPoint.getSignature().toString()+" begins");
	}
	
	@After("execution(* com.mindtree.shoppingcart..*(..))")
	public void afterMethodCall(JoinPoint joinPoint){
		//Advice
		logger.trace( joinPoint.getSignature().toString()+" ends");
	}
}
