package com.xlinyu.web.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysMenu implements Serializable{

	private int id;
	
	private String name;
	
	private int menupid;
	
	private String description;
	
	private String pageurl;
	
	private short type;
	
	private short status;
	
	private Timestamp createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenupid() {
		return menupid;
	}

	public void setMenupid(int menupid) {
		this.menupid = menupid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
