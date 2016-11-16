package com.orders.model;

import java.sql.*;

public class OrdersVO {
	private String ordno;
	private String memno;
	private Integer pricesum;
	private Integer total;
	private String payment;
	private String ordstate;
	private Date orddate;
	private String pickname;
	private String pickup;
	private String pickphone;
	private String pickaddr;

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public Integer getPricesum() {
		return pricesum;
	}

	public void setPricesum(Integer pricesum) {
		this.pricesum = pricesum;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getOrdstate() {
		return ordstate;
	}

	public void setOrdstate(String ordstate) {
		this.ordstate = ordstate;
	}

	public Date getOrddate() {
		return orddate;
	}

	public void setOrddate(Date orddate) {
		this.orddate = orddate;
	}

	public String getPickname() {
		return pickname;
	}

	public void setPickname(String pickname) {
		this.pickname = pickname;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getPickphone() {
		return pickphone;
	}

	public void setPickphone(String pickphone) {
		this.pickphone = pickphone;
	}

	public String getPickaddr() {
		return pickaddr;
	}

	public void setPickaddr(String pickaddr) {
		this.pickaddr = pickaddr;
	}

	@Override
	public String toString() {
		return "OrdersVO [ordno=" + ordno + ", memno=" + memno + ", pricesum=" + pricesum + ", total=" + total
				+ ", payment=" + payment + ", ordstate=" + ordstate + ", orddate=" + orddate + ", pickname=" + pickname
				+ ", pickup=" + pickup + ", pickphone=" + pickphone + ", pickaddr=" + pickaddr + "]";
	}

}
