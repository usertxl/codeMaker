package org.gaofeng.sqltodto;

import java.io.IOException;
import java.util.List;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.domain.TableViewModel;

/**
 * 根据字段名 类型 组织sqlMap格式
 * 
 * @author gf
 *
 */
public class MakeSqlMap {

	public static void makeSqlMap(List<TableViewModel> listCol,
			String tableName) throws IOException {

		StringBuffer sb = new StringBuffer();
		makeHeader(sb);
		sb.append("\n");
		sb.append("\n");
		makeResultMap(listCol, sb);
		sb.append("\n");
		makeColList(listCol, sb);
		sb.append("\n");
		makeWhereList(listCol, sb);
		sb.append("\n");
		makeSelectCount(listCol,sb);
		sb.append("\n");
		makeInsert(listCol, sb);
		sb.append("\n");
		makeUpdateHead(listCol, sb);
		sb.append("\n");
		makeQueryListData(listCol, sb);
		sb.append("\n");
		makeQuerySingleData(listCol, sb);
		sb.append("\n");
		makeEnd(sb);

	}

	private static void makeQuerySingleData(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <select id=\"querySingleData\" parameterType=\""+PropertiesTool.DOMAINPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME +"\" resultMap=\""
				+ MappingRule.forPropertyName(CommonFunction.domainName)
				+ "Map"+"\">");
		sb.append("\n");
		sb.append("        select ");
		sb.append("\n");
		sb.append("        <include refid=\"column_List\"/>");
		sb.append("\n");
		
