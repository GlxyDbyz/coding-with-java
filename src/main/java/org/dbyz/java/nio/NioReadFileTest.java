package org.dbyz.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio文件读取简单例子
 *
 * @ClassName: NioReadFileTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class NioReadFileTest {
	public static void main(String[] args) throws IOException {
		// 随机文件读取对象
		RandomAccessFile file = new RandomAccessFile("C://test.txt", "rw");
		// 得到文件通道
		FileChannel channel = file.getChannel();
		// 创建大小为 1024 Bytes 的通道缓存
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 从通道读取数据写到缓存
		int bytesReaded = channel.read(buffer);

		while (bytesReaded > -1) {
			System.out.println("读取的字节数 :" + bytesReaded);

			// 转换buffer的位置和限制,让buffer从写入模式转换为读取模式,这样才可以从buffer读取数据
			buffer.flip();

			// 判断buffer里面是否有数据
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			System.out.println();

			// 清除此缓冲区。将位置设置为 0，将限制设置为容量，并丢弃标记,让缓冲区可以继续写入
			buffer.clear();
			
			// or 除此缓冲区。将位置设置为 未读取数据大小，将限制设置为容量，并丢弃标记,让缓冲区可以继续写入
			// buffer.compact();//即:保留未读取的数据

			// 继续读取数据
			bytesReaded = channel.read(buffer);
		}
		
		// 关闭文件
		file.close();
	}
}
