package org.dbyz.java.thread.multi.wait;


/**
 * 线程等待和唤醒: wait()之后,当前线程会释放锁,进入等待状态,等待再次唤醒,然后再去竞争锁,然后继续执行;如果线程没有被唤醒,则线程一直处于等待状态,虚拟机不会退出
 *
 * @ClassName: TestWaitAndNotify
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class TestWaitAndNotify {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new MyThread(), "A1").start();
		new Thread(new MyThread(), "A2").start();

		Thread.sleep(1000 * 2);// 使用 main 方法运行不需要此语句

		// 新建线程A1开始运行之后,遇到 lock.wait(); 马上进入了等待状态
		// 线程A2开始运行,线程A2运行,并唤醒A1(A1等待获取锁),A2运行完成之后释放锁,
		// A1获取锁之后继续从上次等待的地方开始运行,直到结束,然后主线程结束,程序退出

		// 结果:
		// Thread[A1,5,main] start
		// Thread[A1,5,main] wait                 // A1进入等待状态
		// Thread[A2,5,main] start
		// Thread[A2,5,main] run
		// Thread[A2,5,main] notify one another   //lock(记住只有锁对象才能唤醒线程哦) 唤醒下一个线程(A1)
		// Thread[A2,5,main] stop
		// Thread[A1,5,main] run
		// Thread[A1,5,main] notify one another   //lock 唤醒下一个线程(其实啥也没有了,当然他不会报错)
		// Thread[A1,5,main] stop
		// Thread[线程名称,线程优先级,线程组名称]
	}

	static class MyThread implements Runnable {
		private final static String lock = "lock";

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
					//....

					System.out.println(Thread.currentThread()
							+ " notify one another");
					lock.notify();// 唤醒一个线程(随机,此处只有线程 A2 )
					// lock.notifyAll(); // 唤醒在 lock 上等待的所有线程
					
					
					System.out.println(Thread.currentThread() + " stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
