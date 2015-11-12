package org.dbyz.java.javax.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class JaxbUtil {
	/**
	 * XML字符串转换为对象
	 * 
	 * @Title: xmlToBean
	 * @param @param xml
	 * @param @param clazz
	 * @param @return
	 * @param @throws JAXBException
	 * @return: T
	 * @since V1.0
	 */
	public static <T> T xmlToBean(String xml, Class<T> clazz)
			throws JAXBException {
		// 应用的入口，用于管理XML/Java绑定信息
		JAXBContext context = JAXBContext.newInstance(clazz);
		// Unmarshaller接口，可以将XML数据转换为Java对象。
		Unmarshaller unmarshaller = context.createUnmarshaller();
		// 流转换为对象
		T result = (T) unmarshaller.unmarshal(new StringReader(xml));
		return result;
	}

	/**
	 * 对象转换为XML字符串
	 * 
	 * @Title: beanToXml
	 * @param @param src
	 * @param @return
	 * @param @throws JAXBException
	 * @return: String
	 * @since V1.0
	 */
	public static <T> String beanToXml(T src, String encoding)
			throws JAXBException {
		// 应用的入口，用于管理XML/Java绑定信息
		JAXBContext context = JAXBContext.newInstance(src.getClass());
		// Marshaller接口，可以将Java对象序列化为XML数据。
		Marshaller marshaller = context.createMarshaller();
		// 格式化
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 设置编码
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		// 由于保存String
		StringWriter writer = new StringWriter();
		// 转换成流
		marshaller.marshal(src, writer);
		return writer.toString();
	}

	/**
	 * 对象转换为XML文件
	 * 
	 * @Title: beanToXmlFile
	 * @param @param src
	 * @param @param xmlFile
	 * @param @throws JAXBException
	 * @return: void
	 * @throws IOException
	 * @since V1.0
	 */
	public static <T> void beanToXmlFile(T src, File xmlFile, String encoding)
			throws JAXBException, IOException {
		// 应用的入口，用于管理XML/Java绑定信息
		JAXBContext context = JAXBContext.newInstance(src.getClass());
		// Marshaller接口，可以将Java对象序列化为XML数据。
		Marshaller marshaller = context.createMarshaller();
		// 格式化
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 设置编码
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		// 文件输出(会创建文件)
		marshaller.marshal(src, xmlFile);
	}

	/**
	 * XML文件转换为对象
	 * 
	 * @Title: xmlFileToBean
	 * @param @param xmlFile
	 * @param @param clazz
	 * @param @return
	 * @param @throws JAXBException
	 * @return: T
	 * @since V1.0
	 */
	public static <T> T xmlFileToBean(File xmlFile, Class<T> clazz)
			throws JAXBException {
		// 应用的入口，用于管理XML/Java绑定信息
		JAXBContext context = JAXBContext.newInstance(clazz);
		// Unmarshaller接口，可以将XML数据转换为Java对象。
		Unmarshaller unmarshaller = context.createUnmarshaller();
		T result = (T) unmarshaller.unmarshal(xmlFile);
		return result;
	}

	/**
	 * 从URL里面获取XmlBean
	 * 
	 * @Title: getXmlBeanFromUrl
	 * @param @param url (http、ftp、file...地址)
	 * @param @param clazz
	 * @param @return
	 * @param @throws JAXBException
	 * @param @throws MalformedURLException
	 * @return: T
	 * @since V1.0
	 */
	public static <T> T getXmlBeanFromUrl(String url, Class<T> clazz)
			throws JAXBException, MalformedURLException {
		// 应用的入口，用于管理XML/Java绑定信息
		JAXBContext context = JAXBContext.newInstance(clazz);
		// Unmarshaller接口，可以将XML数据转换为Java对象。
		Unmarshaller unmarshaller = context.createUnmarshaller();

		T result = (T) unmarshaller.unmarshal(new URL(url));
		return result;
	}
}
