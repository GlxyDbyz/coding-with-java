package org.dbyz.java.nio2.example1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AIOClient {
	public static void main(String[] args) throws Exception {
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		client.connect(new InetSocketAddress("localhost", 8000)).get();
		client.write(ByteBuffer.wrap("你好我是客户端！".getBytes()));
		Thread.sleep(100000);
	}
}