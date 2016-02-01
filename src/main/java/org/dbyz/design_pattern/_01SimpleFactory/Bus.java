package org.dbyz.design_pattern._01SimpleFactory;
/**
 * 公共汽车
 *
 * @ClassName: Bus
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Bus implements Vehicle {
	/**
	 * 包级访问
	 */
	Bus() {
	}

	@Override
	public void run() {
		System.out.println("Bus run....");
	}

}
