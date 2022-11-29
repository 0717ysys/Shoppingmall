package com.kubg.domain;

import java.util.Date;

public class ReplyVO {
	
	private int gdsNum;
	private String userId;
	private int repNum;
	private String repCon;
	private Date repCate;
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRepNum() {
		return repNum;
	}
	public void setRepNum(int repNum) {
		this.repNum = repNum;
	}
	public String getRepCon() {
		return repCon;
	}
	public void setRepCon(String repCon) {
		this.repCon = repCon;
	}
	public Date getRepCate() {
		return repCate;
	}
	public void setRepCate(Date repCate) {
		this.repCate = repCate;
	}

}
