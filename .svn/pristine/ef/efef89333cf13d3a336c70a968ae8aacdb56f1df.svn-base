package com.ampamt.moduler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ampamt_therapies")
@JsonInclude(Include.NON_NULL)
public class AmpamtTherapiesBean {

	@Id
	@Column(name="THERAPY_ID")
	private String therapyId;
	
	@Column(name="THERAPY_NAME")
	private String therapyName;
	
	@Column(name="ACTIVE_FLAG")
	private String activeFlag;

	public String getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(String therapyId) {
		this.therapyId = therapyId;
	}

	public String getTherapyName() {
		return therapyName;
	}

	public void setTherapyName(String therapyName) {
		this.therapyName = therapyName;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
}
