package org.dbyz.frameworks.mina.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyProtocolDecoder implements ProtocolDecoder {

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		Object message = null;
		byte[] bytes = in.array();
		in.flip();
		MySerializer serializer = new MySerializer();
		message = serializer.deserialize(bytes);
		out.write(message);
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}

	@Override
	public void dispose(IoSession session) throws Exception {
	}

}
