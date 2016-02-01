package org.dbyz.design_pattern._4singleton;

/**
 * 延迟加载模式的单例（线程不安全）
 *
 * @ClassName: Singleton2
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Singleton2 {

	// 1.私有化构造方法
	private Singleton2() {
		System.out.println("init");
	}

	private static Singleton2 singleton = null;

	// 2.得到实例的方法（可以无实例时候调用）static 必须
	public static Singleton2 getInstance() {// 线程不安全
		// 自己需要持有一个自己的对象实例
		if (singleton == null) {
			singleton = new Singleton2();
		}
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
			Singleton2 singleton = Singleton2.getInstance();
			singleton.doSomething();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}