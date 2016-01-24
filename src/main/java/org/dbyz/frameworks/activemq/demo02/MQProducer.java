package org.dbyz.frameworks.activemq.demo02;

import java.io.Serializable;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;

/**
 * 消息生产者简单封装
 *
 * @ClassName: MQProducer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class MQProducer extends ActiveMQBase {
	private MessageProducer producer;

	/**
	 * 初始化
	 * 
	 * @param queueName
	 * @throws JMSException
	 */
	public MQProducer(String queueName) throws JMSException {
		super(queueName);
		producer = session.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * 发消息
	 * 
	 * @Title: send
	 * @param @param msg
	 * @param @throws JMSException
	 * @return: void
	 * @since V1.0
	 */
	public <T extends Serializable> void send(T msg) throws JMSException {
		// 发送一条消息
		producer.send(session.createObjectMessage(msg));
		// (对条/单条)消息发送完了,不要忘了提交session
		session.commit();
		// 不要发送一条消息就关闭session资源
		//session.close();
	}
}
