package org.dbyz.java.thread.JUC;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数组实现的阻塞队列
 * (既然是数组实现的,则队列创建之后大小就不可修改)
 * 
 * @ClassName: ArrayBlockingQueueTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class ArrayBlockingQueueTest {

	/**
	 * 启动2条线程,一条线程put 一条线程take ,由于只能放置4个,当放置队列大小为4的时候线程一阻塞,
	 * 大概100毫秒后,线程二take数据1,线程一继续put(5),线程2继续取出全部数据,但是第五次的时候
	 * 没有数据可取了,take 阻塞,差不多两秒之后线程一put(6),然后线程二 take数据6(有没有像生产者和消费者模式呢?)
	 * (复制线程二代码,再启动一条take线程,运行结果是一样的,只是取出数据的线程是线程1和2轮回的,此处带过)
	 */
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);
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
