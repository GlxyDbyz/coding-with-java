package org.dbyz.java.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * java 虚引用例子(虚引用在没有强引用对象指向同一个对象的时候,虚引用会被放入队列,并在下次GC的时候回收这些内存)
 *
 * @ClassName: PhantomReferenceTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class PhantomReferenceTest {
	private static final int LOOP = 100;
	private static final int PER_MEMO = 1;

	public static void main(String[] args) {

		ReferenceQueue<? super byte[]> queue = new ReferenceQueue<>();
		List<PhantomReference<byte[]>> refs = new ArrayList<>();
		List<byte[]> resourses = new ArrayList<>();
		for (int i = 0; i < LOOP; i++) {
			byte[] resourse = new byte[1024 * 1024 * PER_MEMO];
			resourses.add(resourse);
			refs.add(new PhantomReference<byte[]>(resourse, queue));
		}

		// 虚引用 get() 返回NULL

		for (int i = 0; i < LOOP; i++) {
			Reference<?> ref = queue.poll();
			if (null != ref) {
				System.out.println(ref);
			}
		}

		resourses = null;
		System.gc();
		System.err.println("--------GC------");
		// GC之后队列才有数据
		

		for (int i = 0; i < LOOP; i++) {
			Reference<?> ref = queue.poll();
			if (null != ref) {
				System.out.println(ref);
			}
		}

	}
}
