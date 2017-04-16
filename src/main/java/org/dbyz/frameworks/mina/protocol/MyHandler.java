package org.dbyz.frameworks.mina.protocol;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyHandler extends IoHandlerAdapter{
	public static final boolean SERVER = true;
	public static final boolean CLIENT = false;
	private boolean server = false;
	
	public MyHandler(boolean server) {
		this.server = server;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("Received: "+message);
		if(this.server){
			session.write("Hello, I am Server!");
		}
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("Session Closed:" + session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		if (session.isConnected()) {
			session.closeNow();
		}
	}
}