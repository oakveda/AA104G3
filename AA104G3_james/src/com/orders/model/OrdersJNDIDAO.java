package com.orders.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cart.model.CartService;
import com.cart.model.CartVO;
import com.orders_detail.model.Orders_detailJNDIDAO;
import com.orders_detail.model.Orders_detailVO;

public class OrdersJNDIDAO implements OrdersDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO orders VALUES(lpad(orders_seq.nextval,6,0), ?, ?, ?, ?, '0', ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM orders WHERE ordno = ?";
	private static final String UPDATE = "UPDATE orders SET pickname = ? where memno = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM orders WHERE ordno = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM orders order by ordno";

	/* 砍掉購物車內的商品 */
	private static final String DELETE_ALL_CART_BY_MEMNO_AND_PRONO = "DELETE FROM cart WHERE prono = ? and memno = ?";

	/* 撤銷訂單 */
	private static final String REVOKE = "UPDATE orders SET ordstate = 2 where ordno = ?";

	/* 查某會員所有訂單 */
	private static final String GET_ALL_BY_MEMNO = "SELECT * FROM orders WHERE memno = ?";

	@Override
	public void insertWithOrders_detail(OrdersVO ordersVO, LinkedHashSet<CartVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String prono = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			/* 新增訂單 */
			String cols[] = { "ordno" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, ordersVO.getMemno());
			pstmt.setInt(2, ordersVO.getPricesum());
			pstmt.setInt(3, ordersVO.getTotal());
			pstmt.setString(4, ordersVO.getPayment());
			pstmt.setDate(5, ordersVO.getOrddate());
			pstmt.setString(6, ordersVO.getPickname());
			pstmt.setString(7, ordersVO.getPickup());
			pstmt.setString(8, ordersVO.getPickphone());
			pstmt.setString(9, ordersVO.getPickaddr());
			pstmt.executeUpdate();

			/* 取得自增主鍵值 */
			String key = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			key = rs.getString(1);
			rs.close();

			/* 新增訂單細項 */
			Orders_detailJNDIDAO dao = new Orders_detailJNDIDAO();
			Orders_detailVO orders_detailVO = new Orders_detailVO();
			for (CartVO cartVO : list) {
				orders_detailVO.setOrdno(key);
				orders_detailVO.setProno(cartVO.getProno());
				orders_detailVO.setProcount(cartVO.getProcount());
				dao.insert(orders_detailVO, con);
				
				prono = cartVO.getProno();
				
				/*砍掉購物車*/
				pstmt= con.prepareStatement(DELETE_ALL_CART_BY_MEMNO_AND_PRONO);
				pstmt.setString(1, prono);
				pstmt.setString(2, ordersVO.getMemno());
				pstmt.executeUpdate();
			}
			
			
			

			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public void insert(OrdersVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			/* 新增訂單 */
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ordVO.getMemno());
			pstmt.setInt(2, ordVO.getPricesum());
			pstmt.setInt(3, ordVO.getTotal());
			pstmt.setString(4, ordVO.getPayment());
			pstmt.setDate(5, ordVO.getOrddate());
			pstmt.setString(6, ordVO.getPickname());
			pstmt.setString(7, ordVO.getPickup());
			pstmt.setString(8, ordVO.getPickphone());
			pstmt.setString(9, ordVO.getPickaddr());
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ordno);
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
	public void update(OrdersVO ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ordVO.getPickname());
			pstmt.setString(2, ordVO.getMemno());
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
	public OrdersVO findByPrimaryKey(String ordno) {
		ResultSet rs = null;
		OrdersVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ordno);
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
				ordVO.setPickphone(rs.getString("pickphone"));
				ordVO.setPricesum(rs.getInt("pricesum"));
				ordVO.setTotal(rs.getInt("total"));
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
		return ordVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		ResultSet rs = null;
		OrdersVO ordVO = null;
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
				ordVO.setPickphone(rs.getString("pickphone"));
				ordVO.setPricesum(rs.getInt("pricesum"));
				ordVO.setTotal(rs.getInt("total"));
				list.add(ordVO);
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
	public void revoke(String ordno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REVOKE);
			pstmt.setString(1, ordno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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

	}

	@Override
	public List<OrdersVO> getAllByMemno(String memno) {
		ResultSet rs = null;
		OrdersVO ordVO = null;
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMNO);
			pstmt.setString(1, memno);
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
				ordVO.setPickphone(rs.getString("pickphone"));
				ordVO.setPricesum(rs.getInt("pricesum"));
				ordVO.setTotal(rs.getInt("total"));
				list.add(ordVO);
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
