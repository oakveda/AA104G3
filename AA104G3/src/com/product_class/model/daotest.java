package com.product_class.model;

import java.util.List;

import com.product_class.model.Product_classJDBCDAO;
import com.product_class.model.Product_classVO;

public class daotest {

	public static void main(String[] args) {
		Product_classJDBCDAO dao = new Product_classJDBCDAO();
		Product_classVO vo = new Product_classVO();
		
//		insert
//		vo.setClassname("crazy game");
//		dao.insert(vo);
		
//		delete
//		dao.delete("000007");
		
//		update
//		vo.setClassno("000008");
//		vo.setClassname("scare game");
//		dao.update(vo);
		
//		find one
//		System.out.println(dao.findByPrimaryKey("000001").getClassname());
		
//		find all
		List<Product_classVO> list = dao.getAll();
		for (Product_classVO pcvo : list) {
			System.out.println(pcvo.getClassno()+" "+pcvo.getClassname());
		}

	}

}
