package org.dbyz.frameworks.activemq.demo02;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试类
 *
 * @ClassName: TestIt
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)/*按方法字母顺序执行*/
public class TestIt {
	/**
	 * 发送和接收String对象
	 * 
	 * @Title: sendStringMsg
	 * @param @throws Exception
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1SendStringMsg() throws Exception {
		MQProducer sender = new MQProducer("QUEUE_NQME");
		sender.send("hello");
		sender.close();
	}

	@Test
	public void test2RecivStringMsg() throws Exception {
		MQConsumer consumer = new MQConsumer("QUEUE_NQME");
		System.out.println(consumer.receive(String.class));
		consumer.close();
	}

	/**
	 * 发送和接收自定义类对象
	 * 
	 * @Title: sendTestBean
	 * @param @throws Exception    
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test3SendTestBean() throws Exception {
		MQProducer sender = new MQProducer("QUEUE_NQME");
		TestBean bean = new TestBean("dbyz", 23);
		sender.send(bean);
		sender.close();
	}

	@Test
	public void test4RecivTestBean() throws Exception {
		MQConsumer consumer = new MQConsumer("QUEUE_NQME");
		System.out.println(consumer.receive(TestBean.class));
		// TestBean [name=dbyz, age=23]
		consumer.close();
	}

}
