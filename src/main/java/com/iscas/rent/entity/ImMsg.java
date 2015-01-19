package com.iscas.rent.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * @author xpx
 * @class 消息类
 * @version 2014-12-5
 */
@Entity
@Table(name = "im_msg")
public class ImMsg extends IdEntity {
	private Long fromid;
	private Long toid;
	private String body;
	private short status;
	private Date time;
	
	
	public ImMsg() {
		super();
	}

	public ImMsg(Long id) {
		this.id = id;
	}

	public Long getFromid() {
		return fromid;
	}

	public void setFromid(Long fromid) {
		this.fromid = fromid;
	}


	public Long getToid() {
		return toid;
	}


	public void setToid(Long toid) {
		this.toid = toid;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public short getStatus() {
		return status;
	}


	public void setStatus(short status) {
		this.status = status;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
