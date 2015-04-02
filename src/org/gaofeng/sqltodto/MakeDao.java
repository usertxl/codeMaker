package org.gaofeng.sqltodto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.MappingRule;
import org.gaofeng.common.properties.PropertiesTool;

/**
 * 根据字段名 类型 组织dao格式
 * 
 * @author gf
 *
 */
public class MakeDao {

	public static void makeDao(List<Map<String, String>> listCol,
			String tableName) throws IOException {

		StringBuffer sb = new StringBuffer();
		if (PropertiesTool.DAOPACKAGENAME != null
				&& !"".equals(PropertiesTool.DAOPACKAGENAME)) {
			sb.append("package "
					+ PropertiesTool.DAOPACKAGENAME.replaceAll(";", "") + ";");
			sb.append("\n");
			sb.append("\n");
		}

		sb.append("import javax.annotation.Resource;\n\n");
		sb.append("import org.springframework.stereotype.Repository;\n");
		sb.append("import " + PropertiesTool.MAPPERPACKAGENAME.replace(";", "")+"."
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Mapper;\n");
		sb.append("import " + PropertiesTool.DOMAINPACKAGENAME.replace(";","")+"."
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Domain;\n");
		sb.append("\n");
		sb.append("@Repository(\""+MappingRule.forPropertyName(CommonFunction.domainName)+"Dao\")\n");
		sb.append("public class "
				+ MappingRule.forClassName(CommonFunction.domainName) + "Dao {");
		sb.append("\n");
		sb.append("\n");
		sb.append("	private "
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Mapper mapper;");
		sb.append("\n");
		sb.append("\n");
		sb.append("	@Resource");
		sb.append("\n");
		sb.append("	public void setMapper("
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Mapper mapper) {");
		sb.append("\n");
		sb.append("		this.mapper = mapper;");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("	public void create"
				+ MappingRule.forClassName(CommonFunction.domainName) + "("
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Domain domain) {");
		sb.append("\n");
		sb.append("		mapper.add"
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "(domain);");
		sb.append("\n");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("}");

		System.out.println(sb.toString());
		MappingRule.makeFile(
				MappingRule.daoPath + "\\"
						+ MappingRule.forClassName(CommonFunction.domainName)
						+ "Dao.java", sb.toString());

	}
}
