package com.member.model;

import java.sql.Date;
import java.util.Arrays;
import java.io.Serializable;

public class MemberVO implements Serializable{
	private String memno;
	private String memid;
	private String mempsw;
	private String memname;
	private String mememail;
	private String memaddr;
	private String memphone;
	private byte[] mempic;
	private Date regdate;
	private String memnick;;
	private String memident;
	private Date membirth;
	private String memstate;
	private String memgender;
	
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getMempsw() {
		return mempsw;
	}
	public void setMempsw(String mempsw) {
		this.mempsw = mempsw;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public String getMememail() {
		return mememail;
	}
	public void setMememail(String mememail) {
		this.mememail = mememail;
	}
	public String getMemaddr() {
		return memaddr;
	}
	public void setMemaddr(String memaddr) {
		this.memaddr = memaddr;
	}
	public String getMemphone() {
		return memphone;
	}
	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}
	public byte[] getMempic() {
		return mempic;
	}
	public void setMempic(byte[] mempic) {
		this.mempic = mempic;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getMemnick() {
		return memnick;
	}
	public void setMemnick(String memnick) {
		this.memnick = memnick;
	}
	public String getMemident() {
		return memident;
	}
	public void setMemident(String memident) {
		this.memident = memident;
	}
	public Date getMembirth() {
		return membirth;
	}
	public void setMembirth(Date membirth) {
		this.membirth = membirth;
	}
	public String getMemstate() {
		return memstate;
	}
	public void setMemstate(String memstate) {
		this.memstate = memstate;
	}
	public String getMemgender() {
		return memgender;
	}
	public void setMemgender(String memgender) {
		this.memgender = memgender;
	}
	@Override
	public String toString() {
		return "MemberVO [memno=" + memno + ", memid=" + memid + ", mempsw=" + mempsw + ", memname=" + memname
				+ ", mememail=" + mememail + ", memaddr=" + memaddr + ", memphone=" + memphone + ", mempic="
				+ Arrays.toString(mempic) + ", regdate=" + regdate + ", memnick=" + memnick + ", memident=" + memident
				+ ", membirth=" + membirth + ", memstate=" + memstate + ", memgender=" + memgender + "]";
	}

	
}
