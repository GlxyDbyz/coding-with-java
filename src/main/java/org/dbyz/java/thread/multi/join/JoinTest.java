package org.dbyz.java.thread.multi.join;

/**
 * join方法使用简单例子
 *
 * @ClassName: TestJoin
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class JoinTest {
	// 首先:join()方法和join(time)的调用是在某个线程对象T上的
	// 其次:调用之后,当前线程C会进入等待状态,等待被唤醒(当然如果没有唤醒,则一直等待)
	// 其内部实现是调用了Object.wait()方法,也就是调用了T.wait()方法(这里是重点,说明此时的T是一个锁对象),
	// 不同的是,如果是你自己调用锁对象的wait()方法,你需要手动唤醒等待的线程,否则他们不会再次醒来
	// 而T.join()方法 和 T.join(time)分别会在T线程运行结束,和time结束的时候唤醒线程C
	// 而唤醒C的就是线程T本身,因为他就是锁,然后解除锁(不再同步),看最下面的解释
	
	
	public static void main(String[] args) throws InterruptedException {
		/**
		 * ----------------------join()-------------------------
		 */
		Thread t1 = new Thread(() -> {
			try {
				System.out.println("t1 start");
				for (int i = 0; i < 5; i++) {
					Thread.sleep(100);
					System.out.println("t1 run " + i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("t1 end");
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			System.out.println("t2 start");
			try {
				System.out.println("t2 call t1.join(),t2 begain to wait until t1 ended");
				t1.join(); // 此时t2开始等待t1执行结束,再往下执行
				for (int i = 0; i < 5; i++) {
					Thread.sleep(100);
					System.out.println("t2 run " + i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2 end");
		});
		t2.start();
		
		Thread.sleep(1100);
		
		/**
		 * ----------------------join(time)-------------------------
		 */
		System.out.println("-------------------------------------------");
		Thread t3 = new Thread(() -> {
			try {
				System.out.println("t3 start");
				for (int i = 0; i < 5; i++) {
					Thread.sleep(100);
					System.out.println("t3 run " + i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("t3 end");
		});
		t3.start();

		Thread t4 = new Thread(() -> {
			System.out.println("t4 start");
			try {
				System.out.println("t4 call t3.join(300),t2 begain to wait until t3 ended or the waitting time ended");
				t3.join(300); // 此时t2开始等待t1执行结束或者300毫秒时间结束,再往下执行
				for (int i = 0; i < 5; i++) {
					Thread.sleep(100);
					System.out.println("t4 run " + i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t4 end");
		});
		t4.start();
	}
}
//结果:(这些是我自己的理解,可能有误差,一起进步)
/*
		t1 start
		t2 start
		t2 call t1.join(),t2 begain to wait until t1 ended  // 这时候t1和t2 在<t2>这个对象上开始同步,t1获取了锁(他自己),内部调用了t1.wait(),所以t2 开始等待
		t1 run 0
		t1 run 1
		t1 run 2
		t1 run 3
		t1 run 4
		t1 end    // t1结束,唤醒在他上面等待的线程t2,解除锁(他自己),这时候变成非同步状态,因为锁被解除了
		t2 run 0  // t2开始运行
		t2 run 1
		t2 run 2
		t2 run 3
		t2 run 4
		t2 end
		-------------------------------------------
		t3 start
		t4 start
		t4 call t3.join(300),t2 begain to wait until t3 ended or the waitting time ended // 这时候t3和t4 在<t3>这个对象上开始同步,t3获取了锁(他自己),内部调用了t3.wait(300),所以t4 开始等待
		t3 run 0
		t3 run 1
		t3 run 2
		t3 run 3
		t4 run 0  // 300毫秒之后t3唤醒在他上面等待的线程t4,解除锁(他自己),这时候是非同步状态,因为锁被解除了,t4就开始和t3一起运行
		t3 run 4
		t3 end
		t4 run 1
		t4 run 2
		t4 run 3
		t4 run 4
		t4 end
*/