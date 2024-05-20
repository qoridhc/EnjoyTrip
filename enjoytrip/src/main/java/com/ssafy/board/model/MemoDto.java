package com.ssafy.board.model;

public class MemoDto {
	private int memoNo;
	private String comment;
	private String memoTime;
	private int articleNo;
	private String userId;
	public int getMemoNo() {
		return memoNo;
	}
	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMemoTime() {
		return memoTime;
	}
	public void setMemoTime(String memoTime) {
		this.memoTime = memoTime;
	}
	public int getArticleNo() {
		return articleNo;
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
	@Override
	public String toString() {
		return "MemoDto [memoNo=" + memoNo + ", comment=" + comment + ", memoTime=" + memoTime + ", articleNo="
				+ articleNo + ", userId=" + userId + "]";
	}	
}
