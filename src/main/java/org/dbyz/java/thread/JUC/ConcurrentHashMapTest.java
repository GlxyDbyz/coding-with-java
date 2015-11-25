package org.dbyz.java.thread.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单例子证明HashMap 和 ConcurrentHashMap 线程安全性区别
 * 
 * 实现原理:
 * 在ConcurrentHashMap的实现和普通的HashMap是完全不同的,它内部有一个用于放置数据的Segment<K,V>[] segments数组,保证了而对每个数组的操作是原子操作,
 * 当你放置一对数据的时候,通过hash算法得到他放置到那个数组,由于Segment继承了ReentrantLock,在操作之前他会lock()自己,在操作完了之后进行unlock(),所以对于
 * 每个Segment操作保证了原子性,而在这时候你放置一对数据如果hash算法之后没有指向这个Segment,则可以直接操作.这样保证了既保证原子性又保证操作的性能(分治思想).
 * 而Segment的数量[DEFAULT_CONCURRENCY_LEVEL]可以自行设置,默认是16
 * 具体想了解ConcurrentHashMap原理可以自行查看源码进行分析
 * 
 * @ClassName: ConcurrentHashMapTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class ConcurrentHashMapTest {
	
	public static void main(String[] args) throws InterruptedException {
		Map<String, Integer> hashMap = new HashMap<String, Integer>(10000);
		for (int i = 0; i < 10; i++) {
			new MyThread(hashMap).start();
		}
		Thread.sleep(1000);
		System.out.println("HashMap size:" + hashMap.size());
		// Thread-7end
		// Thread-2end
		// Thread-0end
		// Thread-3end
		// Thread-4end
		// Thread-8end
		// Thread-9end
		// Thread-6end
		// Thread-1end
		// Thread-5end
		// HashMap size:9290

		/**
		 * HashMap size 小于10000 说明多线程操作造成了有些数据没有put成功,或者被覆盖了,线程不安全
		 */

		Map<String, Integer> ccMap = new ConcurrentHashMap<String, Integer>(
				10000);
		for (int i = 0; i < 10; i++) {
			new MyThread(ccMap).start();
		}
		Thread.sleep(1000);
		System.out.println("ConcurrentHashMap size:" + ccMap.size());
		// Thread-12end
		// Thread-13end
		// Thread-16end
		// Thread-17end
		// Thread-10end
		// Thread-11end
		// Thread-14end
		// Thread-15end
		// Thread-18end
		// Thread-19end
		// ConcurrentHashMap size:10000

		/**
		 * ConcurrentHashMap size 等于 10000 说明多线程put都成功了,且没有数据被覆盖,线程安全
		 */
	}

	static class MyThread extends Thread {
		Map<String, Integer> map;

		MyThread(Map<String, Integer> map) {
			this.map = map;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				map.put(Thread.currentThread().getName() + i, i);
			}
			System.out.println(this.getName() + "end");
		}
	}
}