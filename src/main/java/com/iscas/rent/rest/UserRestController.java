/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.iscas.rent.entity.Account;
import com.iscas.rent.service.account.AccountService;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

/**
 * User的Restful API的Controller.
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserRestController {

	private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private AccountService userService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public String list() throws JSONException {
		JSONObject object = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject online = new JSONObject();
		JSONArray friend = new JSONArray();
		for (Account user :userService.getAllUser()) {
			JSONObject person = new JSONObject();
			person.put("id", user.getId());
			person.put("name", user.getUsername());
			person.put("face", user.getImage());
			
			friend.put(person);
		}
		online.put("nums", 36);
		online.put("id", 1);
		online.put("item",friend);
		online.put("name","在线好友");
		
		JSONObject online1 = new JSONObject();
		online1.put("nums", 32);
		online1.put("id", 2);
		online1.put("item",friend);
		online1.put("name","同窗");
		
		data.put(online);
		data.put(online1);
		
		object.put("status", 1);
		object.put("msg", "ok");
		object.put("data", data);
		return object.toString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Account getnaem(@PathVariable("id") Long id) {
		Account user = userService.getUser(id);
		if (user == null) {
			String message = "用户不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return user;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Account get(@PathVariable("id") Long id) {
		Account user = userService.getUser(id);
		if (user == null) {
			String message = "用户不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return user;
	}

}
