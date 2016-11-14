package com.orders_detail.model;

import java.util.List;

public interface Orders_detailDAO_interface {
	public void insert(Orders_detailVO orddetVO);
	public void delete(String orddetno);
	public void update(Orders_detailVO orddetVO);
	public Orders_detailVO findByPrimaryKey(String orddetno);
	public List<Orders_detailVO> getAll();
	
	/*�q�s�W�q��ɩI�s*/
	public void insert(Orders_detailVO orddetVO, java.sql.Connection con);
}
