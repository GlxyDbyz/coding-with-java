package org.dbyz.java.thread.multi.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可继承的ThreadLocal简单例子
 *
 * @ClassName: InheritableThreadLocalTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class InheritableThreadLocalTest {
	public static void main(String[] args) throws InterruptedException {

		ThreadLocal<AtomicInteger> simpleSession = new ThreadLocal<>();
		InheritableThreadLocal<AtomicInteger> inheritableSession = new InheritableThreadLocal<>();

		simpleSession.set(new AtomicInteger(999));
		// InheritableThreadLocal操作和ThreadLocal是一样的
		inheritableSession.set(new AtomicInteger(888));

		// 不同的在下面
		new Thread(() -> {
			// 新建的线程继承了父线程的可继承变量的值,拿到了888
			System.out.println(inheritableSession.get());
			System.out.println(simpleSession.get());

			inheritableSession.set(new AtomicInteger(666));
			simpleSession.set(new AtomicInteger(777));

			System.out.println(simpleSession.get());
			System.out.println(inheritableSession.get());
		}).start();

		Thread.sleep(10);

		// 当然子线程不会影响父线程的值
		System.out.println(simpleSession.get());
		System.out.println(inheritableSession.get());

	}
}
