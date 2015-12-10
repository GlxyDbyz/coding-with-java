package org.dbyz.frameworks.rabbitmq.demo1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 消息生产者
 *
 * @ClassName: Producer
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class Producer {
	public static void main(String[] args) throws IOException,
			TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		
		// 1.创建并初始化连接工厂(地址,端口,用户名和密码)
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(15672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		
		// 2.新建连接(异常直接抛出)
		Connection conn = factory.newConnection();
		
		// 3.在连接中创建频道
		Channel channel = conn.createChannel();
		
		// 4.为channel指定消息队列(没有就创建它)
		channel.queueDeclare("HELLO_RABBITMQ", false, false, false, null);
		
		// 5.向频道发送一条消息
		channel.basicPublish("", "HELLO_RABBITMQ", null,
				"hello I am a message from 127.0.0.1 (你好!)".getBytes());
		
		// 6.关闭频道和连接
		channel.close();
		conn.close();
	}
}
