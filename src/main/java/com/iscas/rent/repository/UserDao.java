/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.iscas.rent.entity.Account;

public interface UserDao extends PagingAndSortingRepository<Account, Long> {
	Account findByUsername(String username);
}
