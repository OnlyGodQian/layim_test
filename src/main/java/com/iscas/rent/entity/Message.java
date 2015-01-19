package com.iscas.rent.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * @author xpx
 * @class 消息类
 * @version 2014-12-5
 */
@Entity
@Table(name = "message")
public class Message extends IdEntity {
	private Account from;
	private Account to;
	private String body;
	private short status;
	private short type;
	private Date time;
	
	
	public Message() {
		super();
	}

	public Message(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "from")
	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}


	@ManyToOne
	@JoinColumn(name = "to")
	public Account getTo() {
		return to;
	}


	public void setTo(Account to) {
		this.to = to;
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


	public short getType() {
		return type;
	}


	public void setType(short type) {
		this.type = type;
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
