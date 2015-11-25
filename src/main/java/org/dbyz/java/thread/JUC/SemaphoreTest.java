package org.dbyz.java.thread.JUC;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量简单测试
 * 
 * 信号量是控制资源允许多少个线程同时访问的工具类,当permits = 1 的时候,它就是一个互斥锁,只允许一个线程获取信号量
 * 当空闲的信号量为0的时候,其他想获取信号量的线程就会阻塞,等待其他线程释放
 * 
 * @ClassName: SemaphoreTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class SemaphoreTest {
	private final static Semaphore MAX_AVAILABLE = new Semaphore(10, true);

	// 新建20个线程,每个线程去获取1个信号量,当前十个线程获取信号量之后,剩余的线程进入阻塞,当有线程释放的时候信号量的时候其他线程才可以再去获取
	// 当前面20线程都运行完了之后,才回有足够的10个信号量给最后一个线程去获取,这时候他获取了全部10个的信号量,直到并运行完毕
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			final int num = i;
			new Thread(() -> {
				boolean acquired = false;
				try {
					// 获取资源标记
					MAX_AVAILABLE.acquire();
					acquired = true;
					System.out.println("我是线程：" + num + " \t 获取1个资源！"
							+ System.currentTimeMillis());
					long time = (long) (1000 * 10 * Math.random());
					Thread.sleep(time);
					System.out.println("我是线程：" + num + " \t 释放1个资源！"
							+ System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (acquired) {
						// 释放资源标记
						MAX_AVAILABLE.release();
					}
				}
			}).start();
		}
		
		new Thread(() -> {
			boolean acquired = false;
			try {
				// 获取资源标记
				MAX_AVAILABLE.acquire(10);
				acquired = true;
				System.out.println("我是线程：Last \t获取 10 个资源！"
						+ System.currentTimeMillis());
				long time = (long) (1000 * 10 * Math.random());
				Thread.sleep(time);
				System.out.println("我是线程：Last \t释放 10 个资源！"
						+ System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (acquired) {
					// 释放资源标记
					MAX_AVAILABLE.release(10);
				}
			}
		}).start();
	}
		
		//	我是线程：2 	 获取1个资源！1448461048263
		//	我是线程：4 	 获取1个资源！1448461048263
		//	我是线程：3 	 获取1个资源！1448461048263
		//	我是线程：5 	 获取1个资源！1448461048263
		//	我是线程：0 	 获取1个资源！1448461048263
		//	我是线程：8 	 获取1个资源！1448461048263
		//	我是线程：1 	 获取1个资源！1448461048263
		//	我是线程：7 	 获取1个资源！1448461048264
		//	我是线程：6 	 获取1个资源！1448461048264
		//	我是线程：9 	 获取1个资源！1448461048264
	 													// 某十个线程首先获取信号量资源	
		//	我是线程：0 	 释放1个资源！1448461049206
		//	我是线程：10 	 获取1个资源！1448461049206
		//	我是线程：5 	 释放1个资源！1448461049482
		//	我是线程：11 	 获取1个资源！1448461049482
		//	我是线程：2 	 释放1个资源！1448461050596
		//	我是线程：12 	 获取1个资源！1448461050596
		//	我是线程：6 	 释放1个资源！1448461051340
		//	我是线程：13 	 获取1个资源！1448461051340
		//	我是线程：1 	 释放1个资源！1448461051471
		//	我是线程：14 	 获取1个资源！1448461051471
		//	我是线程：8 	 释放1个资源！1448461051596
		//	我是线程：15 	 获取1个资源！1448461051596
		//	我是线程：11 	 释放1个资源！1448461052200
		//	我是线程：16 	 获取1个资源！1448461052200
		//	我是线程：13 	 释放1个资源！1448461053344
		//	我是线程：17 	 获取1个资源！1448461053344
		//	我是线程：17 	 释放1个资源！1448461053990
		//	我是线程：18 	 获取1个资源！1448461053990	
		//	我是线程：16 	 释放1个资源！1448461054393
		//	我是线程：19 	 获取1个资源！1448461054393
														// 最后运行完的十个线程释放信号量资源
		//	我是线程：9 	 释放1个资源！1448461054549
		//	我是线程：7 	 释放1个资源！1448461055249
		//	我是线程：15 	 释放1个资源！1448461056705
		//	我是线程：10 	 释放1个资源！1448461057328
		//	我是线程：3 	 释放1个资源！1448461057892
		//	我是线程：4 	 释放1个资源！1448461058107
		//	我是线程：14 	 释放1个资源！1448461059286
		//	我是线程：12 	 释放1个资源！1448461060377
		//	我是线程：18 	 释放1个资源！1448461062770
		//	我是线程：19 	 释放1个资源！1448461063114
														// 最后一个线程此时才能获取全部10个信号量资源
		//	我是线程：Last 	获取 10 个资源！1448461063114
		//	我是线程：Last 	释放 10 个资源！1448461070363
}
