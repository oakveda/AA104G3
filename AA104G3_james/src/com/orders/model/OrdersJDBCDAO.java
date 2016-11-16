package com.orders.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.cart.model.CartVO;

public class OrdersJDBCDAO implements OrdersDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "project";
	String passwd = "oakveda";

	private static final String INSERT_STMT = "INSERT INTO orders VALUES(lpad(orders_seq.nextval,6,0), ?, ?, ?, '0', '0', SYSDATE, ?, '0', ?)";
	private static final String DELETE = "DELETE FROM orders WHERE ordno = ?";
	private static final String UPDATE = "UPDATE orders SET pickname = ? where memno = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM orders WHERE ordno = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM orders order by ordno";

	@Override
	public void insert(OrdersVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ordVO.getMemno());
			pstmt.setInt(2, ordVO.getPricesum());
			pstmt.setInt(3, ordVO.getTotal());
			pstmt.setString(4, ordVO.getPickname());
			pstmt.setString(5, ordVO.getPickaddr());
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
	public void delete(String ordno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ordno);
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
	public void update(OrdersVO ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ordVO.getPickname());
			pstmt.setString(2, ordVO.getMemno());
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
	public OrdersVO findByPrimaryKey(String oreno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrdersVO ordVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, "000001");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordVO = new OrdersVO();
				ordVO.setOrdno(rs.getString("ordno"));
				ordVO.setMemno(rs.getString("memno"));
				ordVO.setOrddate(rs.getDate("orddate"));
				ordVO.setOrdstate(rs.getString("ordstate"));
				ordVO.setPayment(rs.getString("payment"));
				ordVO.setPickaddr(rs.getString("pickaddr"));
				ordVO.setPickname(rs.getString("pickname"));
				ordVO.setPickup(rs.getString("pickup"));
				ordVO.setPricesum(rs.getInt("pricesum"));
				ordVO.setTotal(rs.getInt("total"));
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
		return ordVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrdersVO ordVO = null;
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordVO = new OrdersVO();
				ordVO.setOrdno(rs.getString("ordno"));
				ordVO.setMemno(rs.getString("memno"));
				ordVO.setOrddate(rs.getDate("orddate"));
				ordVO.setOrdstate(rs.getString("ordstate"));
				ordVO.setPayment(rs.getString("payment"));
				ordVO.setPickaddr(rs.getString("pickaddr"));
				ordVO.setPickname(rs.getString("pickname"));
				ordVO.setPickup(rs.getString("pickup"));
				ordVO.setPricesum(rs.getInt("pricesum"));
				ordVO.setTotal(rs.getInt("total"));
				list.add(ordVO);
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
	public void revoke(String ordno) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrdersVO> getAllByMemno(String memno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithOrders_detail(OrdersVO ordersVO, LinkedHashSet<CartVO> list) {
		// TODO Auto-generated method stub

	}

}
