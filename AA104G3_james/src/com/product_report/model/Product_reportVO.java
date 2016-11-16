package com.product_report.model;

import java.sql.Date;

public class Product_reportVO {
	private String prorepno;
	private String memno;
	private String prono;
	private String reason;
	private Date repdate;
	private String checked;
	/**
	 * @return the prorepno
	 */
	public String getProrepno() {
		return prorepno;
	}
	/**
	 * @param prorepno the prorepno to set
	 */
	public void setProrepno(String prorepno) {
		this.prorepno = prorepno;
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
		return "Product_reportVO [prorepno=" + prorepno + ", memno=" + memno + ", prono=" + prono + ", reason=" + reason
				+ ", repdate=" + repdate + ", checked=" + checked + "]";
	}
	
	
}
