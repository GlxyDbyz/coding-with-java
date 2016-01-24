package org.dbyz.java.thread.simple;

import org.junit.Test;

/**
 * 简单线程测试
 *
 * @ClassName: TestSimpleThread
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class SimpleThreadTest {

	/**
	 * 线程创建方法1: 实例化自己的 Thread 线程类
	 * 
	 * @Title: test1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testCreateThread1() {
		SimpleThread testThread = new SimpleThread("testThread");
		testThread.run();// run 方法只是纯粹调用对象的方法, main 调用
		testThread.run();// run 方法只是纯粹调用对象的方法,可以重复调用 main 调用
		testThread.start();// start 才是线程启动的方法
		// testThread.start();// start 才是线程启动的方法,不可以重复调用

	}

	/**
	 * 线程创建方法2: 实例化自己的 Runnable 线程类
	 * 
	 * @Title: test1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testCreateThread2() {
		Thread testThread = new Thread(new SimpleRunable());
		// 启动线程 和上面的一样
		testThread.start();

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
	public void testCreateThread3() {
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

		// 还有这样(难看?习惯了,就好看了)
		Thread test2 = new Thread(
				() -> System.out.println("Hello,I am a new Thread:"
						+ Thread.currentThread().getName()));
		test2.start();
	}

	/**
	 * 线程休眠
	 * 
	 * @Title: testSleep
	 * @param
	 * @return: void
	 * @throws InterruptedException
	 * @since V1.0
	 */
	@Test
	public void testSleep() throws InterruptedException {
		System.out.println("before sleep");
		/*
		 * 线程休眠需要捕获中断异常,这里直接抛出去
		 */
		Thread.sleep(1000);
		// 睡眠结束之后线程(当前是主线程)才会继续运行
		System.out.println("after sleep");
	}
}