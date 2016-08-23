package org.dbyz.java.reflact.dynamic_proxy;

import org.dbyz.java.reflact.dynamic_proxy.interface_.ICustomer;

/**
 * 测试类
 *
 * @ClassName: TestIt
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class TestIt {
	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.setPsychologicalPrice(800);
		// customer.setPsychologicalPrice(1500);
		
		Agent agent  = new Agent(customer);
		agent.rentHouse(customer.getRealPay());
	}
}
