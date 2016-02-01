package org.dbyz.design_pattern._4singleton;

/**
 * 提前加载模式的单例(线程安全)
 *
 * @ClassName: Singleton1
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Singleton1 {

	// 1.私有化构造方法
	private Singleton1() {
		System.out.println("init");
	}

	// 3.自己持有自己的对象实例
	private static Singleton1 singleton = new Singleton1();

	// 2.得到实例的方法（可以无实例时候调用）static 必须
	public static Singleton1 getInstance() {
		// 3 自己需要持有一个自己的对象实例
		return singleton;
		// return new Singleton_1(); //??这样还有意义？
	}

	public void doSomething() {
		System.out.println("do something ");
	}

	public static void main(String[] args) {
		try {
			System.out.println("system run");
			Thread.sleep(1000);
			Singleton1 singleton = Singleton1.getInstance();
			singleton.doSomething();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}