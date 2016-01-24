package org.dbyz.frameworks.activemq.demo02;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

/**
 * 消息消费者简单封装
 *
 * @ClassName: MQConsumer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class MQConsumer extends ActiveMQBase {
	/**
	 * 取出消息的等待时间(毫秒)
	 */
	private static final Long WAIT_TIME = 1000L/2;
	/** 内部消费者实现 */
	private MessageConsumer consumer;

	/**
	 * 初始化
	 * 
	 * @param queueName
	 * @throws JMSException
	 */
	public MQConsumer(String queueName) throws JMSException {
		super(queueName);
		consumer = session.createConsumer(queue);
	}

	/**
	 * 接收一条消息
	 * 
	 * @Title: receive
	 * @param @param clazz
	 * @param @return
	 * @param @throws JMSException
	 * @return: T
	 * @since V1.0
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T receive(Class<T> clazz)
			throws JMSException {
		T resule = null ;
		// 接收消息(最多等待 WAIT_TIME 毫秒),为 0 时候表示不超时,一直等待直到接收到一条消息
		Message  message = consumer.receive(WAIT_TIME);
		if(message!= null ){
			resule = (T) ((ObjectMessage) message).getObject();
			// 提交session确认消息接收
			session.commit();
		}
		return resule;
	}
}
