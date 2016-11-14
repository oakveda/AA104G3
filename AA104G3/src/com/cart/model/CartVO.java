package com.cart.model;

import java.io.Serializable;

public class CartVO implements Serializable {
	private String memno;
	private String prono;
	private Integer procount;

	/**
	 * @return the memno
	 */
	public String getMemno() {
		return memno;
	}

	/**
	 * @param memno
	 *            the memno to set
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
	 * @param prono
	 *            the prono to set
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
	 * @param procount
	 *            the procount to set
	 */
	public void setProcount(Integer procount) {
		this.procount = procount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartVO [memno=" + memno + ", prono=" + prono + ", procount=" + procount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memno == null) ? 0 : memno.hashCode());		
		result = prime * result + ((prono == null) ? 0 : prono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVO other = (CartVO) obj;
		if (memno == null) {
			if (other.memno != null)
				return false;
		} else if (!memno.equals(other.memno))
			return false;		
		if (prono == null) {
			if (other.prono != null)
				return false;
		} else if (!prono.equals(other.prono))
			return false;
		return true;
	}

}
