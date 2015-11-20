package com.velocity.template.dao;

import java.util.List;

import com.velocity.template.entity.Attribute;
import com.velocity.template.param.AttributeQueryParam;

public interface IAttributeDao {
	
	int save(Attribute param);
	int update(Attribute param);
	int deleteById(String id);
	Attribute getDataById(String id);
	
	int count(AttributeQueryParam param);
	List<Attribute> query(AttributeQueryParam param, int start, int end);
	int saveBatch(List<Attribute> param);
}
