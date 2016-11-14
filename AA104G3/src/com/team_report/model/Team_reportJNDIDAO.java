package com.team_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class Team_reportJNDIDAO implements Team_reportDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO team_report VALUES (lpad(teamrepno_SEQ.nextval,6,0), ?, ?, ?, ?, 0)";
	private static final String GET_ALL_STMT = "SELECT * FROM team_report where checked = 0  order by teamrepno";
	private static final String GET_ONE_STMT = "SELECT * FROM team_report where teamrepno = ?";
	private static final String DELETE = "DELETE FROM team_report where teamrepno = ?";
	private static final String UPDATE = "UPDATE team_report set checked = ? where teamrepno = ?";
	private static final String UPDATESAME = "UPDATE team_report set checked = ? where teamno = ?";
	private static final String REVOKED = "UPDATE team set teamstate = 2 where teamno = ?";

	@Override
	public void insert(Team_reportVO team_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, team_reportVO.getMemno());
			pstmt.setString(2, team_reportVO.getTeamno());
			pstmt.setString(3, team_reportVO.getReason());
			pstmt.setDate(4, team_reportVO.getRepdate());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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

	}

	@Override
	public void update(Team_reportVO team_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			/* 對檢舉通過的商品狀態改為撤銷，並更改相同商品的檢舉狀態 */
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, team_reportVO.getChecked());
			pstmt.setString(2, team_reportVO.getTeamrepno());
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(UPDATESAME);
			pstmt.setString(1, team_reportVO.getChecked());
			pstmt.setString(2, team_reportVO.getTeamno());
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(REVOKED);
			pstmt.setString(1, team_reportVO.getTeamno());
			pstmt.executeUpdate();			
			con.commit();

			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {					
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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

	}

	@Override
	public void delete(String teamrepno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teamrepno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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

	}

	@Override
	public Team_reportVO findByPrimaryKey(String teamrepno) {
		Team_reportVO team_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, teamrepno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				team_reportVO = new Team_reportVO();
				team_reportVO.setTeamrepno(rs.getString("teamrepno"));
				team_reportVO.setMemno(rs.getString("memno"));
				team_reportVO.setTeamno(rs.getString("teamno"));
				team_reportVO.setReason(rs.getString("reason"));
				team_reportVO.setRepdate(rs.getDate("repdate"));
				team_reportVO.setChecked(rs.getString("checked"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return team_reportVO;
	}

	@Override
	public List<Team_reportVO> getAll() {
		List<Team_reportVO> list = new ArrayList<Team_reportVO>();
		Team_reportVO team_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				team_reportVO = new Team_reportVO();
				team_reportVO.setTeamrepno(rs.getString("teamrepno"));
				team_reportVO.setMemno(rs.getString("memno"));
				team_reportVO.setTeamno(rs.getString("teamno"));
				team_reportVO.setReason(rs.getString("reason"));
				team_reportVO.setRepdate(rs.getDate("repdate"));
				team_reportVO.setChecked(rs.getString("checked"));
				list.add(team_reportVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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

}
