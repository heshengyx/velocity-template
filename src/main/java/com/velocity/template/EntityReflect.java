package com.velocity.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.velocity.template.annotation.Column;
import com.velocity.template.annotation.Comment;
import com.velocity.template.annotation.Element;
import com.velocity.template.annotation.Table;
import com.velocity.template.annotation.Thead;

public class EntityReflect {

	private Class<?> clazz = null;
	
	public EntityReflect(String clazzName) {
		try {
			clazz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getTableName() {
		Table table = clazz.getAnnotation(Table.class);
		return table.value();
	}
	
	public String getTitleName() {
		Table table = clazz.getAnnotation(Table.class);
		return table.title();
	}
	
	public List<EntityAnnotation> getFields() {
		EntityAnnotation entityAnnotation = null;
		List<EntityAnnotation> fields = new ArrayList<EntityAnnotation>();
		
		for (Field field : clazz.getDeclaredFields()) {
			String name = field.getName();
			if (!"id".equals(name)) {
				entityAnnotation = new EntityAnnotation();
				entityAnnotation.setName(name);
				
				Comment comment = field.getAnnotation(Comment.class);
				if (comment != null) {
					entityAnnotation.setComment(comment.value());
				}
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					entityAnnotation.setColumn(column.value());
				} else {
					entityAnnotation.setColumn(name.toUpperCase());
				}
				Thead thead = field.getAnnotation(Thead.class);
				if (thead != null) {
					entityAnnotation.setWidth(thead.width());
					entityAnnotation.setCondition(thead.condition());
				}
				Element element = field.getAnnotation(Element.class);
				if (element != null) {
					entityAnnotation.setType(element.type());
					entityAnnotation.setPattern(element.pattern());
					entityAnnotation.setRequired(element.required());
				}
				
				Class<?> type = field.getType();
				if ("java.util.Date".equals(type.getName())) {
					entityAnnotation.setJdbcType("TIMESTAMP");
				} else if ("java.lang.Long".equals(type.getName())) {
					entityAnnotation.setJdbcType("NUMERIC");
				} else if ("java.lang.String".equals(type.getName())) {
					entityAnnotation.setJdbcType("VARCHAR");
				}
				fields.add(entityAnnotation);
			}
		}
		return fields;
	}
	
	public String getMethodName(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
	
	public String setMethodName(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
