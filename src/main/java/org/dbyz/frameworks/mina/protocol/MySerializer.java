package org.dbyz.frameworks.mina.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MySerializer implements Serializer {

	@Override
	public byte[] serialize(Object obj) throws Exception {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteArrayOut);
        objectOut.writeObject(obj);
        byte[] b = byteArrayOut.toByteArray();
        objectOut.close();
        byteArrayOut.close();
        return b;
	}

	@Override
	public Object deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objectIn = new ObjectInputStream(byteArrayIn);
        Object obj = objectIn.readObject();
        objectIn.close();
        byteArrayIn.close();
        return obj;
	}

}
