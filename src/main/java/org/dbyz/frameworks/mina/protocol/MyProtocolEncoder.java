package org.dbyz.frameworks.mina.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyProtocolEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		MySerializer serializer = new MySerializer();
		byte[] src = serializer.serialize(message);
        IoBuffer buf = IoBuffer.allocate(src.length).setAutoExpand(true);
		buf.put(src);
        buf.flip();
        out.write(buf);
	}

	@Override
	public void dispose(IoSession session) throws Exception {
	}

}
