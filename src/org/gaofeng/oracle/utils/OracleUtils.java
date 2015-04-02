package org.gaofeng.oracle.utils;

import java.util.HashMap;
import java.util.Map;

public class OracleUtils {
	static public Map<String, String> mappingRule = makeRule();
	static public Map<String, String> mappingRuleMybatis = makeRuleMybatis();
	static public Map<String, String> mappingImport = makeImport();

	private static Map<String, String> makeRule() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("NUMBER", "Integer");
		//oracle的number有可能是整数,也可能是浮点数
		//我将浮点数的number转换成NUMBERS
		map.put("NUMBERS", "Double");
		map.put("VARCHAR2", "String");
		map.put("NVARCHAR2", "String");
		map.put("TIMESTAMP", "Date");
		map.put("DATE", "Date");
		map.put("CHAR", "String");
		map.put("LONG", "Long");
		map.put("CLOB", "String");
		map.put("BLOB", "Object");
		map.put("FLOAT", "Double");
		map.put("RAW", "Long");
		return map;
	}
	private static Map<String, String> makeRuleMybatis() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("NUMBER", "NUMBER");
		//oracle的number有可能是整数,也可能是浮点数
		//我将浮点数的number转换成NUMBERS
		map.put("NUMBERS", "NUMBER");
		map.put("VARCHAR2", "VARCHAR");
		map.put("NVARCHAR2", "VARCHAR");
		map.put("TIMESTAMP", "Date");
		map.put("DATE", "Date");
		map.put("CHAR", "VARCHAR");
		map.put("LONG", "NUMBER");
		map.put("CLOB", "CLOB");
		map.put("BLOB", "BLOB");
		map.put("FLOAT", "NUMBER");
		map.put("RAW", "NUMBER");
		return map;
	}
	private static Map<String, String> makeImport() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Timestamp", "import java.sql.Timestamp;");
		map.put("Date", "import java.util.Date;");
		return map;
	}

}
