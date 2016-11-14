package com.product.model;

import java.util.List;

import com.product.model.*;

public class daotest {

	public static void main(String[] args) {
		ProductJDBCDAO dao = new ProductJDBCDAO();
		ProductVO vo = new ProductVO();
//		insert
//		vo.setStono("000002");
//		vo.setClassno("000003");
//		vo.setProname("Quadropolis 四大城邦");
//		vo.setProprice(250);
//		vo.setProstate("1");
//		vo.setProqty(9);
//		vo.setAdddate(new java.sql.Date(System.currentTimeMillis()));
//		vo.setPlayernum("5~8人");
//		vo.setPlayerage("16+");
//		vo.setLang("中文");
//		vo.setPlaytime("45分鐘");
//		vo.setProdesc("Each player builds their own metropolis in Quadropolis (first announced as City Mania), but they're competing with one another for the shops, parks, public services and other structures to be placed in them.");
//		dao.insert(vo);
		
//		update
//		vo.setProno("000002");
//		vo.setProprice(612);
//		dao.update(vo);
		
//		delete
//		dao.delete("000009");
		
//		find one		
//		System.out.println(dao.findByPrimaryKey("000008"));
		
//		find all
		List<ProductVO> list = dao.getAll();
		for (ProductVO provo : list) {
			System.out.println(provo.toString());
		}
	}
}
