package org.dbyz.java.reflact.dynamic_proxy.demo0; 

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  实现自己的InvocationHandler
 *
 * @ClassName: MyInvocationHandler
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class MyInvocationHandler implements InvocationHandler {
	
	// 目标对象 
	private Object target;
	
	/**
	 * 构造方法
	 * @param target 目标对象 
	 */
	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}


	/**
	 * 执行目标对象的方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		// spring AOP 实现的一种方式
		System.out.println("------------------before------------------");
		
		// 执行目标对象的方法
		Object result = method.invoke(target, args);
		
		System.out.println("------------------after------------------");
		
		return result;
	}

	/**
	 * 获取目标对象的代理对象
	 * @return 代理对象
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}
}

