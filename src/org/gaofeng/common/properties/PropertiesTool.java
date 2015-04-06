package org.gaofeng.common.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.gaofeng.common.file.FileUtils;

/**
 * 获得deploy.properties里面值
 * 
 * @author gf
 *
 */
public class PropertiesTool {
	private static PropertiesTool propertiesTool = null;

	public static int POOLSIZE = getIntProperty("poolSize");

	public static String URL = getStringProperty("url");
	public static String USER = getStringProperty("user");
	public static String PASSWORD = getStringProperty("password");
	public static String TYPE = getStringProperty("type");
	public static String DOMAINPATH = getStringProperty("domainPath");
	public static String DAOPATH = getStringProperty("daoPath");
	public static String SQLMAPPATH = getStringProperty("sqlMapPath");
	public static String DOMAINPACKAGENAME = getStringProperty("domainPackageName");
	public static String DAOPACKAGENAME = getStringProperty("daoPackageName");
	public static String MAPPERPACKAGENAME = getStringProperty("mapperPackageName");
	public static String MAPPERPATH = getStringProperty("mapperPath");
	public static String BEANSUFFIXNAME = getStringProperty("beanSuffixName");

	public static Boolean IFDOMAIN = getBooleanProperty("ifDomain");
	public static Boolean IFMAPPER = getBooleanProperty("ifMapper");
	public static Boolean IFSQL = getBooleanProperty("ifSql");
	public static Map<String,Boolean> LOGLEV =getStringsProperty("logLev");

	public static String SQLBATCH = PropertiesTool.getInstance().getSql("batch.sql");
	public static String SQLSINGLE = PropertiesTool.getInstance().getSql("single.sql");
	public static List<String> SQLLIST = PropertiesTool.getInstance()
			.getSqlList();
	public static Map<String, Map<String, String>> commentsMap = PropertiesTool
			.getInstance().getCommentsMap();
	private static Map<String, Properties> properties;

	private String getSql(String fileName) {
		String sql = "";
		List<String> listStr = FileUtils.getInstance().getSqlStrsByFileName(
				fileName);
		for (String a : listStr) {
			if (a != null) {
				sql += " " + a + " ";
			}
		}
		return sql;
	}

	private static Map<String, Boolean> getStringsProperty(String key) {
		PropertiesTool tool = PropertiesTool.getInstance();
		Map<String,Boolean> map=new HashMap<String, Boolean>();
		String value = tool.readValue("/deploy.properties", key);
		if (value == null) {
			value = "";
		}
		String [] strs=value.split(",");
		for(String a:strs){
			map.put(a, true);
		}
		return map;
	}

	private List<String> getSqlList() {
		List<String> listSql = new ArrayList<String>();
		String sql = "";
		List<String> listStr = FileUtils.getInstance().getSqlStrsByFileName(
				"single.sql");

		for (String a : listStr) {
			if (a != null) {
				if (a.indexOf(";") > -1) {
					sql += " " + a.substring(0, a.indexOf(";")) + " ";
					listSql.add(sql);
					sql = "";
				} else {
					sql += " " + a + " ";
				}
			}
		}
		if (!"".equals(sql)) {
			listSql.add(sql);
		}
		return listSql;
	}

	private Map<String, Map<String, String>> getCommentsMap() {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		List<String> listStr = FileUtils.getInstance().getSqlStrsByFileName(
				"CommentsMap.sql");
		try {
			for (String temp : listStr) {
				if (temp.indexOf("###") > -1) {
					String[] a = temp.split("###");
					if (map.get(a[0]) == null) {
						Map<String, String> col = new HashMap<String, String>();
						map.put(a[0], col);
					}
					map.get(a[0]).put(a[1], a[2]);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 获得Boolean型属性
	 * 
	 * @param key
	 * @return
	 */
	private static Boolean getBooleanProperty(String key) {
		PropertiesTool tool = PropertiesTool.getInstance();
		String value = tool.readValue("/deploy.properties", key);
		if (value == null) {
			value = "";
		}
		if ("true".equals(value) || "TRUE".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得int型属性
	 * 
	 * @param key
	 * @return
	 */
	private static int getIntProperty(String key) {
		PropertiesTool tool = PropertiesTool.getInstance();
		String value = tool.readValue("/deploy.properties", key);
		if (value == null || "".equals(value) || "null".equals(value)) {
			value = "0";
		}
		return Integer.parseInt(value);
	}

	/**
	 * 获得Sring型属性
	 * 
	 * @param key
	 * @return
	 */
	private static String getStringProperty(String key) {
		PropertiesTool tool = PropertiesTool.getInstance();
		String value = tool.readValue("/deploy.properties", key);
		if (value == null) {
			value = "";
		}
		return value;
	}

	/**
	 * 根据properties路径和key值 取得value
	 * 
	 * @param filePath
	 *            properties的相对路径
	 * @param key
	 *            key
	 * @return 值
	 */
	public String readValue(String filePath, String key) {
		try {
			if (properties == null) {
				properties = new HashMap<String, Properties>();
			}
			if (properties.get(filePath) == null) {
				Properties props = new Properties();
				File file = FileUtils.getInstance().getFile(filePath);
				FileInputStream in = new FileInputStream(file);
				props.load(in);
				properties.put(filePath, props);
			}

			String value = properties.get(filePath).getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用单例模式，初始化propertiesTool工具
	 * 
	 * @return
	 */
	public static PropertiesTool getInstance() {
		if (propertiesTool == null) {
			propertiesTool = new PropertiesTool();
		}
		return propertiesTool;
	}

}
