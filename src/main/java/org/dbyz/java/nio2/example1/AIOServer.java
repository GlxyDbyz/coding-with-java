package org.dbyz.java.nio2.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AIOServer {
	public final static int PORT = 8000;
	private AsynchronousServerSocketChannel server;

	public AIOServer() throws IOException {
		// 异步Socket监听端口
		server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
	}

	/**
	 * 使用accept()获取future方式
	 * 
	 * @Title: startWithFuture
	 * @param @throws InterruptedException
	 * @param @throws ExecutionException
	 * @param @throws TimeoutException
	 * @return: void
	 * @since V1.0
	 */
	public void startWithFuture() throws Exception {
		// 循环接收客户端请求
		while (true) {
			// accept() 阻塞方法直到有连接过来
			Future<AsynchronousSocketChannel> future = server.accept();
			AsynchronousSocketChannel channel = future.get();
			// 单个汉字在UTF-8中占用3个字节
			ByteBuffer readBuf = ByteBuffer.allocate(3);
			try {
				while (true) {
					Future<Integer> readFeature = channel.read(readBuf);
					// get 设置超时时间，超时断开
					Integer count = readFeature.get(10, TimeUnit.SECONDS);
					if (count == -1) {
						break;
					}
					// 转换读写模式
					readBuf.flip();
					System.out.println("从客户端获取消息： " + Charset.forName("UTF-8").decode(readBuf));
					readBuf.clear();
				}
			} catch (TimeoutException e) {
				try {
					e.printStackTrace();
					channel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void startWithCompletionHandler() throws Exception {
		server.accept(null, new MyCompletionHandler());
	}

	class MyCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
		@Override
		public void completed(AsynchronousSocketChannel channel, Object attachment) {
			try {
				// 单个汉字在UTF-8中占用3个字节
				final ByteBuffer buffer = ByteBuffer.allocate(3);
				long timeout = 10L;
				channel.read(buffer, timeout, TimeUnit.SECONDS, null,
						new CompletionHandler<Integer, Object>() {
							@Override
							public void completed(Integer result,Object attachment) {
								if (result == -1) {
									try {
										channel.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
									return;
								}
								buffer.flip();
								System.out.println("从客户端获取消息："+ Charset.forName("UTF-8").decode(buffer));
								buffer.clear();
								channel.read(buffer, timeout, TimeUnit.SECONDS,null, this);
							}

							@Override
							public void failed(Throwable exc, Object attachment) {
								exc.printStackTrace();
							}
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(Throwable exc, Object attachment) {
		}

	}

	public static void main(String args[]) throws Exception {
		//new AIOServer().startWithFuture();
	    new AIOServer().startWithCompletionHandler();
		Thread.sleep(100000);
	}
}