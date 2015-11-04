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
		
		Thread.sleep(1000 * 2);// 使用 main 方法运行不需要此语句
		
		// 新建线程A1开始运行之后,遇到 lock.wait(); 马上进入了等待状态
		// 线程A2开始运行,线程A2运行,并唤醒A1(A1等待获取锁),A2运行完了,释放锁,
		// A1获取锁之后继续从上次等待的地方开始运行,直到结束,然后主线程结束,程序退出

		// 结果:
		// Thread[A1,5,main] start
		// Thread[A1,5,main] wait
		// Thread[A2,5,main] start
		// Thread[A2,5,main] run
		// Thread[A2,5,main] notify others
		// Thread[A2,5,main] stop
		// Thread[A1,5,main] run
		// Thread[A1,5,main] notify others
		// Thread[A1,5,main] stop
		// Thread[A1,5,main] stop
		// Thread[线程名称,线程优先级,线程组名称]
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
						System.out.println(Thread.currentThread() + " wait");
						lock.wait();
					}

					System.out.println(Thread.currentThread() + " run");

					if (MyThread.count % 2 == 1) {
						System.out.println(Thread.currentThread()
								+ " notify others");
						lock.notify();// 唤醒一个线程(随机,此处只有线程 A2 )
						// lock.notifyAll(); // 唤醒在 lock 上等待的所有线程
					}
					System.out.println(Thread.currentThread() + " stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
