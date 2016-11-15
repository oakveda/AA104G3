/*用來更改購物車內的物品跟資料庫內購物車的差異*/

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

		/* 用來刪舊清單內的商品 */
		List<String> oldList = new LinkedList<String>();
		List<String> newList = new LinkedList<String>();

		/* 若舊清單已存在同商品則修改數量 */
		for (CartVO cartVO : cartList) {
			oldCartList = cartSvc.getOneCart(cartVO.getMemno()); // 取得登入者DB內的購物車列表
			for (CartVO oldCartVO : oldCartList) {
				if (cartVO.getProno().equals(oldCartVO.getProno())
						&& (!cartVO.getProcount().equals(oldCartVO.getProcount()))) {
					System.out.println("更新 from " + oldCartVO + " to " + cartVO);
					cartSvc.updateCart(cartVO.getProno(), cartVO.getMemno(), cartVO.getProcount());
				}
			}
		}

		if (oldCartList != null) {
			for (CartVO cartVO : oldCartList) {
				oldList.add(cartVO.getMemno() + "," + cartVO.getProno()); // 抓出DB清單
			}

			/* 若新清單有舊清單所沒有的，則新增 */
			for (CartVO cartVO : cartList) {
				if (!oldCartList.contains(cartVO)) {
					System.out.println("新增 " + cartVO);
					cartSvc.addCart(cartVO.getProno(), cartVO.getMemno(), cartVO.getProcount());
				}

				newList.add(cartVO.getMemno() + "," + cartVO.getProno()); // 抓出session清單
			}

			/* 若新清單有舊清單所沒有的，則刪除 */
			for (String str : oldList) {
				if (!newList.contains(str)) {
					String[] arr = str.split(",");
					System.out.println("刪除 " + arr[1]);
					cartSvc.deleteOne(arr[0], arr[1]);
				}
			}
		}

	}

}
