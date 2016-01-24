package org.dbyz.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * 写几个Lambda表达式的例子
 *
 * @ClassName: TestLambda
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class LambdaTest {
	/**
	 * 不使用和使用Lambda表达式对比
	 * 
	 * @Title: withoutLambda
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void lambdaYouCode() {
		// Lambda表达式是匿名函数的某种替代

		/**
		 * 1.创建一个线程（这个是我第一个想到例子）
		 */
		new Thread(new Runnable() {
			public void run() {
				System.out.println("I am run !");
			}
		}).start();
		// 下面这样写是不是简洁了许多?
		new Thread(() -> {
			System.out.println("I am run !");
		}).start();

		/**
		 * 2.再看TreeMap自定义排序
		 */
		Map<Integer, Object> map;
		map = new TreeMap<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		// 这样写你看懂了么?你说<>是啥?这个你问Java7去吧！
		map = new TreeMap<>((Integer o1, Integer o2) -> {
			return o1 - o2;
		});
		// 这样？
		map = new TreeMap<>((o1, o2) -> {
			return o1 - o2;
		});
		// 还可以这样哦
		map = new TreeMap<>((o1, o2) -> o1 - o2);
		map.put(1, "hello");

		/**
		 * 3.你说需要再来一个例子?其实和上面的是一样的
		 */
		List<String> words = Arrays.asList("bird", "cat", "monkey");

		Collections.sort(words, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		Collections.sort(words, (String a, String b) -> {
			return b.compareTo(a);
		});
		Collections.sort(words, (a, b) -> {
			return b.compareTo(a);
		});
		Collections.sort(words, (a, b) -> b.compareTo(a));
		// 所以?把Lambda用起来吧（哦忘了，公司的项目还是JDK1.6）
	}
}
