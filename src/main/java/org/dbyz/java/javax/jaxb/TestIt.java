package org.dbyz.java.javax.jaxb;

import java.io.File;

import org.junit.Test;

public class TestIt {

	@Test
	public void beanToXML() throws Exception {
		XmlBean bean = new XmlBean();
		bean.setEmail("abc@qq.com");
		bean.setName("abc");
		System.out.println(JaxbUtil.beanToXml(bean, "UTF-8"));
	}

	@Test
	public void xmlToBean() throws Exception {
		System.out
				.println(JaxbUtil
						.xmlToBean(
								"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><bean><email>abc@qq.com</email><theName>abc</theName></bean>",
								XmlBean.class));
	}

	@Test
	public void beanToXmlFile() throws Exception {
		XmlBean bean = new XmlBean();
		bean.setEmail("abc@qq.com");
		bean.setName("abc");

		JaxbUtil.beanToXmlFile(bean, new File("D://test.xml"), "UTF-8");

	}

	@Test
	public void xmlFileToBean() throws Exception {
		System.out.println(JaxbUtil.xmlFileToBean(new File("D://test.xml"),
				XmlBean.class));
	}

	@Test
	public void getXmlBeanFromUrl() throws Exception {
		System.out.println(JaxbUtil.getXmlBeanFromUrl("file:///D:/test.xml",
				XmlBean.class));
	}
}