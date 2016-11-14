package com.orders_detail.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Orders_detailJDBCDAO implements Orders_detailDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "project";
	String passwd = "oakveda";

	private static final String INSERT_STMT = "INSERT INTO orders_detail VALUES(lpad(orddet_seq.nextval,6,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM orders_detail ORDER BY orddetno";
	private static final String GET_ONE_STMT = "SELECT * FROM orders_detail WHERE orddetno = ?";
	private static final String DELETE = "DELETE FROM orders_detail WHERE orddetno = ?";
	private static final String UPDATE = "UPDATE orders_detail SET procount = ? where orddetno = ?";

	@Override
	public void insert(Orders_detailVO orddetVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, orddetVO.getOrdno());
			pstmt.setString(2, orddetVO.getProno());
			pstmt.setInt(3, orddetVO.getProcount());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(String orddetno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, orddetno);
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(Orders_detailVO orddetVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, orddetVO.getProcount());
			pstmt.setString(2, orddetVO.getOrddetno());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public Orders_detailVO findByPrimaryKey(String orddetno) {
		Orders_detailVO orddetVO = new Orders_detailVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orddetno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetVO = new Orders_detailVO();
				orddetVO.setOrddetno(rs.getString("orddetno"));
				orddetVO.setOrdno(rs.getString("ordno"));
				orddetVO.setProno(rs.getString("prono"));
				orddetVO.setProcount(rs.getInt("procount"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return orddetVO;
	}

	@Override
	public List<Orders_detailVO> getAll() {
		List<Orders_detailVO> list = new ArrayList<Orders_detailVO>();
		Orders_detailVO orddetVO = new Orders_detailVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orddetVO = new Orders_detailVO();
				orddetVO.setOrddetno(rs.getString("orddetno"));
				orddetVO.setOrdno(rs.getString("ordno"));
				orddetVO.setProno(rs.getString("prono"));
				orddetVO.setProcount(rs.getInt("procount"));
				list.add(orddetVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public void insert(Orders_detailVO orddetVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

}
