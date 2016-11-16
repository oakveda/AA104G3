package com.cart.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.*;
import com.member.model.MemberVO;
import com.product.model.*;

/*@WebServlet("/front-end/cart/cart.do")*/
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		MemberVO memberVO = (MemberVO) session.getAttribute("MemberVO");
		LinkedHashSet<CartVO> cartList = (LinkedHashSet<CartVO>) session.getAttribute("cartList");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/* �����ШD�ѼơA��J�榡���~�B�z */
				String memno = request.getParameter("memno");
				if (memno == null || (memno.trim().length() == 0)) {
					errorMsgs.add("�п�J�|���s��");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				try {
					new Integer(memno);
				} catch (Exception e) {
					errorMsgs.add("��J�榡�����T");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/* �}�l�d�߸�� */
				// CartService cartSvc = new CartService();
				// LinkedHashSet<CartVO> list = cartSvc.getOneCart(memno);

				/* �d�ߧ����A�}�l��� */
				// request.setAttribute("list", list);

				String url = "/front-end/cart/listOneCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				response.getWriter().append("test");
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
				failureView.forward(request, response);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/* �����ШD�ѼơA��J�榡�����~�B�z */
				String memno = request.getParameter("memno").trim();
				String prono = request.getParameter("prono").trim();
				Integer procount = null;


				try {
					procount = new Integer(request.getParameter("procount").trim());
				} catch (NumberFormatException e) {
					procount = 1;
					errorMsgs.add("�ƶq�ж�Ʀr");
				}
				
				if (procount > 99) {
					errorMsgs.add("�ƶq�W����99");
					procount = 99;
				}

				for (CartVO vo : cartList) {
					if (vo.getProno().equals(prono)) {
						errorMsgs.add("���ӫ~�w�b�ʪ�����");
					}
				}

				CartVO cartVO = new CartVO();
				cartVO.setMemno(memno);
				cartVO.setProno(prono);
				cartVO.setProcount(procount);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("cartVO", cartVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/addCart.jsp");
					failureView.forward(request, response);
					return;
				}

				/* �}�l�s�W��� */
				cartList.add(cartVO);

				/* �s�W�����A����� */
				String url = "/front-end/cart/listAllCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				/* ��L���~�B�z */
				errorMsgs.add("error: " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/addCart.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				/* ���o�|���s���A�ӫ~�s���A�s�B�¼ƶq */
				String memno = request.getParameter("memno");
				String prono = request.getParameter("prono");

				Integer newcount = 0;

				try {
					newcount = new Integer(request.getParameter("newcount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�J���T�ƶq");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}

				Integer oldcount = 0;
				/*�ק��ʪ��������ƶq*/
				for (CartVO cartVO : cartList) {
					if (cartVO.getMemno().equals(memno) && cartVO.getProno().equals(prono)) {
						cartVO.setProcount(newcount);
					}
				}
				
				/*�ק�w�s*/
//				ProductService productSvc = new ProductService();
//				productSvc.getOneProduct(prono);
				
				
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		if ("delete_One_Product".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				/* �����ШD�Ѽ� */
				String memno = request.getParameter("memno");
				String prono = request.getParameter("prono");

				/* �}�l�R����� */
				// CartService cartSvc = new CartService();
				// cartSvc.deleteOne(memno, prono);

				/*�R���ʪ�������ܪ��ӫ~*/
				for (CartVO vo : cartList) {
					if (vo.getMemno().equals(memno) && vo.getProno().equals(prono)) {
						cartList.remove(vo);

					}
				}

				/* �R�������A�}�l��� */
				// LinkedHashSet<CartVO> list = cartSvc.getOneCart(memno);
				// request.setAttribute("list", list);

				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				/* ��L�i�઺���~�B�z */
				errorMsgs.add("��ƧR������" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

	}// get end

}
