package com.orders_detail.model;

import java.util.List;

public class Orders_detailService {
	private Orders_detailDAO_interface dao;
	public Orders_detailService(){
		dao = new Orders_detailJNDIDAO();
	}
	
	public  Orders_detailVO addOrders_detail(String ordno, String prono, Integer procount){
		Orders_detailVO orders_detailVO = new Orders_detailVO();
		orders_detailVO.setOrdno(ordno);
		orders_detailVO.setProno(prono);
		orders_detailVO.setProcount(procount);
		dao.insert(orders_detailVO);
		return orders_detailVO;
	}
	
	public  Orders_detailVO updateOrders_detail(String ordno, String prono, Integer procount){
		Orders_detailVO orders_detailVO = new Orders_detailVO();
		orders_detailVO.setOrdno(ordno);
		orders_detailVO.setProno(prono);
		orders_detailVO.setProcount(procount);
		dao.update(orders_detailVO);
		return orders_detailVO;
	}
	
	public void deleteOrders_detail(String orddetno){
		dao.delete(orddetno);
	}
	
	public Orders_detailVO getOneOrders_detail(String orddetno){
		return dao.findByPrimaryKey(orddetno);
	}
	
	public List<Orders_detailVO> getAll(){
		return dao.getAll();
	}
}
