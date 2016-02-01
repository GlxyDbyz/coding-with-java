package org.dbyz.design_pattern._02Faced;

/**
 * 机器C 贴标签
 *
 * @ClassName: MachineC
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class MachineC {
	public void operate(String goods) {
		// 贴标签
		System.out.print(goods+" is Labeling");
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(200);
				System.out.print(" .");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("100%");
	}
}
