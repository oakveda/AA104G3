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
//		vo.setPickname("���p��");
//		vo.setPickaddr("116�x�_����s�ϫ��n���G�q64��");		
//		dao.insert(vo);
		
//		delete
//		dao.delete("000006");
		
//		update
//		vo.setMemno("000001");
//		vo.setPickname("�����");
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
