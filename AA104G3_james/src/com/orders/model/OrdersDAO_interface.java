package com.orders.model;

import java.util.LinkedHashSet;
import java.util.List;
import com.cart.model.CartVO;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void delete(String ordno);
	public void update(OrdersVO ordersVO);
	
	/*同時新增訂單跟訂單細項*/
	public void insertWithOrders_detail(OrdersVO ordersVO, LinkedHashSet<CartVO> list);
	/*撤銷單筆訂單*/
	public void revoke(String ordno);
	/*查詢某會員所有訂單*/
	public List<OrdersVO> getAllByMemno(String memno);
	
	public OrdersVO findByPrimaryKey(String ordno);
	public List<OrdersVO> getAll();

}
