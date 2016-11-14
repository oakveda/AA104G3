package com.store_report.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store_report.model.Store_reportService;

/*@WebServlet("/back-end/store_report/store_report.do")*/
public class Store_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		/* 新增店家檢舉 */
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String stono = request.getParameter("stono");
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

				Store_reportService store_reportSvc = new Store_reportService();
				store_reportSvc.addStore_report(memno, stono, reason);
				String url = "/back-end/store_report/listAllStore_report.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}
		
		/* 審核檢舉 */
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			
			try {
				String storepno =request.getParameter("storepno");
				String stono = request.getParameter("stono");
				String checked = request.getParameter("checked");
				
				
				Store_reportService store_reportSvc = new Store_reportService();
				store_reportSvc.updateStore_report(storepno, checked, stono);
				
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
