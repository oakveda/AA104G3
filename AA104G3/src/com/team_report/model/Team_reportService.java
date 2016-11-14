package com.team_report.model;

import java.sql.Date;
import java.util.List;

public class Team_reportService {
	private Team_reportDAO_interface dao;
	
	public Team_reportService(){
		dao = new Team_reportJNDIDAO();
	}
		
	public Team_reportVO addTeam_report(String memno, String teamno, String reason){
		Team_reportVO team_reportVO = new Team_reportVO();
		team_reportVO.setMemno(memno); 
		team_reportVO.setTeamno(teamno); 
		team_reportVO.setReason(reason);
		team_reportVO.setRepdate(new Date(new java.util.Date().getTime()));
		dao.insert(team_reportVO);
		return team_reportVO;
	}
	
	/*對檢舉通過的揪團進行撤銷*/
	public Team_reportVO updateTeam_report(String teamrepno, String checked, String teamno){
		Team_reportVO team_reportVO = new Team_reportVO();
		team_reportVO.setTeamrepno(teamrepno);
		team_reportVO.setChecked(checked);
		team_reportVO.setTeamno(teamno);
		dao.update(team_reportVO);
		return team_reportVO;
	}
	
	public void deleteTeam_report(String teamrepno){
		dao.delete(teamrepno);
	}
	
	public Team_reportVO getOneTeam_report(String teamrepno){
		return dao.findByPrimaryKey(teamrepno);
	}
	
	public List<Team_reportVO> getAll(){
		return dao.getAll();
	}
	
}
