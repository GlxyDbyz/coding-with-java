package org.dbyz.design_pattern._01SimpleFactory.test;

import org.dbyz.design_pattern._01SimpleFactory.CarFactory1;
import org.dbyz.design_pattern._01SimpleFactory.CarFactory2;
import org.dbyz.design_pattern._01SimpleFactory.Vehicle;

public class MainTest {
	public static void main(String[] args) {
		
		// Vehicle car  = new Bus();
		
		// 简单工厂模式一
		Vehicle vehicle = CarFactory1.getCar(CarFactory1.BUS);
		vehicle.run();
		
		vehicle = CarFactory1.getCar(CarFactory1.TRUNK);
		vehicle.run();
		
		// 简单工厂模式二
		vehicle = CarFactory2.getCar();
		vehicle.run();
	}
}
/**
 * 简单工厂模式列举了2种简单的对象创建方式，工厂模式其目的是简化对象的创建，封装和解耦程序
 */