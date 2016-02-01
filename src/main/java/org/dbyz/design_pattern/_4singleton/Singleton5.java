package org.dbyz.design_pattern._4singleton;

/**
 * 内部类实现单例
 *
 * @ClassName: Singleton5
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Singleton5 {
	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系， 而且只有被调用到才会装载，从而实现了延迟加载
	 */
	private static class SingletonHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static Singleton5 instance = new Singleton5();
	}

	/**
	 * 私有化构造方法
	 */
	private Singleton5() {
		System.out.println("init");
	}

	public static Singleton5 getInstance() {
		return SingletonHolder.instance;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("system run");
			Thread.sleep(1000);
			System.out.println(Singleton5.getInstance());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}