package org.dbyz.design_pattern._01SimpleFactory;

/**
 * 简单工厂模式1（基于外部传值判断）
 *
 * @ClassName: CarFactory1
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class CarFactory1 {
	public final static int TRUNK = 1;
	public final static int BUS = 2;
	
	/**
	 * 传入特殊的值对应不同的实现类型
	 * 
	 * @Title: getCar
	 * @param @param type
	 * @param @return    
	 * @return: Vehicle
	 * @since V1.0
	 */
	public static Vehicle getCar(int type) {
		Vehicle car = null;
		if (type == TRUNK) {
			car = new Truck();
		}
		if (type == BUS) {
			car = new Bus();
		}
		return car;
	}
}
