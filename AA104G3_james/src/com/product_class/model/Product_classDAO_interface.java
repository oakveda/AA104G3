package com.product_class.model;

import java.util.*;

import com.product.model.ProductVO;

public interface Product_classDAO_interface {
    public void insert(Product_classVO product_classVO);
    public void update(Product_classVO product_classVO);
    public void delete(String classno);
    public Product_classVO findByPrimaryKey(String classno);
    public List<Product_classVO> getAll();
    public Set<ProductVO> getProductsByClassno(String classno);
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<product_classVO> getAll(Map<String, String[]> map); 

}
