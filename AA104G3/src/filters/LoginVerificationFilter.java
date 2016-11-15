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

		// �qsession���X�n�J�|��
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				
		if (memberVO == null) {
			memberVO = new MemberVO();
			memberVO.setMemno("000000");
			session.setAttribute("memberVO", memberVO);
			
			LinkedHashSet<CartVO> cartList = new LinkedHashSet<CartVO>();			
			session.setAttribute("cartList", cartList);			
			
//			session.setAttribute("location", req.getRequestURI());
//			resp.sendRedirect(req.getContextPath()+"/select_page.jsp");			
//			return;
		}else if(memberVO.getMemno() == "000000"){
			//do nothing
		}else{				
			
			LinkedHashSet<CartVO> cartList = (LinkedHashSet<CartVO>) session.getAttribute("cartList");
			
			if (!(cartList == null)) {
				List<String> list = new LinkedList<String>();
				/* ���X�X�Ȯɪ��ʪ������e */
				for (CartVO cartVO : cartList) {
					list.add(cartVO.getProno() + "," + cartVO.getProcount());
				}

				/* ���X�X�ȭ즳���ʪ��� */
				CartService cartSvc = new CartService();
				cartList = (LinkedHashSet<CartVO>) cartSvc.getOneCart(memberVO.getMemno());

				/* ��X�Ȯɪ��ʪ������e�[��|���쥻�N�����ʪ��� */
				
				for (String str : list) {
					String[] arr = str.split(",");
					CartVO cartVO = new CartVO();
					cartVO.setMemno(memberVO.getMemno());
					cartVO.setProno(arr[0]);
					cartVO.setProcount(Integer.parseInt(arr[1]));
					cartList.add(cartVO);
				}
			} else {
				/* ���X�X�ȭ즳���ʪ��� */
				CartService cartSvc = new CartService();
				cartList = (LinkedHashSet<CartVO>) cartSvc.getOneCart(memberVO.getMemno());
			}

			session.setAttribute("cartList", cartList);

			/* ���ʪ����qsession�����ɡA�|�I�s��ť���A���Ʈw�����ʪ����i���� */
			InsertIntoCartListener listener = new InsertIntoCartListener(context, cartList);
			session.setAttribute("addCart_listener", listener);
						
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
