/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.service.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.iscas.rent.entity.ImMsg;
import com.iscas.rent.repository.ImMsgDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ImMsgService {

	private ImMsgDao imMsgDao;

	public ImMsg getImMsg(Long id) {
		return imMsgDao.findOne(id);
	}

	public void saveImMsg(ImMsg entity) {
		imMsgDao.save(entity);
	}

	public void deleteImMsg(Long id) {
		imMsgDao.delete(id);
	}

	public List<ImMsg> getAllImMsg() {
		return (List<ImMsg>) imMsgDao.findAll();
	}
	
	public List<ImMsg> getByBoth(Long fromid ,Long toid) {
		return (List<ImMsg>) imMsgDao.findByBoth(fromid, toid);
	}
	public Page<ImMsg> getUserImMsg(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ImMsg> spec = buildSpecification(userId, searchParams);

		return imMsgDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ImMsg> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ImMsg> spec = DynamicSpecifications.bySearchFilter(filters.values(), ImMsg.class);
		return spec;
	}

	@Autowired
	public void setImMsgDao(ImMsgDao imMsgDao) {
		this.imMsgDao = imMsgDao;
	}
}
