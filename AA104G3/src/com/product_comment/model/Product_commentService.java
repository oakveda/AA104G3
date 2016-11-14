package com.product_comment.model;

import java.sql.Date;
import java.util.List;

public class Product_commentService {
	private Product_commentDAO_interface dao;
	
	public Product_commentService(){
		dao = new Product_commentDAO();
	}
	
	public Product_commentVO addStroe(String memno,String prono,
	 String comdetail,Date comdate,Double comscore){
		Product_commentVO product_commentVO = new Product_commentVO();
		product_commentVO.setMemno(memno);
		product_commentVO.setProno(prono);
		product_commentVO.setComdetail(comdetail);
		product_commentVO.setComdate(comdate);
		product_commentVO.setComscore(comscore);
		dao.insert(product_commentVO);
		
		return product_commentVO;
	}
	public Product_commentVO updateStroe(String procomno,String memno,String prono,String comdetail,Date comdate,Double comscore){
		Product_commentVO product_commentVO = new Product_commentVO();
		product_commentVO.setMemno(procomno);
		product_commentVO.setMemno(memno);
		product_commentVO.setProno(prono);
		product_commentVO.setComdetail(comdetail);
		product_commentVO.setComdate(comdate);
		product_commentVO.setComscore(comscore);
		dao.update(product_commentVO);
		
		return product_commentVO;
	}
	
	public void deleteStore(String procomno){
		dao.delete(procomno);
	}
	
	public Product_commentVO getOneStoreVO(String procomno){
		return dao.findByPrimaryKey(procomno);
	}
	
	public List<Product_commentVO> getAll(){
		return dao.getAll();
	}
	
}
