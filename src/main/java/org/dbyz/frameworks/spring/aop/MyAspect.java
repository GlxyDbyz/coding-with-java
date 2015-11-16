package org.dbyz.frameworks.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * spring AOP 简单例子
 *
 * @ClassName: MyAspect
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a> 
 * @version: V1.0
 */
@Aspect
@Component
public class MyAspect {

    /**
       execution() 写法
	               以* （有空格）：开始代表无论什么方法的申明和返回值
	               包..代表不限制包深度  
       	 *.helo(..):..代表不限制参数   *则是普通的正则表达式也可以 Ta*  *as*
       eg:
           @Pointcut("execution(* com.test.aop.test.Ta*.helo(..))")
           @Pointcut("execution(* com.test.aop.test.*as*.helo(..))")
           @Pointcut("execution(* com.test.aop.test.*.*(..))")

           @Pointcut("execution(* com..Ta*.helo(..))")
           @Pointcut("execution(* com..*as*.helo(..))")
           @Pointcut("execution(* com..*.*(..))")
     */
    
	/**
	 * 定义切人点
	 * 
	 * @Title: testPoint
	 * @param
	 * @return: void
	 * @since V1.0
	 */
    @Pointcut("execution(* org..*as*.helo(..))")
    void testPoint() {}

    /**
     * 在切入点之前执行
     * 
     * @Title: beforeTestPoint
     * @param @throws Throwable    
     * @return: void
     * @since V1.0
     */
    @Before("testPoint()")
    public void beforeTestPoint() throws Throwable {
        System.out.println("@Before");
    }

    /**
     * 切入点之后的动态代理
     * 
     * @Title: aroundTestPoint
     * @param @param jp
     * @param @throws Throwable    
     * @return: void
     * @since V1.0
     */
    @Around("testPoint()")
    public void aroundTestPoint(ProceedingJoinPoint jp) throws Throwable {
        jp.proceed();//运行 没有此段代码 则不会运行指定的方法
        System.out.println("@Around");
    }

    /**
     * 切入点之后运行
     * 
     * @Title: afterTestPoint
     * @param @throws Throwable    
     * @return: void
     * @since V1.0
     */
    @After("testPoint()")
    public void afterTestPoint() throws Throwable {
        System.out.println("@After");
    }

   /**
    * 返回之后(最后面执行)
    * 
    * @Title: afterReturningTestPoint
    * @param @param jp
    * @param @param logit
    * @param @param retVal
    * @param @throws Throwable    
    * @return: void
    * @since V1.0
    */
    @AfterReturning(returning="retVal", pointcut="within (org.dbyz..*) && @annotation(logit)")
    public void afterReturningTestPoint(JoinPoint jp, Log logit,Object retVal) throws Throwable {
        for(Object obj :jp.getArgs()){
            if(obj.getClass()==String.class){
                System.out.println("i got you arg String ：" +obj);
            }
        }
        System.out.println("i got you returning String ：" +(Long)retVal);
        System.out.println("i got you annotation args ：" +logit.level());
        System.out.println("@AfterReturning");
    }
    //...........
    
}