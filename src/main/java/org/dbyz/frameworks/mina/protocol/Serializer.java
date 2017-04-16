package org.dbyz.frameworks.mina.protocol;
/**
 * 序列化接口类
 */
public interface Serializer {
	
	public byte[] serialize(Object obj) throws Exception;
	
	public Object deserialize(byte[] bytes) throws Exception;
}
