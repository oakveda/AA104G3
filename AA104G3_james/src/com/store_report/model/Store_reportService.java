package com.store_report.model;

import java.sql.Date;
import java.util.List;

public class Store_reportService {
	private Store_reportDAO_interface dao;

	public Store_reportService() {
		dao = new Store_reportJNDIDAO();
	}

	public Store_reportVO addStore_report(String memno, String stono, String reason) {
		Store_reportVO store_reportVO = new Store_reportVO();
		store_reportVO.setMemno(memno);
		store_reportVO.setStono(stono);
		store_reportVO.setReason(reason);
		store_reportVO.setRepdate(new Date(new java.util.Date().getTime()));		
		dao.insert(store_reportVO);
		return store_reportVO;
	}

	public Store_reportVO updateStore_report(String storepno, String checked, String stono) {
		Store_reportVO store_reportVO = new Store_reportVO();
		store_reportVO.setStorepno(storepno);
		store_reportVO.setStono(stono);
		store_reportVO.setChecked(checked);
		//store_reportVO.setRepdate(new Date(new java.util.Date().getTime()));			
		dao.update(store_reportVO);
		return store_reportVO;
	}

	public void deleteStore_report(String storepno) {
		dao.delete(storepno);
	}

	public Store_reportVO getOneStore_report(String storepno) {
		return dao.findByPrimaryKey(storepno);
	}

	public List<Store_reportVO> getAll() {
		return dao.getAll();
	}

}
