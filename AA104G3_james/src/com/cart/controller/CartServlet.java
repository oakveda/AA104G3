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
				/* 接收請求參數，輸入格式錯誤處理 */
				String memno = request.getParameter("memno");
				if (memno == null || (memno.trim().length() == 0)) {
					errorMsgs.add("請輸入會員編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				try {
					new Integer(memno);
				} catch (Exception e) {
					errorMsgs.add("輸入格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/* 開始查詢資料 */
				// CartService cartSvc = new CartService();
				// LinkedHashSet<CartVO> list = cartSvc.getOneCart(memno);

				/* 查詢完成，開始轉交 */
				// request.setAttribute("list", list);

				String url = "/front-end/cart/listOneCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				response.getWriter().append("test");
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cart/select_page.jsp");
				failureView.forward(request, response);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數，輸入格式的錯誤處理 */
				String memno = request.getParameter("memno").trim();
				String prono = request.getParameter("prono").trim();
				Integer procount = null;


				try {
					procount = new Integer(request.getParameter("procount").trim());
				} catch (NumberFormatException e) {
					procount = 1;
					errorMsgs.add("數量請填數字");
				}
				
				if (procount > 99) {
					errorMsgs.add("數量上限為99");
					procount = 99;
				}

				for (CartVO vo : cartList) {
					if (vo.getProno().equals(prono)) {
						errorMsgs.add("此商品已在購物車內");
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

				/* 開始新增資料 */
				cartList.add(cartVO);

				/* 新增完成，轉交資料 */
				String url = "/front-end/cart/listAllCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				/* 其他錯誤處理 */
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
				/* 取得會員編號，商品編號，新、舊數量 */
				String memno = request.getParameter("memno");
				String prono = request.getParameter("prono");

				Integer newcount = 0;

				try {
					newcount = new Integer(request.getParameter("newcount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填入正確數量");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}

				Integer oldcount = 0;
				/*修改購物車中的數量*/
				for (CartVO cartVO : cartList) {
					if (cartVO.getMemno().equals(memno) && cartVO.getProno().equals(prono)) {
						cartVO.setProcount(newcount);
					}
				}
				
				/*修改庫存*/
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
				/* 接收請求參數 */
				String memno = request.getParameter("memno");
				String prono = request.getParameter("prono");

				/* 開始刪除資料 */
				// CartService cartSvc = new CartService();
				// cartSvc.deleteOne(memno, prono);

				/*刪除購物車中選擇的商品*/
				for (CartVO vo : cartList) {
					if (vo.getMemno().equals(memno) && vo.getProno().equals(prono)) {
						cartList.remove(vo);

					}
				}

				/* 刪除完成，開始轉交 */
				// LinkedHashSet<CartVO> list = cartSvc.getOneCart(memno);
				// request.setAttribute("list", list);

				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				/* 其他可能的錯誤處理 */
				errorMsgs.add("資料刪除失敗" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

	}// get end

}
