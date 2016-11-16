package com.store_report.model;

import java.sql.Date;

public class Store_reportVO {
	private String storepno;
	private String memno;
	private String stono;
	private String reason;
	private Date repdate;
	private String checked;
	/**
	 * @return the storepno
	 */
	public String getStorepno() {
		return storepno;
	}
	/**
	 * @param storepno the storepno to set
	 */
	public void setStorepno(String storepno) {
		this.storepno = storepno;
	}
	/**
	 * @return the memno
	 */
	public String getMemno() {
		return memno;
	}
	/**
	 * @param memno the memno to set
	 */
	public void setMemno(String memno) {
		this.memno = memno;
	}
	/**
	 * @return the stono
	 */
	public String getStono() {
		return stono;
	}
	/**
	 * @param stono the stono to set
	 */
	public void setStono(String stono) {
		this.stono = stono;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the repdate
	 */
	public Date getRepdate() {
		return repdate;
	}
	/**
	 * @param repdate the repdate to set
	 */
	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}
	/**
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "Store_reportVO [storepno=" + storepno + ", memno=" + memno + ", stono=" + stono + ", reason=" + reason
				+ ", repdate=" + repdate + ", checked=" + checked + "]";
	}
	
	
	
}
