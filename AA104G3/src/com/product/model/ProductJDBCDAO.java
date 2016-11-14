/*久未更新*/

package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductJDBCDAO implements ProductDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "project";
	String passwd = "oakveda";

	private static final String INSERT_STMT = "INSERT INTO product VALUES (lpad(pro_seq.nextval,6,0), ?, ?,"
			+ "?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM product order by prono ";
	private static final String GET_ONE_STMT = "SELECT * FROM product where prono = ?";
	private static final String DELETE = "DELETE FROM product where prono = ?";
	private static final String UPDATE = "UPDATE product set proprice = ? where prono = ?";

	@Override
	public void insert(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ProductVO.getStono());
			pstmt.setString(2, ProductVO.getClassno());
			pstmt.setString(3, ProductVO.getProname());
			pstmt.setInt(4, ProductVO.getProprice());
			pstmt.setString(5, ProductVO.getProstate());
			pstmt.setInt(6, ProductVO.getProqty());
			pstmt.setString(7, ProductVO.getPlayernum());
			pstmt.setString(8, ProductVO.getPlayerage());
			pstmt.setString(9, ProductVO.getLang());
			pstmt.setString(10, ProductVO.getPlaytime());
			pstmt.setString(11, ProductVO.getProsumm());
			pstmt.setString(12, ProductVO.getProdesc());

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
	public void update(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ProductVO.getProprice());
			pstmt.setString(2, ProductVO.getProno());

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
	public void delete(String prono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prono);

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
	public ProductVO findByPrimaryKey(String prono) {

		ProductVO ProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prono);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProductVO 也稱為 Domain objects
				ProductVO = new ProductVO();
				ProductVO.setProno(rs.getString(1));
				ProductVO.setStono(rs.getString(2));
				ProductVO.setClassno(rs.getString(3));
				ProductVO.setProname(rs.getString(4));
				ProductVO.setProprice(rs.getInt(5));
				ProductVO.setProstate(rs.getString(6));
				ProductVO.setProqty(rs.getInt(7));
				ProductVO.setAdddate(rs.getDate(8));
				ProductVO.setPlayernum(rs.getString(9));
				ProductVO.setPlayerage(rs.getString(10));
				ProductVO.setLang(rs.getString(11));
				ProductVO.setPlaytime(rs.getString(12));
				ProductVO.setProsumm(rs.getString(13));
				ProductVO.setProdesc(rs.getString(14));
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
		return ProductVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO ProductVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProductVO 也稱為 Domain objects
				ProductVO = new ProductVO();
				ProductVO.setProno(rs.getString(1));
				ProductVO.setStono(rs.getString(2));
				ProductVO.setClassno(rs.getString(3));
				ProductVO.setProname(rs.getString(4));
				ProductVO.setProprice(rs.getInt(5));
				ProductVO.setProstate(rs.getString(6));
				ProductVO.setProqty(rs.getInt(7));
				ProductVO.setAdddate(rs.getDate(8));
				ProductVO.setPlayernum(rs.getString(9));
				ProductVO.setPlayerage(rs.getString(10));
				ProductVO.setLang(rs.getString(11));
				ProductVO.setPlaytime(rs.getString(12));
				ProductVO.setProsumm(rs.getString(13));
				ProductVO.setProdesc(rs.getString(14));
				list.add(ProductVO); // Store the row in the list
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
	public List<ProductVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllByName() {
		// TODO Auto-generated method stub
		return null;
	}

}
