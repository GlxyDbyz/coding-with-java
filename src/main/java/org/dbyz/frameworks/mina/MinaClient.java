package org.dbyz.frameworks.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.dbyz.frameworks.mina.protocol.MyHandler;
import org.dbyz.frameworks.mina.protocol.MyProtocolCodecFactory;

public class MinaClient {
	private ConnectFuture cf;
	private SocketConnector socketConnector;

	public MinaClient() {
		socketConnector = new NioSocketConnector();
		socketConnector.getSessionConfig().setKeepAlive(true);
		socketConnector.setConnectTimeoutMillis(5 * 1000);
		socketConnector.getSessionConfig().setTcpNoDelay(true);
		socketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyProtocolCodecFactory()));
		socketConnector.setHandler(new MyHandler(false));
		InetSocketAddress addr = new InetSocketAddress("localhost", 8080);
		cf = socketConnector.connect(addr);
		cf.awaitUninterruptibly();
	}

	public WriteFuture sendMessage(final String msg) {
		try {
			IoSession session = cf.getSession();
			return session.write(msg);
		} catch (Exception e) {
			if (cf.isConnected()) {
				cf.getSession().closeNow();
			}
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException {
		MinaClient myClient = new MinaClient();
		for (int i = 0; i < 3; i++) {
			WriteFuture writeFuture = myClient.sendMessage("Hello, I am Client!" + i);
			while(!writeFuture.isDone()){
				Thread.sleep(200);
			}
		}
		//myClient.getSocketConnector().dispose();
		//System.exit(0);
	}

	public SocketConnector getSocketConnector() {
		return socketConnector;
	}

	public void setSocketConnector(SocketConnector socketConnector) {
		this.socketConnector = socketConnector;
	}
}
