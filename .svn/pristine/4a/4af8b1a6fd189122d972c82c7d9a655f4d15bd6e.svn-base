package com.ampamt.moduler.bean.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="tbl_city")
@JsonInclude(Include.NON_NULL)
public class TblCityBean {

	@Id
	@Column(name="CITY_CODE")
	private String cityCode;
	
	@Column(name="STATE_CODE")
	private String stateCode;
	
	@Column(name="CITY_NAME")
	private String cityName;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
