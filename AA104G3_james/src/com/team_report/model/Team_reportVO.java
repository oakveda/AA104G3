package com.team_report.model;

import java.io.Serializable;
import java.sql.Date;

public class Team_reportVO implements Serializable {
	private String teamrepno;
	private String memno;
	private String teamno;
	private String reason;
	private Date repdate;
	private String checked;

	public String getTeamrepno() {
		return teamrepno;
	}

	public void setTeamrepno(String teamrepno) {
		this.teamrepno = teamrepno;
	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getTeamno() {
		return teamno;
	}

	public void setTeamno(String teamno) {
		this.teamno = teamno;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRepdate() {
		return repdate;
	}

	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Team_reportVO [teamrepno=" + teamrepno + ", memno=" + memno + ", teamno=" + teamno + ", reason="
				+ reason + ", repdate=" + repdate + ", checked=" + checked + "]";
	}

}
