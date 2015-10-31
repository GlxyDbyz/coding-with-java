package org.dbyz.java8.functional_interface;

import org.junit.Test;

public class TestMyLambda {
	@Test
	public void test1() {
		//定义一个Lambda表达式
		MyLambda<String, Integer> myLambda;
		//实现它（唯一的抽象方法）
		myLambda = (String param) -> {
			return Integer.valueOf(param);
		};
		System.out.println(myLambda.getValue("12") == 12);// true

		// 或者这样咯
		myLambda = (param) -> Integer.valueOf(param);
		System.out.println(myLambda.getValue("12") == 12);// true

		// 这样也可以
		myLambda = (param) -> param.length();
		System.out.println(myLambda.getValue("A123456789"));// 10
		
		//（每个Lambda表达式可以对应很多的实现，每个实现都指向一个 @FunctionalInterfaced 的类型）
		System.out.println(myLambda.getClass());//从$$Lambda$3/32441525 看出它是一个Lambda表达式
		//class org.dbyz.java8.functional_interface.TestMyLambda$$Lambda$3/32441525
	}
}
