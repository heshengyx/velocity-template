package com.velocity.template.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.velocity.template.dao.BaseDao;
import com.velocity.template.dao.IClazzDao;
import com.velocity.template.entity.Clazz;
import com.velocity.template.mapper.IClazzMapper;
import com.velocity.template.param.ClazzQueryParam;

@Repository
public class ClazzDaoImpl extends BaseDao<IClazzMapper> implements IClazzDao {
	
	@Override
	public int save(Clazz param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(Clazz param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public Clazz getDataById(String id) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(ClazzQueryParam param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<Clazz> query(ClazzQueryParam param, int start, int end) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.query(param, start, end);
	}
}
