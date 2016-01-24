package org.dbyz.java8.interface_with_defaule_function;

/**
 * 汽车接口
 *
 * @ClassName: ICar
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public interface ICar {
	/**
	 * 普通未实现方法
	 * 
	 * @Title: run
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	public void run();

	/**
	 * 接口的默认实现方法1（是不是有点像抽象类了）
	 * 
	 * @Title: stop
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	public default void stop() {
		System.out.println("汽车停下来了!");
	}

	/**
	 * 接口的默认实现方法2（是不是有点像抽象类了）
	 * 
	 * @Title: accelerate
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	public default void accelerate() {
		System.out.println("汽车在加速!");
	}
}
