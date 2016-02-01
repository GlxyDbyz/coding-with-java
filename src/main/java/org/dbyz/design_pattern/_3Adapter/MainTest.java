package org.dbyz.design_pattern._3Adapter;
/**
 * 适配器模式(以翻译为例)
 *
 * @ClassName: MainTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class MainTest {
	public static void main(String[] args) {
		// 外国科学家==》需要适配的对象 ：否则无法理解他说的话
		ForeignScientist foreignScientist = new ForeignScientist();
		// （中文）翻译 ==》适配器 ：对外语进行翻译
		Interpreter interpreter = new ChinessInterpreter(foreignScientist);
		// 翻译语言（本质是科学家说的话，适配器只是转换了语言环境）
		interpreter.translate();
	}
}
/**
 * 适配器模式是在一个类的功能任然需要，但是又无法直接使用的情况下才使用适配器模式，其本质是对原有无法直接使用的功能进行转换后复用
 */
