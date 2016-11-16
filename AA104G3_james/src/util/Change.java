package util;

public class Change {

	/* 改變產品狀態名稱 */
	public static String changeProState(String prostate) {
		String str = null;
		if (prostate.equals("0")) {
			str = "未上架";
		} else if (prostate.equals("1")) {
			str = "已上架";
		} else {
			str = "已撤銷";
		}
		return str;
	}

	/* 改變付款方式名稱 */
	public static String changePayment(String payment) {
		String str = null;
		if (payment.equals("0")) {
			str = "信用卡";
		} else {
			str = "ATM";
		}
		return str;
	}



	/* 改變訂單狀態名稱 */
	public static String changeOrdState(String ordstate) {
		String str = null;
		if (ordstate.equals("0")) {
			str = "未付款";
		} else if (ordstate.equals("1")) {
			str = "已付款";
		} else {
			str = "已撤銷";
		}
		return str;
	}

	/* 改變收貨方式名稱 */
	public static String changePickup(String pickup){
		String str = null;
		if(pickup.equals("0")){
			str="郵寄";
		}else{
			str="快遞";
		}
		return str;
	}
}