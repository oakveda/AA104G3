package com.product.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class ProductVO implements Serializable {
	private String prono;
	private String stono;
	private String classno;
	private String proname;
	private Integer proprice;
	private String prostate;
	private Integer proqty;
	private Date adddate;
	private String playernum;
	private String playerage;
	private String lang;
	private String playtime;
	private String prosumm;
	private String prodesc;
	private byte[] propic;
	
	public String getProno() {
		return prono;
	}
	public void setProno(String prono) {
		this.prono = prono;
	}
	public String getStono() {
		return stono;
	}
	public void setStono(String stono) {
		this.stono = stono;
	}
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public Integer getProprice() {
		return proprice;
	}
	public void setProprice(Integer proprice) {
		this.proprice = proprice;
	}
	public String getProstate() {
		return prostate;
	}
	public void setProstate(String prostate) {
		this.prostate = prostate;
	}
	public Integer getProqty() {
		return proqty;
	}
	public void setProqty(Integer proqty) {
		this.proqty = proqty;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public String getPlayernum() {
		return playernum;
	}
	public void setPlayernum(String playernum) {
		this.playernum = playernum;
	}
	public String getPlayerage() {
		return playerage;
	}
	public void setPlayerage(String playerage) {
		this.playerage = playerage;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getPlaytime() {
		return playtime;
	}
	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}
	public String getProsumm() {
		return prosumm;
	}
	public void setProsumm(String prosumm) {
		this.prosumm = prosumm;
	}
	public String getProdesc() {
		return prodesc;
	}
	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}
	
	public void setPropic(byte[] propic){
		this.propic=propic;
	}
	public byte[] getPropic(){
		return propic;
	}
	
	@Override
	public String toString() {
		return "ProductVO [prono=" + prono + ", stono=" + stono + ", classno=" + classno + ", proname=" + proname
				+ ", proprice=" + proprice + ", prostate=" + prostate + ", proqty=" + proqty + ", adddate=" + adddate
				+ ", playernum=" + playernum + ", playerage=" + playerage + ", lang=" + lang + ", playtime=" + playtime
				+ ", prosumm=" + prosumm + ", prodesc=" + prodesc + ", propic=" + Arrays.toString(propic) + "]";
	}		
	
}
