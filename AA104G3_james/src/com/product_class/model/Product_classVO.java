package com.product_class.model;

import java.io.Serializable;
import java.util.*;

import com.product.model.ProductVO;

public class Product_classVO implements Serializable {
	private String classno;
	private String classname;
	private Set<ProductVO> products = new HashSet<ProductVO>();
	
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public Set<ProductVO> getProducts() {
		return products;
	}
	public void setProducts(Set<ProductVO> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Product_classVO [classno=" + classno + ", classname=" + classname + ", products=" + products + "]";
	}
	
}
