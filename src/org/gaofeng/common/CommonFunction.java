package org.gaofeng.common;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gaofeng.common.db.DBHelper;
import org.gaofeng.common.properties.PropertiesTool;
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
	public static String domainName = null;
	public static String viewName = null;
	public static DBHelper db = null;
	public static ResultSet ret = null;
	public static String MYSQL = "mysql";
	public static String ORACLE = "oracle";

	/**
	 * 根据sql语句创建一个视图
	 * 
	 * @param stringSqlIn
	 *            传入参数,字符串形式的sql语句
	 * @return 生成的视图的名称
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String createView(String stringSqlIn) throws SQLException,
			ClassNotFoundException {
		String a = "TMP" + System.currentTimeMillis() + "";
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

	public static void makeDomain(String tableName)
			throws  Exception {
		if (MYSQL.equals(PropertiesTool.TYPE)) {
			makeRealMysql(tableName);
		} else if (ORACLE.equals(PropertiesTool.TYPE)) {
			makeRealOracle(tableName);
		}
	}

	public static void makeRealMysql(String tableName)
			throws ClassNotFoundException, SQLException, IOException {
		sql = " SELECT COLUMN_NAME,DATA_TYPE,NUMERIC_SCALE, COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_NAME = '"+tableName+"' ";
		db = new DBHelper(sql);
		List<TableViewModel> listCol = new ArrayList<TableViewModel>();
		ret = db.pst.executeQuery();
		while (ret.next()) {
			TableViewModel model=new TableViewModel();
			model.setField(ret.getString(1));
			model.setType(ret.getString(2));
			model.setScale(ret.getString(3));
			model.setCommon(ret.getString(4));
			getJavaType(model);
			getMybatisType(model);
			getCommentFromLoc(model,tableName);
			listCol.add(model);
		}

		
		if (MappingRule.ifDomain) {
			MakeDomain.makeDomain(listCol, tableName);
		}
		if (MappingRule.ifMapper) {
			MakeMapper.makeMapper(listCol, tableName);
		}
		if (MappingRule.ifSql) {
			MakeSqlMap.makeSqlMap(listCol, tableName);
		}

		ret.close();
		db.close();
	}

	private static void getCommentFromLoc(TableViewModel model,String tableName) {
		if(PropertiesTool.commentsMap.get(tableName.toUpperCase())!=null){
			if(PropertiesTool.commentsMap.get(tableName.toUpperCase()).get(model.getField().toUpperCase())!=null){
				model.setCommon(PropertiesTool.commentsMap.get(tableName.toUpperCase()).get(model.getField().toUpperCase()));
			}
		}
		
	}

	public static void makeRealOracle(String tableName)
			throws  Exception {
		sql = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_SCALE ,k.comments   FROM USER_TAB_COLS T ,user_col_comments k "
				+ " WHERE t.TABLE_NAME=k.table_name and t.COLUMN_NAME=k.column_name and  T.TABLE_NAME='"
				+ tableName + "'";
		db = new DBHelper(sql);
		List<TableViewModel> listCol = new ArrayList<TableViewModel>();
		ret = db.pst.executeQuery();
		while (ret.next()) {
			TableViewModel model=new TableViewModel();
			model.setField(ret.getString(1));
			model.setType(ret.getString(2));
			model.setScale(ret.getString(3));
			model.setCommon(ret.getString(4));
			getJavaType(model);
			getMybatisType(model);
			listCol.add(model);
		}

		if(listCol.size()==0){
			throw new Exception("如果只填写实体类名称，那么这个名称必须是已经存在的表名");
		}
		if (MappingRule.ifDomain) {
			MakeDomain.makeDomain(listCol, tableName);
		}
		if (MappingRule.ifMapper) {
			MakeMapper.makeMapper(listCol, tableName);
		}
		if (MappingRule.ifSql) {
			MakeSqlMap.makeSqlMap(listCol, tableName);
		}
		ret.close();
		db.close();
	}
	
	public static String  getNumbers(TableViewModel tableViewModel){
		
		if("NUMBER".equals(tableViewModel.getType())&& tableViewModel.getScale()!=null && !"0".equals(tableViewModel.getScale())){
			
			return"NUMBERS";
		}
		if("decimal".equals(tableViewModel.getType())&& tableViewModel.getScale()!=null && !"0".equals(tableViewModel.getScale())){
			return "decimals";
		}
		if(tableViewModel.getType().indexOf("(")>-1){
			return tableViewModel.getType().substring(0,tableViewModel.getType().indexOf("("));
		}
		return tableViewModel.getType();
	}
	/**
	 * 获得java中的类型
	 * @param tableViewModel
	 */
	public static void getJavaType(TableViewModel tableViewModel){
		
		tableViewModel.setJavaType(MappingRule.mappingRule.get(getNumbers(tableViewModel)));
	}
	/**
	 * 获得mybatis中的类型
	 * @param tableViewModel
	 */
	public static void getMybatisType(TableViewModel tableViewModel){
		tableViewModel.setMyBatisType(MappingRule.mappingRuleMybatis.get(getNumbers(tableViewModel)));
		
	}
	
	
}
