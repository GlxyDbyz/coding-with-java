package org.dbyz.frameworks.rabbitmq.demo1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 消息消费者
 *
 * @ClassName: Consumer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Consumer {
	public static void main(String[] args) throws IOException,
			TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		
		// 1.创建并初始化连接工厂(地址,端口,用户名和密码)
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		
		// 2.新建连接(异常直接抛出)
		Connection conn = factory.newConnection();
		
		// 3.在连接中创建通道
		Channel channel = conn.createChannel();
		
		// 4.创建消息队列(指定名称的消息队列不存在就创建它,已经存在则什么也不做,防止生产者还没有创建指定名称的消息队列导致报错)
		channel.queueDeclare("HELLO_RABBITMQ", false, false, false, null);
		
		// 5.创建队列消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		// 6.为消费者指定消息队列(此处返回的String服务由于区分是消费者标签)
		channel.basicConsume("HELLO_RABBITMQ", true, consumer);
		
		// 7.1 从消息队列取出一条消息,没有则一直等待(内部是阻塞队列)
		Delivery delivery = consumer.nextDelivery();
		
		// 7.2 从消息队列取出一条消息,最多等待10秒
		// Delivery delivery = consumer.nextDelivery(10);
		
		// 8.还原消息(当然消费者知道自己接收到的消息是什么类型)
		String message = new String(delivery.getBody());
		System.out.println("Received a message:" + message);
		
		//9. 关闭频道和连接
		channel.close();
		conn.close();
	}
}
