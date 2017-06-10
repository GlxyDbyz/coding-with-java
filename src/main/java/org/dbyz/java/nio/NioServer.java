package org.dbyz.java.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings({ "unchecked" })
public class NioServer {

	ServerSocketChannel serverChannel;

	ServerSocket serverSocket;

	public final int port;

	private Selector selector;

	ByteBuffer buffer = ByteBuffer.allocate(1024);

	NioServer(final int port) {

		this.port = port;

	}

	void init() throws Exception {

		// 创建 ServerSocketChannel、ServerSocket

		serverChannel = ServerSocketChannel.open();

		serverSocket = serverChannel.socket();

		serverSocket.bind(new InetSocketAddress(port));

		// 设置通道为非阻塞模式

		serverChannel.configureBlocking(false);

		// 开启通道选择器，并注册 ServerSocketChannel

		selector = Selector.open();

		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

	}

	void go() throws Exception {

		while (true) {

			int num = selector.select();

			if (num <= 0)

				continue;

			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

			while (keyIter.hasNext()) {

				final SelectionKey key = keyIter.next();

				// 接收一个Socket连接

				// key.isAcceptable（）如果为true，说明channnel支持accept（）,也就是说明是一个ServerSocketChannel

				if (key.isAcceptable()) {

					SocketChannel clientChannel = serverChannel.accept();

					if (clientChannel != null) {

						clientChannel.configureBlocking(false);

						clientChannel.register(selector, SelectionKey.OP_READ

						| SelectionKey.OP_WRITE);

					}

				}

				// 如果isReadable()为true,说明是一个SocketChannel

				if (key.isReadable()) {

					String requestContent = read(key);

					// 业务处理

					// responseContent=doSomthing(requestContent);

					write(key, "ok" /* responseContent */);

				}

				keyIter.remove();

			}

		}

	}

	// 从通道读取数据

	String read(SelectionKey key) throws Exception {

		SocketChannel socketChannel = (SocketChannel) key.channel();

		buffer.clear();// 这一步必须有

		int len = 0;

		StringBuffer str = new StringBuffer();

		while ((len = socketChannel.read(buffer)) > 0) {

			byte[] bs = buffer.array();

			String block = new String(bs, 0, len);

			System.out.println("Server read: " + block);

			str.append(block);

		}

		buffer.clear();

		return str.toString();

	}

	// 写数据到通道

	void write(SelectionKey key, String str) throws Exception {

		SocketChannel socketChannel = (SocketChannel) key.channel();

		buffer.clear();

		buffer.put(str.getBytes());

		buffer.flip();// 这一步必须有

		socketChannel.write(buffer);

	}

	public static void main(String[] args) throws Exception {

		final int port = 10000;

		NioServer server = new NioServer(port);

		server.init();

		// /========================================================

		// 接下来模拟3个Client并发访问服务器

		int poolsize = 3;

		ExecutorService pool = Executors.newFixedThreadPool(poolsize);

		Collection<Callable<Object>> tasks = new ArrayList<Callable<Object>>(10);

		final String clientname = "clientThread";

		for (int i = 0; i < poolsize; i++) {

			final int n = i;

			// 若每一个Client都保持使用BIO方式发送数据到Server，并读取数据。

			tasks.add(new Callable<Object>() {

				@Override
				public Object call() throws Exception {

					Socket socket = new Socket("127.0.0.1", port);

					final InputStream input = socket.getInputStream();

					final OutputStream out = socket.getOutputStream();

					final String clientname_n = clientname + "_" + n;

					// BIO读取数据线程

					new Thread(clientname_n + "_read") {

						@Override
						public void run() {

							byte[] bs = new byte[1024];

							while (true) {

								try {

									Thread.sleep(1000);

								} catch (InterruptedException e) {

									e.printStackTrace();

								}

								int len = 0;

								try {

									while ((len = input.read(bs)) != -1) {

										System.out.println("Clinet thread "

										+ Thread.currentThread()

										.getName() + " read: "

										+ new String(bs, 0, len));

									}

								} catch (IOException e) {

									e.printStackTrace();

								}

							}

						}

					}.start();

					// BIO写数据线程

					new Thread(clientname_n + "_write") {
						@Override
						public void run() {
							int a = 0;
							while (true) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								String str = Thread.currentThread().getName()
								+ " hello, " + a;
								try {
									out.write(str.getBytes());
									a++;
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					return null;
				}
			});
		}
		pool.invokeAll(tasks);
		server.go();
	}

}