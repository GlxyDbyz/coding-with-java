package org.dbyz.java.thread.simple;

/**
 * 简单继承Thread构造一个自己的线程类
 *
 * @ClassName: SimpleThread
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class SimpleThread extends Thread {
	/**
	 * 以下是最常用的两中构造方法,@see Thread
	 */
	
	/**
	 * 1.无参的构造方法
	 */
	public SimpleThread() {
		super();
	}

	/**
	 * 2.带线程名称的构造方法
	 * 
	 * @param name
	 */
	public SimpleThread(String name) {
		super(name);
	}

	/**
	 * 重写run方法(线程执行方法)
	 */
	@Override
	public void run() {
		System.out.println("Hello,I am a new Thread:"
				+ Thread.currentThread().getName());
	}
}