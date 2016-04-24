package org.dbyz.java.reflact.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.dbyz.java.reflact.dynamic_proxy.interface_.ICustomer;
import org.dbyz.java.reflact.dynamic_proxy.interface_.IRent;

/**
 * 中介
 *
 * @ClassName: Agent
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Agent implements InvocationHandler, IRent {

	/**
	 * 代理的对象
	 */
	private Object proxyLandlord;

	private ICustomer c;

	public Agent(ICustomer c) {
		super();
		this.c = c;
		this.proxyLandlord = landlordChoseStrategy();
	}

	/**
	 * 房东选择策略
	 * 
	 * @Title: landlordChoseStrategy
	 * @param @param c
	 * @return: void
	 * @since V1.0
	 */
	private Object landlordChoseStrategy() {
		if (this.c.getPsychologicalPrice() > 0
				&& this.c.getPsychologicalPrice() < 1500) {
			// chose a Landlord
			return new Landlord1();
		} else {
			// chose a Landlord
			return new Landlord2();
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("@before");

		System.out.println("Method:" + method);

		// 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
		Object result = method.invoke(proxyLandlord, args);

		System.out.println("@after");

		return result;
	}

	@Override
	public void rentHouse(int money) {
		IRent proxyRent = (IRent) Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), this.proxyLandlord.getClass().getInterfaces(),
				this);
		proxyRent.rentHouse(money);
	}

	@Override
	public int getFeePerMonth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
