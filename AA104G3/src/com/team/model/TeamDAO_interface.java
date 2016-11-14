package com.team.model;

import java.util.*;

public interface TeamDAO_interface {
	public int insert(TeamVO teamVO);
	public int update(TeamVO teamVO);
	public int delete(String teamno);
	public TeamVO findByPrimaryKey(String teamno);
	public List<TeamVO> getAll();

}