package org.dbyz.java.thread.multi.interrupte;

/**
 * 线程中断简单测试
 *
 * @ClassName: TestInterrupte
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class InterrupteTest {
	// 1.首先明确什么时候需要线程中断:线程拥有者希望线程不再继续运行
	// 2.阻塞方法sleep(),wait()为什么抛出InterruptedException:希望线程的调用者知道线程被中断
	// 3.如何处理中断和中断异常:不要吞噬中断异常;让线程中断;
	public static void main(String[] args) throws InterruptedException {

		/**
		 * -----------------------------------情形1------------------------------
		 */
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					System.out.println("thread1 loop: " + i);
					// do something
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// 这样其实没啥用,等于是吞噬了中断异常
				e.printStackTrace();
			}
		}
	}	);

		thread1.start();
		// 线程 thread1循环100次,不会在循环结束之前停止(当然,循环中可以是你想做的任何事情,这里只是做个演示例子)

		// 当我们希望线程在完成某个任务之后就停止,而不是一直循环下去(比如,上面do something 在23次的时候就完成了我们需要的任务,
		// 但是在他完成之前我们并不知道他什么时候完成的,我们希望它在完成任务之后就停止),就可以用中断实现)

		// 让他运行一会儿
		Thread.sleep(20);
		// 中断他就是这么简单
		thread1.interrupt();
		// 但是 ...... 哦NO,线程尽然继续运行了,发生什么?

		/**
		 * ----------------------------情形2 (推荐的形式1) ------------------------
		 */

		// 看来这样我们是无法停止线程的,那该怎没办?看下面的线程写法
		// 让线程thread1运行完
		Thread.sleep(500);

		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (Thread.interrupted()) {
					System.out.println("thread2 break");
					// return;
				break;
			}
			try {
				System.out.println("thread2 loop: " + i);
				// do something
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// 这句话很重要哦,此处保证线程会被中断,你可以注释掉它并多运行几次看看,中断可能失败
				Thread.currentThread().interrupt();
				// 原因:
				// 因为sleep()调用 Thread.interrupted() 方法判断当前的线程是否被中断
				// Thread.interrupted()返回当前线程是否被中断,同时会重置interrupt标志为false,我们的if判断就失效了

				// 而Thread.currentThread().isInterrupted()
				// 只是返回线程的中断状态,不会改变线程的阻塞状态

				e.printStackTrace();
			}
		}
	}	);

		thread2.start();

		// 让他运行一会儿
		Thread.sleep(20);
		// 这次你成功的中断了线程
		thread2.interrupt();

		// 啥?你说报错了? e.printStackTrace();//只是打印错误日志

		// 让线程thread2运行完
		Thread.sleep(500);

		/**
		 * -----------------------------------情形3------------------------------
		 */
		// 无线循环线程中有sleep()方法只是让线程不要一直运行,起到定时的作用,节约CPU资源
		Thread thread3 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (Thread.interrupted()) {
					System.out.println("thread3 break");
					break;
				}
				System.out.println("thread3 loop: " + i);
				// do something
			}
		});

		thread3.start();
		// 让它运行一会儿
		Thread.sleep(0, 1);
		// 由于thread3没有定时,这时候thread3可能已经运行完了,中断可能来不及实施(Java还是很快的^_^)
		thread3.interrupt();

		/**
		 * -----------------------情形4 (推荐的形式2) ---------------------
		 */
		// 当线程是一个不能中断的线程的时候,他收到中断请求的时候,可以这样通知线程调用者线程的被中断,并抛出运行时异常,让调用者去处理这个异常
		Thread thread4 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					System.out.println("thread4 loop: " + i);
					Thread.sleep(5);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					throw new RuntimeException(e);
				}
			}
		});
		thread4.start();
		Thread.sleep(20);
		thread4.interrupt();
	}

}
