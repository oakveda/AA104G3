package com.product_report.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product_report.model.Product_reportService;
import com.product_report.model.Product_reportVO;

/*@WebServlet("/back-end/product_report/Product_repot.do")*/
public class Product_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		/* 新增商品檢舉 */
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String prono = request.getParameter("prono");
				String memno = request.getParameter("memno");
				String reason = request.getParameter("reason");

				if (reason.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉理由");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}

				Product_reportService product_reportSvc = new Product_reportService();
				product_reportSvc.addProduct_report(memno, prono, reason);

				String url = "/back-end/product_report/listAllProduct_report.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		/* 審核檢舉 */
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String prorepno = request.getParameter("prorepno");
				String prono = request.getParameter("prono");
				String checked = request.getParameter("checked");
				

				Product_reportService product_reportSvc = new Product_reportService();
				/*對檢舉通過的商品進行撤銷*/
				product_reportSvc.updateProduct_report(prorepno, checked, prono);
				
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

	}

}
