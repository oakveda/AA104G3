package com.product_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_commentJNDIDAO implements Product_commentDAO_interface{
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
	private static final String INSERT_STMT = "INSERT INTO product_comment (procomno,memno,prono,comdetail,comdate,comscore)"
			+" VALUES (LPAD(product_comment_seq.NEXTVAL,6,0),?,?,?,?,?)";

	private static final String UPDATE = "UPDATE product_comment set memno=?,prono=?,comdetail=?,comdate=?,comscore=? where procomno=?";

	private static final String DELETE = "DELETE FROM product_comment where procomno=?";

	private static final String GET_ALL_STMT = "SELECT procomno,memno,prono,comdetail,comdate,comscore FROM product_comment ";

	private static final String GET_ONE_STMT = "SELECT procomno,memno,prono,comdetail,comdate,comscore  FROM product_comment where procomno=? ";
	
	/*±qprono¨Ó¿z¿ï*/
	private static final String GET_ALL_ByProno = "SELECT procomno,memno,prono,comdetail,comdate,comscore FROM product_comment where prono = ?";
	
	@Override
	public void insert(Product_commentVO product_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_commentVO.getMemno());
			pstmt.setString(2, product_commentVO.getProno());
			pstmt.setString(3, product_commentVO.getComdetail());
			pstmt.setDate(4, product_commentVO.getComdate());
			pstmt.setDouble(5, product_commentVO.getComscore());
			

			System.out.println(pstmt.executeUpdate());

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String procomno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, procomno);
			
			

			System.out.println(pstmt.executeUpdate());

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Product_commentVO product_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_commentVO.getMemno());
			pstmt.setString(2, product_commentVO.getProno());
			pstmt.setString(3, product_commentVO.getComdetail());
			pstmt.setDate(4, product_commentVO.getComdate());
			pstmt.setDouble(5, product_commentVO.getComscore());
			pstmt.setString(6, product_commentVO.getProcomno());
			
			

			System.out.println(pstmt.executeUpdate());

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Product_commentVO findByPrimaryKey(String procomno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Product_commentVO product_CommentVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, procomno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				product_CommentVO = new Product_commentVO();
				product_CommentVO.setProcomno(rs.getString("procomno"));
				product_CommentVO.setMemno(rs.getString("memno"));
				product_CommentVO.setProno(rs.getString("prono"));
				product_CommentVO.setComdetail(rs.getString("comdetail"));
				product_CommentVO.setComdate(rs.getDate("comdate"));
				product_CommentVO.setComscore(rs.getDouble("comscore"));
			}
			


			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return product_CommentVO;
	}

	@Override
	public List<Product_commentVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Product_commentVO> proComList = new ArrayList<Product_commentVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Product_commentVO product_CommentVO = new Product_commentVO();
				product_CommentVO.setProcomno(rs.getString("procomno"));
				product_CommentVO.setMemno(rs.getString("memno"));
				product_CommentVO.setProno(rs.getString("prono"));
				product_CommentVO.setComdetail(rs.getString("comdetail"));
				product_CommentVO.setComdate(rs.getDate("comdate"));
				product_CommentVO.setComscore(rs.getDouble("comscore"));
				proComList.add(product_CommentVO);
			}
			


			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return proComList;
	}
	
	@Override
	public List<Product_commentVO> getAll(String prono) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Product_commentVO> proComList = new ArrayList<Product_commentVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ByProno);
			pstmt.setString(1, prono);

			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Product_commentVO product_CommentVO = new Product_commentVO();
				product_CommentVO.setProcomno(rs.getString("procomno"));
				product_CommentVO.setMemno(rs.getString("memno"));
				product_CommentVO.setProno(rs.getString("prono"));
				product_CommentVO.setComdetail(rs.getString("comdetail"));
				product_CommentVO.setComdate(rs.getDate("comdate"));
				product_CommentVO.setComscore(rs.getDouble("comscore"));
				proComList.add(product_CommentVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return proComList;
	}

	
}
