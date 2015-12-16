package org.dbyz.frameworks.activemq.demo02;

import java.io.Serializable;

public class TestBean implements Serializable{
	private static final long serialVersionUID = -7910049060970800287L;
	
	private String name;
	private Integer age;
	
	public TestBean(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "TestBean [name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
