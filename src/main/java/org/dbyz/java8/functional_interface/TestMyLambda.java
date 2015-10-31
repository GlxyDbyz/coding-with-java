package org.dbyz.java8.functional_interface;

import org.junit.Test;

public class TestMyLambda {
	/**
	 * 普通方法实现
	 * 
	 * @Title: test1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1() {
		// 定义一个Lambda表达式
		MyLambda<String, Integer> myLambda;
		// 实现它（唯一的抽象方法getValue()）
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

		// （每个Lambda表达式可以对应很多的实现，每个实现都指向一个 @FunctionalInterfaced 的类型）
		System.out.println(myLambda.getClass());// 从$$Lambda$3/32441525
												// 看出它是一个Lambda表达式
		// class
		// org.dbyz.java8.functional_interface.TestMyLambda$$Lambda$3/32441525
		
		
		String zore = "0";
		MyLambda<String, Integer> myLambda3;
		myLambda3 = (String param) -> {
			return Integer.valueOf(param + zore);
		};
		System.out.println(myLambda3.getValue("12"));
		// zore = "1";//加上这句就会编译不通过，Lambda表达式本质还是和匿名内部类是一样的
		// 引用的变量自动变成final之后不允许修改
	}

	/**
	 * 方法引用实现(::)
	 * 
	 * @Title: test2
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test2() {
		MyLambda<String, Integer> myLambda1;
		myLambda1 = String::length;
		System.out.println(myLambda1.getValue("A123456789"));// 10

		MyLambda<String, Person> myLambda2 = Person::new;
		System.out.println(myLambda2.getValue("Tom")); // Person [name=Tom]
	}
}
