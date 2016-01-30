package org.dbyz.java.funny.about_inherit;
/**
 * 子类实现
 *
 * @ClassName: Sub
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class Sub extends Super{
	private  String msg = "default msg";

	public Sub() {
	    this.msg = "I am sub class";
	}

	@Override
	public void sayHello() {
		System.out.println(msg);
	}

}
