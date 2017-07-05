package org.dbyz.java.thread.multi.deadlock;
/**
 *  线程死锁例子
 *
 * @ClassName: DeadLockTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class DeadLockTest {
	public static void main(String[] args) throws InterruptedException {
		Object o1 = new Object();
		Object o2 = new Object();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o1) {
					try {
						Thread.sleep(10);
						System.out.println(Thread.holdsLock(o1));
						System.out.println("t1...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (o2) {
						System.out.println("t1。。。");
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o2) {
					try {
						Thread.sleep(10);
						System.out.println(Thread.holdsLock(o2));
						System.out.println("t2...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (o1) {
						System.out.println("t2。。。");
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		
		System.out.println("main end");
	}
}
