package org.gaofeng.common.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

	public static String DOMAINEXTENDS = getStringProperty("domainExtends");
	public static String DAOEXTENDS = getStringProperty("daoExtends");
	public static String MAPPEREXTENDS = getStringProperty("mapperExtends");
	public static String BEANSUFFIXNAME = getStringProperty("beanSuffixName");

	public static Boolean IFDOMAIN = getBooleanProperty("ifDomain");
	public static Boolean IFDAO = getBooleanProperty("ifDao");
	public static Boolean IFMAPPER = getBooleanProperty("ifMapper");
	public static Boolean IFSQL = getBooleanProperty("ifSql");

	public static String SQL=PropertiesTool.getInstance().getSql();
	public static Map<String,Map<String,String>> commentsMap=PropertiesTool.getInstance().getCommentsMap();
	private static Map<String,Properties> properties;


	private String getSql() {
		String sql = "";
		String jarPath = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		jarPath = jarPath.substring(jarPath.indexOf("/") + 1,
				jarPath.lastIndexOf("/"));
		File file = new File(jarPath +"/"+ PropertiesTool.TYPE + ".sql");
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "GBK");
			BufferedReader bufferedReader = new BufferedReader(read);
			String temp = bufferedReader.readLine();
			sql += " "+ temp +" ";
			while (temp != null) {
				temp = bufferedReader.readLine();
				if(temp!=null){
					sql += " "+ temp +" ";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sql;
	}
	private Map<String,Map<String,String>> getCommentsMap() {
		Map<String,Map<String,String>> map=new HashMap<String, Map<String,String>>();
		String jarPath = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		jarPath = jarPath.substring(jarPath.indexOf("/") + 1,
				jarPath.lastIndexOf("/"));
		File file = new File(jarPath +"/"+  "CommentsMap.sql");
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String temp = bufferedReader.readLine();
			while (temp != null) {
				String [] a=temp.split("###");
				if(map.get(a[1])==null){
					Map<String,String> col=new HashMap<String, String>();
					map.put(a[1], col);
				}
				map.get(a[1]).put(a[2], a[0]);
				temp = bufferedReader.readLine();
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
			if(properties==null){
				properties=new HashMap<String, Properties>();
			}
			if (properties.get(filePath) == null) {
				Properties props = new Properties();
				String jarPath= getClass().getProtectionDomain().getCodeSource().getLocation().getPath() ;
				jarPath=jarPath.substring(jarPath.indexOf("/")+1,jarPath.lastIndexOf("/"));
				File file = new File(jarPath+filePath);  
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
