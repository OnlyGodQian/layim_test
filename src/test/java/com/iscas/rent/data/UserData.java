/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.data;

import com.iscas.rent.entity.Account;
import org.springside.modules.test.data.RandomData;

public class UserData {

	public static Account randomNewUser() {
		Account user = new Account();
		user.setUsername(RandomData.randomName("user"));
		user.setMail(RandomData.randomName("mail"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}
}
