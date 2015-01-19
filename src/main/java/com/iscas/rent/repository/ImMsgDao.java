/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.iscas.rent.entity.ImMsg;

public interface ImMsgDao extends PagingAndSortingRepository<ImMsg, Long>, JpaSpecificationExecutor<ImMsg> {

	@Query("select imMsg from ImMsg imMsg where imMsg.fromid=?1 and imMsg.toid=?2 and imMsg.status=0")
	List<ImMsg> findByBoth(Long fromid, Long toid);
}
