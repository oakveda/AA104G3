package com.member.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class MemberDAO implements MemberDAO_interface{

	private static DataSource ds =null;
	static{
		try{
			Context ctx = new InitialContext();
			ds= (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO member (memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender)"
			+ " VALUES (LPAD(member_seq.NEXTVAL,6,0),?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE member set memid=?,mempsw=?,memname=?,mememail=?,memaddr=?,memphone=?,mempic=?,regdate=?,memnick=?,memident=?,membirth=?,memstate=?,memgender=? where memno=?";

	private static final String DELETE = "DELETE FROM member where memno=?";

	private static final String GET_ALL_STMT = "SELECT memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender FROM member order by memno";

	private static final String GET_ONE_STMT = "SELECT memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender FROM member where memno=? ";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMemid());
			pstmt.setString(2, memberVO.getMempsw());
			pstmt.setString(3, memberVO.getMemname());
			pstmt.setString(4, memberVO.getMememail());
			pstmt.setString(5, memberVO.getMemaddr());
			pstmt.setString(6, memberVO.getMemphone());
			pstmt.setBytes(7, memberVO.getMempic());
			pstmt.setDate(8, memberVO.getRegdate());
			pstmt.setString(9, memberVO.getMemnick());
			pstmt.setString(10, memberVO.getMemident());
			pstmt.setDate(11, memberVO.getMembirth());
			pstmt.setString(12, memberVO.getMemstate());
			pstmt.setString(13, memberVO.getMemgender());

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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMemid());
			pstmt.setString(2, memberVO.getMempsw());
			pstmt.setString(3, memberVO.getMemname());
			pstmt.setString(4, memberVO.getMememail());
			pstmt.setString(5, memberVO.getMemaddr());
			pstmt.setString(6, memberVO.getMemphone());
			pstmt.setBytes(7, memberVO.getMempic());
			pstmt.setDate(8, memberVO.getRegdate());
			pstmt.setString(9, memberVO.getMemnick());
			pstmt.setString(10, memberVO.getMemident());
			pstmt.setDate(11, memberVO.getMembirth());
			pstmt.setString(12, memberVO.getMemstate());
			pstmt.setString(13, memberVO.getMemgender());
			pstmt.setString(14, memberVO.getMemno());
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
	public void delete(String memno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memno);
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
	public MemberVO findByPrimaryKey(String memno) {
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemno(rs.getString("memno"));
				memberVO.setMemid(rs.getString("memid"));
				memberVO.setMempsw(rs.getString("mempsw"));
				memberVO.setMemname(rs.getString("memname"));
				memberVO.setMememail(rs.getString("mememail"));
				memberVO.setMemaddr(rs.getString("memaddr"));
				memberVO.setMemphone(rs.getString("memphone"));
				memberVO.setMempic(rs.getBytes("mempic"));
				memberVO.setRegdate(rs.getDate("regdate"));
				memberVO.setMemnick(rs.getString("memnick"));
				memberVO.setMemident(rs.getString("memident"));
				memberVO.setMembirth(rs.getDate("membirth"));
				memberVO.setMemstate(rs.getString("memstate"));
				memberVO.setMemgender(rs.getString("memgender"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemno(rs.getString("memno"));
				memberVO.setMemid(rs.getString("memid"));
				memberVO.setMempsw(rs.getString("mempsw"));
				memberVO.setMemname(rs.getString("memname"));
				memberVO.setMememail(rs.getString("mememail"));
				memberVO.setMemaddr(rs.getString("memaddr"));
				memberVO.setMemphone(rs.getString("memphone"));
				memberVO.setMempic(rs.getBytes("mempic"));
				memberVO.setRegdate(rs.getDate("regdate"));
				memberVO.setMemnick(rs.getString("memnick"));
				memberVO.setMemident(rs.getString("memident"));
				memberVO.setMembirth(rs.getDate("membirth"));
				memberVO.setMemstate(rs.getString("memstate"));
				memberVO.setMemgender(rs.getString("memgender"));
				list.add(memberVO);
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

		return list;
	}

}
