package org.dbyz.design_pattern._4singleton;

/**
 * 枚举模式的单例（java 自动实现的单例 线程安全）
 *
 * @ClassName: Singleton6
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public enum Singleton6 {
	Singleton;// java 对枚举类型自动实现了单例

	public void doSomething() {
		System.out.println("do something ");
	}

	public static void main(String[] args) {
		Singleton6 singleton = Singleton6.Singleton;
		try {
			System.out.println("system run");
			Thread.sleep(1000);
			singleton.doSomething();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}