		sb.append("        from "+CommonFunction.domainName+" ");
		sb.append("\n");
		sb.append("        <where>");
		sb.append("\n");
		sb.append("         <include refid=\"where_List\"/>");
		sb.append("\n");
		sb.append("        </where>");
		sb.append("\n");
		sb.append("    </select>");
		
	}
	private static void makeQueryListData(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <select id=\"queryListData\" parameterType=\""+PropertiesTool.DOMAINPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME +"\" resultMap=\""
				+ MappingRule.forPropertyName(CommonFunction.domainName)
				+ "Map"+"\">");
		sb.append("\n");
		sb.append("        select ");
		sb.append("\n");
		sb.append("        <include refid=\"column_List\"/>");
		sb.append("\n");
		
		sb.append("        from "+CommonFunction.domainName+" ");
		sb.append("\n");
		sb.append("        <where>");
		sb.append("\n");
		sb.append("         <include refid=\"where_List\"/>");
		sb.append("\n");
		sb.append("        </where>");
		sb.append("\n");
		sb.append("    </select>");
		
	}

	private static void makeUpdateHead(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <update id=\"update_demo\">");
		sb.append("\n");
		sb.append("        UPDATE "+ CommonFunction.domainName);
		sb.append("\n");
		sb.append("        SET");
		sb.append("\n");
		  
		for (int i=0;i<listCol.size();i++){
			sb.append("        "+listCol.get(i).getField()+" = #{"+MappingRule.forPropertyName(listCol.get(i).getField())+",jdbcType="
			+listCol.get(i).getMyBatisType() +"}");
			if(i!=listCol.size()-1){
				sb.append(",");
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("        where  1=2 ");
		sb.append("\n");
		sb.append("    </update>");
		sb.append("\n");
	}

	private static void makeSelectCount(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <select id=\"queryCount\" parameterType=\""+PropertiesTool.DOMAINPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME +"\" resultType=\"java.lang.Integer\">");
		sb.append("\n");
		sb.append("        select count(1) from "+CommonFunction.domainName+" ");
		sb.append("\n");
		sb.append("        <where>");
		sb.append("\n");
		sb.append("         <include refid=\"where_List\"/>");
		sb.append("\n");
		sb.append("        </where>");
		sb.append("\n");
		sb.append("    </select>");
		
	}

	private static void makeWhereList(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <sql id=\"where_List\">");
		sb.append("\n");
		for ( int i = 0 ; i<listCol.size();i++){
			sb.append("        <if test=\""+MappingRule.forPropertyName(listCol.get(i).getField())+" != null and "+
					MappingRule.forPropertyName(listCol.get(i).getField()) +" !=''\">");
			sb.append("\n");
			sb.append("            <![CDATA[ and "+listCol.get(i).getField()+" = #{"+MappingRule.forPropertyName(listCol.get(i).getField())
					+",jdbcType="+listCol.get(i).getMyBatisType()+"} ]]>");
			sb.append("\n");
			sb.append("        </if>");
			sb.append("\n");
		}
		sb.append("        <if test=\"strSqlWhere!= null and strSqlWhere !=''\">");
		sb.append("\n");
		sb.append("            <![CDATA[ and #{strSqlWhere} ]]>\n");
		sb.append("        </if>");
		sb.append("\n");
		sb.append("        <if test=\"strSqlOrderBy!= null and strSqlOrderBy !=''\">");
		sb.append("\n");
		sb.append("            <![CDATA[ #{strSqlOrderBy} ]]>\n");
		sb.append("        </if>");
		sb.append("\n");
		sb.append("        <if test=\"limitStart != null and limitStart !='' and limitEnd != null and limitEnd !=''\">");
		sb.append("\n");
		sb.append("            <![CDATA[ limit #{limitStart,jdbcType=NUMBER},#{limitEnd,jdbcType=NUMBER} ]]>\n");
		sb.append("        </if>");
		sb.append("\n");
		
		
		sb.append("    </sql>");
		sb.append("\n");
		
	}

	private static void makeColList(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <sql id=\"column_List\">");
		sb.append("        ");
		for ( int i=0;i<listCol.size();i++){
			sb.append(listCol.get(i).getField());
			if(i!=listCol.size()-1){
				sb.append(",");
			}
			if(i%10==0 && i!=listCol.size()-1){
				sb.append("\n        ");
			}
		}
		sb.append("\n");
		sb.append("    </sql>");
	}

	private static void makeEnd(StringBuffer sb) throws IOException {
		sb.append("</mapper>");

		System.out.println(sb.toString());
		MappingRule.makeFile(
				MappingRule.sqlMapPath + "\\"
						+ MappingRule.forClassName(CommonFunction.domainName)
						+ "Mapper.xml", sb.toString());
	}

	private static void makeInsert(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("	<insert id=\"insert"
				+ "\" parameterType=\""
				+ PropertiesTool.DOMAINPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME +"\"");
		sb.append("\n");
		sb.append("		statementType=\"PREPARED\" useGeneratedKeys=\"true\">");
		sb.append("\n");
		sb.append("		INSERT INTO " + CommonFunction.domainName + " (");
		sb.append("\n");
		sb.append("		<trim  suffixOverrides=\",\" >");
		sb.append("\n");

		for (int i = 0; i < listCol.size(); i++) {
			String field = listCol.get(i).getField();
			String fieldForPropertyName = MappingRule.forPropertyName(listCol
					.get(i).getField());
			sb.append("		<if  test=\"" + fieldForPropertyName + " != null and "
					+ fieldForPropertyName + " !='' \"  >" + field +"</if>");
			sb.append("\n");
		}
		sb.append("		</trim>");
		sb.append("\n");
		sb.append("		)");
		sb.append("\n");
		sb.append("		VALUES");
		sb.append("\n");
		sb.append("		(");
		sb.append("\n");
		sb.append("		<trim  suffixOverrides=\",\" >");
		sb.append("\n");
		for (int i = 0; i < listCol.size(); i++) {
			
			String fieldForPropertyName = MappingRule.forPropertyName(listCol
					.get(i).getField());
			sb.append("		<if test=\"" + fieldForPropertyName + " != null and "
					+ fieldForPropertyName + " !='' \">#{"
					+ fieldForPropertyName + ",jdbcType="
					+ listCol.get(i).getMyBatisType()
					+"}" +"</if>");
			sb.append("\n");
		}
		sb.append("		</trim>");
		sb.append("\n");
		sb.append("		)");
		sb.append("\n");
		sb.append("	</insert>");
	}

	private static void makeResultMap(List<TableViewModel> listCol,
			StringBuffer sb) {
		sb.append("    <resultMap id=\""
				+ MappingRule.forPropertyName(CommonFunction.domainName)
				+ "Map\" type=\""
				+ PropertiesTool.DOMAINPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName) + PropertiesTool.BEANSUFFIXNAME +"\">");
		sb.append("\n");
		for (int i = 0; i < listCol.size(); i++) {
			String field = listCol.get(i).getField();
			String fieldForPropertyName = MappingRule.forPropertyName(listCol
					.get(i).getField());
			sb.append("        <id property=\"" + fieldForPropertyName
					+ "\" column=\"" + field + "\"/>");
			sb.append("\n");
		}
		sb.append("    </resultMap>");
	}

	private static void makeHeader(StringBuffer sb) {
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("\n");
		sb.append("<!DOCTYPE mapper");
		sb.append("\n");
		sb.append("  PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
		sb.append("\n");
		sb.append("  \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		sb.append("\n");
		sb.append("<mapper namespace=\""
				+ PropertiesTool.MAPPERPACKAGENAME.replace(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Mapper\">");

	}
}
