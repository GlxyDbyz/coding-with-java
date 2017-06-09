package org.dbyz.frameworks.tio;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.bouncycastle.jce.provider.JDKMessageDigest.MD5;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;

/**
 * hello world版中服务器端和客户端的编码解码算法是一样的，所以抽象一个公共的父类出来
 * @author tanyaowu 
 *
 */
public abstract class HelloAbsAioHandler implements AioHandler<Object, HelloPacket, Object>
{
    private static MessageDigest MD5Digest = null;
    static{
        try {MD5Digest = MD5.getInstance("MD5");} catch (NoSuchAlgorithmException e) {}
    }
    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体 + 消息尾
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     * 消息尾结构：   4个字节+存储消息尾的长度+byte[]
     */
    @Override
    public ByteBuffer encode(HelloPacket packet, GroupContext<Object, HelloPacket, Object> groupContext, ChannelContext<Object, HelloPacket, Object> channelContext)
    {
        
        byte[] body = packet.getBody();
        int bodyLen = 0;
        if (body != null)
        {
            bodyLen = body.length;
        }
 
        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = HelloPacket.HEADER_LENGHT + bodyLen + HelloPacket.TAIL_LENGHT;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(groupContext.getByteOrder());
 
        //写入消息头----消息头的内容就是消息体的长度
        buffer.putInt(bodyLen);
 
        //写入消息体
        if (body != null)
        {
            buffer.put(body);
            // MD5验证加上消息尾
            
            byte[] md5 =  MD5Digest.digest(body);
            buffer.putInt(md5.length);
            buffer.put(md5);
        }
        return buffer;
    }
 
    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public HelloPacket decode(ByteBuffer buffer, ChannelContext<Object, HelloPacket, Object> channelContext) throws AioDecodeException
    {
        int readableLength = buffer.limit() - buffer.position();
        //收到的数据组不了业务包，则返回null以告诉框架数据不够
        if (readableLength < HelloPacket.HEADER_LENGHT)
        {
            return null;
        }
 
        //读取消息体的长度
        int bodyLength = buffer.getInt();
 
        //数据不正确，则抛出AioDecodeException异常
        if (bodyLength < 0)
        {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }
 
        //计算本次需要的数据长度
        int neededLength = HelloPacket.HEADER_LENGHT + bodyLength;
        //收到的数据是否足够组包
        int isDataEnough = readableLength - neededLength;
        
        // 不够消息体长度(剩下的buffe组不了消息体)
        if (isDataEnough < 0)
        {
            return null;
        } else  //组包成功
        {
            HelloPacket imPacket = new HelloPacket();
            if (bodyLength > 0)
            {
                byte[] body = new byte[bodyLength];
                buffer.get(body);
                
                // 验证
                int md5Len = buffer.getInt();
                byte[] md5 = new byte[md5Len];
                buffer.get(md5);
                if(Arrays.equals(MD5Digest.digest(body), md5)){
                    imPacket.setBody(body);
                }else{
                    return null;
                }
            }
            return imPacket;
        }
    }
}