package util;

public class Change {

	/* ���ܲ��~���A�W�� */
	public static String changeProState(String prostate) {
		String str = null;
		if (prostate.equals("0")) {
			str = "���W�[";
		} else if (prostate.equals("1")) {
			str = "�w�W�[";
		} else {
			str = "�w�M�P";
		}
		return str;
	}

	/* ���ܥI�ڤ覡�W�� */
	public static String changePayment(String payment) {
		String str = null;
		if (payment.equals("0")) {
			str = "�H�Υd";
		} else {
			str = "ATM";
		}
		return str;
	}



	/* ���ܭq�檬�A�W�� */
	public static String changeOrdState(String ordstate) {
		String str = null;
		if (ordstate.equals("0")) {
			str = "���I��";
		} else if (ordstate.equals("1")) {
			str = "�w�I��";
		} else {
			str = "�w�M�P";
		}
		return str;
	}

	/* ���ܦ��f�覡�W�� */
	public static String changePickup(String pickup){
		String str = null;
		if(pickup.equals("0")){
			str="�l�H";
		}else{
			str="�ֻ�";
		}
		return str;
	}
}