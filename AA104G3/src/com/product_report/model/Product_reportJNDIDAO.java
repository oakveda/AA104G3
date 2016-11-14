package com.product_report.model;

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

public class Product_reportJNDIDAO implements Product_reportDAO_interface {
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB"); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static final String INSERT_STMT = "INSERT INTO product_report VALUES (lpad(prorep_seq.nextval,6,0), ?, ?, ?, ?, 0)";
	private static final String GET_ALL_STMT = "SELECT * FROM product_report where checked = 0  order by prorepno";
	private static final String GET_ONE_STMT = "SELECT * FROM product_report where prorepno = ?";
	private static final String DELETE = "DELETE FROM product_report where prorepno = ?";
	private static final String UPDATE = "UPDATE product_report set checked = ? where prorepno = ?";
	private static final String UPDATESAME = "UPDATE product_report set checked = ? where prono = ?";
	private static final String REVOKED = "UPDATE product set prostate = 2 where prono = ?";


	@Override
	public void insert(Product_reportVO prorepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prorepVO.getMemno());
			pstmt.setString(2, prorepVO.getProno());
			pstmt.setString(3, prorepVO.getReason());
			pstmt.setDate(4, prorepVO.getRepdate());			
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public void update(Product_reportVO prorepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			con.setAutoCommit(false);
			
			/*對檢舉通過的商品狀態改為撤銷，並更改相同商品的檢舉狀態*/
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, prorepVO.getChecked());
			pstmt.setString(2, prorepVO.getProrepno());
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(UPDATESAME);
			pstmt.setString(1, prorepVO.getChecked());
			pstmt.setString(2, prorepVO.getProno());
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(REVOKED);			
			pstmt.setString(1, prorepVO.getProno());
			pstmt.executeUpdate();
			con.commit();
			
			con.setAutoCommit(true);

			// Handle any driver errors
		}catch (SQLException se) {
			if(con!=null){
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
	public void delete(String prorepno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prorepno);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public Product_reportVO findByPrimaryKey(String prorepno) {
		Product_reportVO prorepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prorepno);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				prorepVO = new Product_reportVO();
				prorepVO.setProrepno(rs.getString("prorepno"));
				prorepVO.setMemno(rs.getString("memno"));
				prorepVO.setProno(rs.getString("prono"));
				prorepVO.setReason(rs.getString("reason"));
				prorepVO.setRepdate(rs.getDate("repdate"));
				prorepVO.setChecked(rs.getString("checked"));
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return prorepVO;
	}

	@Override
	public List<Product_reportVO> getAll() {
		List<Product_reportVO>  list = new ArrayList<Product_reportVO>();
		Product_reportVO prorepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				prorepVO = new Product_reportVO();
				prorepVO.setProrepno(rs.getString("prorepno"));
				prorepVO.setMemno(rs.getString("memno"));
				prorepVO.setProno(rs.getString("prono"));
				prorepVO.setReason(rs.getString("reason"));
				prorepVO.setRepdate(rs.getDate("repdate"));
				prorepVO.setChecked(rs.getString("checked"));
				list.add(prorepVO);
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
