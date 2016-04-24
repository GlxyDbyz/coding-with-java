/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package org.dbyz.java.net.rpc;

/**
 * HelloServiceImpl
 * 
 * @author william.liangf
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String say(String name) {
		return "Hello " + name;
	}

	@Override
	public String laugh() {
		return " Haha Haha";
	}

}
