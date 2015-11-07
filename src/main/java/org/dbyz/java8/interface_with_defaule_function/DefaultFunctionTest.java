package org.dbyz.java8.interface_with_defaule_function;

import org.junit.Test;

public class DefaultFunctionTest {
	@Test
	public void test1() {
		ICar car = new MyCar();
		car.run();
		car.accelerate();
		car.stop();
	}

	/**
	 * Java8的这个特新是不是有点像 多继承？
	 */
}
