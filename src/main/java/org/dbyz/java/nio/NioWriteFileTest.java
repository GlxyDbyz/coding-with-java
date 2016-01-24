package org.dbyz.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio文件Copy简单例子
 *
 * @ClassName: NioWriteFileTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class NioWriteFileTest {
	public static void main(String[] args) throws IOException {
		// 随机文件读取对象
		RandomAccessFile src = new RandomAccessFile("C://test.txt", "rw");
		// 随机文件输出对象
		RandomAccessFile dest = new RandomAccessFile("D://test.txt", "rw");
		
		// 得到文件输入通道
		FileChannel srcChannel = src.getChannel();
		// 得到文件输出通道
		FileChannel destChannel = dest.getChannel();
		
		// 方法1 极其简单
		// destChannel.transferFrom(srcChannel, 0, srcChannel.size());
		// 方法2也是机器简单和方法1一致
		// srcChannel.transferTo(0, srcChannel.size(), destChannel);
		
		// 下面是方法3 普通操作流程
		
		// 创建大小为 1024 Bytes 的通道缓存
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 从通道读取数据写到缓存
		int bytesReaded = srcChannel.read(buffer);

		while (bytesReaded > -1) {
			// 转换buffer的位置和限制,让buffer从写入模式转换为读取模式,这样才可以从buffer读取数据
			buffer.flip();
			
			// 写入到输出通道
			destChannel.write(buffer);
			
			// 清除此缓冲区。将位置设置为 0，将限制设置为容量，并丢弃标记,让缓冲区可以继续写入
			buffer.clear();
			
			// or 除此缓冲区。将位置设置为 未读取数据大小，将限制设置为容量，并丢弃标记,让缓冲区可以继续写入
			// buffer.compact();//即:保留未读取的数据

			// 继续读取数据
			bytesReaded = srcChannel.read(buffer);
		}
		
		// 关闭文件
		src.close();
		dest.close();
	}
}
