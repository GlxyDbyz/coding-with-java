package org.dbyz.java.thread.JUC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池简单例子
 * 
 * @ClassName: ThreadPoolExecutorTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ThreadPoolExecutorTest extends ThreadPoolExecutor {
	// 线程池是JUC框架提供的高度可用的线程资源管理器,是多线程处理必备的工具类

	/**
	 * 简单线程池构造方法
	 * 
	 * @param corePoolSize    初始化线程池大小
	 * @param maximumPoolSize 线程池最大线程数(当任务量很大,已有线程都在运行之中,且线程任务队列也已满无法再放入线程的时候,会扩展线程池线程数量,当到达最大数时候,任然有任务到来,且任然无法放入队列,则会报 java.util.concurrent.RejectedExecutionException)
	 * @param keepAliveTime   超出初始化大小之后,超出线程如果不活动了,它们的存活时间
	 * @param unit            存活时间单位(eg:TimeUnit.MINUTES)
	 * @param workQueue       线程任务队列(可以设置大小,也可以不限制大小但是容易造成内存溢出)
	 */
	public ThreadPoolExecutorTest(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public static void main(String[] args) throws InterruptedException {
		// 工作线程队列
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(4);
		// 新建线程池
		ThreadPoolExecutorTest myPool = new ThreadPoolExecutorTest(5, 10, 1,
				TimeUnit.MINUTES,workQueue);
		System.out.println("ActiveCount: "+myPool.getActiveCount());
		for(int i=0;i<10;i++){
			// 其实这样是把线程任务放入了线程工作队列
			myPool.execute(new MyRunnable(i));
			// 然后线程池不断从队列取出可用的线程任务,并执行他们
		}
		// 如果任务还没有被运行完,workQueue.size() 应该是 >0 的
		System.out.println("workQueue size: " + workQueue.size());
		
		
		while(myPool.getActiveCount()>0){
			Thread.sleep(10);
			// 还有任务没有执行完成
			System.out.println("ActiveCount: "+myPool.getActiveCount());
		}
		// 执行完成则退出线程池,否则线程池作为系统资源不会主动退出
		myPool.shutdown();
	}

	static class MyRunnable implements Runnable {
		private int i;
		public MyRunnable(int i) {
			super();
			this.i = i;
		}
		public void run() {
			try {
				Thread.sleep(100);
				System.out.println("I am Runnable "+ i +" run in a pool");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}

	}
}
// ActiveCount: 0                          // 刚刚初始化活动线程为0
// workQueue size: 4                       // 队列放入了4个线程任务,其他6个已经被线程池运行(因为我设置了队列最大只能放置4个线程,所以线程池现在有6个线程在同时运行)
// ActiveCount: 6                          // 活动线程为6
// ActiveCount: 6                          // 随着任务完成,活动线程数量也随之变化
// ActiveCount: 6
// ActiveCount: 6
// ActiveCount: 6
// ActiveCount: 6
// I am Runnable 2 run in a pool
// ActiveCount: 6
// I am Runnable 9 run in a pool
// I am Runnable 4 run in a pool
// I am Runnable 1 run in a pool
// I am Runnable 0 run in a pool
// I am Runnable 3 run in a pool
// ActiveCount: 4
// ActiveCount: 4
// ActiveCount: 4
// ActiveCount: 4
// ActiveCount: 4
// ActiveCount: 4
// ActiveCount: 4
// I am Runnable 5 run in a pool
// I am Runnable 7 run in a pool
// I am Runnable 8 run in a pool
// ActiveCount: 1
// I am Runnable 6 run in a pool
// ActiveCount: 0                      // 活动线程为0时候,线程池被我强制关闭
