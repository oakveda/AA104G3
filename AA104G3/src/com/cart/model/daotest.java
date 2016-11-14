package com.cart.model;

import java.util.LinkedHashSet;
import java.util.List;

import com.cart.model.*;

public class daotest {

	public static void main(String[] args) {
		CartJDBCDAO dao = new CartJDBCDAO();

		CartVO vo = new CartVO();
//		vo.setMemno("000002");
//		vo.setProno("000002");
//		vo.setProcount(6);
//		dao.insert(vo);
		
//		delete
//		dao.delete("000002", "000002");

//		update
//		vo.setMemno("000005");
//		vo.setProno("000006");
//		vo.setProcount(7);
//		dao.update(vo);
		
//		find one
//		System.out.println(dao.findByPrimaryKey("000001", "000001").toString());
		
//		find all
		LinkedHashSet<CartVO> list=dao.getAll();
		for(CartVO cvo :list){
			System.out.println(cvo.toString());
		}

	}

}
