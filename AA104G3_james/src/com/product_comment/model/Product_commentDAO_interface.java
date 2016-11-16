package com.product_comment.model;

import java.util.List;

public interface Product_commentDAO_interface {
	public void insert(Product_commentVO product_commentVO);
	public void delete(String procomno);
	public void update(Product_commentVO product_commentVO);
	public Product_commentVO findByPrimaryKey(String procomno);
	public List<Product_commentVO> getAll();
	/*�q�ӫ~�s���d�X����*/
	public List<Product_commentVO> getAll(String prono);
	//public List<Product_commentVO> getAll(Map<String,String[] map>);
}
