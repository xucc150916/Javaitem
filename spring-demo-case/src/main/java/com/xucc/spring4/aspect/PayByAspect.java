package com.xucc.spring4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用注解的方式bean
 * Alipay切面定义
 */

@Aspect
@Component
public class PayByAspect {

    /**
     * 定义切点
     */
    @Pointcut(value = "execution(* com.xucc.spring4.impl.AliPayService.pay())")
    public void payPoint() {

    }

    /**
     * 1. 安全检查
     */
    @Before(value = "payPoint()")
    public void security() {
        System.out.println("> security check");
    }

    /**
     * 2. 日志
     */
    @Before(value = "payPoint()")
    public void log() {
        System.out.println(">> log");
    }

    /**
     * 3-a. 运行前时间
     */
    @Before(value = "payPoint()")
    public void timeStart() {
        System.out.println(">>> time start");
    }

    /**
     * 3-b. 运行后时间
     */
    @After(value = "payPoint()")
    public void timeEnd() {
        System.out.println(">>> time end");
    }

    /**
     * 程序运行花费时间
     * @param joinPoint
     * @return
     */
    @Around(value = "payPoint()")
    public Object useTime(ProceedingJoinPoint joinPoint) {
        Object ret = null;
        long start = 0;
        long end = 0;
        try {
            start = System.currentTimeMillis();
            ret = joinPoint.proceed();
            end = System.currentTimeMillis();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("use: "+(end-start)+" ms");
        return ret;
    }

    /**
     * 返回值之后
     */
    @AfterReturning(value = "payPoint()")
    public void payReturn() {
        System.out.println(">>>> after return");
    }
}
