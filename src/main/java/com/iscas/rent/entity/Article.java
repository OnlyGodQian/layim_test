package com.iscas.rent.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author xpx
 * @class 文章类
 * @version 2014-12-5
 */
@Entity
@Table(name="article")
public class Article extends IdEntity {
	private String title;
	private String body;
	private String image1;
	private String image2;
	private String image3;
	private short status;
	private Date time;
	private Account deployer;
	private int bidder;
	private Date within;
	private int comments;
	private int click;
	public Article() {
		super();
	}
	
	public Article(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
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

	@ManyToOne
	@JoinColumn(name = "deployer")
	public Account getDeployer() {
		return deployer;
	}

	public void setDeployer(Account deployer) {
		this.deployer = deployer;
	}

	public int getBidder() {
		return bidder;
	}

	public void setBidder(int bidder) {
		this.bidder = bidder;
	}

	public Date getWithin() {
		return within;
	}

	public void setWithin(Date within) {
		this.within = within;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
