package org.dbyz.java.jni;

/**
 * JNI 简单测试(接收dll传过来的字符串)
 * 
 * @ClassName: FirstJniTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class FirstJniTest {

	// 简单步骤:[此处给出Code::Blocks + mingGW 编译dll步骤]
	// 1:javac [java文件]                                         生成 class
	// 2:javah -jni [类名]                                        生成*.h 头文件,修改函数名称以 _ 开头
	// 3:实现头文件里面的方法                                                                                                                                生成*.c 文件
	// 4:配置Code::Blocks 编译环境,新建Dynamic Link library项目,并导入文件
	// 具体配置可以Google或者Baidu一下
	// 5:编译项目                                                                                                                                                       生成*.dll目标文件
	
	// 注意:[在windows下使用VS新版本可以不用修改方法名称以_开头,编译器自动处理这个问题,我认为使用VS编译是在WINDOWS系统比较好的实践方式]
	// 使用dll查看工具可以看到C语言实现的方法是下面这样的[可以用同样的方法查看java虚拟机dll里面的方法也是这样的],在多次尝试之后才发现问题所在
	// _Java_org_dbyz_java_jni_FirstJniTest_hello@8
	
	static {
		// 运行前需要把你的*.dll文件放到 java/bin目录下[当然有其他方法处理这个问题]
		System.loadLibrary("FirstJniTest");
	}

	public native String hello();

	public static void main(String[] args) {
		System.out.println(new FirstJniTest().hello());
		// hello I am a String from C
	}
}
