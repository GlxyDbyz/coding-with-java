package org.dbyz.java.thread.JUC.locks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁ReentrantLock简单测试
 *
 * @ClassName: ReentrantLockTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class ReentrantLockTest {
	// 可重入锁对象
	private static ReentrantLock lock = new ReentrantLock();
	// 可以保证操作的Integer工具类,至于它是如何做到原子性的可以去看它的实现,
	// Java内部使用了Unsafe实现(其他一些并发相关的类也使用到了Unsafe)
	private static AtomicInteger count1 = new AtomicInteger();
	private static AtomicInteger count2 = new AtomicInteger();

	/**
	 * 此方法使用ReentrantLock锁定,保证原子性
	 * 
	 * @Title: functionWithLock
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	private static void functionWithLock() {
		// 加锁
		lock.lock();
		try {
			Thread.sleep(1234);
			System.out.println(Thread.currentThread().getName()
					+ " run functionWithLock " + count1.incrementAndGet()
					+ " times " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁 写在finally语句块,防止程序报错之后不释放锁(推荐写法即使不会报错)
			lock.unlock();
		}
	}

	/**
	 * 此方法没有使用锁( 先注释 synchronized )
	 * 
	 * @Title: functionWithOutLock
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	private static/* synchronized */void functionWithOutLock() {
		try {
			Thread.sleep(1234);
			System.out.println(Thread.currentThread().getName()
					+ " run functionWithOutLock " + count2.incrementAndGet()
					+ " times " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		// 五条线程运行 functionWithLock()
		for (int i = 0; i < 5; i++) {
			new Thread(() -> functionWithLock()).start();
		}
		// 五条线程运行 functionWithOutLock()
		for (int i = 0; i < 5; i++) {
			new Thread(() -> functionWithOutLock()).start();
		}
	}
	// Thread-0 run functionWithLock 1 times 1448547153049
	// Thread-6 run functionWithOutLock 1 times 1448547153050
	// Thread-5 run functionWithOutLock 2 times 1448547153050
	// Thread-8 run functionWithOutLock 3 times 1448547153051
	// Thread-7 run functionWithOutLock 4 times 1448547153051
	// Thread-9 run functionWithOutLock 5 times 1448547153051
	// 这六条线程几乎同时运行
	// Thread-1 run functionWithLock 2 times 1448547154283
	// Thread-2 run functionWithLock 3 times 1448547155518
	// Thread-3 run functionWithLock 4 times 1448547156752
	// Thread-4 run functionWithLock 5 times 1448547157986
	// 下面的线程等待线程0运行完之后释放锁,依次获取锁运行并释放锁

	// 解除 functionWithOutLock 前面的 synchronized 的注解 也可以实现锁,但这是两种不同的实现
	// 运行效果如下
	// Thread-0 run functionWithLock 1 times 1448548699675
	// Thread-5 run functionWithOutLock 1 times 1448548699676
	// Thread-1 run functionWithLock 2 times 1448548700909
	// Thread-9 run functionWithOutLock 2 times 1448548700910
	// Thread-4 run functionWithLock 3 times 1448548702143
	// Thread-8 run functionWithOutLock 3 times 1448548702144
	// Thread-2 run functionWithLock 4 times 1448548703377
	// Thread-7 run functionWithOutLock 4 times 1448548703378
	// Thread-3 run functionWithLock 5 times 1448548704611
	// Thread-6 run functionWithOutLock 5 times 1448548704613
	
	// 相比 synchronized 关键字,ReentrantLock锁的特点是更加灵活,在你想要锁定的地方锁定,在你想要释放的时候释放
}
