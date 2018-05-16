package com.klm.travelapi.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;

public class MetricsAspects {
	
	 private static Long minResponseTime;
	    private static Long maxResponseTime;
	    private static Long totalResponseTime;

	    @Autowired
	    private GaugeService gaugeService;

	    @Around("execution(* com.afkl.cases.df.controller.*.*(..))")
	    public Object collect(ProceedingJoinPoint pjp) throws Throwable {
	        long start = System.currentTimeMillis();
	        Object output = pjp.proceed();
	        long elapsedTime = System.currentTimeMillis() - start;

	        minResponseTime = minResponseTime == null ? elapsedTime : Math.min(minResponseTime, elapsedTime);
	        maxResponseTime = maxResponseTime == null ? elapsedTime : Math.max(maxResponseTime, elapsedTime);
	        totalResponseTime = totalResponseTime == null ? elapsedTime : totalResponseTime + elapsedTime;

	        gaugeService.submit("gauge.response.min", minResponseTime);
	        gaugeService.submit("gauge.response.max", maxResponseTime);
	        gaugeService.submit("gauge.response.total", totalResponseTime);

	        return output;
	    }

}
