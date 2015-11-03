package org.dbyz.java.thread.multi.wait;

import org.junit.Test;

/**
 * 线程等待和唤醒(wait()会释放锁,等待再次唤醒,然后再去获取锁,然后继续执行)
 *
 * @ClassName: TestWait
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class TestWaitAndNotify {
	private static String lock = "lock";

	@Test
	public void testWait() throws InterruptedException {
		new Thread(new MyThread(), "A1").start();
		new Thread(new MyThread(), "A2").start();
		Thread.sleep(1000 * 2);
		// 结果:
		// 新建线程A1首先进入了等待状态
		// 新建线程A1运行完,唤醒A1,A1继续运行
		
		/*  Thread[A1,5,main] start
			Thread[A1,5,main]wait
			Thread[A2,5,main] start
			Thread[A2,5,main]run
			Thread[A2,5,main]notify others
			Thread[A2,5,main] stop
			Thread[A1,5,main]run
			Thread[A1,5,main]notify others
			Thread[A1,5,main] stop
		 */
	}

	static class MyThread implements Runnable {
		private static int count = -1;

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " start");
				try {
					MyThread.count++;
					if (MyThread.count % 2 == 0) {
						System.out.println(Thread.currentThread() + "wait");
						lock.wait();
					}

					System.out.println(Thread.currentThread() + "run");

					if (MyThread.count % 2 == 1) {
						System.out.println(Thread.currentThread() + "notify others");
						lock.notify();
					}
					System.out.println(Thread.currentThread() + " stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
