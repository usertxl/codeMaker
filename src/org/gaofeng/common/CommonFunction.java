package org.gaofeng.common;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gaofeng.common.db.DBHelper;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.GroupTableViewModel;
import org.gaofeng.domain.TableViewModel;
import org.gaofeng.sqltodto.MakeDomain;
import org.gaofeng.sqltodto.MakeMapper;
import org.gaofeng.sqltodto.MakeSqlMap;

/**
 * 公共方法 内包含:</br> 根据sql语句创建视图</br> 根据视图名删除视图</br>
 * 
 * @author gf
 *
 */
public class CommonFunction {

	public static String sql = null;
	public static String tableName = null;
	public static String tableNameForClassName = null;
	public static String tableNameForPropertyName = null;
	public static String viewName = null;
	public static DBHelper db = null;
	public static ResultSet ret = null;
	public static String MYSQL = "mysql";
	public static String ORACLE = "oracle";
	public static String INFO = "info";
	public static String DEBUG = "debug";
	public static String ERROR = "error";

	/**
	 * 根据sql语句创建一个视图
	 * 
	 * @param stringSqlIn
	 *            传入参数,字符串形式的sql语句
	 * @return 生成的视图的名称
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String createView(String stringSqlIn,int count) throws SQLException,
			ClassNotFoundException {
		String a = "TMP" + System.currentTimeMillis() + count;
		sysLog("create view " + a + " as " + stringSqlIn, INFO);
		db = new DBHelper("create view " + a + " as " + stringSqlIn);
		db.pst.execute();
		db.close();
		return a;
	}

	/**
	 * 根据视图的名称删除视图
	 * 
	 * @param viewName
	 *            传入参数,字符串形式的视图名称
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void dropView(String viewName) throws ClassNotFoundException,
			SQLException {
		db = new DBHelper("drop view " + viewName);
		db.pst.execute();
		db.close();
	}

	public static void makeDomain(String tableName) throws Exception {
		List<TableViewModel> listCol = getColsByTableName(tableName);
		GroupTableViewModel groupTableViewModel = groupColByPriKey(listCol);

		if(groupTableViewModel==null){
			sysLog("数据库表["+tableName+"]没有主键，不予生成", ERROR);
			return;
		}
		sysLog("正在生成["+tableName+"]", DEBUG);
		if (MappingRule.ifDomain) {
			MakeDomain.makeDomain(groupTableViewModel, tableName);
		}
		if (MappingRule.ifMapper) {
			MakeMapper.makeMapper(listCol, tableName);
		}
		if (MappingRule.ifSql) {
			MakeSqlMap.makeSqlMap(groupTableViewModel, tableName);
		}
	
	}

	protected static List<TableViewModel> getColsByTableName(String tableName)
			throws ClassNotFoundException, SQLException, IOException, Exception {
		List<TableViewModel> listCol = null;
		if (MYSQL.equals(PropertiesTool.TYPE)) {
			listCol = getColInfoMysql(tableName);
		} else if (ORACLE.equals(PropertiesTool.TYPE)) {
			listCol = getColInfoOracle(tableName);
		}
		ret.close();
		db.close();
		return listCol;
	}

	private static GroupTableViewModel groupColByPriKey(
			List<TableViewModel> listCol) {
		GroupTableViewModel list = new GroupTableViewModel();
		for (TableViewModel tb : listCol) {
			if (tb.getIsPrimaryKey()) {
				if (list.getListKey() != null && list.getListKey().size() != 0) {
					list.getListKey().add(tb);
				} else {
					List<TableViewModel> listKey = new ArrayList<TableViewModel>();
					listKey.add(tb);
					list.setListKey(listKey);
				}
			} else {
				if (list.getListUnKey() != null
						&& list.getListUnKey().size() != 0) {
					list.getListUnKey().add(tb);
				} else {
					List<TableViewModel> listUnKey = new ArrayList<TableViewModel>();
					listUnKey.add(tb);
					list.setListUnKey(listUnKey);
				}
			}
		}

		if(list.getListKey()!=null && list.getListKey().size()!=0){
			if(list.getListUnKey()==null){
				List<TableViewModel> listUnKey = new ArrayList<TableViewModel>();
				list.setListUnKey(listUnKey);
			}
			return list;
		}else{
			return null;
		}
	}

	public static List<TableViewModel> getColInfoMysql(String tableName)
			throws ClassNotFoundException, SQLException, IOException {
		sql = " SELECT COLUMN_NAME,DATA_TYPE,NUMERIC_SCALE, COLUMN_COMMENT,COLUMN_KEY  FROM information_schema.COLUMNS WHERE TABLE_NAME = '"
				+ tableName + "' ";
		db = new DBHelper(sql);
		List<TableViewModel> listCol = new ArrayList<TableViewModel>();
		ret = db.pst.executeQuery();
		while (ret.next()) {
			TableViewModel model = new TableViewModel();
			model.setFieldName(ret.getString(1));
			model.setType(ret.getString(2));
			model.setScale(ret.getString(3));
			model.setComment(ret.getString(4));
			if ("PRI".equals(ret.getString(5))) {
				model.setIsPrimaryKey(true);
			} else {
				model.setIsPrimaryKey(false);
			}
			model.setClassName(MappingRule.forClassName(model.getFieldName()));
			model.setPropertyName(MappingRule.forPropertyName(model
					.getFieldName()));
			getJavaType(model);
			getMybatisType(model);
			getCommentFromLoc(model, tableName);
			listCol.add(model);
		}
		return listCol;
	}

	private static void getCommentFromLoc(TableViewModel model, String tableName) {
		if (PropertiesTool.commentsMap.get(tableName.toUpperCase()) != null) {
			if (PropertiesTool.commentsMap.get(tableName.toUpperCase()).get(
					model.getFieldName().toUpperCase()) != null) {
				model.setComment(PropertiesTool.commentsMap.get(
						tableName.toUpperCase()).get(
						model.getFieldName().toUpperCase()));
			}
		}

	}

	public static List<TableViewModel> getColInfoOracle(String tableName)
			throws Exception {
		sql = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_SCALE ,k.comments,(select count(1) from user_cons_columns cu, user_constraints au "
				+ " where cu.constraint_name = au.constraint_name and au.constraint_type = 'P' and au.table_name =t.TABLE_NAME and cu.column_name=t.column_name) as prikey   FROM USER_TAB_COLS T ,user_col_comments k "
				+ " WHERE t.TABLE_NAME=k.table_name and t.COLUMN_NAME=k.column_name and  T.TABLE_NAME='"
				+ tableName + "'";
		db = new DBHelper(sql);
		List<TableViewModel> listCol = new ArrayList<TableViewModel>();
		ret = db.pst.executeQuery();
		while (ret.next()) {
			TableViewModel model = new TableViewModel();
			model.setFieldName(ret.getString(1));
			model.setType(ret.getString(2));
			model.setScale(ret.getString(3));
			model.setComment(ret.getString(4));
			if (ret.getInt(5) == 1) {
				model.setIsPrimaryKey(true);
			} else {
				model.setIsPrimaryKey(false);
			}

			model.setClassName(MappingRule.forClassName(model.getFieldName()));
			model.setPropertyName(MappingRule.forPropertyName(model
					.getFieldName()));
			getJavaType(model);
			getMybatisType(model);
			getCommentFromLoc(model, tableName);
			listCol.add(model);
		}

		return listCol;
	}

	public static String getNumbers(TableViewModel tableViewModel) {

		if ("NUMBER".equals(tableViewModel.getType())
				&& tableViewModel.getScale() != null
				&& !"0".equals(tableViewModel.getScale())) {

			return "NUMBERS";
		}
		if ("decimal".equals(tableViewModel.getType())
				&& tableViewModel.getScale() != null
				&& !"0".equals(tableViewModel.getScale())) {
			return "decimals";
		}
		if (tableViewModel.getType().indexOf("(") > -1) {
			return tableViewModel.getType().substring(0,
					tableViewModel.getType().indexOf("("));
		}
		return tableViewModel.getType();
	}

	/**
	 * 获得java中的类型
	 * 
	 * @param tableViewModel
	 */
	public static void getJavaType(TableViewModel tableViewModel) {

		tableViewModel.setJavaType(MappingRule.mappingRule
				.get(getNumbers(tableViewModel)));
	}

	/**
	 * 获得mybatis中的类型
	 * 
	 * @param tableViewModel
	 */
	public static void getMybatisType(TableViewModel tableViewModel) {
		tableViewModel.setMyBatisType(MappingRule.mappingRuleMybatis
				.get(getNumbers(tableViewModel)));

	}

	public static void sysLog(String log, String lev) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		if (PropertiesTool.getInstance().LOGLEV.get(lev) != null
				&& PropertiesTool.getInstance().LOGLEV.get(lev)) {
			System.out.println(df.format(new Date()) + "[" + lev + "]:" + log);
		}
	}

}
