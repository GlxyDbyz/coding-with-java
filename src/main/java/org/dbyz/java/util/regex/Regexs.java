package org.dbyz.java.util.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 常用正则表达式整理
 *
 * @ClassName: Regexs
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Regexs {
	/**
	 * 正则表达式的使用
	 */
	@Test
	public void useRegex(){
		// 1. Pattern
		String text = "abcdefg";
		Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(text);
        // find ： 有匹配的段
        System.out.println(m.find()); // true
        // matches ： 完全匹配
        System.out.println(m.matches()); // true
        
        text = "123"+text+"123"+text;
        m = p.matcher(text);
        System.out.println(m.find()); // true
        System.out.println(m.matches()); // false
        
        // 直接matches
        System.out.println(Pattern.matches("[1-9]+[a-z]+[1-9]+[a-z]+", text)); // true
        // 一些方法
        System.out.println(m.replaceFirst("*")); // 123*123abcdefg
        System.out.println(m.replaceAll("*")); // 123*123*
        System.out.println(Arrays.asList(p.split(text))); // [123, 123]
        
        // 2. string自带的一些方法内部也是Pattern实现的
        System.out.println(text.replaceFirst("[a-z]+","*")); // 123*123abcdefg
        System.out.println(text.replaceAll("[a-z]+","*")); // 123*123*
        System.out.println(Arrays.asList(text.split("[a-z]+"))); // [123, 123]
        
	}
	
	/**
	 * 中文、日文判断
	 */
	@Test
	public void regex1(){
		String text = "忍ぶ冬…冬でも、寒さに耐えて、葉を落とさないから、こう書くのだそうです。 すいかずらと呼ぶのは、水をよく吸う蔓だからという説、花の根本にある蜜を子供たちが吸ったからという説などがあります。";
		// 替换全部日文
		System.out.println(text.replaceAll("[\u3040-\u309f]|[\u30a0-\u30ff]|[\u31f0-\u31ff]", ""));
		// 替换全部中文
		System.out.println(text.replaceAll("[\u4e00-\u9fa5]", ""));
	}
}
