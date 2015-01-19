package com.iscas.rent.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author xpx
 * @class 评论
 * @version 2014-12-5
 */
@Entity
@Table(name = "comment")
public class Comment extends IdEntity {
	private int article;
	private Account from;
	private Account to;
	private String body;
	private Date time;
	
	
	
	public Comment() {
		super();
	}

	public Comment(Long id) {
		this.id = id;
	}


	public int getArticle() {
		return article;
	}



	public void setArticle(int article) {
		this.article = article;
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
