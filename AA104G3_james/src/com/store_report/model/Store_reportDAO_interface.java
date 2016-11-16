package com.store_report.model;

import java.util.List;

public interface Store_reportDAO_interface {
	public void insert(Store_reportVO storepVO);
    public void update(Store_reportVO storepVO);
    public void delete(String storepno);
    public Store_reportVO findByPrimaryKey(String storepno);
    public List<Store_reportVO> getAll();
//  public List<Product_RepoetVO> getAll(Map<String, String[]> map);
}
