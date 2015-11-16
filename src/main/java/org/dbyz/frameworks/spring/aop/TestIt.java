package org.dbyz.frameworks.spring.aop;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * TODO
 * 
 * @author 方正
 * @version Ver 1.0
 * @since Ver 1.0
 * @Date 2015-6-11
 * 
 */
@SuppressWarnings("all")
public class TestIt {

	/**
	 * ClassPathXmlApplicationContext 支持注解AOP
	 * 
	 * @Title: test1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");// 实用性最强
		BeanFactory fac = (BeanFactory) context;
		Task t = (Task) fac.getBean("task");
		t.helo(" i am arg String");
	}

	/**
	 * XmlBeanFactory 不支持注解AOP
	 * 
	 * @Title: test2
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test2() {
		File file = new File(
				"E:\\github\\Features\\src\\main\\resources\\applicationContext.xml");
		Resource resource = new FileSystemResource(file);
		BeanFactory fac = new XmlBeanFactory(resource);// XmlBeanFactory不支持注解aop
		Task t = (Task) fac.getBean("task");
		t.helo(" i am arg String");
	}

	/**
	 * 同上
	 */
	@Test
	public void test3() {
		Resource resource = new ClassPathResource("applicationContext.xml");// XmlBeanFactory不支持注解aop
		BeanFactory fac = new XmlBeanFactory(resource);
		Task t = (Task) fac.getBean("task");
		t.helo(" i am arg String");
	}

	/**
	 * FileSystemXmlApplicationContext 支持注解AOP
	 * 
	 * @Title: test4
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test4() {
		BeanFactory fac = new FileSystemXmlApplicationContext(
				"E:\\github\\Features\\src\\main\\resources\\applicationContext.xml");
		Task t = (Task) fac.getBean("task");
		t.helo(" i am arg String");
	}

}
