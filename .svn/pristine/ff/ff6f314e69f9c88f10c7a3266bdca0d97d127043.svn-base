package com.ampamt.moduler.bean.advertisement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="advertisements")
@JsonInclude(Include.NON_NULL)
public class AdvertisementsBean {

	@Id
	@Column(name="ADV_ID")
	private String advId;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="DISPLAY_FLAG")
	private String displayFlag;
	
	@Column(name="IMAGE_NAME")
	private String imageName;

	@Column(name="EXTRAS")
	private String extras;
	

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	
	
}
