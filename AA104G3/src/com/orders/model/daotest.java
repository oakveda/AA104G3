package com.orders.model;

import java.util.List;

import com.orders.model.*;

public class daotest {
	public static void main(String[]args){
		OrdersJDBCDAO dao = new OrdersJDBCDAO();
		OrdersVO vo = new OrdersVO();
//		insert
//		vo.setMemno("000001");
//		vo.setPricesum(200);
//		vo.setTotal(8);
//		vo.setPickname("王小明");
//		vo.setPickaddr("116台北市文山區指南路二段64號");		
//		dao.insert(vo);
		
//		delete
//		dao.delete("000006");
		
//		update
//		vo.setMemno("000001");
//		vo.setPickname("黃曉明");
//		dao.update(vo);
		
//		find one
//		vo=dao.findByPrimaryKey("000001");
//		System.out.println(vo.toString());	
		
//		find all
		List<OrdersVO> list =dao.getAll();
		for(OrdersVO ordvo: list){
			System.out.println(ordvo.toString());
		}
		
	}
}
