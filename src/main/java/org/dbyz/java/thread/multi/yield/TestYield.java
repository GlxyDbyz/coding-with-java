package org.dbyz.java.thread.multi.yield;

/**
 * 线程让步例子
 *
 * @ClassName: TestYield
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class TestYield {
	// 首先明确yield方法是线程的静态方法,运行他代表当前线程有让出资源的意愿,
	// 但是是否真的让出资源,由JVM决定,所以测试出明显的现象并不是那么容易
	// 下面四条线程 t1 和 t2 一起运行没有使用yield方法 ,t3 和 t4 一起运行,在t3的循环里面调用了yeild
	// 最下面是我某一次运行的结果,虽然结果不是特别明显,但是也足够说明yield方法起到了他应有的作用,
	// 当然你可以多运行几次(t4 对于t3  总会迟于  t2 对于t1 开始运行)
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			System.out.println("t1 start");
			for (int i = 0; i < 10; i++) {
				System.out.println(1);
			}
			System.out.println("t1 end");
		});

		Thread t2 = new Thread(() -> {
			System.out.println("t2 start");
			for (int i = 0; i < 10; i++) {
				System.out.println(2);
			}
			System.out.println("t2 end");
		});

		t1.start();t2.start();

		Thread.sleep(100);
		System.out.println("-------------------------------------");

		Thread t3 = new Thread(() -> {
			System.out.println("t3 start");
			for (int i = 0; i < 10; i++) {
				System.out.println(3);
			}
			System.out.println("t3 end");
		});

		Thread t4 = new Thread(() -> {
			Thread.yield();
			System.out.println("t4 start");
			for (int i = 0; i < 10; i++) {
				System.out.println(4);
			}
			System.out.println("t4 end");
		});

		t3.start();t4.start();
	}
	/**
		t2 start
		t1 start
		1
		1
		1
		1
		1
		1
		1
		1
		1
		1
		t1 end
		2
		2
		2
		2
		2
		2
		2
		2
		2
		2
		t2 end
		-------------------------------------
		t3 start
		3
		3
		3
		3
		3
		3
		3
		3
		3
		3
		t4 start
		4
		4
		4
		4
		4
		4
		4
		4
		4
		4
		t3 end
		t4 end
	 */
}
