package com.store_report.model;

import java.util.List;

import com.store_report.model.*;
import java.sql.*;



public class daotest {

	public static void main(String[] args) {
		Store_reportJDBCDAO dao = new Store_reportJDBCDAO();
		Store_reportVO vo = new Store_reportVO();
		
//		insert
//		vo.setMemno("000001");
//		vo.setStono("000001");		
//		vo.setRepdate(new Date(new java.util.Date().getTime()));
//		vo.setChecked("0");
//		vo.setReason("到處都是蒼蠅");
//		dao.insert(vo);
		
//		delete
//		dao.delete("000002");
		
//		update
//		vo.setStorepno("000001");
//		vo.setChecked("1");
//		dao.update(vo);
		
//		find one
//		vo = dao.findByPrimaryKey("000001");
//		System.out.println(vo.getStorepno()+" "+vo.getMemno()+" "+vo.getStono()+" "+vo.getReason()+" "+vo.getRepdate()+" "+vo.getChecked());
		
//		find all
		List<Store_reportVO> list = dao.getAll();
		for(Store_reportVO storepvo : list){
			System.out.println(storepvo.getStorepno()+" "+storepvo.getMemno()+" "+storepvo.getStono()+" "+storepvo.getReason()+" "+storepvo.getRepdate()+" "+storepvo.getChecked());
		}

	}

}
