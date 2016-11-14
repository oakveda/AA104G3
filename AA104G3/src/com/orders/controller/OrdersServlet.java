package com.orders.controller;

import java.io.IOException;
import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.*;
import com.member.model.*;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.orders_detail.model.Orders_detailService;
import com.product.model.ProductService;


/*@WebServlet("/OrdersServlet")*/
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		/* 轉向填寫訂單資訊頁面 */
		if ("write_Orders_Detail".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			
			try {
				String memno = request.getParameter("memno");				

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memno);
				request.setAttribute("memberVO", memberVO);
				String url = "/front-end/orders/ordersInfo.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		/* 檢查訂單資訊 */
		if ("check_Orders_Detail".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");			

			try {
//				String memno = request.getParameter("memno");
//				MemberService memberSvc = new MemberService();
//				MemberVO memberVO = memberSvc.getOneMember(memno);
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				String memno = memberVO.getMemno();

				String memname = request.getParameter("memname");
				if ((memname.trim()).length() == 0 || memname == null) {
					errorMsgs.add("請輸入收件人姓名");
					memname = memberVO.getMemname();
				}

				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (!(memname.trim()).matches(nameReg)) {
					errorMsgs.add("請輸入正確的名稱");
					memname = memberVO.getMemname();
				}

				String memphone = request.getParameter("memphone");
				if ((memphone.trim()).length() == 0 || memphone == null) {
					errorMsgs.add("請輸入收件人電話");
					memphone = memberVO.getMemphone();
				}
				String phoneReg = "^[0-9_]{9,10}$";
				if (!(memphone.trim()).matches(phoneReg)) {
					errorMsgs.add("請輸入正確的電話");
					memphone = memberVO.getMemphone();
				}

				String mememail = request.getParameter("mememail");
				if ((mememail.trim()).length() == 0 || mememail == null) {
					errorMsgs.add("請輸入收件人信箱");
					mememail = memberVO.getMememail();
				}
				String emailReg = "^[_a-zA-Z0-9-]+([.][_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+([.][a-zA-Z0-9-]+)*$";
				if (!(mememail.trim()).matches(emailReg)) {
					errorMsgs.add("請輸入正確的信箱");
					mememail = memberVO.getMememail();
				}

				String pickup = request.getParameter("pickup");

				String memaddr = request.getParameter("memaddr");
				if ((memaddr.trim()).length() == 0 || memaddr == null) {
					errorMsgs.add("請輸入收件人地址");
					memaddr = memberVO.getMemaddr();
				}
				
				// String addrReg="";
				// if(!memaddr.trim().matches(addrReg)){
				// errorMsgs.add("請輸入正確的地址");
				// memaddr = memberVO.getMemaddr();;
				// }

				String payment = request.getParameter("payment");

				// String cardtype = request.getParameter("cardtype");
				// String firstnum = request.getParameter("firstnum");
				// String secondnum = request.getParameter("secondnum");
				// String thirdnum = request.getParameter("v");
				// String forthnum = request.getParameter("forthnum");
				// String month = request.getParameter("month");
				// String idnt = request.getParameter("idnt");
				
				/*回傳修改前的資料*/
				memberVO.setMemname(memname);
				memberVO.setMemphone(memphone);
				memberVO.setMememail(mememail);
				memberVO.setMemaddr(memaddr);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memberVO", memberVO);					
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}

				/* 取得購物車內容 & 商品價格 */
				CartService cartSvc = new CartService();
//				LinkedHashSet<CartVO> list = cartSvc.getOneCart(memno);
				
				LinkedHashSet<CartVO> cartList = (LinkedHashSet<CartVO>)session.getAttribute("cartList");
				ProductService productSvc = new ProductService();
				Integer pricesum = 0;
				Integer total = 0;

				for (CartVO vo : cartList) {
					pricesum += vo.getProcount() * (productSvc.getOneProduct(vo.getProno())).getProprice();
					total += vo.getProcount();					
				}
				
				
				
				/* 新增訂單 */
				OrdersService ordersSvc = new OrdersService();
				OrdersVO ordersVO = ordersSvc.addOrders(memno, pricesum, total, payment, new Date(System.currentTimeMillis()), memname, pickup, memphone, memaddr, cartList);
								
				/* 清除購物車 */				
				cartSvc.deleteAll(memno);								
				request.setAttribute("ordersVO", ordersVO);
//				request.setAttribute("list", list);

				RequestDispatcher successView = request.getRequestDispatcher("/front-end/orders/checkUp.jsp");
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("error "+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		/* 撤銷訂單 */
		if ("revoke".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String ordno = request.getParameter("ordno");
				if (ordno == null) {
					errorMsgs.add("無此資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
				}

				OrdersService ordersSvc = new OrdersService();
				ordersSvc.revoke(ordno);

				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		/* 取得某會員所有訂單 */
		if ("get_One_Member".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String memno = request.getParameter("memno");
				if (memno == null || memno.length() == 0) {
					errorMsgs.add("無此資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}

				OrdersService ordersSvc = new OrdersService();
				List<OrdersVO> list = ordersSvc.getAllByMemno(memno);

				request.setAttribute("list", list);
				String url = "/front-end/orders/listAllByMemno.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

	}

}
