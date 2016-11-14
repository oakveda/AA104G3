package com.member.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class MemberJDBCDAO implements MemberDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa1042";
	String passwd = "aa1042";

	private static final String INSERT_STMT = "INSERT INTO member (memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender)"
			+ " VALUES (LPAD(member_seq.NEXTVAL,6,0),?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE member set memid=?,mempsw=?,memname=?,mememail=?,memaddr=?,memphone=?,mempic=?,regdate=?,memnick=?,memident=?,membirth=?,memstate=?,memgender=? where memno=?";

	private static final String DELETE = "DELETE FROM member where memno=?";

	private static final String GET_ALL_STMT = "SELECT memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender FROM member ";

	private static final String GET_ONE_STMT = "SELECT memno,memid,mempsw,memname,mememail,memaddr,memphone,mempic,regdate,memnick,memident,membirth,memstate,memgender FROM member where memno=? ";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memno);
			System.out.println(pstmt.executeUpdate());

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args){
		MemberJDBCDAO dao = new MemberJDBCDAO();
		MemberVO memberVO = new MemberVO();
		
		
		
//insert
		FileInputStream fin;
		byte[] in = null;
		try {
			 /*fin = new FileInputStream("D:/Project_aa104_3/img/gift.png");*/
			 fin = new FileInputStream("src/test/img/gift.png");
			 in = new byte[fin.available()];
			 fin.read(in);
			 fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		memberVO.setMemid("ggodgg");
		memberVO.setMempsw("ggodgg");
		memberVO.setMemname("何君如");
		memberVO.setMememail("HeJunRu@teleworm.us");
		memberVO.setMemaddr("320桃園市中壢區中正路三段220巷");
		memberVO.setMemphone("0945135445");
		memberVO.setMempic(in);
		memberVO.setRegdate(java.sql.Date.valueOf(new SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime())));
		memberVO.setMemnick("nonoe");
		memberVO.setMemident("A213653812");
		memberVO.setMembirth(java.sql.Date.valueOf("1995-10-01"));
		memberVO.setMemstate("0");
		memberVO.setMemgender("1");
		dao.insert(memberVO);
		
//		Calendar cal = Calendar.getInstance();
//      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
//		System.out.println(sdf.format(cal.getTime()));
		
		
//update
//		memberVO.setMemid("ggodgg");
//		memberVO.setMempsw("ggodggaa");
//		memberVO.setMemname("何君如sd");
//		memberVO.setMememail("HeJunRu@teleworm.us");
//		memberVO.setMemaddr("320桃園市中壢區中正路三段220巷");
//		memberVO.setMemphone("0945135445");
//		memberVO.setMempic(null);
//		memberVO.setRegdate(java.sql.Date.valueOf("2006-10-01"));
//		memberVO.setMemnick("nonoeads");
//		memberVO.setMemidnet("A213653812");
//		memberVO.setMembirth(java.sql.Date.valueOf("1995-10-01"));
//		memberVO.setMemstate("1");
//		memberVO.setMemgender("1");
//		memberVO.setMemno("000003");
//		dao.update(memberVO);
		
		
		
		
//delete
//		dao.delete("000004");
		
		
		
//select_all
//		List<MemberVO> list = dao.getAll();
//		for(MemberVO amemberVO:list){
//			System.out.print(amemberVO.getMemno()+",");
//			System.out.print(amemberVO.getMemid()+",");
//			System.out.print(amemberVO.getMempsw()+",");
//			System.out.print(amemberVO.getMemname()+",");
//			System.out.print(amemberVO.getMememail()+",");
//			System.out.print(amemberVO.getMemaddr()+",");
//			System.out.print(amemberVO.getMemphone()+",");
//			System.out.print(amemberVO.getMempic()+",");
//			System.out.print(amemberVO.getRegdate()+",");
//			System.out.print(amemberVO.getMemnick()+",");
//			System.out.print(amemberVO.getMemident()+",");
//			System.out.print(amemberVO.getMembirth()+",");
//			System.out.print(amemberVO.getMemstate()+",");
//			System.out.println(amemberVO.getMemgender());
//		}

		
		
//select_one
//		memberVO = dao.findByPrimarty("000001");
//		System.out.print(memberVO.getMemno()+",");
//		System.out.print(memberVO.getMemid()+",");
//		System.out.print(memberVO.getMempsw()+",");
//		System.out.print(memberVO.getMemname()+",");
//		System.out.print(memberVO.getMememail()+",");
//		System.out.print(memberVO.getMemaddr()+",");
//		System.out.print(memberVO.getMemphone()+",");
//		System.out.print(memberVO.getMempic()+",");
//		System.out.print(memberVO.getRegdate()+",");
//		System.out.print(memberVO.getMemnick()+",");
//		System.out.print(memberVO.getMemident()+",");
//		System.out.print(memberVO.getMembirth()+",");
//		System.out.print(memberVO.getMemstate()+",");
//		System.out.println(memberVO.getMemgender());

	}
}
