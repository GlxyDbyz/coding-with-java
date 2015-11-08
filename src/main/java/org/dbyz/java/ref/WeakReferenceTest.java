package org.dbyz.java.ref;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用例子(弱引用指向的对象会在虚拟机GC的时候会被回收,无论内存是否充足)
 *
 * @ClassName: WeakReferenceTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class WeakReferenceTest {
	private static final int LOOP = 100;
	private static final int PER_MEMO = 5;

	public static void main(String[] args) {

		List<WeakReference<byte[]>> refs = new ArrayList<>();
		for (int i = 0; i < LOOP; i++) {
			refs.add(new WeakReference<byte[]>(new byte[1024 * 1024 * PER_MEMO]));
		}

		// 相同配置下,没有内存益处,因为在GC的时候WeakReference指向的对象全部被回收了
		// 有部分对象没有回收
		for (WeakReference<byte[]> ref : refs) {
			System.out.println(ref + " -> " + ref.get());
		}

		System.err.println("---------GC-------");
		System.gc();// GC之后全部回收
		// ALL NULL
		for (WeakReference<byte[]> ref : refs) {
			System.out.println(ref + " -> " + ref.get());
		}

	}
}
