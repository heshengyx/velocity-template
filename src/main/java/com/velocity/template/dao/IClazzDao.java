package com.velocity.template.dao;

import java.util.List;

import com.velocity.template.entity.Clazz;
import com.velocity.template.param.ClazzQueryParam;

public interface IClazzDao {
	
	int save(Clazz param);
	int update(Clazz param);
	int deleteById(String id);
	Clazz getDataById(String id);
	
	int count(ClazzQueryParam param);
	List<Clazz> query(ClazzQueryParam param, int start, int end);
}
