package com.orders.model;

import java.util.LinkedHashSet;
import java.util.List;
import com.cart.model.CartVO;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void delete(String ordno);
	public void update(OrdersVO ordersVO);
	
	/*�P�ɷs�W�q���q��Ӷ�*/
	public void insertWithOrders_detail(OrdersVO ordersVO, LinkedHashSet<CartVO> list);
	/*�M�P�浧�q��*/
	public void revoke(String ordno);
	/*�d�߬Y�|���Ҧ��q��*/
	public List<OrdersVO> getAllByMemno(String memno);
	
	public OrdersVO findByPrimaryKey(String ordno);
	public List<OrdersVO> getAll();

}
