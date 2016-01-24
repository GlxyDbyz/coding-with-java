package org.dbyz.java.thread.JUC;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 链表实现的阻塞队列 
 * 
 * @ClassName: LinkedBlockingQueueTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class LinkedBlockingQueueTest {

	public static void main(String[] args) {
		// 不指定大小的情况下,这个队列是可以'无限'扩展的(内存允许范围最大值可以是Integer.MAX_VALUE )
		// LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		
		// 当LinkedBlockingQueue像下面这样,指定了大小,那么他就和ArrayBlockingQueue几乎一样了
		//其他的操作和ArrayBlockingQueue是类似的
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(4);
		new Thread(() -> {
			try {
				for (int i = 1; i <= 6; i++) {
					if (i <= 5) {
						queue.put(i);
						System.out.println("put " + i);
						// put 也是阻塞方法,列满时线程进入阻塞状态,等待队列不为空
			} else {
				Thread.sleep(1000);
				System.out.println("take阻塞");
				Thread.sleep(1000);
				queue.put(i);
				System.out.println("put " + i);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}		).start();

		new Thread(() -> {
			for (int i = 1; i <= 6; i++)
				try {
					Thread.sleep(100);
					// take 是阻塞方法,当队列为空时线程进入阻塞
				System.out.println("take " + queue.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}	).start();

		// put 1
		// put 2
		// put 3
		// put 4   size = 4  队列满,put阻塞
		// take 1  size = 3  继续执行 put 5
		// put 5
		// take 2
		// take 3
		// take 4
		// take 5  size = 0 队列空,take阻塞
		// take阻塞
		// put 6   size = 1 继续take  
		// take 6
	}

}
