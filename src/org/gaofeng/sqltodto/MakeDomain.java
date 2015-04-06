package org.gaofeng.sqltodto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.GroupTableViewModel;
import org.gaofeng.domain.TableViewModel;

/**
 * 根据字段名 类型 组织domain格式
 * 
 * @author gf
 *
 */
public class MakeDomain extends CommonFunction {

	public static void makeDomain(GroupTableViewModel mapListCol,
			String tableName) throws IOException {

		List<TableViewModel> listKey = mapListCol.getListKey();
		List<TableViewModel> listUnKey = mapListCol.getListUnKey();
		List<TableViewModel> listAll = new ArrayList<TableViewModel>();
		listAll.addAll(listKey);
		listAll.addAll(listUnKey);
		makeDomainKeyPart(listKey);
		makeDomainUnKeyPart(listUnKey);
		makeDomainExamplePart(listAll);

	}

	private static void makeDomainExamplePart(List<TableViewModel> listAll) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(makePackage());
		sb.append("\n");
		sb.append(makeDynWhereHeader(listAll));
		sb.append(makeDynWhereBody(listAll));
		sb.append(makeDynWhereEnd());
		sysLog(sb.toString(), INFO);
		MappingRule.makeFile(MappingRule.domainPath + "\\"
				+ tableNameForClassName + "DynWhere" + PropertiesTool.BEANSUFFIXNAME
				+ ".java", sb.toString());
		
	}

	private static Object makeDynWhereBody(List<TableViewModel> listAll) {
		StringBuffer sb=new StringBuffer();
		for(TableViewModel tb :listAll){
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"IsNull() {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" is null\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"IsNotNull() {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" is not null\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"EqualTo("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" =\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"NotEqualTo("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" <>\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"GreaterThan("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" >\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getFieldName()+"GreaterThanOrEqualTo("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" >=\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"LessThan("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" <\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"LessThanOrEqualTo("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" <=\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"Like("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" like\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"NotLike("+tb.getJavaType()+" value) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" not like\", value, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"In(List<"+tb.getJavaType()+"> values) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" in\", values, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"NotIn(List<"+tb.getJavaType()+"> values) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" not in\", values, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"Between("+tb.getJavaType()+" value1, "+tb.getJavaType()+" value2) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" between\", value1, value2, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
			sb.append("        public "+tableNameForClassName+"Criteria and"+tb.getClassName()+"NotBetween("+tb.getJavaType()+" value1, "+tb.getJavaType()+" value2) {\n");
			sb.append("            addCriterion(\""+tb.getFieldName()+" not between\", value1, value2, \""+tb.getPropertyName()+"\");\n");
			sb.append("            return ("+tableNameForClassName+"Criteria) this;\n");
			sb.append("        }\n");
			sb.append("\n");
		}
		sb.append("    }\n");
		sb.append("\n");
		return sb.toString();
	}

	private static String makeDynWhereEnd() {
		StringBuffer sb=new StringBuffer();
		sb.append("    public static class "+tableNameForClassName+"Criteria extends GeneratedCriteria {\n");
		sb.append("\n");
		sb.append("        protected "+tableNameForClassName+"Criteria() {\n");
		sb.append("            super();\n");
		sb.append("        }\n");
		sb.append("    }\n");
		sb.append("    public static class Criterion {\n");
		sb.append("        private String condition;\n");
		sb.append("\n");
		sb.append("        private Object value;\n");
		sb.append("\n");
		sb.append("        private Object secondValue;\n");
		sb.append("\n");
		sb.append("        private boolean noValue;\n");
		sb.append("\n");
		sb.append("        private boolean singleValue;\n");
		sb.append("\n");
		sb.append("        private boolean betweenValue;\n");
		sb.append("\n");
		sb.append("        private boolean listValue;\n");
		sb.append("\n");
		sb.append("        private String typeHandler;\n");
		sb.append("\n");
		sb.append("        public String getCondition() {\n");
		sb.append("            return condition;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public Object getValue() {\n");
		sb.append("            return value;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public Object getSecondValue() {\n");
		sb.append("            return secondValue;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public boolean isNoValue() {\n");
		sb.append("            return noValue;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public boolean isSingleValue() {\n");
		sb.append("            return singleValue;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public boolean isBetweenValue() {\n");
		sb.append("            return betweenValue;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public boolean isListValue() {\n");
		sb.append("            return listValue;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public String getTypeHandler() {\n");
		sb.append("            return typeHandler;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected Criterion(String condition) {\n");
		sb.append("            super();\n");
		sb.append("            this.condition = condition;\n");
		sb.append("            this.typeHandler = null;\n");
		sb.append("            this.noValue = true;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected Criterion(String condition, Object value, String typeHandler) {\n");
		sb.append("            super();\n");
		sb.append("            this.condition = condition;\n");
		sb.append("            this.value = value;\n");
		sb.append("            this.typeHandler = typeHandler;\n");
		sb.append("            if (value instanceof List<?>) {\n");
		sb.append("                this.listValue = true;\n");
		sb.append("            } else {\n");
		sb.append("                this.singleValue = true;\n");
		sb.append("            }\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected Criterion(String condition, Object value) {\n");
		sb.append("            this(condition, value, null);\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {\n");
		sb.append("            super();\n");
		sb.append("            this.condition = condition;\n");
		sb.append("            this.value = value;\n");
		sb.append("            this.secondValue = secondValue;\n");
		sb.append("            this.typeHandler = typeHandler;\n");
		sb.append("            this.betweenValue = true;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected Criterion(String condition, Object value, Object secondValue) {\n");
		sb.append("            this(condition, value, secondValue, null);\n");
		sb.append("        }\n");
		sb.append("    }\n");
		sb.append("}\n");
		return sb.toString();
	}

	private static String makeDynWhereHeader(List<TableViewModel> listCol) {
		StringBuffer sb=new StringBuffer();
		sb.append(makeImport(listCol));
		if(sb.indexOf("import java.util.ArrayList;")==-1){
			sb.append("import java.util.ArrayList;\n");
		}
		if(sb.indexOf("import java.util.Date;")==-1){
			sb.append("import java.util.Date;\n");
		}
		if(sb.indexOf("import java.util.Iterator;")==-1){
			sb.append("import java.util.Iterator;\n");
		}
		if(sb.indexOf("import java.util.List;")==-1){
			sb.append("import java.util.List;\n");
		}
		sb.append("\n");
		sb.append("public class "+tableNameForClassName + "DynWhere"+PropertiesTool.BEANSUFFIXNAME+" {\n");
		sb.append("    protected String strSqlOrderBy;\n");
		sb.append("    protected boolean distinct;\n");
		sb.append("    protected List<"+tableNameForClassName+"Criteria> oredCriteria;\n");
		sb.append(mekeDeclare(addModel("limit_Start", "Integer", "分页查询起期")));
		sb.append(mekeDeclare(addModel("limit_End", "Integer", "分页查询止期")));
		sb.append(getterAndSetter(addModel("limit_Start", "Integer", "分页查询起期")));
		sb.append(getterAndSetter(addModel("limit_End", "Integer", "分页查询止期")));
		sb.append("    public "+tableNameForClassName + "DynWhere"+PropertiesTool.BEANSUFFIXNAME+"() {\n");
		sb.append("        oredCriteria = new ArrayList<"+tableNameForClassName+"Criteria>();\n");
		sb.append("    }\n");
		sb.append("    public void setStrSqlOrderBy(String strSqlOrderBy) {\n");
		sb.append("        this.strSqlOrderBy = strSqlOrderBy;\n");
		sb.append("    }\n");
		sb.append("    public String getStrSqlOrderBy() {\n");
		sb.append("        return strSqlOrderBy;\n");
		sb.append("    }\n");
		sb.append("    public void setDistinct(boolean distinct) {\n");
		sb.append("        this.distinct = distinct;\n");
		sb.append("    }\n");
		sb.append("    public boolean isDistinct() {\n");
		sb.append("        return distinct;\n");
		sb.append("    }\n");
		sb.append("    public List<"+tableNameForClassName+"Criteria> getOredCriteria() {\n");
		sb.append("        return oredCriteria;\n");
		sb.append("    }\n");
		sb.append("    public void or("+tableNameForClassName+"Criteria criteria) {\n");
		sb.append("        oredCriteria.add(criteria);\n");
		sb.append("    }\n");
		sb.append("    public "+tableNameForClassName+"Criteria or() {\n");
		sb.append("        "+tableNameForClassName+"Criteria criteria = createCriteriaInternal();\n");
		sb.append("        oredCriteria.add(criteria);\n");
		sb.append("        return criteria;\n");
		sb.append("    }\n");
		sb.append("    public "+tableNameForClassName+"Criteria createCriteria() {\n");
		sb.append("        "+tableNameForClassName+"Criteria criteria = createCriteriaInternal();\n");
		sb.append("        if (oredCriteria.size() == 0) {\n");
		sb.append("            oredCriteria.add(criteria);\n");
		sb.append("        }\n");
		sb.append("        return criteria;\n");
		sb.append("    }\n");
		sb.append("    protected "+tableNameForClassName+"Criteria createCriteriaInternal() {\n");
		sb.append("        "+tableNameForClassName+"Criteria criteria = new "+tableNameForClassName+"Criteria();\n");
		sb.append("        return criteria;\n");
		sb.append("    }\n");
		sb.append("    public void clear() {\n");
		sb.append("        oredCriteria.clear();\n");
		sb.append("        strSqlOrderBy = null;\n");
		sb.append("        distinct = false;\n");
		sb.append("    }\n");
		sb.append("    protected abstract static class GeneratedCriteria {\n");
		sb.append("        protected List<Criterion> criteria;\n");
		sb.append("\n");
		sb.append("        protected GeneratedCriteria() {\n");
		sb.append("            super();\n");
		sb.append("            criteria = new ArrayList<Criterion>();\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public boolean isValid() {\n");
		sb.append("            return criteria.size() > 0;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public List<Criterion> getAllCriteria() {\n");
		sb.append("            return criteria;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        public List<Criterion> getCriteria() {\n");
		sb.append("            return criteria;\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterion(String condition) {\n");
		sb.append("            if (condition == null) {\n");
		sb.append("                throw new RuntimeException(\"Value for condition cannot be null\");\n");
		sb.append("            }\n");
		sb.append("            criteria.add(new Criterion(condition));\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterion(String condition, Object value, String property) {\n");
		sb.append("            if (value == null) {\n");
		sb.append("                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");\n");
		sb.append("            }\n");
		sb.append("            criteria.add(new Criterion(condition, value));\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterion(String condition, Object value1, Object value2, String property) {\n");
		sb.append("            if (value1 == null || value2 == null) {\n");
		sb.append("                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");\n");
		sb.append("            }\n");
		sb.append("            criteria.add(new Criterion(condition, value1, value2));\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterionForJDBCDate(String condition, Date value, String property) {\n");
		sb.append("            if (value == null) {\n");
		sb.append("                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");\n");
		sb.append("            }\n");
		sb.append("            addCriterion(condition, new java.sql.Date(value.getTime()), property);\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {\n");
		sb.append("            if (values == null || values.size() == 0) {\n");
		sb.append("                throw new RuntimeException(\"Value list for \" + property + \" cannot be null or empty\");\n");
		sb.append("            }\n");
		sb.append("            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();\n");
		sb.append("            Iterator<Date> iter = values.iterator();\n");
		sb.append("            while (iter.hasNext()) {\n");
		sb.append("                dateList.add(new java.sql.Date(iter.next().getTime()));\n");
		sb.append("            }\n");
		sb.append("            addCriterion(condition, dateList, property);\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {\n");
		sb.append("            if (value1 == null || value2 == null) {\n");
		sb.append("                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");\n");
		sb.append("            }\n");
		sb.append("            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);\n");
		sb.append("        }\n");
		sb.append("\n");
		return sb.toString();
	}

	private static void makeDomainUnKeyPart(List<TableViewModel> listCol)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(makePackage());
		sb.append(makeHead(listCol, "unKey"));
		for (int i = 0; i < listCol.size(); i++) {
			sb.append(mekeDeclare(listCol.get(i)));

		}
		sb.append(mekeDeclare(addModel("limit_Start", "Integer", "分页查询起期")));
		sb.append(mekeDeclare(addModel("limit_End", "Integer", "分页查询止期")));
		sb.append(mekeDeclare(addModel("str_Sql_where", "String", "自定义sqlWhere")));
		sb.append(mekeDeclare(addModel("str_Sql_Order_By", "String",
				"自定义order by")));
		for (int i = 0; i < listCol.size(); i++) {
			TableViewModel model = listCol.get(i);
			sb.append(getterAndSetter(model));

		}
		sb.append(getterAndSetter(addModel("limit_Start", "Integer", "分页查询起期")));
		sb.append(getterAndSetter(addModel("limit_End", "Integer", "分页查询止期")));
		sb.append(getterAndSetter(addModel("str_Sql_where", "String",
				"自定义sqlWhere")));
		sb.append(getterAndSetter(addModel("str_Sql_Order_By", "String",
				"自定义order by")));
		sb.append("}");

		sysLog(sb.toString(), INFO);
		MappingRule.makeFile(MappingRule.domainPath + "\\"
				+ tableNameForClassName + PropertiesTool.BEANSUFFIXNAME
				+ ".java", sb.toString());

	}

	private static void makeDomainKeyPart(List<TableViewModel> listCol)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(makePackage());
		sb.append(makeHead(listCol, "key"));
		for (int i = 0; i < listCol.size(); i++) {
			sb.append(mekeDeclare(listCol.get(i)));

		}
		for (int i = 0; i < listCol.size(); i++) {
			TableViewModel model = listCol.get(i);
			sb.append(getterAndSetter(model));

		}
		sb.append("}");

		sysLog(sb.toString(), INFO);
		MappingRule.makeFile(MappingRule.domainPath + "\\"
				+ tableNameForClassName + "Key" + PropertiesTool.BEANSUFFIXNAME
				+ ".java", sb.toString());

	}

	/**
	 * 文件头部信息
	 * 
	 * @param listCol
	 * @return
	 */
	private static String makeHead(List<TableViewModel> listCol, String fileType) {
		StringBuffer sb = new StringBuffer();
		sb.append(makeImport(listCol));

		if ("key".equals(fileType)) {
			sb.append("import java.io.Serializable;");
			sb.append("\n");
		}

		sb.append("\n");
		sb.append("public class " + tableNameForClassName);
		if ("key".equals(fileType)) {
			sb.append("Key");
		}
		sb.append(PropertiesTool.BEANSUFFIXNAME);

		if ("key".equals(fileType)) {
			sb.append(" implements Serializable ");
		}
		if ("unKey".equals(fileType)) {
			sb.append(" extends " + tableNameForClassName + "Key"
					+ PropertiesTool.BEANSUFFIXNAME);
		}
		sb.append(" {");
		sb.append("\n");
		sb.append("\n");

		sb.append("	private static final long serialVersionUID = 1L;");
		sb.append("\n");
		return sb.toString();
	}

	private static String makeImport(List<TableViewModel> listCol) {
		StringBuffer sb= new StringBuffer();
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
		return sb.toString();
	}

	private static String makePackage() {
		StringBuffer sb = new StringBuffer();
		if (PropertiesTool.DOMAINPACKAGENAME != null
				&& !"".equals(PropertiesTool.DOMAINPACKAGENAME)) {
			sb.append("package "
					+ PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "")
					+ ";");
			sb.append("\n");
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * 自定义属性</br> 用于附加属性
	 * 
	 * @param filedName
	 *            _分隔的字符串
	 * @param javaType
	 *            java中的类型
	 * @param comment
	 *            备注
	 * @return
	 */
	private static TableViewModel addModel(String filedName, String javaType,
			String comment) {
		TableViewModel model = new TableViewModel();
		model.setFieldName(filedName);
		model.setJavaType(javaType);
		model.setClassName(MappingRule.forClassName(model.getFieldName()));
		model.setPropertyName(MappingRule.forPropertyName(model.getFieldName()));
		model.setComment(comment);
		return model;
	}

	/**
	 * 组织声明区域
	 * 
	 * @param model
	 * @return
	 */
	private static String mekeDeclare(TableViewModel model) {
		StringBuffer sb = new StringBuffer();
		sb.append("	private ");
		sb.append(model.getJavaType());
		sb.append(" ");
		sb.append(model.getPropertyName());
		sb.append(";\n");
		return sb.toString();
	}

	/**
	 * 组织get、set方法
	 * 
	 * @param model
	 * @return
	 */
	private static String getterAndSetter(TableViewModel model) {
		StringBuffer sb = new StringBuffer();
		String type = model.getJavaType();
		String field = model.getPropertyName();
		String funName = model.getClassName();
		String common = model.getComment();
		if (common == null || "null".equals(common)) {
			common = "";
		}
		sb.append("	/**");
		sb.append("\n");
		sb.append("	 * ");
		sb.append("\n");
		sb.append("	 * @return " + common);
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
		sb.append("	 * @param fbMonth " + common);
		sb.append("\n");
		sb.append("	 */");
		sb.append("\n");
		sb.append("	public void set" + funName + "(" + type + " " + field
				+ ") {\n");
		sb.append("		this." + field + " = " + field + ";\n");
		sb.append("	}\n");
		sb.append("\n");
		return sb.toString();
	}
}
