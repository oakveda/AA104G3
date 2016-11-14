package com.product_report.model;

import com.product_report.model.*;
import java.sql.*;
import java.util.List;

public class daotest {

	public static void main(String[] args) {
		Product_reportJDBCDAO dao = new Product_reportJDBCDAO();
		Product_reportVO vo = new Product_reportVO();
		
//		insert
//		vo.setMemno("000001");
//		vo.setProno("000001");		
//		vo.setRepdate(new Date(System.currentTimeMillis()));
//		vo.setChecked("0");
//		vo.setReason("²£«~ª¬ºA¨}¦n");
//		dao.insert(vo);
		
//		delete
//		dao.delete("000002");
		
//		update
//		vo.setProrepno("000001");
//		vo.setChecked("1");
//		dao.update(vo);
		
//		find one
//		vo = dao.findByPrimaryKey("000001");
//		System.out.println(vo.getProrepno()+" "+vo.getMemno()+" "+vo.getProno()+" "+vo.getReason()+" "+vo.getRepdate()+" "+vo.getChecked());
		
//		find all
		List<Product_reportVO> list = dao.getAll();
		for(Product_reportVO prorepvo : list){
			System.out.println(prorepvo.getProrepno()+" "+prorepvo.getMemno()+" "+prorepvo.getProno()+" "+prorepvo.getReason()+" "+prorepvo.getRepdate()+" "+prorepvo.getChecked());
		}

	}

}
