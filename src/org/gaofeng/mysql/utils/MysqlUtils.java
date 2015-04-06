package org.gaofeng.mysql.utils;

import java.util.HashMap;
import java.util.Map;

public class MysqlUtils {
	static public Map<String, String> mappingRule = makeRule();
	static public Map<String, String> mappingRuleMybatis = makeRuleMybatis();
	static public Map<String, String> mappingImport = makeImport();

	private static Map<String, String> makeRule() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mediumint", "Integer");
		map.put("smallint", "Short");
		map.put("tinyint", "Byte");
		map.put("int", "Integer");
		map.put("integer", "Integer");
		map.put("bigint", "Long");
		map.put("bit", "Boolean");
		map.put("real", "Double");
		map.put("double", "Double");
		map.put("float", "Float");
		map.put("decimal", "Long");
		map.put("numeric", "Long");
		map.put("char", "String");
		map.put("varchar", "String");
		map.put("date", "Date");
		map.put("time", "Date");
		map.put("year", "Date");
		map.put("timestamp", "Date");
		map.put("datetime", "Date");
		map.put("tinytext", "String");
		map.put("enum", "String");
		map.put("set", "String");
		map.put("point", "Object");
		map.put("linestring", "Object");
		map.put("polygon", "Object");
		map.put("multipoint", "Object");
		map.put("multilinestring", "Object");
		map.put("multipolygon", "Object");
		map.put("geometrycollection", "Object");
		map.put("tinyblob", "byte[]");
		map.put("blob", "byte[]");
		map.put("mediumblob", "byte[]");
		map.put("longblob", "byte[]");
		map.put("mediumtext", "String");
		map.put("text", "String");
		map.put("longtext", "String");
		map.put("binary", "byte[]");
		map.put("varbinary", "byte[]");
		map.put("geometry", "byte[]");
		
		map.put("decimals", "BigDecimal");
		return map;
	}
	private static Map<String, String> makeRuleMybatis() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mediumint", "INTEGER");
		map.put("smallint", "SMALLINT");
		map.put("tinyint", "TINYINT");
		map.put("int", "INTEGER");
		map.put("integer", "Integer");
		map.put("bigint", "BIGINT");
		map.put("bit", "BIT");
		map.put("real", "DOUBLE");
		map.put("double", "DOUBLE");
		map.put("float", "REAL");
		map.put("decimal", "DECIMAL");
		map.put("numeric", "DECIMAL");
		map.put("char", "CHAR");
		map.put("varchar", "VARCHAR");
		map.put("date", "DATE");
		map.put("time", "TIME");
		map.put("year", "DATE");
		map.put("timestamp", "TIMESTAMP");
		map.put("datetime", "TIMESTAMP");
		map.put("tinytext", "VARCHAR");
		map.put("enum", "CHAR");
		map.put("set", "CHAR");
		map.put("point", "OTHER");
		map.put("linestring", "OTHER");
		map.put("polygon", "OTHER");
		map.put("multipoint", "OTHER");
		map.put("multilinestring", "OTHER");
		map.put("multipolygon", "OTHER");
		map.put("geometrycollection", "OTHER");
		map.put("tinyblob", "BINARY");
		map.put("blob", "LONGVARBINARY");
		map.put("mediumblob", "LONGVARBINARY");
		map.put("longblob", "LONGVARBINARY");
		map.put("mediumtext", "LONGVARBINARY");
		map.put("text", "LONGVARBINARY");
		map.put("longtext", "LONGVARBINARY");
		map.put("binary", "BINARY");
		map.put("varbinary", "VARBINARY");
		map.put("geometry", "BINARY");
		
		map.put("decimals", "DECIMAL");
		return map;
	}

	private static Map<String, String> makeImport() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Timestamp", "import java.sql.Timestamp;");
		map.put("Date", "import java.util.Date;");
		map.put("BigDecimal", "import java.math.BigDecimal;");
		return map;
	}

}
