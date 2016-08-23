package org.dbyz.java.reflact.dynamic_proxy;

import org.dbyz.java.reflact.dynamic_proxy.interface_.ICustomer;

/**
 * 租客
 *
 * @ClassName: Customer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Customer implements ICustomer{
	private int psychologicalPrice = 0;

	@Override
	public int getPsychologicalPrice() {
		return psychologicalPrice;
	}

	public void setPsychologicalPrice(int psychologicalPrice) {
		this.psychologicalPrice = psychologicalPrice;
	}

	@Override
	public int getRealPay() {
		return 3000;
	}
}
