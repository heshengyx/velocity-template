package com.velocity.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.velocity.template.entity.Attribute;
import com.velocity.template.param.AttributeQueryParam;

public interface IAttributeMapper {
	
	int save(@Param("param") Attribute param);
	int update(@Param("param") Attribute param);
	int deleteById(@Param("id") String id);
	Attribute getDataById(@Param("id") String id);
	
	int count(@Param("param") AttributeQueryParam param);
	List<Attribute> query(@Param("param") AttributeQueryParam param,
			@Param("start") int start, @Param("end") int end);
}
