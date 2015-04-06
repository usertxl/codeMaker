package org.gaofeng.domain;

import java.util.List;

public class GroupTableViewModel {

	private List<TableViewModel> listKey;
	private List<TableViewModel> listUnKey;
	/**
	 * @return 主键字段列表
	 */
	public List<TableViewModel> getListKey() {
		return listKey;
	}
	/**
	 * @param 主键字段列表
	 */
	public void setListKey(List<TableViewModel> listKey) {
		this.listKey = listKey;
	}
	/**
	 * @return 非主键字段列表
	 */
	public List<TableViewModel> getListUnKey() {
		return listUnKey;
	}
	/**
	 * @param 非主键字段列表
	 */
	public void setListUnKey(List<TableViewModel> listUnKey) {
		this.listUnKey = listUnKey;
	}


}
