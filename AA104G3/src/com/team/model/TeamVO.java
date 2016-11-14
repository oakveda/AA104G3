package com.team.model;

public class TeamVO implements java.io.Serializable {
	public String teamno; // pk
	public String memno; // fk
	public String resvno; // fk
	public String teamtitle;
	public String teamdesc;
	public Integer teamcount;
	public Integer teamstate;

	public String getTeamno() {
		return teamno;
	}

	public void setTeamno(String teamno) {
		this.teamno = teamno;
	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getResvno() {
		return resvno;
	}

	public void setResvno(String resvno) {
		this.resvno = resvno;
	}

	public Integer getTeamcount() {
		return teamcount;
	}

	public void setTeamcount(Integer teamcount) {
		this.teamcount = teamcount;
	}

	public String getTeamdesc() {
		return teamdesc;
	}

	public void setTeamdesc(String teamdesc) {
		this.teamdesc = teamdesc;
	}

	public Integer getTeamstate() {
		return teamstate;
	}

	public void setTeamstate(Integer teamstate) {
		this.teamstate = teamstate;
	}

	public String getTeamtitle() {
		return teamtitle;
	}

	public void setTeamtitle(String teamtitle) {
		this.teamtitle = teamtitle;
	}
}