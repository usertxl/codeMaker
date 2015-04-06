package org.gaofeng.sqltodto;

import java.io.IOException;
import java.util.List;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.TableViewModel;

/**
 * 根据字段名 类型 组织mapper格式
 * 
 * @author gf
 *
 */
public class MakeMapper  extends CommonFunction{

	public static void makeMapper(List<TableViewModel> listCol,
			String tableName) throws IOException {

		StringBuffer sb = new StringBuffer();
		if (PropertiesTool.MAPPERPACKAGENAME != null
				&& !"".equals(PropertiesTool.MAPPERPACKAGENAME)) {
			sb.append("package "
					+ PropertiesTool.MAPPERPACKAGENAME.replaceAll(";", "")
					+ ";");
			sb.append("\n");
			sb.append("\n");
		}
		sb.append("	import "+PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "")+"."+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+";	\n");
		sb.append("	import "+PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "")+"."+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+";	\n");
		sb.append("	import "+PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "")+"."+tableNameForClassName+"Key"+PropertiesTool.BEANSUFFIXNAME+";	\n");
		sb.append("		\n");
		sb.append("	import java.util.List;	\n");
		sb.append("		\n");
		sb.append("	import org.apache.ibatis.annotations.Param;	\n");
		sb.append("		\n");
		sb.append("	public interface "+tableNameForClassName+"Mapper {	\n");
		sb.append("	    int countByDynWhere("+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");
		sb.append("	    int deleteByDynWhere("+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");
		sb.append("	    int deleteByPrimaryKey("+tableNameForClassName+"Key"+PropertiesTool.BEANSUFFIXNAME+" key);	\n");
		sb.append("	    int insert("+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record);	\n");
		sb.append("	    int insertSelective("+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record);	\n");
		sb.append("	    List<"+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+"> selectByDynWhere("+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");
		sb.append("	    "+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" selectSingleByDynWhere("+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");

		sb.append("	    "+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" selectByPrimaryKey("+tableNameForClassName+"Key"+PropertiesTool.BEANSUFFIXNAME+" key);	\n");
		sb.append("	    int updateByDynWhereSelective(@Param(\"record\") "+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record, @Param(\"dynWhere\") "+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");
		sb.append("	    int updateByDynWhere(@Param(\"record\") "+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record, @Param(\"dynWhere\") "+tableNameForClassName+"DynWhere"+PropertiesTool.BEANSUFFIXNAME+" dynWhere);	\n");
		sb.append("	    int updateByPrimaryKeySelective("+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record);	\n");
		sb.append("	    int updateByPrimaryKey("+tableNameForClassName+PropertiesTool.BEANSUFFIXNAME+" record);	\n");
		sb.append("	}	\n");

		
		
		
		sysLog(sb.toString(), INFO);
		MappingRule.makeFile(
				MappingRule.mapperPath + "\\"
						+ tableNameForClassName
						+ "Mapper.java", sb.toString());

	}
}
