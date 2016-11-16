package com.orders_detail.model;

public class Orders_detailVO {
	private String orddetno;
	private String ordno;
	private String prono;
	private Integer procount;
	/**
	 * @return the orddetno
	 */
	public String getOrddetno() {
		return orddetno;
	}
	/**
	 * @param orddetno the orddetno to set
	 */
	public void setOrddetno(String orddetno) {
		this.orddetno = orddetno;
	}
	/**
	 * @return the ordno
	 */
	public String getOrdno() {
		return ordno;
	}
	/**
	 * @param ordno the ordno to set
	 */
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	/**
	 * @return the prono
	 */
	public String getProno() {
		return prono;
	}
	/**
	 * @param prono the prono to set
	 */
	public void setProno(String prono) {
		this.prono = prono;
	}
	/**
	 * @return the procount
	 */
	public Integer getProcount() {
		return procount;
	}
	/**
	 * @param procount the procount to set
	 */
	public void setProcount(Integer procount) {
		this.procount = procount;
	}
	@Override
	public String toString() {
		return "Orders_detailVO [orddetno=" + orddetno + ", ordno=" + ordno + ", prono=" + prono + ", procount="
				+ procount + "]";
	}
	
	
	
}
