package com.velocity.template.enums;

public enum AttributeTypeEnum {

	STRING("String", "VARCHAR"),
	DATE("Date", "TIMESTAMP");
	
	private String text;
	private String jdbcType;
	
	private AttributeTypeEnum(String text, String jdbcType) {
		this.text = text;
		this.jdbcType = jdbcType;
	}
	
	public String getJdbcType() {
		return jdbcType;
	}
	public String getText() {
		return text;
	}

}
