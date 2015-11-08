package org.dbyz.java.reflact;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

@SuppressWarnings("all")
public class TestReflect {
	public static void main(String[] args) throws Exception {
		// 记载类
		Class<Entity> e = (Class<Entity>) Class
				.forName("org.dbyz.java.reflact.Entity");
		// 创建类实例
		Entity a = e.newInstance();

		System.out.println("-- 获取自己和父类公共的方法 --");
		for (Method f : e.getMethods()) {
			System.out.println(f.getName());
			// 运行这些方法(部分会报错的)
			// e.getMethod(f.getName()).invoke(a, null);
		}

		System.out.println("\n-- 获取在类中定义的全部方法 --");
		for (Method f : e.getDeclaredMethods()) {
			System.out.println(f.getName());
			f.setAccessible(true);

			// java.lang.reflect.Modifier
			if (f.getModifiers() != Modifier.PRIVATE)
				// 运行这些方法(非private的方法)
				System.out.println("return: "
						+ e.getDeclaredMethod(f.getName()).invoke(a, null));
		}

		System.out.println("\n-- 获取公共属性 --");
		for (Field f : e.getFields()) {
			System.out.print(f.getName() + ": ");
			System.out.println(f.get(a));
		}

		System.out.println("\n-- 获取所有在类中定义的的属性 --");
		for (Field f : e.getDeclaredFields()) {
			System.out.print(f.getGenericType() + ":" + f.getName() + "  :");
			 if (f.getModifiers() == Modifier.PRIVATE){
				f.setAccessible(true);
				System.out.println("private "+f.get(a));
			}else{
				System.out.println("other "+f.get(a));
			}
		}

	}

}