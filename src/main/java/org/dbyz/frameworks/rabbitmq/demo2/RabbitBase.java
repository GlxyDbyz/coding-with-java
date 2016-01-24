package org.dbyz.frameworks.rabbitmq.demo2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ 基础类封装
 *
 * @ClassName: RabbitBase
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public abstract class RabbitBase {
	private static final String HOST = "127.0.0.1";
	private static final Integer PORT = 5672;
	private static final String USERNAME = "guest";
	private static final String PWD = "guest";

	/** 公用的连接对象 */
	protected Connection connection;
	/** 公用的通道对象 */
	protected Channel channel;
	/** 队列名称 */
	protected String queueName;

	/**
	 * 初始化
	 * 
	 * @param queueName
	 *            队列名称
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public RabbitBase(String queueName) throws IOException, TimeoutException {
		this.queueName = queueName;
		// 初始化连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PWD);
		// 新建连接和通道
		connection = factory.newConnection();
		channel = connection.createChannel();
		// 创建简单消息队列
		channel.queueDeclare(queueName, false, false, false, null);
	}

	/**
	 * 关闭
	 * 
	 * @Title: close
	 * @param @throws IOException
	 * @param @throws TimeoutException
	 * @return: void
	 * @since V1.0
	 */
	public void close() throws IOException, TimeoutException {
		channel.close();
		connection.close();
	}

	/**
	 * 序列化某个对象
	 * 
	 * @Title: serialize
	 * @param @param t
	 * @param @return
	 * @return: byte[]
	 * @since V1.0
	 */
	public static <T extends Serializable> byte[] serialize(T t) {
		if (t == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(t);
			oos.flush();
		} catch (IOException ex) {
			throw new IllegalArgumentException(
					"Failed to serialize object of type: " + t.getClass(), ex);
		}
		return baos.toByteArray();
	}

	/**
	 * 反序列化某个对象
	 * 
	 * @Title: deserialize
	 * @param @param bytes
	 * @param @param t
	 * @param @return
	 * @return: T
	 * @since V1.0
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] bytes,
			Class<T> clazz) {
		if (bytes == null) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(bytes));
			return (T) ois.readObject();
		} catch (IOException ex) {
			throw new IllegalArgumentException("反虚拟化失败", ex);
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("反虚拟化失败", ex);
		}
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
