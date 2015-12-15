package org.dbyz.frameworks.activemq.demo01;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 *
 * @ClassName: Producer
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class Producer {

	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				"admin", "admin", "tcp://localhost:61616");
		
		// 2.创建连接
		Connection conn = factory.createConnection();
		
		// 3.开启连接
		conn.start();
		
		// 4.创建session
		Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		
		// 5.创建消息队列
		Queue queue = session.createQueue("First_ActiveMQ_Queue");
		
		// 6.创建生产者/设置不持久化消息
		MessageProducer producer = session.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		// 7.发送十条消息到队列
		for (int i = 1; i <= 10; i++) {
			String msg = "ActiveMQ message :" + i;
			TextMessage message = session.createTextMessage(msg);
			System.out.println("send " + msg);
			// 发送消息
			producer.send(message);
		}
		
		// 8.提交session关闭连接
		session.commit();
		conn.close();
	}
}
