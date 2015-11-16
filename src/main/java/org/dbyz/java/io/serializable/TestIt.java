package org.dbyz.java.io.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.dbyz.java.reflact.tostring.ObjectToStringUtil;
import org.junit.Test;

public class TestIt {
	static SerializableBean c = new SerializableBean("aa");

	@Test
	public void test1() throws IOException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {

		FileOutputStream fos = new FileOutputStream("D://t.tmp");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(12345);
		oos.writeObject(c);
		oos.writeObject("Today");
		oos.writeObject(new Date());
		System.out.println(ObjectToStringUtil.toString(c));
		oos.close();
	}

	@Test
	public void test2() throws IOException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {

		FileInputStream fis = new FileInputStream("D://t.tmp");
		ObjectInputStream ois = new ObjectInputStream(fis);

		int i = ois.readInt();
		SerializableBean bean = (SerializableBean) ois.readObject();
		String today = (String) ois.readObject();
		Date date = (Date) ois.readObject();
		System.out.println(today);
		System.out.println(i);
		System.out.println(bean);
		System.out.println(date);

		System.out.println(ObjectToStringUtil.toString(bean));
		ois.close();
	}
}