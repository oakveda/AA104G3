package com.cart.model;

import java.util.LinkedHashSet;

public interface CartDAO_interface {
	public void insert(CartVO CartVO);

	public void update(CartVO CartVO);

	public void delete(String memno, String prono);

	public void delete(String memno);

	public LinkedHashSet<CartVO> findByPrimaryKey(String memno);

	public LinkedHashSet<CartVO> getAll();
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<ProductVO> getAll(Map<String, String[]> map);
}
