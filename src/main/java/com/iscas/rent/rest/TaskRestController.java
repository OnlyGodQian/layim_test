/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.iscas.rent.rest;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import com.iscas.rent.entity.ImMsg;
import com.iscas.rent.entity.Task;
import com.iscas.rent.service.account.ShiroDbRealm.ShiroUser;
import com.iscas.rent.service.task.ImMsgService;
import com.iscas.rent.service.task.TaskService;

/**
 * Task的Restful API的Controller.
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/api/v1/task")
public class TaskRestController {

	private static Logger logger = LoggerFactory.getLogger(TaskRestController.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private ImMsgService imMsgService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Task> list(Model model) {
		return taskService.getAllTask();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Task get(@PathVariable("id") Long id) {
		Task task = taskService.getTask(id);
		if (task == null) {
			String message = "任务不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return task;
	}
	
	@RequestMapping(value = "data", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public String send(@RequestParam(value = "id") Long toid,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "sign_key", defaultValue = "1") int sign_key,
			@RequestParam(value = "time") Long timestamp, Model model) throws JSONException {
		
		JSONObject data = new JSONObject();
		Date time = new Date(timestamp);
		data.put("id",toid);
		data.put("conent",content);
		data.put("sign_key",sign_key);
		data.put("time",time);
		ImMsg imMsg= new ImMsg();
		imMsg.setFromid(getCurrentUserId());
		imMsg.setToid(toid);
		imMsg.setBody(content);
		imMsg.setTime(time);
		imMsg.setStatus((short)0);
		imMsgService.saveImMsg(imMsg);
		return data.toString();
	}
	
	@RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ArrayList<ImMsg> refatch(@RequestParam(value = "fromid") Long fromid, Model model) throws JSONException {
		ArrayList<ImMsg> msgList = (ArrayList<ImMsg>) imMsgService.getByBoth(fromid, getCurrentUserId());
		return msgList;
	}

	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody Task task, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, task);

		// 保存任务
		taskService.saveTask(task);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		Long id = task.getId();
		URI uri = uriBuilder.path("/api/v1/task/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Task task) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, task);

		// 保存任务
		taskService.saveTask(task);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
	}
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
