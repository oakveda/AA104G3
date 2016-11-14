package com.orders_detail.model;

import java.util.List;

import com.orders_detail.model.*;

public class daotest {

	public static void main(String[] args) {
		Orders_detailJDBCDAO dao = new Orders_detailJDBCDAO();
		Orders_detailVO vo = new Orders_detailVO();
//		insert
//		vo.setOrdno("000005");
//		vo.setProno("000003");
//		vo.setProcount(3);		
//		dao.insert(vo);
		
//		delete
//		dao.delete("000006");
//		
//		update
//		vo.setOrddetno("000001");
//		vo.setProcount(6);		
//		dao.update(vo);
	
//		find one
//		vo=dao.findByPrimaryKey("000001");
//		System.out.println(vo.getOrddetno()+" "+vo.getOrdno()+" "+vo.getProno()+" "+vo.getProcount());
		
//		find all
		List<Orders_detailVO> list = dao.getAll();
		for(Orders_detailVO orddetvo :list){
			System.out.println(orddetvo.getOrddetno()+" "+orddetvo.getOrdno()+" "+orddetvo.getProno()+" "+orddetvo.getProcount());
		}

	}

}
