package org.dbyz.java8.functional_interface;

/**
 * 定义一个Lambda表达式（有点抽象） P：参数类型 T：返回的值类型
 * 
 * @ClassName: MyLambda
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 * @param <P>
 */
@FunctionalInterface
public interface MyLambda<P, T> {
	/**
	 * Lambda的返回方法
	 * 
	 * @Title: getValue
	 * @param param
	 * @return: T
	 * @since V1.0
	 */
	T getValue(P param);

	/**
	 * 只能有一个抽象方法
	 */
	// T getValue2(P param);//不允许

	/**
	 * 默认方法是允许的存在的
	 * 
	 * @Title: print
	 * @param @param param
	 * @return: void
	 * @since V1.0
	 */
	default void print(P param) {
		System.out.println(param);
	}
}
