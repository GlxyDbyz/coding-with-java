package org.dbyz.frameworks.lombok;

/**
 * LombokTest 简单测试
 *
 * @ClassName: LombokTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class LombokTest {
	public static void main(String[] args) {
		User u = User.of("Lombok", 12).setScore(99.9D).setTags(new String[]{"Lombok","Great"});
		System.out.println(u.getName());
		
		Student student = Student.builder().name("Lombok").age(12).build();
		System.out.println(student.getName());
		
	}
}
