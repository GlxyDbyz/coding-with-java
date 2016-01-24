package org.dbyz.java.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用例子(软引用指向的对象在虚拟机内存不足GC时会回收内存)
 *
 * @ClassName: SoftReferenceTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class SoftReferenceTest {
	private static final int LOOP = 100;
	private static final int PER_MEMO = 5;

	public static void main(String[] args) {

		List<SoftReference<byte[]>> refs = new ArrayList<>();
		for (int i = 0; i < LOOP; i++) {
			refs.add(new SoftReference<byte[]>(new byte[1024 * 1024 * PER_MEMO]));
		}

		// 相同配置下,没有内存益处,因为在GC的时候SoftReference指向的对象被选择性的回收了

		for (SoftReference<byte[]> ref : refs) {
			System.out.println(ref+" -> "+ref.get());
		}
		
	}
}
