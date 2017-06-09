package org.dbyz.frameworks.tio;

import org.tio.core.intf.Packet;

public class HelloPacket extends Packet
{
    public static final int HEADER_LENGHT = 4;//消息头的长度
    public static final int TAIL_LENGHT = 4 + 32; //消息尾的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;
 
    /**
     * @return the body
     */
    public byte[] getBody()
    {
        return body;
    }
 
    /**
     * @param body the body to set
     */
    public void setBody(byte[] body)
    {
        this.body = body;
    }
}