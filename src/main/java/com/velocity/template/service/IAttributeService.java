package com.velocity.template.service;

import com.velocity.template.entity.Attribute;
import com.velocity.template.page.IPage;
import com.velocity.template.param.AttributeQueryParam;

public interface IAttributeService {

	void save(Attribute param);
	void update(Attribute param);
	void deleteById(String id);
	Attribute getDataById(String id);
	
	IPage<Attribute> query(AttributeQueryParam param, int page, int rows);
}
