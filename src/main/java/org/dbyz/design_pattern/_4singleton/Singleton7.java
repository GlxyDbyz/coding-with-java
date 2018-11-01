package org.dbyz.design_pattern._4singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 多例
 * 
 * @ClassName: Singleton7
 * @Description:
 * @author 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version 创建时间：2015年7月26日下午6:25:24
 */
public class Singleton7 {
	/**
	 * 定义一个缺省的key值的前缀
	 */
	private final static String DEFAULT_PREKEY = "Cache";
	/**
	 * 缓存实例的容器
	 */
	private static Map<String, Singleton7> map = new HashMap<String, Singleton7>();
	/**
	 * 用来记录当前正在使用第几个实例，到了控制的最大数目，就返回从1开始
	 */
	private volatile static int num = 1;
	/**
	 * 定义控制实例的最大数目
	 */
	private final static int NUM_MAX = 3;

	private Singleton7() {
	}

	public static Singleton7 getInstance() {
		String key = DEFAULT_PREKEY + num;
		Singleton7 Singleton7 = map.get(key);
		if (Singleton7 == null) {
			Singleton7 = new Singleton7();
			map.put(key, Singleton7);
		}
		// 把当前实例的序号加1
		num++;
		if (num > NUM_MAX) {
			// 如果实例的序号已经达到最大数目了，那就重复从1开始获取
			num = 1;
		}
		return Singleton7;
	}

	public static void main(String[] args) {
		Singleton7 t1 = getInstance();
		Singleton7 t2 = getInstance();
		Singleton7 t3 = getInstance();
		Singleton7 t4 = getInstance();
		Singleton7 t5 = getInstance();
		Singleton7 t6 = getInstance();

		System.out.println("t1==" + t1);
		System.out.println("t2==" + t2);
		System.out.println("t3==" + t3);
		System.out.println("t4==" + t4);
		System.out.println("t5==" + t5);
		System.out.println("t6==" + t6);
	}
}