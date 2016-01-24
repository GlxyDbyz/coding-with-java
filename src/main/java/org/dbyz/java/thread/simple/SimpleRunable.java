package org.dbyz.java.thread.simple;

/**
 * 实现 Runnable 构造一个自己的线程类
 *
 * @ClassName: SimpleThread
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class SimpleRunable implements Runnable {
	
	/**
	 * 1.无参的构造方法
	 */
	public SimpleRunable() {
		super();
	}

	/**
	 * 重写run方法(线程执行方法)
	 */
	@Override
	public void run() {
		System.out.println("Hello,I am a new Runnable:"
				+ Thread.currentThread().getName());
	}
}