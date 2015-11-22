package org.dbyz.java.thread.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单例子证明HashMap 和 ConcurrentHashMap 线程安全性区别
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