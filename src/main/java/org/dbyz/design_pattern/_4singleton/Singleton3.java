package org.dbyz.design_pattern._4singleton;

/**
 * 延迟加载模式的单例（线程安全但是方法有阻塞）
 *
 * @ClassName: Singleton3
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Singleton3 {

	// 1.私有化构造方法
	private Singleton3() {
		System.out.println("init");
	}

	private static Singleton3 singleton = null;

	// 2.得到实例的方法（可以无实例时候调用）static 必须
	public synchronized static Singleton3 getInstance() {// 线程安全但是每次都会阻塞，不够高效
		// 自己需要持有一个自己的对象实例
		if (singleton == null) {
			singleton = new Singleton3();
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
			Singleton3 singleton = Singleton3.getInstance();
			singleton.doSomething();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}