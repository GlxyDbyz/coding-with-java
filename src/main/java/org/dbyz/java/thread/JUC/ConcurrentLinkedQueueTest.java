package org.dbyz.java.thread.JUC;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 同步的ConcurrentLinkedQueue和普通LinkedBlockingQueue对比例子
 * (LinkedBlockingQueue 在没有使用阻塞方法的时候就是普通的Queue,所以Java没有单独去实现普通的Queue)
 * 
 * @ClassName: ConcurrentLinkedQueueTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ConcurrentLinkedQueueTest {

	// 对不同类型的queue进行add和pull操作,十条线程add 1000*10 数据,还有十条线程pull 100*10 数据,对线程安全进行验证
	// 得出的结论是 ConcurrentLinkedQueueTest 是线程安全的 而 LinkedBlockingQueue 则不是
	
	public static void main(String[] args) throws InterruptedException {
		Queue<String> queue  = new LinkedBlockingQueue<String>();
		for (int i = 0; i < 10; i++) {
			// add和pull方法都不是阻塞操作,所以这里当作普通的Queue处理
			new MyThreadQueueAdd(queue).start();
			new MyThreadQueuePull(queue).start();
		}
		
		Thread.sleep(1000);
		System.out.println("LinkedBlockingQueue size:" + queue.size());
		// Thread-3end
		// Thread-1end
		// Thread-5end
		// Thread-7end
		// Thread-9end
		// Thread-11end
		// Thread-13end
		// Thread-15end
		// Thread-17end
		// Thread-19end
		// Thread-18end
		// Thread-8end
		// Thread-0end
		// Thread-14end
		// Thread-2end
		// Thread-10end
		// Thread-4end
		// Thread-12end
		// Thread-6end
		// Thread-16end
		// LinkedBlockingQueue size:9233
		/**
		 * LinkedBlockingQueue size 大于9000 说明多线程操作造成了有些数据没有pull()成功或者add()成功,线程不安全
		 */
		
		Queue<String> ccQueue  = new ConcurrentLinkedQueue<String>();
		for (int i = 0; i < 10; i++) {
			new MyThreadQueueAdd(ccQueue).start();
			new MyThreadQueuePull(ccQueue).start();
		}
		Thread.sleep(1000);
		System.out.println("ConcurrentLinkedQueue size:" + ccQueue.size());
		
		// Thread-20end
		// Thread-21end
		// Thread-24end
		// Thread-25end
		// Thread-28end
		// Thread-29end
		// Thread-22end
		// Thread-23end
		// Thread-26end
		// Thread-27end
		// Thread-30end
		// Thread-31end
		// Thread-34end
		// Thread-35end
		// Thread-38end
		// Thread-39end
		// Thread-32end
		// Thread-33end
		// Thread-36end
		// Thread-37end
		// ConcurrentLinkedQueue size:9000
		
		/**
		 * ConcurrentLinkedQueue size 等于9000  说明多线程操作造没有造成数据丢失或者数据没有取到的问题,或者被覆盖了,线程安全
		 */
	}

	static class MyThreadQueueAdd extends Thread {
		Queue<String> queue;

		MyThreadQueueAdd(Queue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				queue.add(Thread.currentThread().getName() + i);
				// 可以打印出来,没有 false
				//System.out.println(queue.add(Thread.currentThread().getName()+ i));
			}
			System.out.println(this.getName() + "end");
		}
	}
	
	static class MyThreadQueuePull extends Thread {
		Queue<String> queue;

		MyThreadQueuePull(Queue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				queue.poll();
				// 可以打印出来,没有null
				// System.out.println(queue.poll());
			}
			System.out.println(this.getName() + "end");
		}
	}
}
