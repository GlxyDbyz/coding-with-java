package org.dbyz.java.io.serializable;

import java.io.Serializable;

public class SerializableBean implements Serializable {

	private static final long serialVersionUID = 2410872170503442475L;
	private String name;

	public SerializableBean(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SerializableBean [name=" + name + "]";
	}

}
