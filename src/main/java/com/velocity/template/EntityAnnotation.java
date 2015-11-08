package com.velocity.template;

public class EntityAnnotation {

	private String name;
	private String comment;
	private String column;
	private String jdbcType;
	private String width;
	private String title;
	private boolean condition;
	
	private String type;
	private String pattern;
	private boolean required;

	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isCondition() {
		return condition;
	}
	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
}