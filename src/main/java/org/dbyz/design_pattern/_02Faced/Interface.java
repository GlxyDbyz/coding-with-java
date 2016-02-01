package org.dbyz.design_pattern._02Faced;

/**
 * 界面类所有的事情在这里完成调用（可以理解为电脑）
 *
 * @ClassName: Interface_ACompter
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Interface {
	/**
	 * 进行货物打包
	 * 
	 * @Title: justPackageIt
	 * @param @param goods
	 * @return: void
	 * @since V1.0
	 */
	public static void justPackageIt(String goods) {
		new MachineA().operate(goods);
		new MachineB().operate(goods);
		new MachineC().operate(goods);
		System.out.println("Courier can send out " + goods
				+ " to the customer.");
	}
}
