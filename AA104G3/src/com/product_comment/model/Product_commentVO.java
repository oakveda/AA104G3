package com.product_comment.model;

import java.io.Serializable;
import java.sql.Date;

public class Product_commentVO implements Serializable{
	private String procomno;
	private String memno;
	private String prono;
	private String comdetail;
	private Date comdate;
	private Double comscore;
	
	public String getProcomno() {
		return procomno;
	}
	public void setProcomno(String procomno) {
		this.procomno = procomno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getProno() {
		return prono;
	}
	public void setProno(String prono) {
		this.prono = prono;
	}
	public String getComdetail() {
		return comdetail;
	}
	public void setComdetail(String comdetail) {
		this.comdetail = comdetail;
	}
	public Date getComdate() {
		return comdate;
	}
	public void setComdate(Date comdate) {
		this.comdate = comdate;
	}
	public Double getComscore() {
		return comscore;
	}
	public void setComscore(Double comscore) {
		this.comscore = comscore;
	}

}
