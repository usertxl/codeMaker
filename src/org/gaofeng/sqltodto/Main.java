package org.gaofeng.sqltodto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.db.DBHelper;
import org.gaofeng.common.properties.PropertiesTool;

public class Main extends CommonFunction {
	static DBHelper db1 = null;

	/**
	 * 主方法入口
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		long ak = System.currentTimeMillis();
			sql = PropertiesTool.SQL;
		System.out.println(sql);
		db1 = new DBHelper(sql);
		List<String> listTable = new ArrayList<String>();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String tablename = ret.getString(1);
				listTable.add(tablename);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < listTable.size(); i++) {
			String domainNameStr = listTable.get(i);
			domainName = domainNameStr;
			makeDomain(domainNameStr);
		}

		System.out.println("所有对象生成完毕，总共用时：" + (System.currentTimeMillis() - ak)
				/ 1000 + "s");

	}

	public static void makeDomain(String domainNameStr, String sqlStr)
			throws Exception {
		sql = sqlStr;
		domainName = domainNameStr.toUpperCase();
		if (sqlStr != null && !"".equals(sqlStr)) {
			viewName = createView(sql);
			makeDomain(viewName);
			dropView(viewName);
		} else {
			makeDomain(domainNameStr.toUpperCase());
		}

	}

}
