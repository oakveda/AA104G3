/*圖片的表格砍掉，圖片欄位加到產品內*/
package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.*;

public class ProductJNDIDAO implements ProductDAO_interface {

	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product VALUES (lpad(pro_seq.nextval,6,0), ?, ?,"
			+ "?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT prono, stono, classno, proname, proprice, prostate, proqty,"
			+ " adddate, playernum, playerage, lang, playtime, prosumm, prodesc FROM product order by prono ";

	private static final String GET_ALL_STMT_BY_NAME = "SELECT prono, stono, classno, proname, proprice, prostate, proqty,"
			+ " adddate, playernum, playerage, lang, playtime, prosumm, prodesc FROM product order by proname ";

	private static final String GET_ONE_STMT = "SELECT prono, stono, classno, proname, proprice, prostate, proqty,"
			+ " adddate, playernum, playerage, lang, playtime, prosumm, prodesc FROM product where prono = ?";

	private static final String DELETE = "DELETE FROM product where prono = ?";

	private static final String UPDATE = "UPDATE product set classno=?, proprice = ?, proname=?, prostate=?, proqty=?,"
			+ " playernum=?, playerage=?, lang=?, playtime=?, prosumm=?, prodesc=?, propic=? where prono = ?";

	@Override
	public void insert(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
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
			pstmt.setBytes(13, ProductVO.getPropic());
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("A database error occured. " + se.getMessage());
				}
			}
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ProductVO.getClassno());
			pstmt.setInt(2, ProductVO.getProprice());
			pstmt.setString(3, ProductVO.getProname());
			pstmt.setString(4, ProductVO.getProstate());
			pstmt.setInt(5, ProductVO.getProqty());
			pstmt.setString(6, ProductVO.getPlayernum());
			pstmt.setString(7, ProductVO.getPlayerage());
			pstmt.setString(8, ProductVO.getLang());
			pstmt.setString(9, ProductVO.getPlaytime());
			pstmt.setString(10, ProductVO.getProsumm());
			pstmt.setString(11, ProductVO.getProdesc());
			pstmt.setBytes(12, ProductVO.getPropic());
			pstmt.setString(13, ProductVO.getProno());

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
	public void delete(String prono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, prono);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ProductVO findByPrimaryKey(String prono) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prono);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString(1));
				productVO.setStono(rs.getString(2));
				productVO.setClassno(rs.getString(3));
				productVO.setProname(rs.getString(4));
				productVO.setProprice(rs.getInt(5));
				productVO.setProstate(rs.getString(6));
				productVO.setProqty(rs.getInt(7));
				productVO.setAdddate(rs.getDate(8));
				productVO.setPlayernum(rs.getString(9));
				productVO.setPlayerage(rs.getString(10));
				productVO.setLang(rs.getString(11));
				productVO.setPlaytime(rs.getString(12));
				productVO.setProsumm(rs.getString(13));
				productVO.setProdesc(rs.getString(14));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString(1));
				productVO.setStono(rs.getString(2));
				productVO.setClassno(rs.getString(3));
				productVO.setProname(rs.getString(4));
				productVO.setProprice(rs.getInt(5));
				productVO.setProstate(rs.getString(6));
				productVO.setProqty(rs.getInt(7));
				productVO.setAdddate(rs.getDate(8));
				productVO.setPlayernum(rs.getString(9));
				productVO.setPlayerage(rs.getString(10));
				productVO.setLang(rs.getString(11));
				productVO.setPlaytime(rs.getString(12));
				productVO.setProsumm(rs.getString(13));
				productVO.setProdesc(rs.getString(14));
				list.add(productVO); // Store the row in the list
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

	@Override
	public List<ProductVO> getAllByName() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_NAME);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString(1));
				productVO.setStono(rs.getString(2));
				productVO.setClassno(rs.getString(3));
				productVO.setProname(rs.getString(4));
				productVO.setProprice(rs.getInt(5));
				productVO.setProstate(rs.getString(6));
				productVO.setProqty(rs.getInt(7));
				productVO.setAdddate(rs.getDate(8));
				productVO.setPlayernum(rs.getString(9));
				productVO.setPlayerage(rs.getString(10));
				productVO.setLang(rs.getString(11));
				productVO.setPlaytime(rs.getString(12));
				productVO.setProsumm(rs.getString(13));
				productVO.setProdesc(rs.getString(14));
				list.add(productVO); // Store the row in the list
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

	@Override
	public List<ProductVO> getAll(Map<String, String[]> map) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from product" + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			// System.out.println(finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getString(1));
				productVO.setStono(rs.getString(2));
				productVO.setClassno(rs.getString(3));
				productVO.setProname(rs.getString(4));
				productVO.setProprice(rs.getInt(5));
				productVO.setProstate(rs.getString(6));
				productVO.setProqty(rs.getInt(7));
				productVO.setAdddate(rs.getDate(8));
				productVO.setPlayernum(rs.getString(9));
				productVO.setPlayerage(rs.getString(10));
				productVO.setLang(rs.getString(11));
				productVO.setPlaytime(rs.getString(12));
				productVO.setProsumm(rs.getString(13));
				productVO.setProdesc(rs.getString(14));
				list.add(productVO);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
