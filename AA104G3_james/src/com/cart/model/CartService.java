package com.cart.model;

import java.util.*;

public class CartService {
	private CartDAO_interface dao;

	public CartService() {
		dao = new CartJNDIDAO();
	}

	public CartVO addCart(String prono, String memno, Integer procount) {
		CartVO cartVO = new CartVO();
		cartVO.setMemno(memno);
		cartVO.setProno(prono);
		cartVO.setProcount(procount);
		dao.insert(cartVO);
		return cartVO;
	}
	
	public CartVO updateCart(String prono, String memno, Integer procount){
		CartVO cartVO = new CartVO();
		cartVO.setMemno(memno);
		cartVO.setProno(prono);
		cartVO.setProcount(procount);
		dao.update(cartVO);
		return cartVO;
	}
	
	public void deleteOne(String memno, String prono){
		dao.delete(memno, prono);
	}
	
	public void deleteAll(String memno){
		dao.delete(memno);
	}
	
	
	public LinkedHashSet<CartVO> getOneCart(String memno){
		return dao.findByPrimaryKey(memno);
	}
	
	public LinkedHashSet<CartVO> getAll(){
		return dao.getAll();
	}
}
