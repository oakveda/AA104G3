package com.product.model;

import java.util.*;

import com.product_class.model.Product_classHibernateDAO;
import com.product_class.model.Product_classVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductHibernateDAO();
	}

	public ProductVO addProduct(String stono, String classno, String proname, Integer proprice, String prostate,
			Integer proqty, String playernum, String playerage, String lang, String playtime, String prodesc,
			byte[] propic) {

		ProductVO productVO = new ProductVO();

		productVO.setStono(stono);
		Product_classVO product_classVO = new Product_classVO();
		product_classVO.setClassno(classno);		
		productVO.setProduct_classVO(product_classVO);
		productVO.setProname(proname);
		productVO.setProprice(proprice);
		productVO.setProstate(prostate);
		productVO.setProqty(proqty);
		productVO.setPlayernum(playernum);
		productVO.setPlayerage(playerage);
		productVO.setLang(lang);
		productVO.setPlaytime(playtime);
		productVO.setProdesc(prodesc);
		productVO.setPropic(propic);
		dao.insert(productVO);
		return productVO;

	}

	public ProductVO updateProduct(String prono, String stono, String classno, String proname, Integer proprice,
			String prostate, Integer proqty, String playernum, String playerage, String lang, String playtime,
			String prodesc, byte[] propic) {

		ProductVO productVO = new ProductVO();

		productVO.setProno(prono);
		productVO.setStono(stono);
		Product_classVO product_classVO = new Product_classVO();
		product_classVO.setClassno(classno);		
		productVO.setProduct_classVO(product_classVO);
		productVO.setProname(proname);
		productVO.setProprice(proprice);
		productVO.setProstate(prostate);
		productVO.setProqty(proqty);
		productVO.setPlayernum(playernum);
		productVO.setPlayerage(playerage);
		productVO.setLang(lang);
		productVO.setPlaytime(playtime);
		productVO.setProdesc(prodesc);
		productVO.setPropic(propic);

		dao.update(productVO);
		return productVO;

	}

	public void updateQty(ProductVO productVO) {
		dao.update(productVO);
	}

	public void deleteProduct(String prono) {
		dao.delete(prono);
	}

	public ProductVO getOneProduct(String prono) {
		return dao.findByPrimaryKey(prono);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<ProductVO> getAllSortByName() {
		return dao.getAllByName();
	}

	public List<ProductVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
