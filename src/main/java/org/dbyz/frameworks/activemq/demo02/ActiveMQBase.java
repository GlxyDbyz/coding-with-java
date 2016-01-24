package org.dbyz.frameworks.activemq.demo02;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * RabbitMQ 基础类封装
 *
 * @ClassName: RabbitBase
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public abstract class ActiveMQBase {
	private static final String HOST = "127.0.0.1";
	private static final Integer PORT = 61616;
	private static final String USERNAME = "admin";
	private static final String PWD = "admin";

	/** 公用的连接对象 */
	protected Connection connection;
	/** 公用的通道对象 */
	protected Session session;
	/** 公用queue */
	protected Queue queue;
	/** 队列名称 */
	protected String queueName;

	/**
	 * 初始化
	 * 
	 * @param queueName
	 * @throws JMSException
	 */
	public ActiveMQBase(String queueName) throws JMSException {
		this.queueName = queueName;
		// 初始化连接工厂
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				USERNAME, PWD, "tcp://" + HOST + ":" + PORT);
		// 新建连接
		connection = factory.createConnection();
		// 开启连接
		connection.start();
		// 创建自动ACK的session
		session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		// 创建队列
		queue = session.createQueue(queueName);
	}

	/**
	 * 关闭
	 * 
	 * @Title: close
	 * @param @throws JMSException
	 * @return: void
	 * @since V1.0
	 */
	public void close() throws JMSException {
		session.close();
		connection.close();
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
