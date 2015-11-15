package org.dbyz.frameworks.spring.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class TestIt {
	public static void main(String args[]) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-schedule.xml");
		// 你会看到这样配置的定时任务 Spring对定时任务的开启是使用一个线程对任务进行运行,这个线程来自一个单线程线程池
		// 如果一个任务阻塞了,那么所有的任务都会等待了
	}
}
