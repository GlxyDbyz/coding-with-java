package org.dbyz.java7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.FilerException;

import org.junit.Test;

/**
 * java 7 编程特性（和Java 8 一样，我们只关心它在编程和语法上的特性，至于框架性的东西就太多了）
 *
 * @ClassName: TestJava7Features
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class TestJava7Features {
	/**
	 * 数字表达
	 * 
	 * @Title: testNumber
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testNumber() {
		/**
		 * 1.Java 7 开始支持二进制数字表达方式,你还记得你自己写的十进制转二进制方法么?
		 */
		int binary = 0b000000011;// 3
		System.out.println(binary);// 3

		/**
		 * 2.Java 7 支持数字下划线分隔符(不限定是三位哦,我们中国人喜欢四位么)
		 */
		Long longNum = 183872617236L;// 这样?
		longNum = 183_872_617_236L;// 这样吧
		longNum = 1838_7261_7236L;// 还是这样吧,1838亿...
		System.out.println(longNum);// 当然正常运行输出 183872617236

		// 你是不是发现了什么?
		binary = 0b0000_0011;// 这样符合程序员看吧
	}

	/**
	 * Java 7 支持字符串Switch咯,程序员的福音啊
	 * 
	 * @Title: testSwitch
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testSwitch() {
		String key = "Monday";
		switch (key) {
		case "Monday":
			System.out.println("Work hard");
			break;
		case "":
			System.out.println("Work hard and hard");
			break;
		// .....
		default:
			System.out.println("Input a Day Of Week");
			break;
		}
		// 什么,你说这个没什么用?你写过计算器程序么?
	}

	/**
	 * 泛型推断
	 * 
	 * @Title: testGeneric
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testGeneric() {
		/**
		 * 以前我们泛型这样写,挺好
		 */
		Map<String, Object> map;
		map = new HashMap<String, Object>();

		/**
		 * 现在可以这样写(虚拟机自己推断),我知道你想说没啥用,但是代码真的变得简洁了
		 */
		map = new HashMap<>();
		// map = new HashMap();// 其实我也觉得还不如直接这样子(额,我只是开玩笑的啦)

	}

	/**
	 * 捕获多个异常
	 * 
	 * @Title: testException
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testException() {
		try {
			int a = 12;
			if (a > 12) {
				throw new FileNotFoundException();
			} else if (a < 12) {
				throw new FileAlreadyExistsException("test.doc");
			} else {
				throw new FilerException("test.doc");
			}
			/**
			 * 以前catch你是不是这样写
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		} catch (FilerException e) {
			e.printStackTrace();
		}
		// ...

		try {
			int a = 12;
			if (a > 12) {
				throw new FileNotFoundException();
			} else if (a < 12) {
				throw new FileAlreadyExistsException("test.doc");
			} else {
				throw new FilerException("test.doc");
			}

			/**
			 * 现在catch你可以这样写
			 */
		} catch (FileNotFoundException | FilerException
				| FileAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用Try去管理资源回收
	 * 
	 * @Title: testResouse
	 * @param
	 * @return: void
	 * @throws IOException
	 * @throws
	 * @since V1.0
	 */
	@Test
	public void testTryBlockManageResouse() throws IOException {
		/**
		 * 以前你这样回收资源
		 */
		BufferedReader br = new BufferedReader(new FileReader("E://test.txt"));
		try {
			br.readLine();
		} finally {
			br.close();
		}

		/**
		 * 现在交给Try去处理
		 */
		try (BufferedReader br2 = new BufferedReader(new FileReader(
				"E://test.txt"))) {
			br2.readLine();
		}

		/**
		 * 还来可以这样,管理多个资源(哦,代码有点难看)
		 */
		try (BufferedReader br2 = new BufferedReader(new FileReader(
				"E://test.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"test2.txt"))) {

			String line = br2.readLine();
			bw.write(line);
		}

		// 你问我 br2 为什么还可以再次定义,不冲突?作用域问题咯
		// br2. // br2 在这里是够不到的,他们定义在Try语句块里面哟
	}

	/**
	 * 变长参数警告问题
	 * 
	 * @Title: testVariableParameter
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testVariableParameter() {
		/**
		 * 这里需要有警告
		 */
		newFunction1(new ArrayList<>());
		
		/**
		 * 使用 @SafeVarargs 减少大量警告
		 */
		newFunction2(new ArrayList<>());
	}

	public static <T> T newFunction1(T... args) {
		return null;
	}

	@SafeVarargs
	public static <T> T newFunction2(T... args) {
		return args.length > 0 ? args[0] : null;
	}
}
