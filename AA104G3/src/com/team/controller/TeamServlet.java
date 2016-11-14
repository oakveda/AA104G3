package com.team.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.team.model.*;

public class TeamServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String teamno = req.getParameter("teamno");
				if (teamno == null || (teamno.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if(teamno.length() != 6){
					errorMsgs.add("揪團編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TeamService teamSvc = new TeamService();
				TeamVO teamVO = teamSvc.getOneTeam(teamno);
				if (teamVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teamVO", teamVO); // 資料庫取出的teamVO物件,存入req
				String url = "/back-end/team/listOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTeam.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllTeam.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String teamno = req.getParameter("teamno");
				
				/***************************2.開始查詢資料****************************************/
				TeamService teamSvc = new TeamService();
				TeamVO teamVO = teamSvc.getOneTeam(teamno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("teamVO", teamVO);         // 資料庫取出的teamVO物件,存入req
				String url = "/back-end/team/update_team_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_team_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/listAllTeam.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_team_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String teamno = req.getParameter("teamno").trim();
				String teamtitle = req.getParameter("teamtitle").trim();
				String teamdesc = req.getParameter("teamdesc").trim();
				String memno = req.getParameter("memno").trim();
				String resvno = req.getParameter("resvno").trim();

				if(teamtitle == null || (teamtitle.trim()).length() == 0){
					teamtitle = "";
					errorMsgs.add("請輸入揪團名稱");
				}

				if(teamdesc == null || (teamdesc.trim()).length() == 0){
					teamdesc = "";
					errorMsgs.add("請輸入揪團介紹");
				}
				
				if(memno == null || memno.trim().length() == 0 || Integer.parseInt(memno) == 0){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("請輸入揪團者編號(如:000001)");
				}
				if(memno.trim().length() != 6){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("請輸入正確的揪團者編號格式(如:000001)");
				}
					
				if(resvno == null || resvno.trim().length() == 0 || Integer.parseInt(resvno) == 0){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("請輸入訂位編號(如:000001)");
				}
				if(resvno.trim().length() != 6){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("請輸入正確的訂位編號格式(如:000001)");
				}
				
				Integer teamcount = null;
				try {
					teamcount = new Integer(req.getParameter("teamcount").trim());
				} catch (NumberFormatException e) {
					teamcount = 1;
					errorMsgs.add("揪團人數不得為空值");
				}
				if(teamcount == 0){
					teamcount = 1;
					errorMsgs.add("揪團人數不得為0");
				}
				
				Integer teamstate = null;
				try {
					teamstate = new Integer(req.getParameter("teamstate").trim());
				} catch (NumberFormatException e) {
					teamstate = 0;
					errorMsgs.add("揪團狀態不得為空值");
				}
				if(teamstate >= 2){
					teamstate = 0;
					errorMsgs.add("揪團狀態只可為0(未滿)或1(已滿)");
				}


				TeamVO teamVO = new TeamVO();
				teamVO .setTeamno(teamno);
				teamVO .setTeamtitle(teamtitle);
				teamVO .setTeamdesc(teamdesc);
				teamVO .setMemno(memno);
				teamVO .setResvno(resvno);
				teamVO .setTeamcount(teamcount);
				teamVO .setTeamstate(teamstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teamVO", teamVO); // 含有輸入格式錯誤的teamVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/update_team_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TeamService teamSvc = new TeamService();
				teamVO = teamSvc.updateTeam(teamno, teamtitle, teamdesc, memno, resvno, teamcount, teamstate);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teamVO", teamVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/team/listOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTeam.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/update_team_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addTeam.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String teamtitle = req.getParameter("teamtitle").trim();
				String teamdesc = req.getParameter("teamdesc").trim();
				String memno = req.getParameter("memno").trim();
				String resvno = req.getParameter("resvno").trim();


				if(teamtitle == null || (teamtitle.trim()).length() == 0){
					teamtitle = "";
					errorMsgs.add("請輸入揪團名稱");
				}

				if(teamdesc == null || (teamdesc.trim()).length() == 0){
					teamdesc = "";
					errorMsgs.add("請輸入揪團介紹");
				}
				
				if(memno == null || memno.trim().length() == 0 || Integer.parseInt(memno) == 0){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("請輸入揪團者編號(如:000001)");
				}
				if(memno.trim().length() != 6){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("請輸入正確的揪團者編號格式(如:000001)");
				}
					
				if(resvno == null || resvno.trim().length() == 0 || Integer.parseInt(resvno) == 0){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("請輸入訂位編號(如:000001)");
				}
				if(resvno.trim().length() != 6){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("請輸入正確的訂位編號格式(如:000001)");
				}
				
				Integer teamcount = null;
				try {
					teamcount = new Integer(req.getParameter("teamcount").trim());
				} catch (NumberFormatException e) {
					teamcount = 1;
					errorMsgs.add("揪團人數不得為空值");
				}
				if(teamcount == 0){
					teamcount = 1;
					errorMsgs.add("揪團人數不得為0");
				}
				
				Integer teamstate = null;
				try {
					teamstate = new Integer(req.getParameter("teamstate").trim());
				} catch (NumberFormatException e) {
					teamstate = 0;
					errorMsgs.add("揪團狀態不得為空值");
				}
				if(teamstate >= 2){
					teamstate = 0;
					errorMsgs.add("揪團狀態只可為0(未滿)或1(已滿)");
				}

				TeamVO teamVO = new TeamVO();
				teamVO.setTeamtitle(teamtitle);
				teamVO.setTeamdesc(teamdesc);
				teamVO.setMemno(memno);
				teamVO.setResvno(resvno);
				teamVO.setTeamcount(teamcount);
				teamVO.setTeamstate(teamstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teamVO", teamVO); // 含有輸入格式錯誤的teamVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/addTeam.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TeamService teamSvc = new TeamService();
				teamVO = teamSvc.addTeam(teamtitle, teamdesc, memno, resvno, teamcount, teamstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/team/listAllTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/addTeam.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllTeam.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String teamno = req.getParameter("teamno");
				
				/***************************2.開始刪除資料***************************************/
				TeamService teamSvc = new TeamService();
				teamSvc.deleteTeam(teamno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/team/listAllTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/listAllTeam.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
