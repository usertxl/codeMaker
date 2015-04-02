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
public class MakeMapper {

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

		sb.append("import java.util.List;");
		sb.append("\n");
		sb.append("\n");
		sb.append("import "
				+ PropertiesTool.DOMAINPACKAGENAME.replaceAll(";", "") + "."
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ PropertiesTool.BEANSUFFIXNAME+";");
		sb.append("\n");
		sb.append("\n");
		sb.append("public interface "
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ "Mapper {");
		sb.append("\n");
		sb.append("\n");
		sb.append("    public int insert ("
				+ MappingRule.forClassName(CommonFunction.domainName)
				+ PropertiesTool.BEANSUFFIXNAME+" "+PropertiesTool.BEANSUFFIXNAME+");");
		sb.append("\n");
		sb.append("    public int queryCount("+  MappingRule.forClassName(CommonFunction.domainName)
				+PropertiesTool.BEANSUFFIXNAME+" "+PropertiesTool.BEANSUFFIXNAME+");");
		sb.append("\n");
		sb.append("    public List<"+MappingRule.forClassName(CommonFunction.domainName)
				+ PropertiesTool.BEANSUFFIXNAME+"> queryListData("+  MappingRule.forClassName(CommonFunction.domainName)
				+PropertiesTool.BEANSUFFIXNAME+" "+PropertiesTool.BEANSUFFIXNAME+");");
		sb.append("\n");
		sb.append("    public "+MappingRule.forClassName(CommonFunction.domainName)
				+ PropertiesTool.BEANSUFFIXNAME+" querySingleData("+  MappingRule.forClassName(CommonFunction.domainName)
				+PropertiesTool.BEANSUFFIXNAME+" "+PropertiesTool.BEANSUFFIXNAME+");");
		sb.append("\n");
		sb.append("\n");
		sb.append("}");
		sb.append("\n");

		System.out.println(sb.toString());
		MappingRule.makeFile(
				MappingRule.mapperPath + "\\"
						+ MappingRule.forClassName(CommonFunction.domainName)
						+ "Mapper.java", sb.toString());

	}
}
