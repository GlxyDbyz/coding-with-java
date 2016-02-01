package org.dbyz.design_pattern._02Faced;
/**
 * 机器A 捡货
 *
 * @ClassName: MachineA
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class MachineA {
	public void operate(String goods) {
		// 捡货
		System.out.print(goods+" is Picking");
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
