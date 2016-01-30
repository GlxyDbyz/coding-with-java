/**
 * 
 */
package org.dbyz.java.funny.about_inherit;

/**
 * 分析一个来自 Effective Java 第二版 第17条的示例程序
 * 
 * @ClassName: InheritTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Test {
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub = new Sub();
		sub.sayHello();
	}
	
	// 以上代码会输出什么
	// A :
	// 1. I am sub class
	
	// B :
	// 1. default msg
	// 2. I am sub class
	
	// C :
	// 1. I am sub class
	// 2. I am sub class

	// 解释见最下面注释[只是现阶段的个人见解]
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 调用流程[此处只分析Sub和Super的调用关系,不分析Super和Object的调用关系]
	 * 
	 *  1.首先man()方法调用 Sub()构造器(虚拟机按照对象大小为新对象分配内存),Sub()会首先调用父类Super()构造器
	 *  
	 *  2.Super的构造器调用sayHello(),因为sayHello()被Sub覆盖了,调用其实的是Sub的sayHello()
	 *  **[此时由于上下文环境是子类,所以调用的是子类的sayHello(),此时msg没有被初始化所以是null,子类所有属性的初始
	 *  **化是在父类的构造器调用完之后从类的最顶部代码开始向下执行的,所有属性(无论位置在那里)初始化完毕(此时msg的值是"default msg"),
	 *  **然后才开始执行子类构造器里面的实际代码(赋值语句 this.msg = "I am sub class")]
	 *  
	 *  3.sub实例调用sub.sayHello() 输出  I am sub class
	 *  
	 */
	
	
	
	
	// 正确答案
	// C :
	// 1. null
	// 2. I am sub class
	
	// 可以把  msg 改成静态的再运行下试下效果[静态变量或者代码块是在类加载的时候初始化的,而且只会初始化一次]
	
}
