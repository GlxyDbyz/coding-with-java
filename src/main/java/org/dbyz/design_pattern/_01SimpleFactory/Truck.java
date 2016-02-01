package org.dbyz.design_pattern._01SimpleFactory;
/**
 * 卡车
 *
 * @ClassName: Truck
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Truck implements Vehicle {
	
	/**
	 * 包级访问
	 */
	Truck() {
	}

	@Override
	public void run() {
		System.out.println("Truck run....");
	}

}
