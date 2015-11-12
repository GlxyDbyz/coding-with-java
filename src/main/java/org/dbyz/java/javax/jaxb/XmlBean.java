package org.dbyz.java.javax.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bean")
public class XmlBean {
	private String name;
	private String email;

	public String getName() {
		return name;
	}

	@XmlElement(name = "theName")
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public XmlBean() {
		super();
	}

	@Override
	public String toString() {
		return "Bean [email=" + email + ", name=" + name + "]";
	}

}