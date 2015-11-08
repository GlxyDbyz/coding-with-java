package org.dbyz.java.reflact.tostring;

import org.junit.Test;

public class TestIt {
	@Test
	public void test1() {
		Entity e = new Entity(12, 22, 12L, 22L);
		Entity2 e2 = new Entity2(12, 22, 12L, 22L);
		System.out.println(e);
		System.out.println(e2);
	}

	@Test
	public void test2() throws IllegalArgumentException, IllegalAccessException {
		System.out.println(ObjectToStringUtil.toString(new String("aaa")));
		System.out.println(ObjectToStringUtil.toString(new Double("1.2")));
		System.out.println(ObjectToStringUtil.toString(new Long("12")));
		System.out.println(ObjectToStringUtil.toString(new Float("1.2")));
		System.out.println(ObjectToStringUtil.toString(new Integer(12)));
		System.out.println(ObjectToStringUtil.toString(new Short("12")));
		System.out.println(ObjectToStringUtil.toString(new Byte("12")));
		System.out.println(ObjectToStringUtil.toString(new Object()));
		System.out.println("");
		System.out.println(ObjectToStringUtil.toString("aaa"));
		System.out.println(ObjectToStringUtil.toString(3.12));
		System.out.println(ObjectToStringUtil.toString(12L));
		System.out.println(ObjectToStringUtil.toString(1.2f));
	}
}