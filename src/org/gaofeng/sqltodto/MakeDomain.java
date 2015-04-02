package org.gaofeng.sqltodto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.TableViewModel;

/**
 * 根据字段名 类型 组织domain格式
 * 
 * @author gf
 *
 */
public class MakeDomain {

	public static void makeDomain(List<TableViewModel> listCol,
			String tableName) throws IOException {

		StringBuffer sb = new StringBuffer();
		if (PropertiesTool.DOMAINPACKAGENAME != null
				&& !"".equals(PropertiesTool.DOMAINPACKAGENAME)) {
			sb.append("package "
					+ PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "") + ";");
			sb.append("\n");
			sb.append("\n");
		}

		Map<String, Boolean> mapImport = new HashMap<String, Boolean>();

		for (int i = 0; i < listCol.size(); i++) {
			String type = listCol.get(i).getJavaType();
			if (MappingRule.mappingImport.get(type) != null) {
				mapImport.put(type, true);
			}
		}

		for (Map.Entry<String, Boolean> entry : mapImport.entrySet()) {
			if (entry.getKey() != null && !"".equals(entry.getKey())) {
				sb.append(MappingRule.mappingImport.get(entry.getKey()));
				sb.append("\n");
			}

		}

		
		
		sb.append("import java.io.Serializable;");
		sb.append("\n");
		if(PropertiesTool.DOMAINEXTENDS!=null && !"".equals(PropertiesTool.DOMAINEXTENDS)){
			sb.append("import "+PropertiesTool.DOMAINEXTENDS.replace(";", "")+";");
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("public class "
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME);
		
		if(PropertiesTool.DOMAINEXTENDS!=null && !"".equals(PropertiesTool.DOMAINEXTENDS)){
			String a=PropertiesTool.DOMAINEXTENDS.replace(";", "");
			String[] b=a.split("\\.");
			
			sb.append("extends "+b[b.length-1]+" ");
		}
		sb.append(" implements Serializable {");
		sb.append("\n");
		sb.append("\n");

		sb.append("	private static final long serialVersionUID = 1L;");
		sb.append("\n");
		for (int i = 0; i < listCol.size(); i++) {
			sb.append("	private "
					+ listCol.get(i).getJavaType() + " "
					+ MappingRule.forPropertyName(listCol.get(i).getField())
					+ ";\n");

		}
		sb.append("	private Integer limitStart;");
		sb.append("\n");
		sb.append("	private Integer limitEnd;");
		sb.append("\n");
		sb.append("	private String strSqlWhere;");
		sb.append("\n");
		sb.append("	private String strSqlOrderBy;");
		sb.append("\n");
		sb.append("\n");
		for (int i = 0; i < listCol.size(); i++) {

			TableViewModel model=listCol.get(i);
			getterAndSetter(sb, model);

		}
		TableViewModel model=new TableViewModel();
		model.setField("limit_Start");
		model.setJavaType("Integer");
		model.setCommon("分页查询起期");
		getterAndSetter(sb,model);
		model.setField("limit_End");
		model.setCommon("分页查询止期");
		getterAndSetter(sb,model);
		model.setField("str_Sql_where");
		model.setJavaType("String");
		model.setCommon("自定义sqlWhere");
		getterAndSetter(sb,model);
		model.setField("str_Sql_Order_By");
		model.setJavaType("String");
		model.setCommon("自定义order by");
		getterAndSetter(sb,model);
		
		
		
		sb.append("}");

		System.out.println(sb.toString());
		MappingRule.makeFile(
				MappingRule.domainPath + "\\"
						+ MappingRule.forClassName(CommonFunction.domainName)
						+ PropertiesTool.BEANSUFFIXNAME+".java", sb.toString());

	}

	private static void getterAndSetter(StringBuffer sb, TableViewModel model) {
		String type = model.getJavaType();
		String field = MappingRule.forPropertyName(model.getField());
		String funName = MappingRule.forClassName(model.getField());
		String common = model.getCommon();
		if (common ==null || "null".equals(common)){
			common="";
		}
		sb.append("	/**");
		sb.append("\n");
		sb.append("	 * ");
		sb.append("\n");
		sb.append("	 * @return "+common);
		sb.append("\n");
		sb.append("	 */");
		sb.append("\n");
		sb.append("	public " + type + " get" + funName + "() {\n");
		sb.append("		return " + field + ";\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**");
		sb.append("\n");
		sb.append("	 * ");
		sb.append("\n");
		sb.append("	 * @param fbMonth "+common);
		sb.append("\n");
		sb.append("	 */");
		sb.append("\n");
		sb.append("	public void set" + funName + "(" + type + " " + field
				+ ") {\n");
		sb.append("		this." + field + " = " + field + ";\n");
		sb.append("	}\n");
		sb.append("\n");
	}
}
