package org.dbyz.frameworks.rabbitmq.demo2;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 消息消费者简单封装
 *
 * @ClassName: MQConsumer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class MQConsumer extends RabbitBase {
	/**
	 * 取出消息的等待时间(毫秒)
	 */
	private static final Long WAIT_TIME = 1000L/2;
	/** 内部消费者实现 */
	private QueueingConsumer consumer;

	/**
	 * 初始化
	 * 
	 * @param queueName
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public MQConsumer(String queueName) throws IOException, TimeoutException {
		super(queueName);
		// 创建消费者
		consumer = new QueueingConsumer(channel);
		// 让消费者注册到通道上
		channel.basicConsume(queueName, true, consumer);
	}

	/**
	 * 接收下一条消息
	 * 
	 * @Title: receive
	 * @param @param clazz
	 * @param @return
	 * @param @throws IOException
	 * @param @throws ShutdownSignalException
	 * @param @throws ConsumerCancelledException
	 * @param @throws InterruptedException
	 * @return: T
	 * @since V1.0
	 */
	public <T extends Serializable> T receive(Class<T> clazz)
			throws IOException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		
		// 接收消息(最多等待 WAIT_TIME 毫秒),为 0 时候表示不超时,一直等待直到接收到一条消息
		Delivery delivery = consumer.nextDelivery(WAIT_TIME);
		return RabbitBase.deserialize(delivery.getBody(), clazz);
	}
}
