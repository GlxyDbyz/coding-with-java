package org.dbyz.java.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
/**
 * 简单文件分割
 *
 * @ClassName: SplitFileTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class SplitFileTest {
	@Test
	public  void test1() throws IOException {
		File bigFile = new File("D:\\rwt\\test.log");
		System.out.println(bigFile.length() / 1024 / 1024+" M");
		BufferedReader br = null;
		BufferedWriter bw = null;
		br = new BufferedReader(new FileReader(bigFile));
		for (int i = 0; ((bigFile.length() - 1) / (1024 * 1024 * 10)) + 1 > i; i++) {
			File small = new File("D:\\rwt\\small1\\"+i+".log");
			bw = new BufferedWriter(new FileWriter(small));
			String line = "";
			while(small.length()<1024*1024*10 && (line = br.readLine()).length()>0){
				bw.write(line);
				bw.write("\r\n");
			}
			bw.flush();
			bw.close();
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		}
		br.close();
	}
	
	@Test
	public  void test2() throws IOException {
		File bigFile = new File("D:\\rwt\\test.log");
		System.out.println(bigFile.length() / 1024 / 1024+" M");
		BufferedReader br = null;
		BufferedWriter bw = null;
		br = new BufferedReader(new FileReader(bigFile));
		char[] buff = new char[1 * 1024 * 1024];
		for (int i = 0; (bigFile.length() - 1) / (1024 * 1024 * 10) + 1 > i; i++) {
			File small = new File("D:\\rwt\\small2\\" + i + ".log");
			bw = new BufferedWriter(new FileWriter(small));
			int lenth;
			while (small.length() < 1024 * 1024 * 9 && (lenth = br.read(buff)) > 0) {
				bw.write(buff,0,lenth);
			}
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
			if(small.length()>0){	
				bw.flush();
				bw.close();
			}else{
				small.delete();
			}
		}
		br.close();
	}

}
