package com.example.abbs.entity;

public class Schedule {
	private int sid;
	private String uid;
	private String sadte;
	private String title;
	private String place;
	private String startTime;
	private String endTime;
	private int isImportant;
	private String memo;
	public Schedule() { }
	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", uid=" + uid + ", sadte=" + sadte + ", title=" + title + ", place=" + place
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", isImportant=" + isImportant + ", memo="
				+ memo + "]";
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSadte() {
		return sadte;
	}
	public void setSadte(String sadte) {
		this.sadte = sadte;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getIsImportant() {
		return isImportant;
	}
	public void setIsImportant(int isImportant) {
		this.isImportant = isImportant;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
