package org.dbyz.frameworks.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.dbyz.frameworks.mina.protocol.MyHandler;
import org.dbyz.frameworks.mina.protocol.MyProtocolCodecFactory;

public class MinaServer {
	
	public void init() throws Exception{
		SocketAcceptor acceptor = new NioSocketAcceptor();
		//设置解析器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new MyProtocolCodecFactory()));
		acceptor.setHandler(new MyHandler(true));
		acceptor.bind(new InetSocketAddress(8080));
	}
	
	public MinaServer(){
	}
	
	public static void main(String[] args) throws Exception {
		new MinaServer().init();
		System.out.println("Server start");
	}
}