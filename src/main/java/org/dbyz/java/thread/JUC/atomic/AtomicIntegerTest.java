package org.dbyz.java.thread.JUC.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支持原子操作的整型类
 *
 * @ClassName: AtomicIntegerTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class AtomicIntegerTest {
	public static AtomicInteger count1 = new AtomicInteger(0);
	public static Integer count2 = new Integer(0);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10000; i++) {
						count1.incrementAndGet();
						count2++;
					}
				}
			}).start();
		}

		while (Thread.activeCount() > 1) {
			Thread.yield();
		}

		System.out.println("count1:" + count1.get() + " count2:" + count2);
	}
	  //  count1:100000 count2:67694
	
	  //  结果很明显 AtomicInteger是线程安全的,而普通的Integer是线程不安全的有数据被覆盖
}
