package com.team.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class TeamDAO implements TeamDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO team(TEAMNO,MEMNO,RESVNO,TEAMCOUNT,TEAMDESC,TEAMSTATE,TEAMTITLE) VALUES(lpad(teamno_SEQ.nextval,6,0), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM team where teamstate = 1 order by teamno";
	private static final String GET_ONE_STMT = "SELECT * FROM team where teamno = ?";
	private static final String DELETE = "DELETE FROM team where teamno = ?";
	private static final String UPDATE = "UPDATE team set memno = ?, resvno = ?, teamcount = ?, teamdesc = ?, teamstate = ?, teamtitle = ? where teamno = ?";

	@Override
	public int insert(TeamVO teamVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, teamVO.getMemno());
			pstmt.setString(2, teamVO.getResvno());
			pstmt.setInt(3, teamVO.getTeamcount());
			pstmt.setString(4, teamVO.getTeamdesc());
			pstmt.setInt(5, teamVO.getTeamstate());
			pstmt.setString(6, teamVO.getTeamtitle());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new RuntimeException("A database error occured." + sqle.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(TeamVO teamVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, teamVO.getMemno());
			pstmt.setString(2, teamVO.getResvno());
			pstmt.setInt(3, teamVO.getTeamcount());
			pstmt.setString(4, teamVO.getTeamdesc());
			pstmt.setInt(5, teamVO.getTeamstate());
			pstmt.setString(6, teamVO.getTeamtitle());
			pstmt.setString(7, teamVO.getTeamno());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new RuntimeException("A database error occured." + sqle.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int delete(String teamno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, teamno);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new RuntimeException("A database error occured." + sqle.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public TeamVO findByPrimaryKey(String teamno) {
		TeamVO teamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, teamno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teamVO = new TeamVO();
				teamVO.setTeamno(rs.getString("teamno"));
				teamVO.setTeamtitle(rs.getString("teamtitle"));
				teamVO.setTeamcount(rs.getInt("teamcount"));
				teamVO.setTeamdesc(rs.getString("teamdesc"));
				teamVO.setResvno(rs.getString("resvno"));
				teamVO.setTeamstate(rs.getInt("teamstate"));
				teamVO.setMemno(rs.getString("memno"));
			}
		} catch (SQLException sqle) {
			throw new RuntimeException("A database error occured." + sqle.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return teamVO;
	}

	@Override
	public List<TeamVO> getAll() {
		List<TeamVO> list = new ArrayList<TeamVO>();
		TeamVO teamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teamVO = new TeamVO();
				teamVO.setTeamno(rs.getString("teamno"));
				teamVO.setTeamtitle(rs.getString("teamtitle"));
				teamVO.setTeamcount(rs.getInt("teamcount"));
				teamVO.setTeamdesc(rs.getString("teamdesc"));
				teamVO.setResvno(rs.getString("resvno"));
				teamVO.setTeamstate(rs.getInt("teamstate"));
				teamVO.setMemno(rs.getString("memno"));
				list.add(teamVO);
			}
		} catch (SQLException sqle) {
			throw new RuntimeException("A database error occured." + sqle.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	/*
	 * public static void main(String[] args) { TeamDAO dao = new TeamDAO();
	 * 
	 * // insert TeamVO teamVO1 = new TeamVO(); teamVO1.setMemno("000003");
	 * teamVO1.setTeamtitle("測試資料"); teamVO1.setTeamdesc("This is test data");
	 * teamVO1.setResvno("000005"); teamVO1.setTeamcount(3);
	 * teamVO1.setTeamstate(1); int updateCount_insert = dao.insert(teamVO1);
	 * System.out.println(updateCount_insert);
	 * 
	 * 
	 * // update TeamVO teamVO2 = new TeamVO(); teamVO2.setTeamcount(10);
	 * teamVO2.setTeamtitle("測試資料"); teamVO2.setTeamdesc("Another test data");
	 * teamVO2.setTeamno("000004"); teamVO2.setMemno("000003");
	 * teamVO2.setResvno("000005"); teamVO2.setTeamstate(0); int
	 * updateCount_update = dao.update(teamVO2);
	 * System.out.println(updateCount_update);
	 * 
	 * 
	 * // delete int updateCount_delete = dao.delete("000004");
	 * System.out.println(updateCount_delete);
	 * 
	 * // single query TeamVO teamVO3 = dao.findByPrimaryKey("000001");
	 * System.out.print(teamVO3.getTeamno() + " ");
	 * System.out.print(teamVO3.getTeamtitle() + " ");
	 * System.out.print(teamVO3.getTeamdesc() + " ");
	 * System.out.print(teamVO3.getMemno() + " ");
	 * System.out.print(teamVO3.getResvno() + " ");
	 * System.out.print(teamVO3.getTeamcount() + " ");
	 * System.out.print(teamVO3.getTeamstate() + " "); System.out.println();
	 * 
	 * 
	 * // query all List<TeamVO> list = dao.getAll(); for(TeamVO team : list){
	 * System.out.print(team.getTeamno() + " ");
	 * System.out.print(team.getTeamtitle() + " ");
	 * System.out.print(team.getTeamdesc() + " ");
	 * System.out.print(team.getMemno() + " ");
	 * System.out.print(team.getResvno() + " ");
	 * System.out.print(team.getTeamcount() + " ");
	 * System.out.println(team.getTeamstate() + " "); } }
	 */
}
