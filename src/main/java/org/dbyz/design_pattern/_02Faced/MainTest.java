package org.dbyz.design_pattern._02Faced;

/**
 * 外观模式（以物流打包发货为例）
 *
 * @ClassName: MainTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class MainTest {
	public static void main(String[] args) {
		// 电脑上点下发货
		Interface.justPackageIt("iPad mini 3");
	}
}
/**
 * 外观模式的本质是把某些操作（具有连续性的）进行封装，简化调用
 */
