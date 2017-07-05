package org.dbyz.java.reflact.dynamic_proxy.demo0; 

import org.junit.Test;

/**
 *  动态代理测试类
 *
 * @ClassName: ProxyTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class TestIt {

	@Test
	public void testProxy() throws Throwable {
		// 实例化目标对象
		Service service = new ServiceImpl();
		
		// 实例化InvocationHandler
		MyInvocationHandler invocationHandler = new MyInvocationHandler(service);
		
		// 根据目标对象生成代理对象
		Service proxy = (Service) invocationHandler.getProxy();
		
		// 调用代理对象的方法
		proxy.service();
		
		System.out.println(service == proxy); // false

		//其他的方法也被代理，如Object上的方法
		//proxy.hashCode(); 
		// 因为是被代理的所以返回值是一样的
		System.out.println(service.hashCode() == proxy.hashCode()); // true
		
		
	}
}
