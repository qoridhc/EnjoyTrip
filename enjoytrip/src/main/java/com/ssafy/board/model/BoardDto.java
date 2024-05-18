package com.ssafy.board.model;

import java.util.Map;

public class BoardDto {

	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private String registerTime;
	private String hit;
	
	public int getArticleNo() {
		return articleNo;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	public void setArticle(Map<String, String> params) {
		this.setUserId(params.get("id"));
		this.setSubject(params.get("subject"));
		this.setContent(params.get("content"));
	}

	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleNo + ", userId=" + userId + ", subject=" + subject + ", content="
				+ content + ", registerTime=" + registerTime + ", hit=" + hit + "]";
	}
}
