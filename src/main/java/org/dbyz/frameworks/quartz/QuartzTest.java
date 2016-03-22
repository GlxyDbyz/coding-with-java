package org.dbyz.frameworks.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 简单测试
 */
public class QuartzTest {
	public static void main(String[] args) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String job_name = "firstJob";
		try {
			System.out.println(DateFormat.format(new Date()) + "【测试开始】");
			Thread.sleep(1000);
			
			System.out.println("【添加一个JOB】");
			QuartzManager.addJob(job_name, MyJob.class, "0/1 * * * * ?"); // 每2秒钟执行一次
			Thread.sleep(10000);

			System.out.println("【修改定时】");
			QuartzManager.modifyJobTime(job_name, "0/5 * * * * ?");
			Thread.sleep(20000);
			
			System.out.println("【移除JOB】");
			QuartzManager.removeJob(job_name);
			Thread.sleep(5000);
			
			System.out.println("【添加一个JOB】");
			QuartzManager.addJob(job_name, MyJob.class, "0/1 * * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}