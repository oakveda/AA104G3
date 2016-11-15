/*�Ψӧ���ʪ����������~���Ʈw���ʪ������t��*/

package listener;

import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.cart.model.CartService;
import com.cart.model.CartVO;

@WebListener
public class InsertIntoCartListener implements HttpSessionBindingListener {

	ServletContext context;
	LinkedHashSet<CartVO> cartList;

	public InsertIntoCartListener() {

	}

	public InsertIntoCartListener(ServletContext context, LinkedHashSet<CartVO> cartList) {
		this.context = context;
		this.cartList = cartList;
	}

	public void valueBound(HttpSessionBindingEvent event) {

	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		CartService cartSvc = new CartService();
		LinkedHashSet<CartVO> oldCartList = null;

		/* �ΨӧR�²M�椺���ӫ~ */
		List<String> oldList = new LinkedList<String>();
		List<String> newList = new LinkedList<String>();

		/* �Y�²M��w�s�b�P�ӫ~�h�ק�ƶq */
		for (CartVO cartVO : cartList) {
			oldCartList = cartSvc.getOneCart(cartVO.getMemno()); // ���o�n�J��DB�����ʪ����C��
			for (CartVO oldCartVO : oldCartList) {
				if (cartVO.getProno().equals(oldCartVO.getProno())
						&& (!cartVO.getProcount().equals(oldCartVO.getProcount()))) {
					System.out.println("��s from " + oldCartVO + " to " + cartVO);
					cartSvc.updateCart(cartVO.getProno(), cartVO.getMemno(), cartVO.getProcount());
				}
			}
		}

		if (oldCartList != null) {
			for (CartVO cartVO : oldCartList) {
				oldList.add(cartVO.getMemno() + "," + cartVO.getProno()); // ��XDB�M��
			}

			/* �Y�s�M�榳�²M��ҨS�����A�h�s�W */
			for (CartVO cartVO : cartList) {
				if (!oldCartList.contains(cartVO)) {
					System.out.println("�s�W " + cartVO);
					cartSvc.addCart(cartVO.getProno(), cartVO.getMemno(), cartVO.getProcount());
				}

				newList.add(cartVO.getMemno() + "," + cartVO.getProno()); // ��Xsession�M��
			}

			/* �Y�s�M�榳�²M��ҨS�����A�h�R�� */
			for (String str : oldList) {
				if (!newList.contains(str)) {
					String[] arr = str.split(",");
					System.out.println("�R�� " + arr[1]);
					cartSvc.deleteOne(arr[0], arr[1]);
				}
			}
		}

	}

}
