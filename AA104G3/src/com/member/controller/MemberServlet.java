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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				String memno = req.getParameter("memno");
				System.out.println(action);
				System.out.println("getOne_For_Display".equals(action));
				if (memno == null || (memno.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				//

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memno);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ insert ================================

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String memid = null;

				try {
					memid = req.getParameter("memid").trim();
					if (memid == null || memid.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memid = req.getParameter("memid").trim();
					errorMsgs.add("請輸入帳號!");
				}

				String mempsw = null;
				try {
					mempsw = req.getParameter("mempsw").trim();
					if (mempsw.length() < 6) {
						throw new Exception();
					}
					char[] mempswcheck = mempsw.toCharArray();
					int[] count = { 0, 0, 0 };// 0 大寫 1 小寫 2 數字
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
					errorMsgs.add("密碼不符規定!");
				}

				String memname = null;
				try {
					memname = req.getParameter("memname").trim();
					if (memname == null || memname.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memname = req.getParameter("memname").trim();
					errorMsgs.add("請輸入姓名!");
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
					errorMsgs.add("請輸入符合規則的email!");
				}

				String memaddr = null;

				try {
					memaddr = req.getParameter("memaddr").trim();
					if (memaddr == null || memaddr.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memaddr = req.getParameter("memaddr").trim();
					errorMsgs.add("請輸入地址!");
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
					errorMsgs.add("請輸入數字!");
				} catch (Exception e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("請輸入符合的手機號碼!");
				}

				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}

				String memnick = null;

				try {
					memnick = req.getParameter("memnick").trim();
					if (memnick == null || memnick.length() == 0) {
						throw new Exception();

					}

				} catch (Exception e) {
					memnick = req.getParameter("memnick").trim();
					errorMsgs.add("請輸入暱稱!");
				}

				String memident = null;

				try {
					memident = req.getParameter("memident").trim();
					if (memident == null || memident.length() != 10 || !identcheck(memident)) {
						throw new Exception();

					}

				} catch (Exception e) {
					memident = req.getParameter("memident").trim();
					errorMsgs.add("請輸入正確的身分證字號!");
				}

				String memgender = null;

				try {
					memgender = req.getParameter("memgender");
					System.out.println(memgender);
					if (memgender == null) {
						throw new Exception();
					}

				} catch (Exception e) {

					errorMsgs.add("請選擇性別!");
				}

				// 圖片預定區==========================================================================

				byte[] mempic = null;

				// 圖片預定區==========================================================================

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
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(memid, mempsw, memname, mememail, memaddr, memphone, mempic, memnick,
						memident, membirth, memgender);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ update ================================

		if ("update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					int[] count = { 0, 0, 0 };// 0 大寫 1 小寫 2 數字
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
					errorMsgs.add("密碼不符規定!");
				}

				String memname = null;
				try {
					memname = req.getParameter("memname").trim();
					if (memname == null || memname.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memname = req.getParameter("memname").trim();
					errorMsgs.add("請輸入姓名!");
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
					errorMsgs.add("請輸入符合規則的email!");
				}

				String memaddr = null;

				try {
					memaddr = req.getParameter("memaddr").trim();
					if (memaddr == null || memaddr.length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					memaddr = req.getParameter("memaddr").trim();
					errorMsgs.add("請輸入地址!");
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
					errorMsgs.add("請輸入數字!");
				} catch (Exception e) {
					memphone = req.getParameter("memphone").trim();
					errorMsgs.add("請輸入符合的手機號碼!");
				}

				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}

				String memnick = null;

				try {
					memnick = req.getParameter("memnick").trim();
					if (memnick == null || memnick.length() == 0) {
						throw new Exception();

					}

				} catch (Exception e) {
					memnick = req.getParameter("memnick").trim();
					errorMsgs.add("請輸入暱稱!");
				}

				String memident = null;

				try {
					memident = req.getParameter("memident").trim();
					if (memident == null || memident.length() != 10 || !identcheck(memident)) {
						throw new Exception();

					}

				} catch (Exception e) {
					memident = req.getParameter("memident").trim();
					errorMsgs.add("請輸入正確的身分證字號!");
				}

				String memgender = null;

				try {
					memgender = req.getParameter("memgender");
					// System.out.println(memgender);
					if (memgender == null) {
						throw new Exception();
					}

				} catch (Exception e) {

					errorMsgs.add("請選擇性別!");
				}

				String memstate = req.getParameter("memstate");
				Date regdate = Date.valueOf(req.getParameter("regdate").trim());
				// 圖片預定區==========================================================================

				byte[] mempic = null;

				// 圖片預定區==========================================================================

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
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/member/update_member_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始更新資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memno, memid, mempsw, memname, mememail, memaddr, memphone, mempic,
						regdate, memnick, memident, membirth, memstate, memgender);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		} // update end

		// ================ delete ================================

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String memno = req.getParameter("memno");

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(memno);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

		// ================ getOne_For_Update ================================

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memno = req.getParameter("memno");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memno);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
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
			 * 檢查是否為英文字 是的話運算出檢查用數字
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
					System.out.println("身份證字號第1碼，所輸入的資料必須為英文字");

					return false;
				}
				// System.out.println("======01=====");
				// for(int j =0;j<identchartoint.length;j++){
				// System.out.println("identchartoint["+(j)+"]"+identchartoint[j]);
				// }
			}

			else if (i == 1) {
				/*
				 * 檢查是否為1或2 轉化為數值
				 */

				if (identchartoint[i + 1] == 1 || intcheck[i + 1] == 2) {
					intcheck[i] = identchartoint[i + 1];
				} else {
					System.out.println("身份證字號第2碼，所輸入的資料必須為1或2");
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
			 * 運算檢查碼
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
			 * 檢查檢查碼是否為零且相加後之值除以模數10的餘數也是否為零
			 */
			return true;
		} else {
			if (identchartoint[10] == (10 - sum % 10)) {
				return true;
			} else {
				System.out.println("檢查值不符");
				return false;
			}
		}
	}
}
