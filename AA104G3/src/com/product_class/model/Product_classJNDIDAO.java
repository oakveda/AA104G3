package com.product_class.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class Product_classJNDIDAO implements Product_classDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO product_class(classno, classname) VALUES (lpad(class_seq.nextval,6,0), ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM product_class ORDER BY classno";
	private static final String GET_ONE_STMT = "SELECT * FROM product_class where classno = ?";
	private static final String DELETE = "DELETE FROM product_class where classno = ?";
	private static final String UPDATE = "UPDATE product_class SET classname= ? where classno= ?";
	private static final String DELETE_PRODUCTs = "DELETE FROM product where classno = ?";
	private static final String Get_Prodcut_ByClassno_STMT = "SELECT * from product where classno=? order by prono";

	@Override
	public void insert(Product_classVO product_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, product_classVO.getClassname());

			pstmt.executeUpdate();

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
	public void update(Product_classVO product_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_classVO.getClassname());
			pstmt.setString(2, product_classVO.getClassno());

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
	public void delete(String classno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {

			con = ds.getConnection();

			con.setAutoCommit(false);
			// 先刪產品
			pstmt = con.prepareStatement(DELETE_PRODUCTs);
			pstmt.setString(1, classno);
			count = pstmt.executeUpdate();
			// 在刪類別
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, classno);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除類別"+ classno +"，共"+count +"款遊戲被刪除");

			// Handle any driver errors
		} catch (SQLException se) {

			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("rollback error" + e.getMessage());
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
	public Product_classVO findByPrimaryKey(String classno) {

		Product_classVO product_classVO = new Product_classVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, classno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_classVO = new Product_classVO();
				product_classVO.setClassno(rs.getString(1));
				product_classVO.setClassname(rs.getString(2));
			}
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
		return product_classVO;
	}

	@Override
	public List<Product_classVO> getAll() {
		List<Product_classVO> list = new ArrayList<Product_classVO>();
		Product_classVO product_classVO = new Product_classVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_classVO = new Product_classVO();
				product_classVO.setClassno(rs.getString(1));
				product_classVO.setClassname(rs.getString(2));
				list.add(product_classVO);
			}
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
	public Set<ProductVO> getProductsByClassno(String classno) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO prodcutVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Prodcut_ByClassno_STMT);
			pstmt.setString(1, classno);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				prodcutVO = new ProductVO();
				prodcutVO.setProno(rs.getString(1));
				prodcutVO.setStono(rs.getString(2));
				prodcutVO.setClassno(rs.getString(3));
				prodcutVO.setProname(rs.getString(4));
				prodcutVO.setProprice(rs.getInt(5));
				prodcutVO.setProstate(rs.getString(6));
				prodcutVO.setProqty(rs.getInt(7));
				prodcutVO.setAdddate(rs.getDate(8));
				prodcutVO.setPlayernum(rs.getString(9));
				prodcutVO.setPlayerage(rs.getString(10));
				prodcutVO.setLang(rs.getString(11));
				prodcutVO.setPlaytime(rs.getString(12));
				prodcutVO.setProsumm(rs.getString(13));
				prodcutVO.setProdesc(rs.getString(14));
				set.add(prodcutVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

}
