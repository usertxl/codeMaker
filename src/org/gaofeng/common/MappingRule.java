package org.gaofeng.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.mysql.utils.MysqlUtils;
import org.gaofeng.oracle.utils.OracleUtils;

public class MappingRule extends CommonFunction{
	static public Boolean ifDomain=PropertiesTool.IFDOMAIN;
	static public Boolean ifMapper=PropertiesTool.IFMAPPER;
	static public Boolean ifSql=PropertiesTool.IFSQL;
	static public String domainPath = PropertiesTool.DOMAINPATH;
	static public String mapperPath = PropertiesTool.MAPPERPATH;
	static public String daoPath = PropertiesTool.DAOPATH;
	static public String sqlMapPath = PropertiesTool.SQLMAPPATH;
	

	static public Map<String,String> mappingRuleMybatis = makeRuleMybatis();
	static public Map<String, String> mappingRule = makeRule();
	static public Map<String, String> mappingImport = makeImport();
	private static Map<String, String> makeRule() {
		if ("mysql".equals(PropertiesTool.TYPE)) {
			return MysqlUtils.mappingRule;
		} else if ("oracle".equals(PropertiesTool.TYPE)) {
			return OracleUtils.mappingRule;
		} else {
			return null;
		}

	}
	private static Map<String, String> makeRuleMybatis() {
		if ("mysql".equals(PropertiesTool.TYPE)) {
			return MysqlUtils.mappingRuleMybatis;
		} else if ("oracle".equals(PropertiesTool.TYPE)) {
			return OracleUtils.mappingRuleMybatis;
		} else {
			return null;
		}

	}

	private static Map<String, String> makeImport() {
		if ("mysql".equals(PropertiesTool.TYPE)) {
			return MysqlUtils.mappingImport;
		} else if ("oracle".equals(PropertiesTool.TYPE)) {
			return OracleUtils.mappingImport;
		} else {
			return null;
		}
	}

	public static String getColType1(String colType) {
			return mappingRule.get(getColTypeSort(colType));
	}
	public static String getColTypeMybatiss1(String colType) {
		return mappingRuleMybatis.get(getColTypeSort(colType));
}
	public static String getColTypeSort(String colType) {
		int a = colType.indexOf("(");
		if (a != -1) {
			return colType.substring(0, a);
		} else {
			return colType;
		}

	}

	/**
	 * 将字符串转化成类名格式:驼峰式明明 根据_判断大写字母的位置
	 * 
	 * @param name
	 * @return
	 */
	public static String forClassName(String name) {
		String returnString = "";
		boolean flag_ = false;
		for (int i = 0; i < name.length(); i++) {
			// 类名
			if (i == 0) {
				returnString += name.substring(i, i + 1).toUpperCase();
			} else {

				if ("_".equals(name.substring(i, i + 1))) {
					flag_ = true;
				} else {
					if (flag_) {
						returnString += name.substring(i, i + 1).toUpperCase();
						flag_=false;
					} else {
						returnString += name.substring(i, i + 1).toLowerCase();
					}

				}
			}
		}
		return returnString;
	}

	/**
	 * 将字符串转化成属性名格式:驼峰式明明 根据_判断大写字母的位置
	 * 
	 * @param name
	 * @return
	 */
	public static String forPropertyName(String name) {
		String returnString = "";
		boolean flag_ = false;
		for (int i = 0; i < name.length(); i++) {
			// 类名
			if ("_".equals(name.substring(i, i + 1))) {
				flag_ = true;
			} else {
				if (flag_) {
					returnString += name.substring(i, i + 1).toUpperCase();
					flag_=false;
				} else {
					returnString += name.substring(i, i + 1).toLowerCase();
				}

			}
		}
		return returnString;
	}

	/**
	 * 根据路径 和文件内容创建文件
	 * 
	 * @param path
	 * @param context
	 * @throws IOException
	 */
	public static void makeFile(String path, String context) throws IOException {
		File file = new File(path);
		sysLog(path, INFO);
		File parent = file.getParentFile(); 
		if(parent!=null&&!parent.exists()){ 
		parent.mkdirs(); 
		} 
		if (CheckFileIfExists.getFileList().get(path)==null) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file, false);
		out.write(context.getBytes("utf-8"));
		out.close();
	}
	
}
