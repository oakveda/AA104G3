package com.product_report.model;

import java.util.List;

public interface Product_reportDAO_interface {
    public void insert(Product_reportVO prorepVO);
    public void update(Product_reportVO prorepVO);
    public void delete(String prorepno);
    public Product_reportVO findByPrimaryKey(String prorepno);
    public List<Product_reportVO> getAll();
//  public List<Product_RepoetVO> getAll(Map<String, String[]> map);
}
