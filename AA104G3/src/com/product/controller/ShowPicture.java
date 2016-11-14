package com.product.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.sql.DataSource;

/*@WebServlet("/ShowPicture")*/
public class ShowPicture extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		req.setCharacterEncoding("Big5");
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();		

		try {

			Statement stmt = con.createStatement();
			String prono = req.getParameter("prono");
			String prono2 = new String(prono.getBytes("ISO-8859-1"), "Big5");

			ResultSet rs = stmt.executeQuery("select propic from PRODUCT where prono = '" + prono2 + "'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("propic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = new FileInputStream(getServletContext().getRealPath("images/noimg.gif"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = new FileInputStream(getServletContext().getRealPath("images/noimg.gif"));
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
