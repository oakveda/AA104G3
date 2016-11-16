/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Product {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		System.out.println(columnName+" "+value);
		System.out.println(111);
		if ("proprice".equals(columnName)) // �Ω��L
			aCondition = columnName + "=" + value;
		else if ("prono".equals(columnName)||"proname".equals(columnName) || "product_classVO.classno".equals(columnName)) // �Ω�varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("adddate".equals(columnName))                          // �Ω�Oracle��date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
				
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {		
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				//System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
}
