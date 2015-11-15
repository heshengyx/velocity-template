package com.velocity.template.service;

import com.velocity.template.data.ClazzAttribute;
import com.velocity.template.entity.Clazz;
import com.velocity.template.page.IPage;
import com.velocity.template.param.ClazzQueryParam;

public interface IClazzService {

	void save(Clazz param);
	void update(Clazz param);
	void deleteById(String id);
	Clazz getDataById(String id);
	
	void save(ClazzAttribute param);
	IPage<Clazz> query(ClazzQueryParam param, int page, int rows);
}
