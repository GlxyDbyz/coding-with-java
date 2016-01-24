package org.dbyz.java.ref;

import java.util.ArrayList;
import java.util.List;

/**
 * 强引用
 *
 * @ClassName: StrongReference
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class StrongReferenceTest {
	private static final int LOOP = 100;
	private static final int PER_MEMO = 5;

	public static void main(String[] args) {
		// 强引用是Java默认的引用方式,强引用容易引起内存溢出
		// eg:
		List<byte[]> refs = new ArrayList<>();
		for (int i = 0; i < LOOP; i++) {
			refs.add(new byte[1024 * 1024 * PER_MEMO]);
		}
//	Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//	at org.dbyz.java.ref.StrongReference.main(StrongReference.java:22)
	}
}
