package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// System.out.println(action);
		// System.out.println("getOne_For_Display".equals(action));
		
		if("logout".equals(action)){
			HttpSession session = req.getSession();
			session.removeAttribute("memberVO");
			session.removeAttribute("cartList");
			RequestDispatcher logout = req.getRequestDispatcher("/select_page.jsp");
			logout.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/

				String memno = req.getParameter("memno");
				System.out.println(action);
				System.out.println("getOne_For_Display".equals(action));
				if (memno == null || (memno.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				//

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memno);
				if (memberVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ insert ================================

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String memid = null;

				try {
					memid = req.getParameter("memid").trim();
					if (memid == null || memid.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memid = req.getParameter("memid").trim();
					errorMsgs.add("�п�J�b��!");
				}

				String mempsw = null;
				try {
					mempsw = req.getParameter("mempsw").trim();
					if (mempsw.length() < 6) {
						throw new Exception();
					}
					char[] mempswcheck = mempsw.toCharArray();
					int[] count = { 0, 0, 0 };// 0 �j�g 1 �p�g 2 �Ʀr
					for (char ch : mempswcheck) {
						if (ch >= 48 && ch <= 57) {
							count[2]++;
						} else if (65 >= 48 && ch <= 90) {
							count[0]++;
						} else if (97 >= 48 && ch <= 122) {
							count[1]++;
						}
					}
					for (int i : count) {
						// System.out.println(i);
						if (i < 2) {
							throw new Exception();
						}
					}
				} catch (Exception e) {
					mempsw = req.getParameter("mempsw").trim();
					errorMsgs.add("�K�X���ųW�w!");
				}

				String memname = null;
				try {
					memname = req.getParameter("memname").trim();
					if (memname == null || memname.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memname = req.getParameter("memname").trim();
					errorMsgs.add("�п�J�m�W!");
				}

				String mememail = null;

				try {
					mememail = req.getParameter("mememail").trim();
					if (mememail == null || mememail.length() == 0 || mememail.indexOf("@") == -1
							|| mememail.indexOf(".") == -1) {
						throw new Exception();
					}
				} catch (Exception e) {
					mememail = req.getParameter("mememail").trim();
					errorMsgs.add("�п�J�ŦX�W�h��email!");
				}

				String memaddr = null;

				try {
					memaddr = req.getParameter("memaddr").trim();
					if (memaddr == null || memaddr.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memaddr = req.getParameter("memaddr").trim();
					errorMsgs.add("�п�J�a�}!");
				}

				String memphone = null;

				try {
					memphone = req.getParameter("memphone").trim();
					if (memphone == null || memphone.length() != 10) {
						throw new Exception();

					}
					Integer memPhoneNumber = new Integer(memphone);

				} catch (NumberFormatException e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("�п�J�Ʀr!");
				} catch (Exception e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("�п�J�ŦX��������X!");
				}

				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ͤ�!");
				}

				String memnick = null;

				try {
					memnick = req.getParameter("memnick").trim();
					if (memnick == null || memnick.length() == 0) {
						throw new Exception();

					}

				} catch (Exception e) {
					memnick = req.getParameter("memnick").trim();
					errorMsgs.add("�п�J�ʺ�!");
				}

				String memident = null;

				try {
					memident = req.getParameter("memident").trim();
					if (memident == null || memident.length() != 10 || !identcheck(memident)) {
						throw new Exception();

					}

				} catch (Exception e) {
					memident = req.getParameter("memident").trim();
					errorMsgs.add("�п�J���T�������Ҧr��!");
				}

				String memgender = null;

				try {
					memgender = req.getParameter("memgender");
					System.out.println(memgender);
					if (memgender == null) {
						throw new Exception();
					}

				} catch (Exception e) {

					errorMsgs.add("�п�ܩʧO!");
				}

				// �Ϥ��w�w��==========================================================================

				byte[] mempic = null;

				// �Ϥ��w�w��==========================================================================

				MemberVO memberVO = new MemberVO();
				memberVO.setMemid(memid);
				memberVO.setMempsw(mempsw);
				memberVO.setMemname(memname);
				memberVO.setMememail(mememail);
				memberVO.setMemaddr(memaddr);
				memberVO.setMemphone(memphone);
				memberVO.setMembirth(membirth);
				memberVO.setMemnick(memnick);
				memberVO.setMemident(memident);
				memberVO.setMemgender(memgender);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(memid, mempsw, memname, mememail, memaddr, memphone, mempic, memnick,
						memident, membirth, memgender);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ update ================================

		if ("update".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/

				String memno = req.getParameter("memno").trim();
				String memid = req.getParameter("memid").trim();

				String mempsw = null;
				try {
					mempsw = req.getParameter("mempsw").trim();
					if (mempsw.length() < 6) {
						throw new Exception();
					}
					char[] mempswcheck = mempsw.toCharArray();
					int[] count = { 0, 0, 0 };// 0 �j�g 1 �p�g 2 �Ʀr
					for (char ch : mempswcheck) {
						if (ch >= 48 && ch <= 57) {
							count[2]++;
						} else if (65 >= 48 && ch <= 90) {
							count[0]++;
						} else if (97 >= 48 && ch <= 122) {
							count[1]++;
						}
					}
					for (int i : count) {
						// System.out.println(i);
						if (i < 2) {
							throw new Exception();
						}
					}
				} catch (Exception e) {
					mempsw = req.getParameter("mempsw").trim();
					errorMsgs.add("�K�X���ųW�w!");
				}

				String memname = null;
				try {
					memname = req.getParameter("memname").trim();
					if (memname == null || memname.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memname = req.getParameter("memname").trim();
					errorMsgs.add("�п�J�m�W!");
				}

				String mememail = null;

				try {
					mememail = req.getParameter("mememail").trim();
					if (mememail == null || mememail.length() == 0 || mememail.indexOf("@") == -1
							|| mememail.indexOf(".") == -1) {
						throw new Exception();
					}
				} catch (Exception e) {
					mememail = req.getParameter("mememail").trim();
					errorMsgs.add("�п�J�ŦX�W�h��email!");
				}

				String memaddr = null;

				try {
					memaddr = req.getParameter("memaddr").trim();
					if (memaddr == null || memaddr.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memaddr = req.getParameter("memaddr").trim();
					errorMsgs.add("�п�J�a�}!");
				}

				String memphone = null;

				try {
					memphone = req.getParameter("memphone").trim();
					if (memphone == null || memphone.length() != 10) {
						throw new Exception();

					}
					Integer memPhoneNumber = new Integer(memphone);

				} catch (NumberFormatException e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("�п�J�Ʀr!");
				} catch (Exception e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("�п�J�ŦX��������X!");
				}

				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ͤ�!");
				}

				String memnick = null;

				try {
					memnick = req.getParameter("memnick").trim();
					if (memnick == null || memnick.length() == 0) {
						throw new Exception();

					}

				} catch (Exception e) {
					memnick = req.getParameter("memnick").trim();
					errorMsgs.add("�п�J�ʺ�!");
				}

				String memident = null;

				try {
					memident = req.getParameter("memident").trim();
					if (memident == null || memident.length() != 10 || !identcheck(memident)) {
						throw new Exception();

					}

				} catch (Exception e) {
					memident = req.getParameter("memident").trim();
					errorMsgs.add("�п�J���T�������Ҧr��!");
				}

				String memgender = null;

				try {
					memgender = req.getParameter("memgender");
					// System.out.println(memgender);
					if (memgender == null) {
						throw new Exception();
					}

				} catch (Exception e) {

					errorMsgs.add("�п�ܩʧO!");
				}

				String memstate = req.getParameter("memstate");
				Date regdate = Date.valueOf(req.getParameter("regdate").trim());
				// �Ϥ��w�w��==========================================================================

				byte[] mempic = null;

				// �Ϥ��w�w��==========================================================================

				MemberVO memberVO = new MemberVO();
				memberVO.setMemno(memno);
				memberVO.setMemid(memid);
				memberVO.setMempsw(mempsw);
				memberVO.setMemname(memname);
				memberVO.setMememail(mememail);
				memberVO.setMemaddr(memaddr);
				memberVO.setMemphone(memphone);
				memberVO.setMempic(mempic);
				memberVO.setRegdate(regdate);
				memberVO.setMemnick(memnick);
				memberVO.setMemident(memident);
				memberVO.setMembirth(membirth);
				memberVO.setMemstate(memstate);
				memberVO.setMemgender(memgender);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/member/update_member_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l��s��� ***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memno, memid, mempsw, memname, mememail, memaddr, memphone, mempic,
						regdate, memnick, memident, membirth, memstate, memgender);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		} // update end

		// ================ delete ================================

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String memno = req.getParameter("memno");

				/*************************** 2.�}�l�R����� ***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(memno);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ getOne_For_Update ================================

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String memno = req.getParameter("memno");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memno);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

	}

	protected boolean identcheck(String ident) {
		char[] identcheck = ident.toCharArray();
		int[] identchartoint = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] intcheck = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int sum = 0;

		for (int i = 1; i < identcheck.length; i++) {
			identchartoint[i + 1] = identcheck[i] - '0';
		}

		for (int i = 0; i < identchartoint.length; i++) {
			if (i == 0)
			/*
			 * �ˬd�O�_���^��r �O���ܹB��X�ˬd�μƦr
			 */
			{
				int char1 = identcheck[i];

				if (char1 >= 65 && char1 <= 72) {
					char1 = char1 - 55;
					identchartoint[0] = (int) Math.floor(char1 / 10);
					identchartoint[1] = char1 % 10;
				} else if (char1 == 73)// I
				{

					identchartoint[0] = 3;
					identchartoint[1] = 4;
				} else if (char1 >= 74 && char1 <= 78) {
					char1 = char1 - 56;
					identchartoint[0] = (int) Math.floor(char1 / 10);
					identchartoint[1] = char1 % 10;
				} else if (char1 == 79)// O
				{

					identchartoint[0] = 3;
					identchartoint[1] = 5;
				} else if (char1 >= 80 && char1 <= 86) {
					char1 = char1 - 57;
					identchartoint[0] = (int) Math.floor(char1 / 10);
					identchartoint[1] = char1 % 10;
				} else if (char1 == 87)// W
				{

					identchartoint[0] = 3;
					identchartoint[1] = 2;
				} else if (char1 >= 88 && char1 <= 89) {
					char1 = char1 - 58;
					identchartoint[0] = (int) Math.floor(char1 / 10);
					identchartoint[1] = char1 % 10;
				} else if (char1 == 90)// Z
				{

					identchartoint[0] = 3;
					identchartoint[1] = 3;
				}

				else {
					System.out.println("�����Ҧr����1�X�A�ҿ�J����ƥ������^��r");

					return false;
				}
				// System.out.println("======01=====");
				// for(int j =0;j<identchartoint.length;j++){
				// System.out.println("identchartoint["+(j)+"]"+identchartoint[j]);
				// }
			}

			else if (i == 1) {
				/*
				 * �ˬd�O�_��1��2 ��Ƭ��ƭ�
				 */

				if (identchartoint[i + 1] == 1 || intcheck[i + 1] == 2) {
					intcheck[i] = identchartoint[i + 1];
				} else {
					System.out.println("�����Ҧr����2�X�A�ҿ�J����ƥ�����1��2");
					return false;
				}
			}
		}
		// System.out.println("======01=====");
		// for(int j =0;j<identchartoint.length;j++){
		// System.out.println("identchartoint["+(j)+"]"+identchartoint[j]);
		// }
		for (int i = 0; i < identchartoint.length; i++) {
			/*
			 * �B���ˬd�X
			 */
			if (i == 0 || i == 9) {
				sum = sum + identchartoint[i];
			} else if (i >= 1 || i <= 8) {
				sum = sum + identchartoint[i] * (10 - i);

			}
		}
		System.out.println("sum" + sum);

		if ((identchartoint[10] == sum % 10) && (identchartoint[10] == 0)) {
			/*
			 * �ˬd�ˬd�X�O�_���s�B�ۥ[�ᤧ�Ȱ��H�Ҽ�10���l�Ƥ]�O�_���s
			 */
			return true;
		} else {
			if (identchartoint[10] == (10 - sum % 10)) {
				return true;
			} else {
				System.out.println("�ˬd�Ȥ���");
				return false;
			}
		}
	}
}
