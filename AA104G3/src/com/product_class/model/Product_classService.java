package com.product_class.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_classService {
	private Product_classDAO_interface dao;

	public Product_classService() {
		dao = new Product_classJNDIDAO();
	}

	public Product_classVO addProduct_class(String classname) {
		Product_classVO product_classVO = new Product_classVO();
		dao.insert(product_classVO);
		return product_classVO;
	}
	
	public Product_classVO updateProduct_class(String classname) {
		Product_classVO product_classVO = new Product_classVO();
		dao.update(product_classVO);
		return product_classVO;
	}
	
	public void deleteProduct_class(String classno){
		dao.delete(classno);
	}
	
	public Product_classVO getOneProduct_class(String classno){
		return dao.findByPrimaryKey(classno);		
	}
	
	public List<Product_classVO> getAll(){
		return dao.getAll();
	}

	public Set<ProductVO> getProductByClassno(String classno) {		
		return dao.getProductsByClassno(classno);
	}
	
}
