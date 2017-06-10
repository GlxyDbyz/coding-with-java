package org.dbyz.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOEchoServer {

	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8000;

	public static void main(String[] args) {
		selector();
	}

	public static void selector() {
		Selector selector = null;
		ServerSocketChannel ssc = null;
		try {
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(PORT));
			ssc.configureBlocking(false);
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while (selector.select()>0) {
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					if (key.isAcceptable()) {
						ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
						SocketChannel client = ssChannel.accept();
						System.out.println("client:"+client);
						client.configureBlocking(false);
						client.register(key.selector(), SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
					}
					if (key.isReadable()) {
						SocketChannel client = (SocketChannel) key.channel();
						  ByteBuffer buffer = ByteBuffer.allocate(100);  
						  client.read(buffer);  
			              byte[] data = buffer.array();  
			              String msg = new String(data).trim();
			              System.out.println("客户端：" + msg);
			              ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
			              // 将消息回送给客户端  
			              client.write(outBuffer);
					}
					if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buf = (ByteBuffer) key.attachment();
						buf.flip();
						client.write(buf);
						buf.compact();
					}
					iter.remove();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selector != null) {
					selector.close();
				}
				if (ssc != null) {
					ssc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static class Client {
		public static void main(String[] args) throws Exception {
			SocketChannel channel = null;
			channel = SocketChannel.open();
			// 设置通道为非阻塞
			channel.configureBlocking(false);
			// 获得一个通道管理器
			Selector selector = Selector.open();
			// 客户端连接服务器,其实方法执行并没有实现连接
			channel.connect(new InetSocketAddress("localhost", PORT));
			channel.register(selector, SelectionKey.OP_CONNECT);
			while (selector.select() > 0) {
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
				while (ite.hasNext()) {
					SelectionKey selectKey = (SelectionKey) ite.next();
					ite.remove();
					if (selectKey.isConnectable()) {
						/* 设置成非阻塞 */
						channel.configureBlocking(false);
						if(channel.isConnectionPending()){  
			                channel.finishConnect();
			            }  
						/* 给服务端发送信息 */
						channel.write(ByteBuffer.wrap(new String("你好，我是客户端!").getBytes()));
						/* 注册读事件 */
						channel.register(selector, SelectionKey.OP_READ);
					} else if (selectKey.isReadable()) {
						// 创建读取的缓冲区
						ByteBuffer buffer = ByteBuffer.allocate(100);
						channel.read(buffer);
						byte[] data = buffer.array();
						String msg = new String(data).trim();
						System.out.println(msg);
						ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
						// 将消息回送给服务端
						channel.write(outBuffer);
					}
				}
			}
			Thread.sleep(100000);
		}
	}
}