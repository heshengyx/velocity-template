package com.velocity.template.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.velocity.template.dao.BaseDao;
import com.velocity.template.dao.IAttributeDao;
import com.velocity.template.entity.Attribute;
import com.velocity.template.mapper.IAttributeMapper;
import com.velocity.template.param.AttributeQueryParam;

@Repository
public class AttributeDaoImpl extends BaseDao<IAttributeMapper> implements IAttributeDao {
	
	@Override
	public int save(Attribute param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(Attribute param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public Attribute getDataById(String id) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(AttributeQueryParam param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<Attribute> query(AttributeQueryParam param, int start, int end) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.query(param, start, end);
	}

	@Override
	public int saveBatch(List<Attribute> param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.saveBatch(param);
	}
}
