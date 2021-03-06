/*
判斷是否為會員，非會員的話給一個 memno 為 000000 的編號，
為會員的情況，會先從session抓出還在訪客時有新增過的商品
新增進去，因為改寫過 hashset 重複的東西不會再加進去，再把
更動過的購物車覆蓋session，再對購物車附加傾聽器
*/

package filters;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.*;
import com.member.model.*;

import listener.InsertIntoCartListener;

/*@WebFilter("/LoginVerificationFilter")*/
public class LoginVerificationFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();

		// 從session取出登入會員
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		if (memberVO == null) {
			memberVO = new MemberVO();
			memberVO.setMemno("000000");
			session.setAttribute("memberVO", memberVO);

			LinkedHashSet<CartVO> cartList = new LinkedHashSet<CartVO>();
			session.setAttribute("cartList", cartList);

			// session.setAttribute("location", req.getRequestURI());
			// resp.sendRedirect(req.getContextPath()+"/select_page.jsp");
			// return;
		} else if (memberVO.getMemno() == "000000") {
			// do nothing
		} else {

			LinkedHashSet<CartVO> guestCartList = (LinkedHashSet<CartVO>) session.getAttribute("cartList");
			LinkedHashSet<CartVO> cartList = null;

			if (!(guestCartList == null)) {
				List<String> list = new LinkedList<String>();
				/* 取出訪客時的購物車內容 */
				for (CartVO cartVO : guestCartList) {
					list.add(cartVO.getProno() + "," + cartVO.getProcount());
				}

				/* 取出訪客原有的購物車 */
				CartService cartSvc = new CartService();
				cartList = (LinkedHashSet<CartVO>) cartSvc.getOneCart(memberVO.getMemno());

				/* 把訪客時的購物車內容加到會員原本就有的購物車 */

				for (String str : list) {
					String[] arr = str.split(",");
					CartVO cartVO = new CartVO();
					cartVO.setMemno(memberVO.getMemno());
					cartVO.setProno(arr[0]);
					cartVO.setProcount(Integer.parseInt(arr[1]));
					cartList.add(cartVO);
				}
			} else {
				/* 取出訪客原有的購物車 */
				CartService cartSvc = new CartService();
				cartList = (LinkedHashSet<CartVO>) cartSvc.getOneCart(memberVO.getMemno());
			}

			session.setAttribute("cartList", cartList);

			/* 當購物車從session脫離時，會呼叫傾聽器，對資料庫內的購物車進行更新 */
			InsertIntoCartListener listener = new InsertIntoCartListener(context, cartList);
			session.setAttribute("addCart_listener", listener);

		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
