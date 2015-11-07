package org.dbyz.java.thread.multi.wait;



/**
 * 线程等待和唤醒   : wait(time)之后,当前线程会释放锁,进入等待状态,等待再次唤醒,然后再去竞争锁,然后继续执行;如果线程没有被唤醒,则线程一直处于等待状态,虚拟机不会退出
 *
 * @ClassName: TestWaitAndNotify2
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class WaitAndNotifyTest2 {
	public static void main(String[] args) throws InterruptedException {
		new Thread(new MyThread(), "B1").start();
		new Thread(new MyThread(), "B2").start();
		/**
			Thread[B1,5,main] start
			Thread[B1,5,main] wait(1000)   //A1进入等待状态,1000这个参数他其实并不知道,对他来说等同于调用了wait(),1000是lock需要知道的东西
			Thread[B2,5,main] start
			Thread[B2,5,main] run
			Thread[B2,5,main] stop
			Thread[B1,5,main] run          //在1000毫秒结束的时候,lock唤醒了在他上面等待的线程(有可能此时还有线程保持这锁,A1还是不能运行),此时B2早就释放了锁并结束了     (你也可以提前唤醒她,解除51行的注释即可)
			Thread[B1,5,main] stop

		 */
	}

	static class MyThread implements Runnable {
		//自定义某个不变的变量
		private final static Object lock = new Object();

		private static int count = -1;

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " start");
				try {
					MyThread.count++;
					if (MyThread.count % 2 == 0) {
						System.out.println(Thread.currentThread()
								+ " wait(1000)");
						lock.wait(1000);
					}

					System.out.println(Thread.currentThread() + " run");
					Thread.sleep(10);

					//lock.notify();

					System.out.println(Thread.currentThread() + " stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
