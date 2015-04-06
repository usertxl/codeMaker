package org.gaofeng.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.db.DBHelper;
import org.gaofeng.common.properties.PropertiesTool;

public class RunBatch extends CommonFunction {
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
		sysLog("当前配置数据库为:" + PropertiesTool.TYPE, DEBUG);
		sysLog("当前配置数据库连接为:" + PropertiesTool.URL, DEBUG);
		sysLog("当前生成domain文件路径为:" + PropertiesTool.DOMAINPATH, DEBUG);
		sysLog("当前生成sqlMap文件路径为:" + PropertiesTool.SQLMAPPATH, DEBUG);
		sysLog("当前生成mapper文件路径为:" + PropertiesTool.MAPPERPATH, DEBUG);
		sql = PropertiesTool.SQLBATCH;
		sysLog(sql, INFO);
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
			tableName = domainNameStr;
			tableNameForClassName = MappingRule.forClassName(domainNameStr);
			tableNameForPropertyName = MappingRule
					.forPropertyName(domainNameStr);
			makeDomain(domainNameStr);
		}

		sysLog("所有对象生成完毕，总共用时：" + (System.currentTimeMillis() - ak) / 1000
				+ "s", DEBUG);

	}

}
