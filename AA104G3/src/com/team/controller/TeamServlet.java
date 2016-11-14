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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String teamno = req.getParameter("teamno");
				if (teamno == null || (teamno.trim()).length() == 0) {
					errorMsgs.add("�п�J���νs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				if(teamno.length() != 6){
					errorMsgs.add("���νs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				TeamService teamSvc = new TeamService();
				TeamVO teamVO = teamSvc.getOneTeam(teamno);
				if (teamVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("teamVO", teamVO); // ��Ʈw���X��teamVO����,�s�Jreq
				String url = "/back-end/team/listOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneTeam.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllTeam.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String teamno = req.getParameter("teamno");
				
				/***************************2.�}�l�d�߸��****************************************/
				TeamService teamSvc = new TeamService();
				TeamVO teamVO = teamSvc.getOneTeam(teamno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("teamVO", teamVO);         // ��Ʈw���X��teamVO����,�s�Jreq
				String url = "/back-end/team/update_team_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_team_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/listAllTeam.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_team_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String teamno = req.getParameter("teamno").trim();
				String teamtitle = req.getParameter("teamtitle").trim();
				String teamdesc = req.getParameter("teamdesc").trim();
				String memno = req.getParameter("memno").trim();
				String resvno = req.getParameter("resvno").trim();

				if(teamtitle == null || (teamtitle.trim()).length() == 0){
					teamtitle = "";
					errorMsgs.add("�п�J���ΦW��");
				}

				if(teamdesc == null || (teamdesc.trim()).length() == 0){
					teamdesc = "";
					errorMsgs.add("�п�J���Τ���");
				}
				
				if(memno == null || memno.trim().length() == 0 || Integer.parseInt(memno) == 0){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("�п�J���Ϊ̽s��(�p:000001)");
				}
				if(memno.trim().length() != 6){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("�п�J���T�����Ϊ̽s���榡(�p:000001)");
				}
					
				if(resvno == null || resvno.trim().length() == 0 || Integer.parseInt(resvno) == 0){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("�п�J�q��s��(�p:000001)");
				}
				if(resvno.trim().length() != 6){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("�п�J���T���q��s���榡(�p:000001)");
				}
				
				Integer teamcount = null;
				try {
					teamcount = new Integer(req.getParameter("teamcount").trim());
				} catch (NumberFormatException e) {
					teamcount = 1;
					errorMsgs.add("���ΤH�Ƥ��o���ŭ�");
				}
				if(teamcount == 0){
					teamcount = 1;
					errorMsgs.add("���ΤH�Ƥ��o��0");
				}
				
				Integer teamstate = null;
				try {
					teamstate = new Integer(req.getParameter("teamstate").trim());
				} catch (NumberFormatException e) {
					teamstate = 0;
					errorMsgs.add("���Ϊ��A���o���ŭ�");
				}
				if(teamstate >= 2){
					teamstate = 0;
					errorMsgs.add("���Ϊ��A�u�i��0(����)��1(�w��)");
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
					req.setAttribute("teamVO", teamVO); // �t����J�榡���~��teamVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/update_team_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				TeamService teamSvc = new TeamService();
				teamVO = teamSvc.updateTeam(teamno, teamtitle, teamdesc, memno, resvno, teamcount, teamstate);

				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("teamVO", teamVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/team/listOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneTeam.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/update_team_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addTeam.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String teamtitle = req.getParameter("teamtitle").trim();
				String teamdesc = req.getParameter("teamdesc").trim();
				String memno = req.getParameter("memno").trim();
				String resvno = req.getParameter("resvno").trim();


				if(teamtitle == null || (teamtitle.trim()).length() == 0){
					teamtitle = "";
					errorMsgs.add("�п�J���ΦW��");
				}

				if(teamdesc == null || (teamdesc.trim()).length() == 0){
					teamdesc = "";
					errorMsgs.add("�п�J���Τ���");
				}
				
				if(memno == null || memno.trim().length() == 0 || Integer.parseInt(memno) == 0){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("�п�J���Ϊ̽s��(�p:000001)");
				}
				if(memno.trim().length() != 6){
					memno = req.getParameter("memno").trim();
					errorMsgs.add("�п�J���T�����Ϊ̽s���榡(�p:000001)");
				}
					
				if(resvno == null || resvno.trim().length() == 0 || Integer.parseInt(resvno) == 0){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("�п�J�q��s��(�p:000001)");
				}
				if(resvno.trim().length() != 6){
					resvno = req.getParameter("resvno").trim();
					errorMsgs.add("�п�J���T���q��s���榡(�p:000001)");
				}
				
				Integer teamcount = null;
				try {
					teamcount = new Integer(req.getParameter("teamcount").trim());
				} catch (NumberFormatException e) {
					teamcount = 1;
					errorMsgs.add("���ΤH�Ƥ��o���ŭ�");
				}
				if(teamcount == 0){
					teamcount = 1;
					errorMsgs.add("���ΤH�Ƥ��o��0");
				}
				
				Integer teamstate = null;
				try {
					teamstate = new Integer(req.getParameter("teamstate").trim());
				} catch (NumberFormatException e) {
					teamstate = 0;
					errorMsgs.add("���Ϊ��A���o���ŭ�");
				}
				if(teamstate >= 2){
					teamstate = 0;
					errorMsgs.add("���Ϊ��A�u�i��0(����)��1(�w��)");
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
					req.setAttribute("teamVO", teamVO); // �t����J�榡���~��teamVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/team/addTeam.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				TeamService teamSvc = new TeamService();
				teamVO = teamSvc.addTeam(teamtitle, teamdesc, memno, resvno, teamcount, teamstate);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/team/listAllTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/addTeam.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllTeam.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String teamno = req.getParameter("teamno");
				
				/***************************2.�}�l�R�����***************************************/
				TeamService teamSvc = new TeamService();
				teamSvc.deleteTeam(teamno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/team/listAllTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/team/listAllTeam.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
