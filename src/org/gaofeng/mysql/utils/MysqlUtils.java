package org.gaofeng.mysql.utils;

import java.util.HashMap;
import java.util.Map;

public class MysqlUtils {
	static public Map<String, String> mappingRule = makeRule();
	static public Map<String, String> mappingRuleMybatis = makeRuleMybatis();
	static public Map<String, String> mappingImport = makeImport();

	private static Map<String, String> makeRule() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("int", "Integer");
		map.put("varchar", "String");
		map.put("float", "Float");
		map.put("double", "Double");
		map.put("timestamp", "Date");
		map.put("date", "Date");
		map.put("datetime", "Date");
		map.put("char", "String");
		map.put("mediumtext", "String");
		map.put("tinyint", "Integer");
		map.put("decimal", "Integer");
		map.put("decimals", "Double");
		map.put("int", "Integer");
		map.put("bigint", "Integer");
		map.put("longblob", "Object");
		map.put("longtext", "String");
		map.put("text", "String");
		return map;
	}
	private static Map<String, String> makeRuleMybatis() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("int", "NUMBER");
		map.put("varchar", "VARCHAR");
		map.put("float", "NUMBER");
		map.put("double", "NUMBER");
		map.put("timestamp", "Date");
		map.put("date", "Date");
		map.put("datetime", "Date");
		map.put("char", "VARCHAR");
		map.put("mediumtext", "VARCHAR");
		map.put("tinyint", "NUMBER");
		map.put("decimal", "NUMBER");
		map.put("decimals", "NUMBER");
		map.put("int", "NUMBER");
		map.put("bigint", "NUMBER");
		map.put("longblob", "BLOB");
		map.put("longtext", "VARCHAR");
		map.put("text", "VARCHAR");
		return map;
	}

	private static Map<String, String> makeImport() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Timestamp", "import java.sql.Timestamp;");
		map.put("Date", "import java.util.Date;");
		return map;
	}

}
