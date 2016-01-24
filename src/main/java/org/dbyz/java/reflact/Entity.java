package org.dbyz.java.reflact;

/**
 * 为了测试反射而写的类(丑陋)
 *
 * @ClassName: Entity
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
@SuppressWarnings("all")
public class Entity {
	byte byte0 = 0;
	
	protected int integer1 = 1;
	
	private Double double2 = 2.0;
	
	public long long3 = 3L;
	
	final String  srting4 = "--srting4--";

	public Entity() {

	}

	void method() {
		System.out.println("--默认方法 void  method0 run--");
	}

	String method0() {
		System.out.println("--默认方法 String method0 run--");
		return "helo";
	}

	protected void method1() {
		System.out.println("--protected void method1 run--");
	}

	protected String method2() {
		System.out.println("--protected String void method2 run--");
		return "hello";
	}

	public void method3() {
		System.out.println("--public void method3 run--");
	}

	public String method4() {
		System.out.println("--public String method4 run--");
		return "hello";
	}

	private void method5() {
		System.out.println("--private void method5 run--");
	}

	private String method6() {
		System.out.println("--method6 run--");
		return "hello";
	}

}
