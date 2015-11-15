package com.velocity.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.velocity.template.entity.Clazz;
import com.velocity.template.param.ClazzQueryParam;

public interface IClazzMapper {
	
	int save(@Param("param") Clazz param);
	int update(@Param("param") Clazz param);
	int deleteById(@Param("id") String id);
	Clazz getDataById(@Param("id") String id);
	
	int count(@Param("param") ClazzQueryParam param);
	List<Clazz> query(@Param("param") ClazzQueryParam param,
			@Param("start") int start, @Param("end") int end);
}
