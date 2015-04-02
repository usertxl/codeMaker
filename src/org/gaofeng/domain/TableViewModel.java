package org.gaofeng.domain;

public class TableViewModel {

	private String field;
	private String type;
	private String scale;
	private String common;
	private String javaType;
	private String myBatisType;

	/**
	 * 字段名
	 * @return
	 */
	public String getField() {
		return field;
	}

	/**
	 * 字段名
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 字段类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 字段类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 小数点位数
	 * @return
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * 小数点位数
	 * @param scale
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * 备注
	 * @return
	 */
	public String getCommon() {
		return common;
	}

	/**
	 * 备注
	 * @param common
	 */
	public void setCommon(String common) {
		this.common = common;
	}

	/**
	 * 对应java的类型
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}

	/**
	 * 对应java的类型
	 * @param javaType the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * 对应mybatis的类型
	 * @return the myBatisType
	 */
	public String getMyBatisType() {
		return myBatisType;
	}

	/**
	 * 对应mybatis的类型
	 * @param myBatisType the myBatisType to set
	 */
	public void setMyBatisType(String myBatisType) {
		this.myBatisType = myBatisType;
	}
}
