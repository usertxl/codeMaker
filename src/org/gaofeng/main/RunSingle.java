package org.gaofeng.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.db.DBHelper;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.GroupTableViewModel;
import org.gaofeng.domain.TableViewModel;
import org.gaofeng.sqltodto.MakeDomain;
import org.gaofeng.sqltodto.MakeSqlMap;

public class RunSingle extends CommonFunction {
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
		sysLog("当前配置数据库为:" + PropertiesTool.TYPE, DEBUG);
		sysLog("当前配置数据库连接为:" + PropertiesTool.URL, DEBUG);
		sysLog("当前生成domain文件路径为:" + PropertiesTool.DOMAINPATH, DEBUG);
		sysLog("当前生成sqlMap文件路径为:" + PropertiesTool.SQLMAPPATH, DEBUG);
		sql = PropertiesTool.SQLSINGLE;
		List<String> listViewName=new ArrayList<String>();
		List<List<TableViewModel>> listColLists=new ArrayList<List<TableViewModel>>();
		String[] argSql=sql.split(";");
		for(int i=0;i<argSql.length;i++){
			String a=argSql[i];
			String test = null;
			if(a!=null){
				test=a.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
			}
			if(test!=null && !"".equals(test)){
				listViewName.add(createView(a,i));
			}
		}
		sysLog("本次一共读取到"+listViewName.size()+"个sql，正在生成", DEBUG);
		
		
		for(String a:listViewName){
			List<TableViewModel> b=getColsByTableName(a);
			listColLists.add(b);
			dropView(a);
		}
		Map <String ,String> mapCheck=new HashMap<String, String>();
		List<TableViewModel> listColNew=new ArrayList<TableViewModel>();
		
		
		for(List<TableViewModel> a:listColLists){
			for(TableViewModel b:a){
				String filedName=b.getFieldName();
				if(mapCheck.get(filedName)!=null && !"".equals(filedName)){
					
				}else{
					mapCheck.put(filedName, "have");
					listColNew.add(b);
				}
			}
		}
		GroupTableViewModel groupTableViewModel=new GroupTableViewModel();
		groupTableViewModel.setListUnKey(listColNew);
		groupTableViewModel.setListKey(new ArrayList<TableViewModel>());
		tableNameForClassName = "Temp";
		tableNameForPropertyName = "temp";
		MakeDomain.makeDomain(groupTableViewModel, "TEMP");
		MakeSqlMap.makeSqlMap(groupTableViewModel, "TEMP");

		sysLog("生成完毕", DEBUG);
	}

}
