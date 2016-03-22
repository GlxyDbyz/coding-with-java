package org.dbyz.frameworks.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTest {

	public static void main(String[] args) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String job_name = "firstJob";
		try {
			System.out.println(DateFormat.format(new Date()) + "【系统启动】");
			QuartzManager.addJob(job_name, MyJob.class, "0/2 * * * * ?"); // 每2秒钟执行一次

			Thread.sleep(10000);
			System.out.println("【修改时间】");
			QuartzManager.modifyJobTime(job_name, "0/5 * * * * ?");
			
			Thread.sleep(20000);
			System.out.println("【移除定时】");
			QuartzManager.removeJob(job_name);
			
			Thread.sleep(10000);
			System.out.println("【添加定时任务】");
			QuartzManager.addJob(job_name, MyJob.class, "0/1 * * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}