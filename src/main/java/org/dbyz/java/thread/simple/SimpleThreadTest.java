package org.dbyz.java.thread.simple;

import org.junit.Test;

/**
 * 简单线程测试
 *
 * @ClassName: TestSimpleThread
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class SimpleThreadTest {

	/**
	 * 线程创建方法1: 实例化自己的线程类
	 * 
	 * @Title: test1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1() {
		SimpleThread testThread = new SimpleThread("testThread");
		testThread.run();// run 方法只是纯粹调用对象的方法, main 调用
		testThread.run();// run 方法只是纯粹调用对象的方法,可以重复调用 main 调用
		testThread.start();// start 才是线程启动的方法
		// testThread.start();// start 才是线程启动的方法,不可以重复调用

	}

	/**
	 * 线程创建方法2: 用Runnable开启一个线程(常用方法)
	 * 
	 * @Title: test2
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test2() {
		Thread test = new Thread(new Runnable() {
			public void run() {
				System.out.println("Hello,I am a new Thread:"
						+ Thread.currentThread().getName());
			}
		}, "testThread");

		test.start();

		// 当然,学习了 Java 8 我们使用 Lambda 表达式来新建一个线程
		// 这样
		Thread test1 = new Thread(() -> {
			System.out.println("Hello,I am a new Thread:"
					+ Thread.currentThread().getName());
			// 没带线程名称参数
			});
		test1.start();

		// 还有这样(是不是难看?,习惯了,就好看了)
		Thread test2 = new Thread(
				() -> System.out.println("Hello,I am a new Thread:"
						+ Thread.currentThread().getName()));
		test2.start();

	}
}
