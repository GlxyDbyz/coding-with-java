package org.dbyz.java.thread.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
/**
 * Future 的使用
 * (Future支持异步返回，在知道某件事情需要长时间才能完成的时候无需等待其完成)
 * 
 * @ClassName: FutureTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class FutureTest {
	private static ExecutorService pool =  Executors.newCachedThreadPool();
	@Test
	public void test1_get() throws InterruptedException, ExecutionException{
		System.out.println("1.Submit your task");
		Future<String> future = pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {Thread.sleep(1000);} catch (Exception e) {}
				return "hello";
			}
		});
		
		System.out.println("2.Do other things");
		try {Thread.sleep(2000);} catch (Exception e) {}
		
		System.out.println("3.Get your result when you need it :"+future.get());
	}
	
	@Test
	public void test2_isDone() throws InterruptedException, ExecutionException{
		System.out.println("1.Submit your task");
		Future<String> future = pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {Thread.sleep(1);} catch (Exception e) {}
				return "hello";
			}
		});
		
		while (!future.isDone()) {
			System.out.println("Do other things");
		}
		
		System.out.println("3.Task is Done, get your result:"+future.get());
	}
	
	@Test
	public void test3_cancle() throws InterruptedException, ExecutionException{
		System.out.println("1.Submit your task");
		Future<String> future = pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {Thread.sleep(2000);} catch (Exception e) {e.printStackTrace();}
				return "hello";
			}
		});
		
		System.out.println("2.Do other things");
		try {Thread.sleep(1000);} catch (Exception e) {}
		
		while (!future.isCancelled() && !future.isDone()) {
			System.out.println("3.Wait tooooo long , cancel the task ...");
//			future.cancel(true);
			future.cancel(false);
		}
		
		System.out.println("4.Can you get your result when task cancled ?");
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			// CancellationException
			e.printStackTrace();
		}
		
	}
}
