package org.dbyz.frameworks.commons.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Before;
import org.junit.Test;
/**
 * FTPClient 使用
 *
 * @ClassName: TestDigest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class FTPClientTest {
	private static String IP = "118.123.5.22";
	private static int PORT = 21;
	private static int TIMEOUT = 60 * 1000;
	private static String USERNAME = "glxydbyz.top";
	private static String PASSWORD = "GGvwGynxTk";
	
	private FTPClient client;
	
	@Before
	public void connect() throws SocketException, IOException{
		client = new FTPClient();
		client.setConnectTimeout(TIMEOUT);
		client.connect(IP,PORT);
		client.login(USERNAME, PASSWORD);
		client.setFileType(FTPClient.BINARY_FILE_TYPE);
		int reply = client.getReplyCode();
		if (FTPReply.isPositiveCompletion(reply)) {
			System.out.println(reply+ " connection established");
		}else{
			System.out.println(reply+ " connection can not establish");			
		}
	}
	
	@Test
	public void testSwitchPath() throws SocketException, IOException{
		System.out.println(client.changeWorkingDirectory("data"));
	}
	
	@Test
	public void testListPath() throws SocketException, IOException{
		FTPFile[] files = client.listFiles();
		listFile(files);
	}
	
	public void listFile(FTPFile[] files) throws IOException{
		for (FTPFile ftpFile : files) {
			System.out.println(ftpFile.getName());
			if(ftpFile.isDirectory()){
				client.cwd("./"+ftpFile.getName());
				FTPFile[] files1 = client.listFiles();
				listFile(files1);
			}
		}
		client.cwd("..");
	}
	
	@Test
	public void testMakePath() throws SocketException, IOException{
		System.out.println(client.makeDirectory("b"));
	}
	
	@Test
	public void testDelPath() throws SocketException, IOException{
		System.out.println(client.deleteFile("a"));
	}
	
	@Test
	public void testUpload() throws SocketException, IOException{
		InputStream input = new FileInputStream(new File("D://a.txt"));
		System.out.println(client.storeFile("b/a.txt", input));
	}
	
	
}
