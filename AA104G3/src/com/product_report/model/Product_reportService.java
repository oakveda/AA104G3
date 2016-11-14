package com.product_report.model;

import java.sql.Date;
import java.util.List;

public class Product_reportService {
	private Product_reportDAO_interface dao;
	
	public  Product_reportService(){
		dao = new Product_reportJNDIDAO();
	}
	
	public Product_reportVO addProduct_report(String memno, String prono, String reason){
		Product_reportVO product_reportVO = new Product_reportVO();
		product_reportVO.setMemno(memno);
		product_reportVO.setProno(prono);
		product_reportVO.setReason(reason);
		product_reportVO.setRepdate(new Date(new java.util.Date().getTime()));		
		dao.insert(product_reportVO);
		return product_reportVO;
	}
	
	/*對檢舉通過的商品進行撤銷*/
	public Product_reportVO updateProduct_report(String prorepno, String checked, String prono){
		Product_reportVO product_reportVO = new Product_reportVO();			
		product_reportVO.setProrepno(prorepno);		
		product_reportVO.setProno(prono);
		product_reportVO.setChecked(checked);
		dao.update(product_reportVO);		
		return product_reportVO;
	}
	
	public void deleteProduct_report(String prorepno){
		dao.delete(prorepno);
	}
	
	public Product_reportVO getOneProduct_report(String prorepno){
		return dao.findByPrimaryKey(prorepno);
	}
	
	public List<Product_reportVO> getAll(){
		return dao.getAll();
	}
}
