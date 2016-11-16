package com.product.model;

import java.util.List;
import java.util.Map;

public interface ProductDAO_interface {
    public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(String prono);
    public ProductVO findByPrimaryKey(String prono);
    public List<ProductVO> getAll();  
    public List<ProductVO> getAllByName(); 
    public List<ProductVO> getAll(Map<String, String[]> map);	
}
