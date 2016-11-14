package com.team.model;

import java.util.List;

public class TeamService {
	private TeamDAO_interface dao;

	public TeamService() {
		dao = new TeamDAO();
	}

	public TeamVO addTeam(String teamtitle, String teamdesc, String memno, String resvno,
			Integer teamcount, Integer teamstate) {

		TeamVO teamVO = new TeamVO();

		teamVO.setMemno(memno);
		teamVO.setResvno(resvno);
		teamVO.setTeamcount(teamcount);
		teamVO.setTeamdesc(teamdesc);
		teamVO.setTeamstate(teamstate);
		teamVO.setTeamtitle(teamtitle);
		dao.insert(teamVO);

		return teamVO;
	}

	public TeamVO updateTeam(String teamno, String teamtitle, String teamdesc, String memno, String resvno,
			Integer teamcount, Integer teamstate) {

		TeamVO teamVO = new TeamVO();

		teamVO.setTeamno(teamno);
		teamVO.setMemno(memno);
		teamVO.setResvno(resvno);
		teamVO.setTeamcount(teamcount);
		teamVO.setTeamdesc(teamdesc);
		teamVO.setTeamstate(teamstate);
		teamVO.setTeamtitle(teamtitle);
		dao.update(teamVO);

		return teamVO;
	}

	public void deleteTeam(String teamno) {
		dao.delete(teamno);
	}

	public TeamVO getOneTeam(String teamno) {
		return dao.findByPrimaryKey(teamno);
	}

	public List<TeamVO> getAll() {
		return dao.getAll();
	}
}