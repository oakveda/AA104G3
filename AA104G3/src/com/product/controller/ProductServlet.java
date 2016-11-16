package com.product.controller;

import java.io.*;
import util.Output;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/*@WebServlet("/product/product.do")*/
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* System.out.println(request.getServletContext().getContextPath()); */

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getAllByDate".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> list = (List<ProductVO>) productSvc.getAll();
			request.setAttribute("list", list);
			String url = "/front-end/product/search_product_list.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		if ("getAllByName".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> list = (List<ProductVO>) productSvc.getAllSortByName();
			request.setAttribute("list", list);
			String url = "/front-end/product/search_product_list.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		/* �d�߳浧 */
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String prono = request.getParameter("prono");

				if (prono == null || (prono.trim()).length() < 6) {
					errorMsgs.add("�п�J���T�ӫ~�s��");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				try {
					new Integer(prono);
				} catch (NumberFormatException e) {
					errorMsgs.add("��J�榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;

				}

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prono);

				request.setAttribute("productVO", productVO);
				String url = "/front-end/product/product_detail.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���");
				RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
				failureView.forward(request, response);
			}
		}

		/* �s�W��� */
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String stono = request.getParameter("stono").trim();
				String classno = request.getParameter("classno").trim();
				String proname = request.getParameter("proname").trim();

				Integer proprice = null;
				try {
					proprice = new Integer(request.getParameter("proprice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�����ж�Ʀr");
					proprice = 0;
				}

				String prostate = request.getParameter("prostate").trim();

				Integer proqty = null;
				try {
					proqty = new Integer(request.getParameter("proqty").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�w�s�ж�Ʀr");
					proqty = 0;
				}

				String playernum = request.getParameter("playernum").trim();
				String playerage = request.getParameter("playerage").trim();
				String lang = request.getParameter("lang").trim();
				String playtime = request.getParameter("playtime").trim();
				String prodesc = request.getParameter("prodesc").trim();

				ProductVO productVO = new ProductVO();
				productVO.setStono(stono);
				productVO.setClassno(classno);
				productVO.setProname(proname);
				productVO.setProprice(proprice);
				productVO.setProstate(prostate);
				productVO.setProqty(proqty);
				productVO.setPlayernum(playernum);
				productVO.setPlayerage(playerage);
				productVO.setLang(lang);
				productVO.setProdesc(prodesc);

				/* ���o�Ϥ� */
				Part part = request.getPart("addimg");
				byte[] propic = new Output().outputBytes(part);

				if (propic.length == 0) {
					errorMsgs.add("�ФW�ǹϤ�");
				}

				/* ���~�ɸ��� */
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("productVO", productVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/addProduct.jsp");
					failureView.forward(request, response);
					return;
				}

				ProductService productSvc = new ProductService();
				productSvc.addProduct(stono, classno, proname, proprice, prostate, proqty, playernum, playerage, lang,
						playtime, prodesc, propic);

				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/addProduct.jsp");
				failureView.forward(request, response);
			}

		}

		/* �ШD�ק� */
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {

				/* �����Ѽ� */
				String prono = request.getParameter("prono");

				/* ���o���� */
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prono);
				request.setAttribute("productVO", productVO);

				/* ��e */
				String url = "/front-end/product/update_product_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(request, response);
			}
		}

		/* �ק��� */
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String prono = request.getParameter("prono").trim();
				String stono = request.getParameter("stono").trim();
				String classno = request.getParameter("classno").trim();
				String proname = request.getParameter("proname").trim();

				Integer proprice = null;
				try {
					proprice = new Integer(request.getParameter("proprice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�����ж�Ʀr");
					proprice = 0;
				}

				String prostate = request.getParameter("prostate").trim();

				Integer proqty = null;
				try {
					proqty = new Integer(request.getParameter("proqty").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�w�s�ж�Ʀr");
					proqty = 0;
				}

				String playernum = request.getParameter("playernum").trim();
				String playerage = request.getParameter("playerage").trim();
				String lang = request.getParameter("lang").trim();
				String playtime = request.getParameter("playtime").trim();
				String prodesc = request.getParameter("prodesc").trim();

				/* ���o�Ϥ� */
				Part part = request.getPart("addimg");
				byte[] propic = new Output().outputBytes(part);

				ProductVO productVO = new ProductVO();
				productVO.setProno(prono);
				productVO.setStono(stono);
				productVO.setClassno(classno);
				productVO.setProname(proname);
				productVO.setProprice(proprice);
				productVO.setProstate(prostate);
				productVO.setProqty(proqty);
				productVO.setPlayernum(playernum);
				productVO.setPlayerage(playerage);
				productVO.setLang(lang);
				productVO.setProdesc(prodesc);
				productVO.setPropic(propic);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("productVO", productVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/update_product_input.jsp");
					failureView.forward(request, response);
					return;
				}

				ProductService productSvc = new ProductService();
				productSvc.updateProduct(prono, stono, classno, proname, proprice, prostate, proqty, playernum,
						playerage, lang, playtime, prodesc, propic);

				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/product/update_product_input.jsp");
				failureView.forward(request, response);
			}
		}

		/* �R�� */
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String prono = request.getParameter("prono");				
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(prono);

				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�R������" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(request, response);
			}

		}

		/* �ƦX�d�� */
		if ("listProducts_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				if (request.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) request.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) request.getParameterMap();
				}

				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getAll(map);

				request.setAttribute("listProducts_ByCompositeQuery", list);
				RequestDispatcher successView = request
						.getRequestDispatcher("/front-end/product/listProdcuts_ByCompositeQuery.jsp");
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
