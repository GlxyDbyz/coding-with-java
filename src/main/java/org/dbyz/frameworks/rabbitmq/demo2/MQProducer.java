package org.dbyz.frameworks.rabbitmq.demo2;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者简单封装
 *
 * @ClassName: MQProducer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class MQProducer extends RabbitBase {

	/**
	 * 初始化
	 * 
	 * @param queueName
	 *            队列名称
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public MQProducer(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}

	/**
	 * 发送消息
	 * 
	 * @Title: send
	 * @param @param t
	 * @param @throws IOException
	 * @return: void
	 * @since V1.0
	 */
	public <T extends Serializable> void send(T msg) throws IOException {
		channel.basicPublish("", queueName, null, RabbitBase.serialize(msg));
	}
}
