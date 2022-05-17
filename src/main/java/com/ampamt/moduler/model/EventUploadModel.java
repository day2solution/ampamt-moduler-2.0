package com.ampamt.moduler.model;

import java.util.Date;

public class EventUploadModel {

	private String eventId;
	private String eventImgBase64;
	private String eventImgName;
	private String accountId;
	private String eventTitle;
	private String eventDescription;
	private String eventState;
	private String eventCity;
	private String activeFlag;
	private Date eventDate;
	
	public String getEventImgBase64() {
		return eventImgBase64;
	}
	public void setEventImgBase64(String eventImgBase64) {
		this.eventImgBase64 = eventImgBase64;
	}
	public String getEventImgName() {
		return eventImgName;
	}
	public void setEventImgName(String eventImgName) {
		this.eventImgName = eventImgName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getEventCity() {
		return eventCity;
	}
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
}
