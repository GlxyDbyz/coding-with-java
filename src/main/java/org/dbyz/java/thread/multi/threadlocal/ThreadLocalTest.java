package org.dbyz.java.thread.multi.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal简单测试
 *
 * @ClassName: ThreadLocalTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ThreadLocalTest {
	public static void main(String[] args) throws InterruptedException {
		// 首先我们要知道什么是ThreadLocal:它是线程的本地变量,它是线程独立的一个变量存储
		final ThreadLocal<AtomicInteger> session = new ThreadLocal<>();
		// 我们看看session里面有什么
		System.out.println(session.get());
		// 啥也没有?我们给他设置一个值
		session.set(new AtomicInteger(100));
		// 请看源码set()的实现,它把true存入了当前线程的一个ThreadLocalMap里面,让他线程独立
		// 所以现在只要主线程没有死亡,你也没有再去修改它的值,你再次去获取session的值是不会改变的
		System.out.println(session.get());

		// 新建一个线程,你再次取出session的值会是什么?true?
		new Thread(() -> {
			System.out.println(session.get());
			session.set(new AtomicInteger(10));
			System.out.println(session.get());
		}).start();

		Thread.sleep(10); // 主线程休眠10毫秒,让新建的线程跑完

		// 我们再次取出主线程里面session的值看看
		System.out.println(session.get());
		// 明白了没有?
	}
}
