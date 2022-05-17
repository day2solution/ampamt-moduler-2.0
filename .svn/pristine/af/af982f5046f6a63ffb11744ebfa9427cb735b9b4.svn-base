package com.ampamt.moduler.bean.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ampamt_events")
@JsonInclude(Include.NON_NULL)
public class AmpamtEventsBean {

	@Id
	@Column(name="EVENT_ID")
	private String eventId;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="EVENT_IMG")
	private String eventImg;
	
	@Column(name="EVENT_TITLE")
	private String eventTitle;
	
	@Column(name="EVENT_DESCRIPTION")
	private String eventDescription;
	
	@Column(name="EVENT_STATE")
	private String eventState;
	
	@Column(name="EVENT_CITY")
	private String eventCity;
	
	@Column(name="ACTIVE_FLAG")
	private String activeFlag;
	
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="CREATE_DATE")
	private Date createDate;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEventImg() {
		return eventImg;
	}

	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
