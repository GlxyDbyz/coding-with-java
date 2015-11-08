package org.dbyz.java.thread.simple;

/**
 * 守护线程例子
 *
 * @ClassName: TestDaemon
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class DaemonTest {
	public static void main(String[] args) throws InterruptedException {
		Thread daemon = new Thread(new SimpleRunable());
		daemon.setDaemon(true);// 设置为创建他的线程的守护线程(此处为主线程)
		System.out.println(daemon.isDaemon());
		daemon.start();
		// 你会发现 daemon 线程里面的输出没有打印出来,把下面的注释去掉就会打印出来了
		// Thread.sleep(1);
		
		// 解释:
		// 因为守护线程随着主线程的退出而退出(其实他只需要给他多
		// 1毫秒的时间哦,或者更少),而普通的线程则不会随着主线程的退出而退出
	}
}
