package com.velocity.template.enums;

import java.util.HashMap;
import java.util.Map;

public enum AttributeTypeEnum {

	STRING("String", "VARCHAR"), DATE("Date", "TIMESTAMP");

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

	private static Map<String, String> types = new HashMap<String, String>();
	static {
		AttributeTypeEnum[] enums = AttributeTypeEnum.values();
		for (AttributeTypeEnum attributeTypeEnum : enums) {
			types.put(attributeTypeEnum.getText(),
					attributeTypeEnum.getJdbcType());
		}
	}

	public static String getJdbcType(String text) {
		return types.get(text);
	}
}
