package org.dbyz.design_pattern._01SimpleFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 简单工厂模式2（基于配置文件）
 *
 * @ClassName: CarFactory2
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class CarFactory2 {
	public static Vehicle getCar() {
		Vehicle car = null;
		InputStream inStream = null;
		Properties properties = new Properties();
		try {
			// 获取配置文件流
			inStream = Vehicle.class.getResourceAsStream("config.properties");
			// 加载properties文件
			properties.load(inStream);
			// 从配置文件读取实现类并新建实例
			car = (Vehicle) Class.forName(properties.getProperty("impl"))
					.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}
}
