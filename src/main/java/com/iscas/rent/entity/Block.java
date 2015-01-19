package com.iscas.rent.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * @author xpx
 * @class 版块类
 * @version 2014-12-5
 */
@Entity
@Table(name = "block")
public class Block extends IdEntity {
	private String name;
	private short status;
	
	
	public Block() {
		super();
	}

	public Block(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
