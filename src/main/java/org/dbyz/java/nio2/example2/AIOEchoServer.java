package org.dbyz.java.nio2.example2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AIOEchoServer {

	public final static int PORT = 8000;
	public final static String IP = "localhost";

	private AsynchronousServerSocketChannel server = null;

	public AIOEchoServer() {
		try {
			// 同样是利用工厂方法产生一个通道，异步通道 AsynchronousServerSocketChannel
			server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(IP, PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		// 注册事件和事件完成后的处理器，这个CompletionHandler就是事件完成后的处理器
		server.accept(null,new CompletionHandler<AsynchronousSocketChannel, Object>() {
			final ByteBuffer buffer = ByteBuffer.allocate(1024);
			@Override
			public void completed(AsynchronousSocketChannel result,Object attachment) {
				System.out.println(Thread.currentThread().getName());
				Future<Integer> writeResult = null;
				try {
					buffer.clear();
					result.read(buffer).get(10, TimeUnit.SECONDS);
					System.out.println("In server: " + new String(buffer.array()));
					// 将数据写回客户端
					buffer.flip();
					writeResult = result.write(buffer);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					server.accept(null, this);
					try {
						writeResult.get();
						result.close();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
			}
		});
	}

	public static void main(String[] args) {
		new AIOEchoServer().start();
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}