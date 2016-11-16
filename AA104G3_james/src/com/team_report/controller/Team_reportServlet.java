package com.team_report.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team_report.model.Team_reportService;

/*@WebServlet("/Team_reportServlet")*/
public class Team_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		/*新增檢舉*/
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			
			try {
				String teamno = request.getParameter("teamno");
				String memno = request.getParameter("memno");
				String reason = request.getParameter("reason");
				
				if(reason.trim().length()==0){
					errorMsgs.add("請輸入檢舉理由");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}
				
				Team_reportService team_reportSvc = new Team_reportService();
				team_reportSvc.addTeam_report(memno, teamno, reason);
				
				String url="/back-end/team_report/listAllTeam_report.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
			
		}		
		
		
		/*審核檢舉*/
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			
			try {
				String teamrepno = request.getParameter("teamrepno");
				String teamno = request.getParameter("teamno");
				String checked = request.getParameter("checked");				
				
				/*對檢舉通過的揪團進行撤銷*/
				Team_reportService team_reportSvc = new Team_reportService();
				team_reportSvc.updateTeam_report(teamrepno, checked, teamno);
				
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request,response);
			}
			
		}
	}

}
