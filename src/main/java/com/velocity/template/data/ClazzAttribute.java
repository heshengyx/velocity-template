package com.velocity.template.data;

import java.io.Serializable;

public class ClazzAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2914024918461261967L;
	private String clazzName;
	private String tableName;
	private String title;//
	private String[] attributeName;
	private String[] attributeTitle;
	private String[] attributeType;
	private String[] attributeSearch;
	private String[] attributeColumn;
	private String[] attributeEdit;
	
	public String[] getAttributeEdit() {
		return attributeEdit;
	}
	public void setAttributeEdit(String[] attributeEdit) {
		this.attributeEdit = attributeEdit;
	}
	public String[] getAttributeColumn() {
		return attributeColumn;
	}
	public void setAttributeColumn(String[] attributeColumn) {
		this.attributeColumn = attributeColumn;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getAttributeSearch() {
		return attributeSearch;
	}
	public void setAttributeSearch(String[] attributeSearch) {
		this.attributeSearch = attributeSearch;
	}
	public String[] getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String[] attributeName) {
		this.attributeName = attributeName;
	}
	public String[] getAttributeTitle() {
		return attributeTitle;
	}
	public void setAttributeTitle(String[] attributeTitle) {
		this.attributeTitle = attributeTitle;
	}
	public String[] getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String[] attributeType) {
		this.attributeType = attributeType;
	}
}
