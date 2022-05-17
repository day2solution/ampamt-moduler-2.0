package com.ampamt.moduler.bean.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="tbl_state")
@JsonInclude(Include.NON_NULL)
public class TblStateBean {

	@Id
	@Column(name="STATE_CODE")
	private String stateCode;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="STATE_COUNTRY")
	private String stateCountry;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCountry() {
		return stateCountry;
	}

	public void setStateCountry(String stateCountry) {
		this.stateCountry = stateCountry;
	}
	
	
}
