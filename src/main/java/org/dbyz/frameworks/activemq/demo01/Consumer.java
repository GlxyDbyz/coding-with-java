package org.dbyz.frameworks.activemq.demo01;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息消费者
 *
 * @ClassName: Consumer
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class Consumer {

	public static void main(String[] args) throws JMSException, InterruptedException {
		// 1.创建连接工厂（用户名、密码和消息地址）
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				"admin", "admin", "tcp://localhost:61616");
		
		// 2.创建连接
		Connection conn = factory.createConnection();
		
		// 3.开启连接
		conn.start();
		
		// 4.创建session
		Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		
		// 5.创建消息队列(防止无Queue报错)
		Queue queue = session.createQueue("First_ActiveMQ_Queue");
		
		// 6.创建消费者
		MessageConsumer  consumer = session.createConsumer(queue);
		
		// 7.接收消息
		consumer.setMessageListener((Message msg)->{
			TextMessage message = (TextMessage)msg;
			try {
				System.out.println("Get message:"+message.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		// 等待接收消息
		Thread.sleep(1000);
		
		// 8.提交session关闭连接
		session.commit();
		conn.close();
	}
}
