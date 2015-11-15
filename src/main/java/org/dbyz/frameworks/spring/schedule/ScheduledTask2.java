package org.dbyz.frameworks.spring.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask2 {
	static int a = 1;

	@Scheduled(cron = "0/1 * * * * ?")
	public void t1() {
		System.out.println(Thread.currentThread().getName() + "  static a="
				+ a++ + this.getClass().getSimpleName() + ".t1()");
	}

	@Scheduled(cron = "0/2 * * * * ?")
	public void t2() {
		System.out.println(Thread.currentThread().getName() + "  static a="
				+ a++ + this.getClass().getSimpleName() + ".t2()");
	}

	@Scheduled(cron = "0/4 * * * * ?")
	public void t3() throws InterruptedException {
		Thread.sleep(500 * a);
		System.out.println(Thread.currentThread().getName() + "  static a="
				+ a++ + this.getClass().getSimpleName() + ".t3()");
	}
}