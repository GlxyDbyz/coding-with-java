/**
 * 
 */
package org.dbyz.java.thread.multi.sync;

import org.junit.Test;

/**
 * 
 * @ClassName: TestMultiThread
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class SynchronizedTest {

	/**
	 * 测试同步的方法 (同步最主要的概念是锁,只有当前的线程获取锁,才能(继续)执行,否则一直阻塞,sleep()仅仅只是延迟,不会释放锁)
	 * 
	 * @Title: testSynchronizedMethod
	 * @param @throws InterruptedException
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testSynchronizedMethod() throws InterruptedException {
		/*
		 * 同步的情况
		 */
		new Thread(() -> runWithSync()).start();
		new Thread(() -> runWithSync()).start();
		new Thread(() -> runWithSync()).start();
		/*
		 * 不同步的情况
		 */
		new Thread(() -> runWithOutSync()).start();
		new Thread(() -> runWithOutSync()).start();
		new Thread(() -> runWithOutSync()).start();

		/*
		 * 结果:没有同步的代码几乎同时执行(可能相差几毫秒左右), 同步的代码按 顺序执行(相差1000毫秒,也就是我们线程执行休眠的时间)
		 */

		/*
		 * 主线程要一直运行哦,不然他退出了,看不到结果
		 */
		Thread.sleep(1000 * 4);
	}

	public synchronized void runWithSync() {
		try {
			System.out.println("--runWithSync: " + System.currentTimeMillis());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runWithOutSync() {
		try {
			System.out.println("--runWithOutSync: "
					+ System.currentTimeMillis());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试同步的代码块
	 * 
	 * @Title: testSynchronizedblock
	 * @param
	 * @return: void
	 * @throws InterruptedException
	 * @since V1.0
	 */
	@Test
	public void testSynchronizedblock() throws InterruptedException {
		new Thread(() -> runWithSyncBlock()).start();
		new Thread(() -> runWithSyncBlock()).start();
		new Thread(() -> runWithSyncBlock()).start();
		new Thread(() -> runWithSyncBlock()).start();

		/*
		 * 效果和上面的是一样的
		 */
		Thread.sleep(5000);
	}

	public void runWithSyncBlock() {
		/**
		 * 括号里面的称为锁,锁必须唯一才可以保证同步,此处是 this,指代当前对象, this
		 * 有可能不是同一个对象,造成同步失败,一般使用某些不变量作为锁,
		 * 比如(TestSynchronized.class/或者其他类,类一般只会加载一次)
		 */
		synchronized (this) {
			// 打印出来的东西一致,说明是同一个锁,这样才能同步
			System.out.println(this);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void runWithSyncBlock2() {
		synchronized (SynchronizedTest.class) {
			// 打印出来的东西一致,说明是同一个锁,这样才能同步
			System.out.println(SynchronizedTest.class);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
