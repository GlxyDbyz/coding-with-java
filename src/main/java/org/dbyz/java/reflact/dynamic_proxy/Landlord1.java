package org.dbyz.java.reflact.dynamic_proxy;

import org.dbyz.java.reflact.dynamic_proxy.interface_.IRent;

/**
 * 房东1
 *
 * @ClassName: Landlord1
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Landlord1 implements IRent{

	@Override
	public void rentHouse(int money) {
		System.out.println("Rent for " + money/(getFeePerMonth()/30) + " Days");
	}

	@Override
	public int getFeePerMonth() {
		return 950;
	}
	
}
