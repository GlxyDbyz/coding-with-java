package org.dbyz.java8.interface_with_defaule_function;

/**
 * 实现ICar接口
 *
 * @ClassName: MyCar
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class MyCar implements ICar {
	/**
	 * default 的方法无需实现（当然你可以覆盖它们）
	 */
	@Override
	public void run() {
		System.out.println("我的汽车开动了");
	}
}