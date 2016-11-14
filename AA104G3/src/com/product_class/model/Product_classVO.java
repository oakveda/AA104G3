package com.product_class.model;

import java.io.Serializable;

public class Product_classVO implements Serializable {
	private String classno;
	private String classname;
	/**
	 * @return the classno
	 */
	public String getClassno() {
		return classno;
	}
	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}
	/**
	 * @return the classname
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * @param classname the classname to set
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
