package com.orders.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;

import com.cart.model.CartVO;

public class OrdersService {
	private OrdersDAO_interface dao;
	public OrdersService(){
		dao= new OrdersJNDIDAO();
	}
	
	public OrdersVO addOrders(String memno, Integer pricesum, Integer total, String payment, Date orddate, String pickname, String pickup, String pickphone, String pickaddr, LinkedHashSet<CartVO> list){
		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setMemno(memno);
		ordersVO.setPricesum(pricesum);
		ordersVO.setTotal(total);
		ordersVO.setPayment(payment);
		ordersVO.setOrddate(orddate);
		ordersVO.setPickname(pickname);
		ordersVO.setPickup(pickup);
		ordersVO.setPickphone(pickphone);
		ordersVO.setPickaddr(pickaddr);
		dao.insertWithOrders_detail(ordersVO, list);
		return ordersVO;
	}
	
	public OrdersVO updateOrders(String memno, Integer pricesum, Integer total, String pickname, String pickaddr){
		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setMemno(memno);
		ordersVO.setPricesum(pricesum);
		ordersVO.setTotal(total);
		ordersVO.setPickname(pickname);
		ordersVO.setPickaddr(pickaddr);
		dao.update(ordersVO);
		return ordersVO;
	}
	
	public void deleteOrders(String ordno){
		dao.delete(ordno);
	}
	
	public OrdersVO getOneOrders(String ordno){
		return dao.findByPrimaryKey(ordno);
	}
	
	public List<OrdersVO> getAll(){
		return dao.getAll();
	}
	
	/*撤銷訂單*/
	public void revoke(String ordno){
		dao.revoke(ordno);
	}
	
	/* 查某會員所有訂單 */
	public List<OrdersVO> getAllByMemno(String memno){
		return dao.getAllByMemno(memno);
		
	}

}
