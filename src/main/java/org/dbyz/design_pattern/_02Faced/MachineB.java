package org.dbyz.design_pattern._02Faced;
/**
 * 机器B 包装
 *
 * @ClassName: MachineB
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class MachineB {
	public void operate(String goods) {
		// 包装
		System.out.print(goods+" is Packing");
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
