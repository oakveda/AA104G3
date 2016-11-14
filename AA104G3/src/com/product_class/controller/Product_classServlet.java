package com.product_class.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.product_class.model.Product_classService;

/*@WebServlet("/product_class/product_class.do")*/
public class Product_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("listProducts_ByClassno_b".equals(action) || "listProducts_ByClassno_a".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String classno = request.getParameter("classno");
				Product_classService product_classSvc = new Product_classService();
				Set<ProductVO> set = product_classSvc.getProductByClassno(classno);
				request.setAttribute("listProducts_ByClassno", set);

				String url = null;
				if ("listProducts_ByClassno_a".equals(action)) {
					url = "/front-end/product_class/listProducts_ByClassno.jsp";
				} else if ("listProducts_ByClassno_b".equals(action)) {
					url = "/front-end/product_class/listAllProduct_class.jsp";
				}

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {				
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
			}
		}

		if ("delete_Product_class".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String classno = request.getParameter("classno");
				Product_classService product_classSvc = new Product_classService();
				product_classSvc.deleteProduct_class(classno);

				String url = "/front-end/product_class/listAllProduct_class.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/product_class/listAllProduct_class.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
