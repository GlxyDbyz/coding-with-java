package org.dbyz.basics.encode;

import org.junit.Test;

/**
 * 编码
 *
 * @ClassName: EncodeTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class EncodeTest {
	/**
	 * 不同的符号不同编码表中占用的空间会不一定一样<br/>
	 * 中文UTF-8编码后占三个字节
	 */
	@Test
	public void test1() throws Exception {
		System.out.println("".getBytes("ISO-8859-1").length + " | "
				+ "a".getBytes("ISO-8859-1").length + " | "
				+ "啊".getBytes("ISO-8859-1").length);

		System.out.println("".getBytes("GBK").length + " | "
				+ "a".getBytes("GBK").length + " | "
				+ "啊".getBytes("GBK").length);

		System.out.println("".getBytes("UTF-8").length + " | "
				+ "a".getBytes("UTF-8").length + " | "
				+ "啊".getBytes("UTF-8").length);

		// 0 | 1 | 1
		// 0 | 1 | 2
		// 0 | 1 | 3
	}
}
