package com.team_report.model;

import java.util.List;

public interface Team_reportDAO_interface {
    public void insert(Team_reportVO team_reportVO);
    public void update(Team_reportVO team_reportVO);
    public void delete(String teamrepno);
    public Team_reportVO findByPrimaryKey(String teamrepno);
    public List<Team_reportVO> getAll();
//  public List<Team_RepoetVO> getAll(Map<String, String[]> map);
}
