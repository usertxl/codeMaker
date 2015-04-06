package org.gaofeng.domain;

public class TableViewModel {

	private String fieldName;
	private String type;
	private String scale;
	private String comment;
	private String javaType;
	private String myBatisType;
	private String propertyName;
	private String className;
	private Boolean isPrimaryKey;

	/**
	 * @return 字段名
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param 字段名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return 字段类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param 字段类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return 小数点位数
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @param 小数点位数
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * @return 数据库字段备注
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param 数据库字段备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return 对应java的类型
	 */
	public String getJavaType() {
		return javaType;
	}

	/**
	 * @param 对应java的类型
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * @return 对应mybatis的类型
	 */
	public String getMyBatisType() {
		return myBatisType;
	}

	/**
	 * @param 对应mybatis的类型
	 */
	public void setMyBatisType(String myBatisType) {
		this.myBatisType = myBatisType;
	}

	/**
	 * @return 驼峰式首字母小写
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param 驼峰式首字母小写
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return 驼峰式首字母大写
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param  驼峰式首字母大写
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return 是否是主键
	 */
	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	/**
	 * @param 是否是主键
	 */
	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
}
